package co.com.app.homerepair.view;

/**
 * Created by jfmg9029 on 15/02/2018.
 */

public interface IPrincipalView {

    /**
     * Inicia la actividad de creacion de solicitudes.
     */
    void startCreaSolicitudActivity();

    /**
     * Inicia la actividad de consulta de solicitudes.
     */
    void startConsultaSolicitudActivity();

    /**
     * Inicia la actividad de edicion de perfil.
     */
    void startEditaPerfilActivity();

    /**
     * Cerrar la sesion del usuario
     */
    void onLogoff();

}
