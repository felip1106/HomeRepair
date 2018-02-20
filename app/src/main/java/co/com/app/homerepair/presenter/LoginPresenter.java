package co.com.app.homerepair.presenter;

import android.content.Context;

import co.com.app.homerepair.controller.AppController;
import co.com.app.homerepair.dao.UsuariosDao;
import co.com.app.homerepair.model.Usuarios;
import co.com.app.homerepair.view.ILoginView;

/**
 * Created by jfmg9029 on 13/02/2018.
 */

public class LoginPresenter<V extends ILoginView> implements ILoginPresenter<V> {

    @Override
    public Usuarios findUsuarioByNombrePassword(String nombre, String password, Context context) {
        Usuarios usuarios;

        UsuariosDao usuariosDao = ((AppController)context).getDaoSession().getUsuariosDao();
        usuarios = usuariosDao.queryBuilder()
                .where(UsuariosDao.Properties.Usu_nombre.eq(nombre), UsuariosDao.Properties.Usu_pass.eq(password)).unique();

        return usuarios;
    }
}
