package com.tristian.monumentabaernecessities.api;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Items {


    private static final List<MonumentaItem> items;


    static void addItem(MonumentaItem item) {
        items.add(item);
    }


    public static List<MonumentaItem> getAllItems() {
        return items;
    }

    static {
        items = new CopyOnWriteArrayList<>();
    }

}
