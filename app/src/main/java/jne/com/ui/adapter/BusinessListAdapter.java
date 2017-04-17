package jne.com.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import jne.com.R;
import jne.com.base.BaseAdapter;
import jne.com.model.bean.Business;
import jne.com.ui.adapter.holder.BusinessItemViewHolder;


public class BusinessListAdapter extends BaseAdapter<Business> {

    @Override
    public int getViewLayoutId(int viewType) {
        return R.layout.adapter_business_item;
    }

    @Override
    public BusinessItemViewHolder createViewHolder(View view, int viewType) {
        return new BusinessItemViewHolder(view);
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder holder, Business business, int position) {
        if (holder instanceof BusinessItemViewHolder) {
            ((BusinessItemViewHolder) holder).bind(business);
        }
    }
}
