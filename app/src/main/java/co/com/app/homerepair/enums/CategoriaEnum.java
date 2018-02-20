package co.com.app.homerepair.enums;

import co.com.app.homerepair.R;

/**
 * Created by jfmg9029 on 17/02/2018.
 */

public enum CategoriaEnum {

    CARPINTERIA(1L, "Carpintería", EstadoEnum.ACTIVO, R.mipmap.ic_hummer),
    CERRAJERIA(2L, "Cerrajería", EstadoEnum.ACTIVO, R.mipmap.ic_key),
    PLOMERIA(3L, "Plomería", EstadoEnum.ACTIVO, R.mipmap.ic_water),
    ELECTRICA(4L, "Eléctrica", EstadoEnum.ACTIVO, R.mipmap.ic_plug);

    public long id;
    public String categoria;
    public EstadoEnum estado;
    public int resIconCategoria;

    CategoriaEnum(long id, String categoria, EstadoEnum estado, int resIconCategoria) {
        this.id = id;
        this.categoria = categoria;
        this.estado = estado;
        this.resIconCategoria = resIconCategoria;
    }
}
