package jne.com.util;

import jne.com.R;
import jne.com.context.AppContext;
import jne.com.controller.BusinessController;

public class StringFetcher {

    public static String getString(int id) {
        return AppContext.getContext().getString(id);
    }

    public static String getString(int id, Object... format) {
        return AppContext.getContext().getString(id, format);
    }

    public static String getString(BusinessController.BusinessTab tab) {
        switch (tab) {
            case PRODUCT:
                return getString(R.string.title_food);
            case COMMENT:
                return getString(R.string.title_comment);
            case DETAIL:
                return getString(R.string.title_restaurant);
        }
        return getString(R.string.app_name);
    }
}