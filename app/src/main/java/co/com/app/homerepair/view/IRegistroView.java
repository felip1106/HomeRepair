package co.com.app.homerepair.view;

import android.support.v4.app.Fragment;

/**
 * Created by jfmg9029 on 30/01/2018.
 */

public interface IRegistroView {

    /**
     * Muestra el fragment segun la seleccion del tipo de usuario a registrar.
     *
     * @param fragment El fragment a cargar
     */
    void showFragmentTipoUsuario(Fragment fragment);

    /**
     * Si el registro del usuario es exitoso le permite continuar en la aplicacion y finalizar la actividad actual.
     */
    void onRegistroSuccess();

    /**
     * Le indica al usuario cuando el registro no es valido como consecuencia de inconsistencia de datos. Tambien aplica cuando el usuario ya se encuentra registrado en la base de datos.
     *
     * @param resId id
     */
    void onRegistroFailed(int resId);

    /**
     * Valida si el usuario que se esta registrando ya se encuentra registrado previamente.
     *
     * @param nombre Nombre del usuario
     * @return true si el usuario ya se encuentra registrado
     */
    boolean findUsuarioByNombre(String nombre);

    /**
     * Encripta la contraseña del usuario Base64 con algoritmo AES.
     *
     * @param password {@code String} que sera encriptado
     * @return Password encriptado
     */
    String encriptarPassword(String password);

}
