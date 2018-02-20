package co.com.app.homerepair.view;

import java.util.List;

import co.com.app.homerepair.model.Categoria;
import co.com.app.homerepair.model.Clientes;
import co.com.app.homerepair.model.Solicitud;
import co.com.app.homerepair.model.Usuarios;

/**
 * Created by jfmg9029 on 17/02/2018.
 */

public interface IRegistroSolicitudView {

    /**
     * Muestra el fragment para la seleccion de categorias.
     */
    void showFragmentCategorias();

    /**
     * Muestra el fragment para la seleccion del tipo de imagen.
     */
    void showFragmentTypeImages();

    /**
     * Define la lista de categorias.
     */
    List<Categoria> loadCategoriaItems();

    /**
     * Inicia la accion de camara para capturar una fotografia
     */
    void onCameraIntent();

    /**
     * Inicia la accion de seleccion de imagen desde la galeria
     */
    void onGalleryIntent();

    /**
     * Valida que el tipo de categoria y la descripcion de la solicitud sean diligenciadas para el registro.
     *
     * @return true si la categoria y la descripcion fueron diligenciados
     */
    boolean validateRegistroSolicitud();

    /**
     * Si el registro de la solicitud es exitoso le permite continuar en la aplicacion y finalizar la actividad actual.
     */
    void onRegistroSolicitudSuccess();

    /**
     * Crea la solicitud como un objeto a fin de ser registrada transacionalmente.
     *
     * @return Solicitud creada
     */
    Solicitud onCreateSolicitud();

    /**
     * Retorna una entidad cliente a partir de la identificacion del usuario
     *
     * @param usuario {@link Usuarios}
     * @return {@link Clientes}
     */
    Clientes findClienteByUsuario(Usuarios usuario);

    /**
     * Adiciona el bitmap de la imagen cargada a la lista de adjuntos que seran almacenados.
     *
     * @param bytes Arreglo de bytes de la imagen
     */
    void addAdjuntoList(byte[] bytes);
}
