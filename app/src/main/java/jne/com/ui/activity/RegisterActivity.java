package jne.com.ui.activity;

import android.content.Intent;

import jne.com.R;
import jne.com.base.BaseActivity;
import jne.com.base.BaseController;
import jne.com.context.AppContext;
import jne.com.controller.UserController;
import jne.com.ui.Display;
import jne.com.util.ContentView;
import jne.com.util.RegisterStep;

@ContentView(R.layout.activity_register)
public class RegisterActivity extends BaseActivity<UserController.UserUiCallbacks>
        implements UserController.UserRegisterUi {

    @Override
    protected BaseController getController() {
        return AppContext.getContext().getMainController().getUserController();
    }

    @Override
    protected void handleIntent(Intent intent, Display display) {
        if (!display.hasMainFragment()) {
            display.showRegisterStep(RegisterStep.STEP_FIRST, null);
        }
    }
}
