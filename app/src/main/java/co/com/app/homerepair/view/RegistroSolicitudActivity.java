package co.com.app.homerepair.view;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.com.app.homerepair.R;
import co.com.app.homerepair.controller.AppController;
import co.com.app.homerepair.enums.EstadoSolicitudEnum;
import co.com.app.homerepair.model.Adjunto;
import co.com.app.homerepair.model.AdjuntoSolicitud;
import co.com.app.homerepair.model.Categoria;
import co.com.app.homerepair.model.Clientes;
import co.com.app.homerepair.model.EstadoSolicitud;
import co.com.app.homerepair.model.Solicitud;
import co.com.app.homerepair.model.Usuarios;
import co.com.app.homerepair.presenter.ISolicitudPresenter;
import co.com.app.homerepair.utils.BitmapScaler;
import co.com.app.homerepair.view.fragment.CargaImagenFragment;
import co.com.app.homerepair.view.fragment.CategoriaFragment;
import co.com.app.homerepair.view.fragment.TipoImagenFragment;
import co.com.app.homerepair.view.fragment.content.ImagenContent;
import co.com.app.homerepair.view.fragment.content.TipoImagenContent;
import dagger.android.AndroidInjection;

public class RegistroSolicitudActivity extends AppCompatActivity implements IRegistroSolicitudView, CategoriaFragment.OnListFragmentInteractionListener, TipoImagenFragment.OnListFragmentInteractionListener, CargaImagenFragment.OnListFragmentInteractionListener {

    @Inject
    ISolicitudPresenter solicitudPresenter;

    @Inject
    Solicitud solicitud;

    @Inject
    Categoria categoria;

    @Inject
    Application application;

    @BindView(R.id.input_categoria)
    EditText _categoriaText;

    @BindView(R.id.input_descripcion)
    EditText _descripcionText;

    @BindString(R.string.text_selecciona_imagen_galeria)
    String _seleccionaImagenGaleriaText;

    private static final int REQUEST_CAMERA = 1;
    private static final int SELECT_FILE = 2;
    private static final String TYPE_IMAGE_GALLERY = "image/*";
    private static final int IMAGE_WIDTH = 400;
    private static final int IMAGE_HEIGHT = 500;
    private static final String FORMAT_EXT_IMAGE = "jpg";
    private ImageView imageSelected;
    private DialogFragment categoriaDialog;
    private DialogFragment typeImageDialog;
    private FragmentTransaction ft;
    final Fragment fragmentCategoria = new CategoriaFragment();
    final Fragment fragmentTipoImagen = new TipoImagenFragment();
    final List<Adjunto> adjuntos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_registro_solicitud);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        final Fragment fragmentCargaImagen = new CargaImagenFragment();
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment_carga_imagen, fragmentCargaImagen);
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.registro_solicitud, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save_solicitud:
                if (validateRegistroSolicitud()) {
                    final ProgressDialog progressDialog = new ProgressDialog(this,
                            R.style.AppTheme_Dark_Dialog);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage(getString(R.string.MSG_SOLICITUD_REGISTRO_PROGRESS_DIALOG));
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    solicitud = onCreateSolicitud();
                    solicitudPresenter.saveSolicitud(solicitud, application.getApplicationContext());
                    solicitudPresenter.saveAdjunto(adjuntos, application.getApplicationContext());

                    List<AdjuntoSolicitud> adjuntosSolicitud = new ArrayList<>();
                    for (Adjunto adjunto : adjuntos) {
                        AdjuntoSolicitud adjuntoSolicitud = new AdjuntoSolicitud();
                        adjuntoSolicitud.setSol_id(solicitud.getId());
                        adjuntoSolicitud.setAdj_id(adjunto.getId());
                        adjuntosSolicitud.add(adjuntoSolicitud);
                    }

                    solicitudPresenter.saveAdjuntoSolicitud(adjuntosSolicitud, application.getApplicationContext());

                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            onRegistroSolicitudSuccess();
                            progressDialog.dismiss();
                        }
                    }, 3000);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.input_categoria)
    void onCategoriasShow(View view) {
        showFragmentCategorias();
    }

    @Override
    public void onListFragmentInteraction(ImagenContent.Item item) {

    }

    @Override
    public void onImageViewInteraction(ImageView imageView) {
        showFragmentTypeImages();
        imageSelected = imageView;
    }

    @Override
    public void onCameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onGalleryIntent() {
        Intent intent = new Intent();
        intent.setType(TYPE_IMAGE_GALLERY);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, _seleccionaImagenGaleriaText), SELECT_FILE);
    }

    @Override
    public boolean validateRegistroSolicitud() {
        boolean validate = true;

        if (_categoriaText.getText().toString().isEmpty()) {
            _categoriaText.setError(getString(R.string.MSG_SOLICITUD_CATEGORIA_VACIO));
            validate = false;
        } else {
            _categoriaText.setError(null);
        }

        if (_descripcionText.getText().toString().isEmpty()) {
            _descripcionText.setError(getString(R.string.MSG_SOLICITUD_DESCRIPCION_VACIO));
            validate = false;
        } else {
            _descripcionText.setError(null);
        }

        return validate;
    }

    @Override
    public void onRegistroSolicitudSuccess() {
        setResult(RESULT_OK, null);
        finish();
        Toast.makeText(this, R.string.MSG_SOLICITUD_REGISTRO_EXITO, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Solicitud onCreateSolicitud() {
        Solicitud solicitud = new Solicitud();
        solicitud.setSol_descripcion(_descripcionText.getText().toString());
        solicitud.setClientes(findClienteByUsuario(((AppController) application).getUsuarios()));
        solicitud.setEstadoSolicitud(new EstadoSolicitud(EstadoSolicitudEnum.REGISTRADA.id));
        solicitud.setCategoria(categoria);

        return solicitud;
    }

    @Override
    public Clientes findClienteByUsuario(Usuarios usuario) {
        return solicitudPresenter.findClienteByUsuarioId(usuario.getId(), application.getApplicationContext());
    }

    @Override
    public void addAdjuntoList(byte[] bytes) {
        final Adjunto adjunto = new Adjunto();
        adjunto.setAdj_img(bytes);
        adjuntos.add(adjunto);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onSelectFromGalleryResult(Intent data) {
        InputStream inputStream = null;
        try {
            if (data != null)
                inputStream = getContentResolver().openInputStream(data.getData());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Bitmap bitmap = decodeBitmapFromStream(inputStream);

        imageSelected.setImageBitmap(bitmap);
        imageSelected.setBackgroundResource(R.drawable.ic_image_backgroung);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        addAdjuntoList(outputStream.toByteArray());
    }

    public Bitmap scaleBitmapStream(InputStream inputStream) {
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        return BitmapScaler.strechToFill(bitmap, IMAGE_WIDTH, IMAGE_HEIGHT);
    }

    public Bitmap scaleBitmap(Bitmap bitmap) {
        return BitmapScaler.strechToFill(bitmap, IMAGE_WIDTH, IMAGE_HEIGHT);
    }

    public Bitmap decodeBitmapFromStream(InputStream inputStream) {
        return scaleBitmapStream(inputStream);
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        File file = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + "." + FORMAT_EXT_IMAGE);
        FileOutputStream fileOutputStream;
        try {
            file.createNewFile();
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(outputStream.toByteArray());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageSelected.setImageBitmap(scaleBitmap(bitmap));
        imageSelected.setBackgroundResource(R.drawable.ic_image_backgroung);

        addAdjuntoList(outputStream.toByteArray());
    }

    @Override
    public void onListFragmentInteraction(Categoria item) {
        _categoriaText.setText(item.getCat_descripcion());
        _categoriaText.setError(null);
        categoriaDialog.dismiss();
        categoria = item;
    }

    @Override
    public void showFragmentCategorias() {
        ft = getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(fragmentCategoria.toString());
        final List<Categoria> categorias = loadCategoriaItems();
        categoriaDialog = CategoriaFragment.newInstance(1, categorias);
        categoriaDialog.show(ft, "_seleccionCategoriaFragment");
    }

    @Override
    public void showFragmentTypeImages() {
        ft = getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(fragmentTipoImagen.toString());
        typeImageDialog = TipoImagenFragment.newInstance(1);
        typeImageDialog.show(ft, "_seleccionTypeImageFragment");
    }

    @Override
    public List<Categoria> loadCategoriaItems() {
        return solicitudPresenter.findAllCategorias(getApplicationContext());
    }

    @Override
    public void onListFragmentInteraction(TipoImagenContent.Item item) {
        if (item.id == REQUEST_CAMERA) {
            onCameraIntent();
        } else if (item.id == SELECT_FILE) {
            onGalleryIntent();
        }
        typeImageDialog.dismiss();
    }
}
