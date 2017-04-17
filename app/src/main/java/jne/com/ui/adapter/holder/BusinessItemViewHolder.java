package jne.com.ui.adapter.holder;

import android.view.View;
import android.widget.TextView;

import jne.com.R;
import jne.com.base.BaseViewHolder;
import jne.com.model.ShoppingCart;
import jne.com.model.bean.Business;
import jne.com.util.StringFetcher;
import jne.com.widget.PicassoImageView;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bingoogolapple.badgeview.BGABadgeFrameLayout;

import static jne.com.util.Constants.ClickType.CLICK_TYPE_BUSINESS_CLICKED;

public class BusinessItemViewHolder extends BaseViewHolder<Business> {

    @Bind(R.id.list_item)
    View mListItem;

    @Bind(R.id.badge_view)
    BGABadgeFrameLayout mBadgeView;

    @Bind(R.id.img_photo)
    PicassoImageView mPhotoImg;

    @Bind(R.id.txt_name)
    TextView mNameTxt;

    @Bind(R.id.txt_month_sales)
    TextView mMonthSalesTxt;

    @Bind(R.id.txt_content)
    TextView mMultiContentTxt;

    public BusinessItemViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(Business business) {
        mPhotoImg.loadBusinessPhoto(business);
        mNameTxt.setText(business.getName());
        mMonthSalesTxt.setText(StringFetcher.getString(R.string.label_month_sales,
                business.getMonthSales()));
        mMultiContentTxt.setText(StringFetcher.getString(R.string.label_business_multi_content,
                String.valueOf(business.getMinPrice()),
                String.valueOf(business.getShippingFee()),
                String.valueOf(business.getShippingTime())));
        ShoppingCart shoppingCart = ShoppingCart.getInstance();
        int count = shoppingCart.getTotalQuantity();
        if (business.getId().equals(shoppingCart.getBusinessId())
                && count > 0) {
            mBadgeView.showTextBadge(String.valueOf(count));
        } else {
            mBadgeView.hiddenBadge();
        }
    }

    @OnClick(R.id.list_item)
    public void onClick(View view) {
        notifyItemAction(CLICK_TYPE_BUSINESS_CLICKED);
    }
}
