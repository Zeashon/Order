package jne.com.ui.adapter.holder;

import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import jne.com.R;
import jne.com.base.BaseViewHolder;
import jne.com.model.bean.PaymentPlatform;
import jne.com.widget.PicassoImageView;

import butterknife.Bind;
import butterknife.OnClick;

import static jne.com.util.Constants.ClickType.CLICK_TYPE_PAYMENT_PLATFORM_CLICKED;


public class PlatformItemViewHolder extends BaseViewHolder<PaymentPlatform> {

    @Bind(R.id.list_item)
    View listItem;

    @Bind(R.id.img_icon)
    PicassoImageView platformIcon;

    @Bind(R.id.txt_name)
    TextView platformName;

    @Bind(R.id.radio)
    RadioButton radioBtn;

    @Bind(R.id.divider)
    View divider;

    public PlatformItemViewHolder(View view) {
        super(view);
    }

    public void bind(boolean isSelected, boolean isShowDivider) {
        platformIcon.loadPlatformIcon(mItem);
        platformName.setText(mItem.getName());
        radioBtn.setChecked(isSelected);
        divider.setVisibility(isShowDivider ? View.VISIBLE : View.GONE);
    }

    @OnClick(R.id.list_item)
    public void onClick() {
        notifyItemAction(CLICK_TYPE_PAYMENT_PLATFORM_CLICKED);
    }
}
