package co.com.app.homerepair.presenter;

import android.content.Context;

import java.util.List;

import co.com.app.homerepair.controller.AppController;
import co.com.app.homerepair.dao.AdjuntoDao;
import co.com.app.homerepair.dao.AdjuntoSolicitudDao;
import co.com.app.homerepair.dao.CategoriaDao;
import co.com.app.homerepair.dao.ClientesDao;
import co.com.app.homerepair.dao.SolicitudDao;
import co.com.app.homerepair.model.Adjunto;
import co.com.app.homerepair.model.AdjuntoSolicitud;
import co.com.app.homerepair.model.Categoria;
import co.com.app.homerepair.model.Clientes;
import co.com.app.homerepair.model.Solicitud;
import co.com.app.homerepair.view.IRegistroSolicitudView;

/**
 * Created by jfmg9029 on 17/02/2018.
 */

public class SolicitudPresenter<V extends IRegistroSolicitudView> implements ISolicitudPresenter<V> {
    @Override
    public List<Categoria> findAllCategorias(Context context) {
        List<Categoria> categorias = null;

        CategoriaDao categoriaDao = ((AppController) context).getDaoSession().getCategoriaDao();
        categorias = categoriaDao.loadAll();

        return categorias;
    }

    @Override
    public void saveSolicitud(Solicitud solicitud, Context context) {
        SolicitudDao solicitudDao = ((AppController) context).getDaoSession().getSolicitudDao();
        solicitudDao.insert(solicitud);
    }

    @Override
    public Clientes findClienteByUsuarioId(long usuarioId, Context context) {
        Clientes clientes = null;

        ClientesDao clientesDao = ((AppController) context).getDaoSession().getClientesDao();
        clientes = clientesDao.queryBuilder().where(ClientesDao.Properties.Cl_usu_id.eq(usuarioId)).unique();

        return clientes;
    }

    @Override
    public void saveAdjunto(List<Adjunto> adjuntos, Context context) {
        AdjuntoDao adjuntoDao = ((AppController) context).getDaoSession().getAdjuntoDao();
        adjuntoDao.insertInTx(adjuntos);
    }

    @Override
    public void saveAdjuntoSolicitud(List<AdjuntoSolicitud> adjuntosSolicitud, Context context) {
        AdjuntoSolicitudDao adjuntoSolicitudDao = ((AppController) context).getDaoSession().getAdjuntoSolicitudDao();
        adjuntoSolicitudDao.insertInTx(adjuntosSolicitud);
    }
}
