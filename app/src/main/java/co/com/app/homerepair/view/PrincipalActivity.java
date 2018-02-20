package co.com.app.homerepair.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import co.com.app.homerepair.R;
import co.com.app.homerepair.enums.ItemMenuEnum;
import co.com.app.homerepair.view.fragment.MenuPrincipalFragment;
import co.com.app.homerepair.view.fragment.content.MenuPrincipalContent;

public class PrincipalActivity extends AppCompatActivity implements IPrincipalView, MenuPrincipalFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        final Fragment fragmentPrincipal = new MenuPrincipalFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment_menu_principal, fragmentPrincipal);
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_acerca_de:
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.text_acerca_de)
                        .setView(R.layout.custom_acerca_de)
                        .setPositiveButton(R.string.MSG_CERRAR, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create();
                builder.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(MenuPrincipalContent.Item item) {
        ItemMenuEnum itemMenuEnum = findEnumByItem(item);
        switch (itemMenuEnum) {
            case ITEM_CREAR_SOLICITUD:
                startCreaSolicitudActivity();
                break;
            case ITEM_VER_SOLICITUDES:
                startConsultaSolicitudActivity();
                break;
            case ITEM_EDITAR_INFORMACION:
                startEditaPerfilActivity();
                break;
            case ITEM_CERRAR_SESION:
                onLogoff();
                break;
        }
    }

    public ItemMenuEnum findEnumByItem(MenuPrincipalContent.Item item) {
        ItemMenuEnum menuEnum = null;
        for (ItemMenuEnum itemMenuEnumTmp : ItemMenuEnum.values()) {
            if (item.id == itemMenuEnumTmp.id) {
                menuEnum = itemMenuEnumTmp;
            }
        }

        return menuEnum;
    }

    @Override
    public void startCreaSolicitudActivity() {
        Intent intent = new Intent(this, RegistroSolicitudActivity.class);
        startActivity(intent);
    }

    @Override
    public void startConsultaSolicitudActivity() {

    }

    @Override
    public void startEditaPerfilActivity() {

    }

    @Override
    public void onLogoff() {

    }
}
