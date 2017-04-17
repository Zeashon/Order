package jne.com.ui.adapter;

import android.view.View;

import jne.com.R;
import jne.com.base.BaseListAdapter;
import jne.com.model.bean.ProductCategory;
import jne.com.ui.adapter.holder.ProductCategoryItemViewHolder;

public class ProductCategoryListAdapter extends BaseListAdapter<ProductCategory, ProductCategoryItemViewHolder> {

    @Override
    protected int getViewLayoutId() {
        return R.layout.layout_product_category_item;
    }

    @Override
    protected ProductCategoryItemViewHolder createViewHolder(View view) {
        return new ProductCategoryItemViewHolder(view);
    }

    @Override
    protected void showData(ProductCategoryItemViewHolder holder, int position) {
        ProductCategory category = getItem(position);
        holder.bind(category);
    }
}
