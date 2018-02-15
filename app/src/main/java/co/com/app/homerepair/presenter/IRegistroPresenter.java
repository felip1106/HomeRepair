package co.com.app.homerepair.presenter;

import android.content.Context;

import co.com.app.homerepair.model.Clientes;
import co.com.app.homerepair.model.Proveedor;
import co.com.app.homerepair.model.Usuarios;
import co.com.app.homerepair.view.IRegistroView;

/**
 * Created by jfmg9029 on 02/02/2018.
 */

public interface IRegistroPresenter<V extends IRegistroView> {

    /**
     * Retorna una entidad usuario consultado por nombre.
     *
     * @param nombre  Nombre de usuario
     * @param context Contexto
     * @return {@link Usuarios}
     */
    Usuarios findUsuarioByNombre(String nombre, Context context);

    /**
     * Guarda el registro del usuario que crea la cuenta.
     *
     * @param usuario Usuario a registrar
     * @param context Contexto
     */
    void saveUsuario(Usuarios usuario, Context context);

    /**
     * Guarda la informacion basica del usuario cliente.
     *
     * @param cliente Cliente a registrar
     * @param context Contexto
     */
    void saveCliente(Clientes cliente, Context context);

    /**
     * Almacena la informacion del usuario proveedor
     *
     * @param proveedor Proveedor a registrar
     * @param context   Contexto
     */
    void saveRegistroProveedor(Proveedor proveedor, Context context);

}
