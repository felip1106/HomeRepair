package co.com.app.homerepair.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.app.homerepair.R;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    @BindView(R.id.link_registro)
    TextView _registroLink;

    @BindView(R.id.button_ingresar)
    AppCompatButton _ingresarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        _registroLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRegistroActivity();
            }
        });

        _ingresarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Ingresar. Not implemented yet;
            }
        });
    }

    @Override
    public void startRegistroActivity() {
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }
}
