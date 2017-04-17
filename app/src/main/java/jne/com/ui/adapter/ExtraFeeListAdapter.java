package jne.com.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import jne.com.R;
import jne.com.base.BaseAdapter;
import jne.com.model.bean.CartInfo;
import jne.com.ui.adapter.holder.ExtraFeeItemViewHolder;


public class ExtraFeeListAdapter extends BaseAdapter<CartInfo.ExtraFee> {

    @Override
    public int getViewLayoutId(int viewType) {
        return R.layout.adapter_extra_fee_item;
    }

    @Override
    public RecyclerView.ViewHolder createViewHolder(View view, int viewType) {
        return new ExtraFeeItemViewHolder(view);
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder holder, CartInfo.ExtraFee extraFee, int position) {
        if (holder instanceof ExtraFeeItemViewHolder) {
            ((ExtraFeeItemViewHolder) holder).bind(extraFee);
        }
    }
}
