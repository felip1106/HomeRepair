package co.com.app.homerepair.enums;

import co.com.app.homerepair.R;

/**
 * Created by jfmg9029 on 18/02/2018.
 */

public enum TipoImagenEnum {

    ITEM_CAMARA(1, R.mipmap.ic_camera, R.string.text_toma_fotografia),
    ITEM_GALERIA(2, R.mipmap.ic_gallery, R.string.text_selecciona_galeria);

    public int id;
    public int resIconMenu;
    public int resMessage;

    TipoImagenEnum(int id, int resIconMenu, int resMessage) {
        this.id = id;
        this.resIconMenu = resIconMenu;
        this.resMessage = resMessage;
    }
}
