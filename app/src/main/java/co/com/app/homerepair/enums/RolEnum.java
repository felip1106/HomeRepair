package co.com.app.homerepair.enums;

/**
 * Created by jfmg9029 on 13/02/2018.
 */

public enum RolEnum {

    CLIENTE(1L), PROVEEDOR(2L);

    public long id;

    RolEnum(long id) {
        this.id = id;
    }

}
