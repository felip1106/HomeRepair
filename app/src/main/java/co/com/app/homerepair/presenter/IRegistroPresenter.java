package co.com.app.homerepair.presenter;

import co.com.app.homerepair.model.Usuarios;
import co.com.app.homerepair.view.IRegistroView;

/**
 * Created by jfmg9029 on 02/02/2018.
 */

public interface IRegistroPresenter<V extends IRegistroView> {

    /**
     * Almacena el registro del usuario
     *
     * @param usuario Usuario a registrar
     */
    void saveRegistroUsuario(Usuarios usuario);
}
