package jne.com.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import jne.com.R;
import jne.com.base.BaseActivity;
import jne.com.base.BaseController;
import jne.com.context.AppContext;
import jne.com.controller.OrderController;
import jne.com.model.bean.Order;
import jne.com.model.bean.PaymentPlatform;
import jne.com.model.bean.ResponseError;
import jne.com.ui.Display;
import jne.com.ui.adapter.PlatformListAdapter;
import jne.com.util.ContentView;
import jne.com.util.ToastUtil;
import jne.com.util.ViewEventListener;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;


@ContentView(R.layout.activity_payment)
public class PaymentActivity extends BaseActivity<OrderController.OrderUiCallbacks>
        implements OrderController.OrderPaymentUi {

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Bind(R.id.btn_payment)
    Button mBtnPayment;

    private PlatformListAdapter mAdapter;

    private Order mOrder;

    @Override
    protected BaseController getController() {
        return AppContext.getContext().getMainController().getOrderController();
    }

    @Override
    protected void handleIntent(Intent intent, Display display) {
        mOrder = (Order) intent.getSerializableExtra(Display.PARAM_OBJ);
    }

    @Override
    protected void initializeViews(Bundle savedInstanceState) {
        mAdapter = new PlatformListAdapter();
        mAdapter.setSelectedPosition(0);
        mAdapter.setViewEventListener(new ViewEventListener<PaymentPlatform>() {
            @Override
            public void onViewEvent(int actionId, PaymentPlatform item, int position, View view) {
                mAdapter.setSelectedPosition(position);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public String getRequestParameter() {
        return mOrder.getId();
    }

    @Override
    public void setPlatforms(List<PaymentPlatform> platforms) {
        mAdapter.setItems(platforms);
    }

    @Override
    public void onFinishPayment(Order order) {
        cancelLoading();
        ToastUtil.showToast(R.string.toast_success_online_payment);
        getCallbacks().showOrderDetail(order);
    }

    @Override
    public void onResponseError(ResponseError error) {
        cancelLoading();
        mBtnPayment.setEnabled(true);
        ToastUtil.showToast(error.getMessage());
    }

    @OnClick({R.id.btn_payment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_payment:
                payment();
                break;
        }
    }

    /**
     * 执行付款操作
     */
    private void payment() {
        // 获取选择的支付平台
        int selectedPosition = mAdapter.getSelectedPosition();
        PaymentPlatform platform = mAdapter.getItem(selectedPosition);

        showLoading(R.string.label_being_simulate_payment);
        mBtnPayment.setEnabled(false);
        getCallbacks().payment(mOrder, platform.getId());
    }

    @Override
    public void onBackPressed() {
        Display display = getDisplay();
        if (display != null) {
            display.showOrderDetail(mOrder);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Display display = getDisplay();
                if (display != null) {
                    display.showOrderDetail(mOrder);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}