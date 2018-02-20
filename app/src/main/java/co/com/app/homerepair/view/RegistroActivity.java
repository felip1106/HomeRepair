package co.com.app.homerepair.view;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.com.app.homerepair.R;
import co.com.app.homerepair.model.Clientes;
import co.com.app.homerepair.model.Proveedor;
import co.com.app.homerepair.model.Usuarios;
import co.com.app.homerepair.presenter.IRegistroPresenter;
import co.com.app.homerepair.utils.AESCipher;
import co.com.app.homerepair.view.fragment.RegistroClienteFragment;
import co.com.app.homerepair.view.fragment.RegistroProveedorFragment;
import co.com.app.homerepair.view.fragment.RegistroUsuarioFragment;
import dagger.android.AndroidInjection;

public class RegistroActivity extends AppCompatActivity implements IRegistroView, RegistroClienteFragment.OnFragmentInteractionListener, RegistroUsuarioFragment.OnFragmentInteractionListener, RegistroProveedorFragment.OnFragmentInteractionListener {

    @Inject
    IRegistroPresenter registroPresenter;

    @Inject
    Usuarios usuarios;

    @Inject
    Clientes clientes;

    @Inject
    Proveedor proveedor;

    @Inject
    Application application;

    @BindView(R.id.fab_save)
    FloatingActionButton _saveFab;

    @BindView(R.id.label_registro)
    TextView _tipoUsuarioText;

    @BindView(R.id.fragment_registro_usuario)
    FrameLayout _registroUsuarioFragment;

    private FragmentTransaction ft;
    private Fragment fragment;

    @SuppressLint("RestrictedApi")
    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_registro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        // Carga el fragment para la seleccion del tipo de usuario
        final Fragment fragmentUser = new RegistroUsuarioFragment();
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment_registro_usuario, fragmentUser);
        ft.commit();

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (fragmentUser.isVisible() && fragmentUser != null) {
                    _saveFab.setVisibility(View.INVISIBLE);
                    _tipoUsuarioText.setText(R.string.label_tipo_registro);
                } else {
                    _saveFab.setVisibility(View.VISIBLE);
                    _tipoUsuarioText.setText(R.string.text_registro);
                }
            }
        });

        if (fragmentUser.isMenuVisible()) {
            _saveFab.setVisibility(View.INVISIBLE);
            _tipoUsuarioText.setText(R.string.label_tipo_registro);
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        // TODO Not implemented yet
    }

    @Override
    public void saveUsuarioInteraction(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public void saveProveedorInteraction(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public void saveClienteInteraction(Clientes clientes) {
        this.clientes = clientes;
    }

    @Override
    public boolean validateRegistroUsuario(boolean validate) {
        return validate;
    }

    @Override
    public void showFragmentTipoUsuario(Fragment fragment) {
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(_registroUsuarioFragment.getId(), fragment);
        ft.addToBackStack(fragment.toString());
        ft.commit();

        _saveFab.setVisibility(View.VISIBLE);

        this.fragment = fragment;
    }

    @Override
    public void onRegistroSuccess() {
        setResult(RESULT_OK, null);
        finish();
        Toast.makeText(this, R.string.MSG_REGISTRO_USUARIO_EXITO, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegistroFailed(int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean findUsuarioByNombre(String nombre) {
        Usuarios usuario = registroPresenter.findUsuarioByNombre(nombre, application.getApplicationContext());

        return usuario != null;
    }

    @Override
    public String encriptarPassword(String password) {
        String encrypted = null;

        try {
            AESCipher cipher = new AESCipher();
            encrypted = cipher.encrypt(password);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return encrypted;
    }

    @OnClick(R.id.fab_save)
    void onSaveUsuario(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.MSG_REGISTRO_PROGRESS_DIALOG));
        progressDialog.show();

        if (fragment instanceof RegistroClienteFragment) {
            if (!((RegistroClienteFragment) fragment).validateRegistro()) {
                onRegistroFailed(R.string.MSG_REGISTRO_FALLIDO);
                progressDialog.dismiss();
                return;
            }

            ((RegistroClienteFragment) fragment).saveUsuario();
            if (!findUsuarioByNombre(usuarios.getUsu_nombre())) {
                usuarios.setUsu_pass(encriptarPassword(usuarios.getUsu_pass()));
                registroPresenter.saveUsuario(usuarios, application.getApplicationContext());
            } else {
                onRegistroFailed(R.string.MSG_CONSTRAINT_UNIQUE_USUARIO);
                progressDialog.dismiss();
                return;
            }

            ((RegistroClienteFragment) fragment).saveCliente();
            registroPresenter.saveCliente(clientes, application.getApplicationContext());

        } else if (fragment instanceof RegistroProveedorFragment) {
            if (!((RegistroProveedorFragment) fragment).validateRegistro()) {
                onRegistroFailed(R.string.MSG_REGISTRO_FALLIDO);
                progressDialog.dismiss();
                return;
            }
            ((RegistroProveedorFragment) fragment).saveUsuario();
            if (!findUsuarioByNombre(usuarios.getUsu_nombre())) {
                usuarios.setUsu_pass(encriptarPassword(usuarios.getUsu_pass()));
                registroPresenter.saveUsuario(usuarios, application.getApplicationContext());
            } else {
                onRegistroFailed(R.string.MSG_CONSTRAINT_UNIQUE_USUARIO);
                progressDialog.dismiss();
                return;
            }

            ((RegistroProveedorFragment) fragment).saveProveedor();
            registroPresenter.saveRegistroProveedor(proveedor, application.getApplicationContext());
        }

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        onRegistroSuccess();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

}
