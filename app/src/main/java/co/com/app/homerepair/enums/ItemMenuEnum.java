package co.com.app.homerepair.enums;

import co.com.app.homerepair.R;

/**
 * Created by jfmg9029 on 15/02/2018.
 */

public enum ItemMenuEnum {

    ITEM_CREAR_SOLICITUD(1, R.mipmap.ic_request_create, R.string.item_crear_solicitud, RolEnum.CLIENTE),
    ITEM_VER_SOLICITUDES(2, R.mipmap.ic_request_search, R.string.item_ver_solicitudes),
    ITEM_EDITAR_INFORMACION(3, R.mipmap.ic_profile_edit, R.string.item_editar_informacion),
    ITEM_CERRAR_SESION(4, R.mipmap.ic_logoff, R.string.item_cerrar_sesion);

    public final int id;
    public int resIconMenu;
    public int resMessage;
    public RolEnum rolEnum;

    ItemMenuEnum(int id, int resIconMenu, int resMessage) {
        this.id = id;
        this.resIconMenu = resIconMenu;
        this.resMessage = resMessage;
    }

    ItemMenuEnum(int id, int resIconMenu, int resMessage, RolEnum rolEnum) {
        this.id = id;
        this.resIconMenu = resIconMenu;
        this.resMessage = resMessage;
        this.rolEnum = rolEnum;
    }
}
