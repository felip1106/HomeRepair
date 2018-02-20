package co.com.app.homerepair.view.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.com.app.homerepair.R;
import co.com.app.homerepair.enums.EstadoEnum;
import co.com.app.homerepair.enums.RolEnum;
import co.com.app.homerepair.model.Clientes;
import co.com.app.homerepair.model.Usuarios;
import co.com.app.homerepair.presenter.IRegistroPresenter;
import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegistroClienteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegistroClienteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistroClienteFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Inject
    Usuarios usuarios;

    @Inject
    Clientes clientes;

    @Inject
    IRegistroPresenter registroPresenter;

    @BindView(R.id.input_usuario_registro)
    EditText _usuarioText;

    @BindView(R.id.input_contrasegna_registro)
    EditText _contrasegnaText;

    @BindView(R.id.layout_input_contrasegna_registro)
    TextInputLayout _contrasegnaTextLayout;

    @BindView(R.id.input_nombres_registro)
    EditText _nombresText;

    @BindView(R.id.input_apellidos_registro)
    EditText _apellidosText;

    @BindView(R.id.input_email_registro)
    EditText _emailText;

    @BindView(R.id.input_telefono_registro)
    EditText _telefonoText;

    @BindView(R.id.input_direccion_registro)
    EditText _direccionText;

    @BindView(R.id.input_fecha_nacimiento_registro)
    EditText _fechaNacimientoText;

    private OnFragmentInteractionListener mListener;

    public RegistroClienteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistroClienteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistroClienteFragment newInstance(String param1, String param2) {
        RegistroClienteFragment fragment = new RegistroClienteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registro_cliente, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void saveUsuario() {
        if (mListener != null) {
            usuarios.setUsu_nombre(_usuarioText.getText().toString());
            usuarios.setUsu_pass(_contrasegnaText.getText().toString());
            usuarios.setUsu_estado(EstadoEnum.ACTIVO.toString());
            usuarios.setUsu_rol_id(RolEnum.CLIENTE.id);

            mListener.saveUsuarioInteraction(usuarios);
        }
    }

    public void saveCliente() {
        if (mListener != null) {
            clientes.setCl_nombre(_nombresText.getText().toString());
            clientes.setCl_apellidos(_apellidosText.getText().toString());
            clientes.setCl_email(_emailText.getText().toString());
            clientes.setCl_telefono(_telefonoText.getText().toString());
            clientes.setCl_direccion(_direccionText.getText().toString());
            clientes.setCl_fecha_naci(_fechaNacimientoText.getText().toString());
            clientes.setCl_estado(EstadoEnum.ACTIVO.toString());
            clientes.setUsuarios(usuarios);

            mListener.saveClienteInteraction(clientes);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        AndroidSupportInjection.inject(this);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

        void saveUsuarioInteraction(Usuarios usuarios);

        void saveClienteInteraction(Clientes clientes);

        boolean validateRegistroUsuario(boolean validate);
    }

    void showDatePickerFechaNacimiento() {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                final String selectedDate = day + " / " + (month + 1) + " / " + year;
                _fechaNacimientoText.setText(selectedDate);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    public boolean validateRegistro() {
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
            if (_contrasegnaText.getText().toString().length() < 6) {
                _contrasegnaText.setError(getString(R.string.MSG_REGISTRO_PASSWORD_LENGTH));
                validate = false;
            } else {
                if (!(_contrasegnaText.getText().toString().matches("([A-Za-z]+[0-9]|[0-9]+[A-Za-z])[A-Za-z0-9]*"))) {
                    _contrasegnaText.setError(getString(R.string.MSG_REGISTRO_PASSWORD_LENGTH));
                    validate = false;
                } else {
                    _contrasegnaText.setError(null);
                }
            }
        }

        mListener.validateRegistroUsuario(validate);

        return validate;
    }

    @OnClick(R.id.input_fecha_nacimiento_registro)
    void onShowDatePickerFechaNacimiento(View view) {
        showDatePickerFechaNacimiento();
    }
}
