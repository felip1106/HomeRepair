package co.com.app.homerepair.controller;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import org.greenrobot.greendao.database.Database;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import co.com.app.homerepair.component.DaggerAppComponent;
import co.com.app.homerepair.dao.DaoMaster;
import co.com.app.homerepair.dao.DaoSession;
import co.com.app.homerepair.enums.CategoriaEnum;
import co.com.app.homerepair.enums.EstadoSolicitudEnum;
import co.com.app.homerepair.enums.RolEnum;
import co.com.app.homerepair.model.Categoria;
import co.com.app.homerepair.model.EstadoSolicitud;
import co.com.app.homerepair.model.Rol;
import co.com.app.homerepair.model.Usuarios;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by jfmg9029 on 02/02/2018.
 */

public class AppController extends Application implements HasActivityInjector, HasSupportFragmentInjector {

    public static final boolean ENCRYPTED = true;

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidActivityInjector;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidFragmentInjector;

    @Inject
    Usuarios usuarios;

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder().create(this).inject(this);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "home-repair-db-encrypted" : "home-repair-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

        loadEntityInitialDatabase();
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public void loadEntityInitialDatabase() {
        addRoles();
        addCategorias();
        addEstadosSolicitud();
    }

    private void addRoles() {
        List<Rol> roles = new ArrayList<>();

        for (RolEnum rolEnum : RolEnum.values()) {
            Rol rol = new Rol();
            rol.setId(rolEnum.id);
            rol.setRol_nombre(rolEnum.rol);
            rol.setRol_estado(rolEnum.estado.toString());
            roles.add(rol);
        }

        daoSession.getRolDao().insertOrReplaceInTx(roles);
    }

    private void addCategorias() {
        List<Categoria> categorias = new ArrayList<>();

        for (CategoriaEnum categoriaEnum : CategoriaEnum.values()) {
            Categoria categoria = new Categoria();
            categoria.setId(categoriaEnum.id);
            categoria.setCat_descripcion(categoriaEnum.categoria);
            categoria.setCat_estado(categoriaEnum.estado.toString());
            categorias.add(categoria);
        }

        daoSession.getCategoriaDao().insertOrReplaceInTx(categorias);
    }

    private void addEstadosSolicitud() {
        List<EstadoSolicitud> estadosSolicitud = new ArrayList<>();

        for (EstadoSolicitudEnum estadoSolicitudEnum : EstadoSolicitudEnum.values()) {
            EstadoSolicitud estadoSolicitud = new EstadoSolicitud();
            estadoSolicitud.setId(estadoSolicitudEnum.id);
            estadoSolicitud.setEstsol_descripcion(estadoSolicitudEnum.toString());
            estadosSolicitud.add(estadoSolicitud);
        }

        daoSession.getEstadoSolicitudDao().insertOrReplaceInTx(estadosSolicitud);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidActivityInjector;
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidFragmentInjector;
    }
}
