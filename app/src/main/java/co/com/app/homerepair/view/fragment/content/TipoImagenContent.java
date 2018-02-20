package co.com.app.homerepair.view.fragment.content;

import java.util.ArrayList;
import java.util.List;

import co.com.app.homerepair.enums.TipoImagenEnum;

/**
 * Created by jfmg9029 on 15/02/2018.
 */

public class TipoImagenContent {

    public static final List<Item> ITEMS = new ArrayList<>();
    private static final TipoImagenEnum[] ITEMS_TYPE_IMAGE_CONTENT = TipoImagenEnum.values();

    static {

        for (TipoImagenEnum itemTipoImagenEnum : ITEMS_TYPE_IMAGE_CONTENT) {
            ITEMS.add(new Item(itemTipoImagenEnum.id, itemTipoImagenEnum.resIconMenu, itemTipoImagenEnum.resMessage));
        }
    }

    public static class Item {

        public final int id;
        public final int icon;
        public final int message;

        public Item(int id, int icon, int message) {
            this.id = id;
            this.icon = icon;
            this.message = message;
        }
    }
}
