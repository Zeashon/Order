package jne.com.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import jne.com.R;
import jne.com.base.BaseAdapter;
import jne.com.model.bean.Order;
import jne.com.ui.adapter.holder.OrderItemViewHolder;


public class OrderListAdapter extends BaseAdapter<Order> {

    @Override
    public int getViewLayoutId(int viewType) {
        return R.layout.adapter_order_list_item;
    }

    @Override
    public OrderItemViewHolder createViewHolder(View view, int viewType) {
        return new OrderItemViewHolder(view);
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder holder, Order order, int position) {
        if (holder instanceof OrderItemViewHolder) {
            ((OrderItemViewHolder) holder).bind(order);
        }
    }
}
