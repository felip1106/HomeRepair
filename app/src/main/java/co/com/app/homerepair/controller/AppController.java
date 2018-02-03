package co.com.app.homerepair.controller;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

import co.com.app.homerepair.dao.DaoMaster;
import co.com.app.homerepair.dao.DaoSession;

/**
 * Created by jfmg9029 on 02/02/2018.
 */

public class AppController extends Application {

    public static final boolean ENCRYPTED = true;

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "home-repair-db-encrypted" : "home-repair-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

}
