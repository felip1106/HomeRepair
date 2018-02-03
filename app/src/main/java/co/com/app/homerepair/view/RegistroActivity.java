package co.com.app.homerepair.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.com.app.homerepair.R;
import co.com.app.homerepair.controller.AppController;
import co.com.app.homerepair.dao.DaoSession;
import co.com.app.homerepair.model.Usuarios;
import co.com.app.homerepair.presenter.IRegistroPresenter;
import co.com.app.homerepair.view.fragment.RegistroClienteFragment;
import co.com.app.homerepair.view.fragment.RegistroProveedorFragment;
import co.com.app.homerepair.view.fragment.RegistroUsuarioFragment;

public class RegistroActivity extends AppCompatActivity implements IRegistroView, RegistroClienteFragment.OnFragmentInteractionListener, RegistroUsuarioFragment.OnFragmentInteractionListener, RegistroProveedorFragment.OnFragmentInteractionListener {

    @BindView(R.id.fab_save)
    FloatingActionButton _saveFab;

    @BindView(R.id.fragment_registro_usuario)
    FrameLayout _registroUsuarioFragment;

    private IRegistroPresenter registroPresenter;
    private FragmentTransaction ft;

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_registro);
        ButterKnife.bind(this);

        DaoSession daoSession = ((AppController)getApplication()).getDaoSession();

        // Carga el fragment para la seleccion del tipo de usuario
        if (savedInstanceState == null) {
            final Fragment fragmentUser = new RegistroUsuarioFragment();
            ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.fragment_registro_usuario, fragmentUser);
            ft.commit();

            getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                @Override
                public void onBackStackChanged() {
                    if (fragmentUser.isVisible()) {
                        _saveFab.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }

        _saveFab.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        // TODO Not implemented yet
    }

    @Override
    public void showFragmentTipoUsuario(Fragment fragment) {
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(_registroUsuarioFragment.getId(), fragment);
        ft.addToBackStack(fragment.toString());
        ft.commit();

        _saveFab.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.fab_save)
    void saveRegistroUsuario(View view) {
        // TODO Guardar registro. Not implemented yet;
        registroPresenter.saveRegistroUsuario(new Usuarios());
    }
}
