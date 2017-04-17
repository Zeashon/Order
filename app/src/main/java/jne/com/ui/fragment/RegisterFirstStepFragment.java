package jne.com.ui.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import jne.com.R;
import jne.com.base.BaseController;
import jne.com.base.BaseFragment;
import jne.com.context.AppContext;
import jne.com.controller.UserController;
import jne.com.model.bean.ResponseError;
import jne.com.ui.Display;
import jne.com.util.ContentView;
import jne.com.util.RegisterStep;
import jne.com.util.StringUtil;
import jne.com.util.SystemUtil;
import jne.com.util.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnTextChanged;


@ContentView(R.layout.fragment_register_first_step)
public class RegisterFirstStepFragment extends BaseFragment<UserController.UserUiCallbacks>
        implements UserController.RegisterFirstStepUi {

    @Bind(R.id.edit_mobile)
    EditText mMobileEdit;

    @Bind(R.id.btn_clear_mobile)
    ImageView mClearMobileBtn;

    @Bind(R.id.btn_send_code)
    Button mSendCodeBtn;

    @Override
    protected BaseController getController() {
        return AppContext.getContext().getMainController().getUserController();
    }

    @Override
    public void sendCodeFinish() {
        cancelLoading();
        mSendCodeBtn.setEnabled(true);
        // 跳转到步骤2页面
        Display display = getDisplay();
        if (display != null) {
            display.showRegisterStep(RegisterStep.STEP_SECOND,
                    mMobileEdit.getText().toString().trim());
        }
    }

    @Override
    public void onResponseError(ResponseError error) {
        cancelLoading();
        mSendCodeBtn.setEnabled(true);
        ToastUtil.showToast(error.getMessage());
    }

    @OnTextChanged(R.id.edit_mobile)
    public void onMobileTextChange(CharSequence s) {
        int visible = StringUtil.isEmpty(s.toString()) ? View.GONE : View.VISIBLE;
        mClearMobileBtn.setVisibility(visible);
    }

    @OnClick({R.id.btn_clear_mobile, R.id.btn_send_code})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_clear_mobile:
                mMobileEdit.setText("");
                break;
            case R.id.btn_send_code:
                sendSmsCode();
                break;
        }
    }

    /**
     * 执行发送验证码的操作
     */
    private void sendSmsCode() {
        // 隐藏软键盘
        SystemUtil.hideKeyBoard(getContext());

        // 验证手机号是否为空
        final String mobile = mMobileEdit.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            ToastUtil.showToast(R.string.toast_error_empty_phone);
            return;
        }

        showLoading(R.string.label_being_something);
        // 避免重复点击
        mSendCodeBtn.setEnabled(false);
        // 发起发送验证码的API请求
        getCallbacks().sendCode(mobile);
    }
}