package jne.com.ui.activity;

import android.content.Intent;

import jne.com.R;
import jne.com.base.BaseActivity;
import jne.com.base.BaseController;
import jne.com.context.AppContext;
import jne.com.controller.UserController;
import jne.com.ui.Display;
import jne.com.util.ContentView;


@ContentView(R.layout.activity_user_profile)
public class UserProfileActivity extends BaseActivity<UserController.UserUiCallbacks>
        implements UserController.UserUi {

    @Override
    protected BaseController getController() {
        return AppContext.getContext().getMainController().getUserController();
    }

    @Override
    protected void handleIntent(Intent intent, Display display) {
        if (!display.hasMainFragment()) {
            display.showUserProfileFragment();
        }
    }
}
