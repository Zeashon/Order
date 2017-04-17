package jne.com.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import jne.com.R;
import jne.com.base.BaseAdapter;
import jne.com.model.bean.CartInfo;
import jne.com.ui.adapter.holder.DiscountInfoItemViewHolder;


public class DiscountInfoListAdapter extends BaseAdapter<CartInfo.DiscountInfo> {

    @Override
    public int getViewLayoutId(int viewType) {
        return R.layout.adapter_discount_info_item;
    }

    @Override
    public RecyclerView.ViewHolder createViewHolder(View view, int viewType) {
        return new DiscountInfoItemViewHolder(view);
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder holder, CartInfo.DiscountInfo discountInfo, int position) {
        if (holder instanceof DiscountInfoItemViewHolder) {
            ((DiscountInfoItemViewHolder) holder).bind(discountInfo);
        }
    }
}
