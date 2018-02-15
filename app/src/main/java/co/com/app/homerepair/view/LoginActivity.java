package co.com.app.homerepair.view;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.com.app.homerepair.R;
import co.com.app.homerepair.model.Usuarios;
import co.com.app.homerepair.presenter.ILoginPresenter;
import co.com.app.homerepair.utils.AESCipher;
import dagger.android.AndroidInjection;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    @Inject
    Application application;

    @Inject
    Usuarios usuarios;

    @Inject
    ILoginPresenter loginPresenter;

    @BindView(R.id.input_usuario)
    AutoCompleteTextView _usuarioText;

    @BindView(R.id.input_contrasegna)
    EditText _contrasegnaText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    public void startRegistroActivity() {
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }

    @Override
    public void startPrincipalActivity() {
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
    }

    @Override
    public void onLoginSuccess() {
        startPrincipalActivity();
        finish();
    }

    @Override
    public void onLoginFailed() {
        Toast.makeText(this, R.string.MSG_LOGIN_FALLIDO, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean validateLogin() {
        boolean validate = true;

        if (_usuarioText.getText().toString().isEmpty()) {
            _usuarioText.setError(getString(R.string.MSG_LOGIN_USUARIO_VACIO));
            validate = false;
        } else {
            _usuarioText.setError(null);
        }

        if (_contrasegnaText.getText().toString().isEmpty()) {
            _contrasegnaText.setError(getString(R.string.MSG_LOGIN_PASSWORD_VACIO));
            validate = false;
        } else {
            _contrasegnaText.setError(null);
        }

        return validate;
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

    @OnClick(R.id.link_registro)
    void onRegistroUsuario(View view) {
        startRegistroActivity();
    }

    @OnClick(R.id.button_ingresar)
    void onLoginUsuario(View view) {
        if (!validateLogin()) {
            onLoginFailed();
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.MSG_LOGIN_PROGRESS_DIALOG));
        progressDialog.show();

        usuarios = loginPresenter.findUsuarioByNombrePassword(_usuarioText.getText().toString(), encriptarPassword(_contrasegnaText.getText().toString()), application.getApplicationContext());

        if (usuarios == null) {
            Toast.makeText(this, R.string.MSG_LOGIN_CREDENCIALES_NO_VALIDAS, Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
            return;
        }

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onLoginSuccess();
                progressDialog.dismiss();
            }
        }, 3000);

    }

}
