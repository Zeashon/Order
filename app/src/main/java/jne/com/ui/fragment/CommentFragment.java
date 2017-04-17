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

@ContentView(R.layout.fragment_comment)
public class CommentFragment extends BaseFragment<BusinessController.BusinessUiCallbacks>
        implements BusinessController.CommentListUi {

    private Business mBusiness;

    public static CommentFragment create(Business business) {
        CommentFragment fragment = new CommentFragment();
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
