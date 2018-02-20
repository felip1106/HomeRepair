package co.com.app.homerepair.view.fragment.content;

import java.util.ArrayList;
import java.util.List;

import co.com.app.homerepair.enums.ItemMenuEnum;

/**
 * Created by jfmg9029 on 15/02/2018.
 */

public class MenuPrincipalContent {

    public static final List<Item> ITEMS = new ArrayList<>();
    private static final ItemMenuEnum[] ITEMS_MENU_CONTENT = ItemMenuEnum.values();

    static {

        for (ItemMenuEnum itemMenuEnum : ITEMS_MENU_CONTENT) {
            ITEMS.add(new Item(itemMenuEnum.id, itemMenuEnum.resIconMenu, itemMenuEnum.resMessage));
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
