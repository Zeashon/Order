package jne.com.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import jne.com.R;
import jne.com.base.BaseActivity;
import jne.com.base.BaseController;
import jne.com.context.AppContext;
import jne.com.controller.UserController;
import jne.com.model.bean.ResponseError;
import jne.com.ui.Display;
import jne.com.util.ContentView;
import jne.com.util.StringUtil;
import jne.com.util.SystemUtil;
import jne.com.util.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnTextChanged;


@ContentView(R.layout.activity_login_pre)
public class LoginActivity extends BaseActivity<UserController.UserUiCallbacks>
        implements UserController.UserLoginUi {

    @Bind(R.id.et_user_account)
    EditText mEtAccount;

    @Bind(R.id.et_user_password)
    EditText mEtPassword;

    @Bind(R.id.iv_delete_account)
    ImageView mIvDeleteAccount;

    @Bind(R.id.iv_delete_password)
    ImageView mIvDeletePassword;

    @Bind(R.id.btn_login)
    Button mBtnLogin;

    @Bind(R.id.tv_forget_password)
    TextView mTvForgetPassword;

    @Bind(R.id.tv_go_to_register)
    TextView mTvGoToRegister;

    @Override
    protected BaseController getController() {
        return AppContext.getContext().getMainController().getUserController();
    }

    @Override
    public void onResponseError(ResponseError error) {
        cancelLoading();
        mBtnLogin.setEnabled(true);
        ToastUtil.showToast(error.getMessage());
    }

    @Override
    public void userLoginFinish() {
        cancelLoading();
        Display display = getDisplay();
        if (display != null) {
            display.finishActivity();
        }
    }

    @OnTextChanged(R.id.et_user_account)
    public void onAccountTextChange(CharSequence s) {
        int visible = StringUtil.isEmpty(s.toString()) ? View.GONE : View.VISIBLE;
        mIvDeleteAccount.setVisibility(visible);
    }

    @OnTextChanged(R.id.et_user_password)
    public void onPasswordTextChange(CharSequence s) {
        int visible = StringUtil.isEmpty(s.toString()) ? View.GONE : View.VISIBLE;
        mIvDeletePassword.setVisibility(visible);
    }

    @OnClick({R.id.iv_delete_account, R.id.iv_delete_password, R.id.btn_login, R.id.tv_forget_password, R.id.tv_go_to_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_delete_account:
                mEtAccount.setText("");
                break;
            case R.id.iv_delete_password:
                mEtPassword.setText("");
                break;
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_forget_password:
                ToastUtil.showToast("该功能还未开发");
                break;
            case R.id.tv_go_to_register:
                getCallbacks().showRegister();
                break;
        }
    }

    /**
     * 执行登录操作
     */
    private void login() {
        // 隐藏软键盘
        SystemUtil.hideKeyBoard(this);

        // 验证用户名是否为空
        final String account = mEtAccount.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            ToastUtil.showToast(R.string.toast_error_empty_account);
            return;
        }
        // 验证密码是否为空
        final String password = mEtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            ToastUtil.showToast(R.string.toast_error_empty_password);
            return;
        }
        // 禁用登录按钮,避免重复点击
        mBtnLogin.setEnabled(false);
        // 显示提示对话框
        showLoading(R.string.label_being_something);
        // 发起登录的网络请求
        getCallbacks().login(account, password);
    }
}
