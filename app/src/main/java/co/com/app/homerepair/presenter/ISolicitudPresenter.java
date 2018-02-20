package co.com.app.homerepair.presenter;

import android.content.Context;

import java.util.List;

import co.com.app.homerepair.model.Adjunto;
import co.com.app.homerepair.model.AdjuntoSolicitud;
import co.com.app.homerepair.model.Categoria;
import co.com.app.homerepair.model.Clientes;
import co.com.app.homerepair.model.Solicitud;
import co.com.app.homerepair.view.IRegistroSolicitudView;

/**
 * Created by jfmg9029 on 17/02/2018.
 */

public interface ISolicitudPresenter<V extends IRegistroSolicitudView> {

    /**
     * Retorna las categorias.
     *
     * @param context Contexto
     * @return {@link java.util.Collections} de categorias
     */
    List<Categoria> findAllCategorias(Context context);

    /**
     * Guarda la informacion de la solicitud.
     *
     * @param solicitud Solicitud que se va a registrar
     * @param context   Contexto
     */
    void saveSolicitud(Solicitud solicitud, Context context);

    /**
     * Retorna una entidad cliente a partir de la identificacion del usuario.
     *
     * @param usuarioId Identificacion del usuario
     * @param context   Contexto
     * @return {@link Clientes}
     */
    Clientes findClienteByUsuarioId(long usuarioId, Context context);

    /**
     * Guarda la informacion de los adjuntos.
     *
     * @param adjuntos Adjuntos que se van a registrar
     * @param context  Contexto
     */
    void saveAdjunto(List<Adjunto> adjuntos, Context context);

    /**
     * Guarda la informacion de los adjuntos relacionandolos a la solicitud.
     *
     * @param adjuntosSolicitud Adjuntos que se van a registrar
     */
    void saveAdjuntoSolicitud(List<AdjuntoSolicitud> adjuntosSolicitud, Context context);

}
