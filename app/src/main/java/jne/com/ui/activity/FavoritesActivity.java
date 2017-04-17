package jne.com.ui.activity;

import jne.com.base.BaseController;
import jne.com.base.BaseListActivity;
import jne.com.context.AppContext;
import jne.com.controller.UserController;
import jne.com.model.bean.Favorite;
import jne.com.ui.adapter.FavoriteListAdapter;

import static jne.com.util.Constants.ClickType.CLICK_TYPE_BUSINESS_CLICKED;


public class FavoritesActivity extends BaseListActivity<Favorite, UserController.UserUiCallbacks>
        implements UserController.UserFavoriteListUi {

    @Override
    protected BaseController getController() {
        return AppContext.getContext().getMainController().getUserController();
    }

    @Override
    protected FavoriteListAdapter getAdapter() {
        return new FavoriteListAdapter();
    }

    @Override
    protected void refreshPage() {
        getCallbacks().refresh();
    }

    @Override
    protected void nextPage() {
        getCallbacks().nextPage();
    }

    @Override
    protected void onItemClick(int actionId, Favorite favorite) {
        switch (actionId) {
            case CLICK_TYPE_BUSINESS_CLICKED:
                getCallbacks().showBusiness(favorite.getBusiness());
                break;
        }
    }
}
