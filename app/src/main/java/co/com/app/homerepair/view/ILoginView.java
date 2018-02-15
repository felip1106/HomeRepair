package co.com.app.homerepair.view;

/**
 * Created by JuanFelipe on 29/01/2018.
 */

public interface ILoginView {

    /**
     * Inicia la actividad de registro de usuarios.
     */
    void startRegistroActivity();

    /**
     * Inicia la actividad del menu pricipal cuando el login es correcto
     */
    void startPrincipalActivity();

    /**
     * Si el login del usuario es exitoso le permite continuar en la aplicacion y finalizar la actividad actual.
     */
    void onLoginSuccess();

    /**
     * Si el login del usuario es fallido, le indica al usuario que sus credenciales no son validas.
     */
    void onLoginFailed();

    /**
     * Valida que el usuario y la credencial sean diligenciadas para el login.
     *
     * @return true si el usuario y la contraseña cumplen con los criterios
     */
    boolean validateLogin();

    /**
     * Encripta la contraseña del usuario Base64 con algoritmo AES.
     *
     * @param password {@code String} que sera encriptado
     * @return Password encriptado
     */
    String encriptarPassword(String password);
}
