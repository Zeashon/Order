package jne.com.ui.adapter.holder;

import android.view.View;
import android.widget.TextView;

import jne.com.R;
import jne.com.base.BaseViewHolder;
import jne.com.model.ShoppingCart;
import jne.com.model.bean.ProductCategory;

import butterknife.Bind;
import cn.bingoogolapple.badgeview.BGABadgeFrameLayout;


public class ProductCategoryItemViewHolder extends BaseViewHolder<ProductCategory> {

    @Bind(R.id.txt_name)
    TextView mNameTxt;

    @Bind(R.id.badge_view)
    BGABadgeFrameLayout mBadgeView;

    public ProductCategoryItemViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(ProductCategory category) {
        mNameTxt.setText(category.getName());

        int count = ShoppingCart.getInstance().getQuantityForCategory(category);
        if (count > 0) {
            mBadgeView.showTextBadge(String.valueOf(count));
        } else {
            mBadgeView.hiddenBadge();
        }
    }
}
