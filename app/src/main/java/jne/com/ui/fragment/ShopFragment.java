package jne.com.ui.fragment;

import jne.com.R;
import jne.com.base.BaseController;
import jne.com.base.BaseListFragment;
import jne.com.context.AppContext;
import jne.com.controller.BusinessController;
import jne.com.model.bean.Business;
import jne.com.ui.adapter.BusinessListAdapter;

import static jne.com.util.Constants.ClickType.CLICK_TYPE_BUSINESS_CLICKED;

public class ShopFragment extends BaseListFragment<Business, BusinessController.BusinessUiCallbacks>
        implements BusinessController.BusinessListUi {

    @Override
    protected BaseController getController() {
        return AppContext.getContext().getMainController().getBusinessController();
    }

    @Override
    public Business getRequestParameter() {
        return null;
    }

    @Override
    protected String getTitle() {
        return getString(R.string.title_shop);
    }

    @Override
    protected boolean isShowBack() {
        return false;
    }

    @Override
    protected BusinessListAdapter getAdapter() {
        return new BusinessListAdapter();
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
    protected void onItemClick(int actionId, Business business) {
        switch (actionId) {
            case CLICK_TYPE_BUSINESS_CLICKED:
                getCallbacks().showBusiness(business);
                break;
        }
    }
}
