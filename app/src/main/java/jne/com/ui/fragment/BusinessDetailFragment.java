package jne.com.ui.fragment;

import android.os.Bundle;

import jne.com.R;
import jne.com.base.BaseController;
import jne.com.base.BaseFragment;
import jne.com.context.AppContext;
import jne.com.controller.BusinessController;
import jne.com.model.bean.Business;
import jne.com.ui.Display;
import jne.com.util.ContentView;

@ContentView(R.layout.fragment_business_detail)
public class BusinessDetailFragment extends BaseFragment<BusinessController.BusinessUiCallbacks>
        implements BusinessController.BusinessProfileUi {

    private Business mBusiness;

    public static BusinessDetailFragment create(Business business) {
        BusinessDetailFragment fragment = new BusinessDetailFragment();
        if (business != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(Display.PARAM_OBJ, business);
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    protected BaseController getController() {
        return AppContext.getContext().getMainController().getBusinessController();
    }

    @Override
    protected void handleArguments(Bundle arguments) {
        if (arguments != null) {
            mBusiness = (Business) arguments.getSerializable(Display.PARAM_OBJ);
        }
    }

    @Override
    public Business getRequestParameter() {
        return mBusiness;
    }
}