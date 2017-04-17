package jne.com.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import jne.com.R;
import jne.com.base.BaseAdapter;
import jne.com.model.bean.ShoppingEntity;
import jne.com.ui.adapter.holder.ShoppingCartItemViewHolder;

public class ShoppingCartListAdapter extends BaseAdapter<ShoppingEntity> {

    @Override
    public int getViewLayoutId(int viewType) {
        return R.layout.layout_shopping_cart_item;
    }

    @Override
    public ShoppingCartItemViewHolder createViewHolder(View view, int viewType) {
        return new ShoppingCartItemViewHolder(view);
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder holder, ShoppingEntity entity, int position) {
        if (holder instanceof ShoppingCartItemViewHolder) {
            ((ShoppingCartItemViewHolder) holder).bind(entity);
        }
    }
}