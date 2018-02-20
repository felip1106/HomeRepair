package co.com.app.homerepair.enums;

/**
 * Created by jfmg9029 on 19/02/2018.
 */

public enum EstadoSolicitudEnum {

    REGISTRADA(1L),
    COTIZADA(2L),
    FINALIZADA(3L);

    public long id;

    EstadoSolicitudEnum(long id) {
        this.id = id;
    }
}
