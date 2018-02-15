package co.com.app.homerepair.module;

import android.app.Application;

import co.com.app.homerepair.controller.AppController;
import co.com.app.homerepair.model.Clientes;
import co.com.app.homerepair.model.Proveedor;
import co.com.app.homerepair.model.Usuarios;
import co.com.app.homerepair.presenter.ILoginPresenter;
import co.com.app.homerepair.presenter.IRegistroPresenter;
import co.com.app.homerepair.presenter.LoginPresenter;
import co.com.app.homerepair.presenter.RegistroPresenter;
import co.com.app.homerepair.view.LoginActivity;
import co.com.app.homerepair.view.RegistroActivity;
import co.com.app.homerepair.view.fragment.RegistroClienteFragment;
import co.com.app.homerepair.view.fragment.RegistroProveedorFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by jfmg9029 on 12/02/2018.
 */

@Module(includes = {AndroidInjectionModule.class})
public abstract class AppModule {

    @Binds
    abstract Application application(AppController appController);

    @ContributesAndroidInjector
    abstract RegistroActivity contributeRegistroActivityInjector();

    @ContributesAndroidInjector
    abstract LoginActivity contributeLoginActivityInjector();

    @ContributesAndroidInjector
    abstract RegistroClienteFragment contributeRegistroClienteFragmentInjector();

    @ContributesAndroidInjector
    abstract RegistroProveedorFragment contributeRegistroProveedorFragmentInjector();

    @Provides
    static IRegistroPresenter provideRegistroPresenter() {
        return new RegistroPresenter();
    }

    @Provides
    static ILoginPresenter provideLoginPresenter() {
        return new LoginPresenter();
    }

    @Provides
    static Usuarios provideUsuario() {
        return new Usuarios();
    }

    @Provides
    static Clientes provideCliente() {
        return new Clientes();
    }

    @Provides
    static Proveedor provideProveedor() {
        return new Proveedor();
    }

}
