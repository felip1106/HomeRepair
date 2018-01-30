package co.com.app.homerepair;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.app.homerepair.view.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.link_registro)
    TextView _registroLink;

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
    }

    @Override
    public void startRegistroActivity() {
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }
}
