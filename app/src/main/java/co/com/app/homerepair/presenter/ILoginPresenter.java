package co.com.app.homerepair.presenter;

import android.content.Context;

import co.com.app.homerepair.model.Usuarios;
import co.com.app.homerepair.view.ILoginView;

/**
 * Created by jfmg9029 on 13/02/2018.
 */

public interface ILoginPresenter<V extends ILoginView> {

    /**
     * Retorna una entidad usuario consultado por nombre y password.
     *
     * @param nombre   Nombre de usuario
     * @param password Password encriptado
     * @param context  Contexto
     * @return {@link Usuarios}
     */
    Usuarios findUsuarioByNombrePassword(String nombre, String password, Context context);
}
