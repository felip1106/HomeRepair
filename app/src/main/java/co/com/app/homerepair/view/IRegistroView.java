package co.com.app.homerepair.view;

import android.support.v4.app.Fragment;

/**
 * Created by jfmg9029 on 30/01/2018.
 */

public interface IRegistroView {

    /**
     * Muestra el fragment segun la seleccion del tipo de usuario a registrar
     *
     * @param fragment El fragment a cargar
     */
    void showFragmentTipoUsuario(Fragment fragment);

}
