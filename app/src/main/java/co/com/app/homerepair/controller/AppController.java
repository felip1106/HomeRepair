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
import co.com.app.homerepair.enums.EstadoEnum;
import co.com.app.homerepair.enums.RolEnum;
import co.com.app.homerepair.model.Rol;
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

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder().create(this).inject(this);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "home-repair-db-encrypted" : "home-repair-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

        initial();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public void initial() {
        List<Rol> arrayRoles = new ArrayList<Rol>();
        arrayRoles.add(new Rol(1L, RolEnum.CLIENTE.name(), EstadoEnum.ACTIVO.name()));
        arrayRoles.add(new Rol(2L, RolEnum.PROVEEDOR.name(), EstadoEnum.ACTIVO.name()));

        for (Rol rol : arrayRoles) {
            daoSession.getRolDao().insertOrReplaceInTx(rol);
        }
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
