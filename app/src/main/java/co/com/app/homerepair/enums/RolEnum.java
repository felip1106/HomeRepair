package co.com.app.homerepair.enums;

/**
 * Created by jfmg9029 on 13/02/2018.
 */

public enum RolEnum {

    ADMINISTRADOR(1L, "Administrador", EstadoEnum.ACTIVO),
    CLIENTE(2L, "Cliente", EstadoEnum.ACTIVO),
    PROVEEDOR(3L, "Proveedor", EstadoEnum.ACTIVO);

    public long id;
    public String rol;
    public EstadoEnum estado;

    RolEnum(long id, String rol, EstadoEnum estado) {
        this.id = id;
        this.rol = rol;
        this.estado = estado;
    }

}
