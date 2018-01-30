package co.com.app.homerepair;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistroActivity extends AppCompatActivity {

    @BindView(R.id.spinner_tipo_registro)
    Spinner _tipoRegistroSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_registro);
        ButterKnife.bind(this);

        String[] tiposRegistro = getResources().getStringArray(R.array.array_tipo_registro);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tiposRegistro);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _tipoRegistroSpinner.setAdapter(spinnerArrayAdapter);

    }

}
