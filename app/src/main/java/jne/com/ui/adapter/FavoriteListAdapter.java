package jne.com.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import jne.com.R;
import jne.com.base.BaseAdapter;
import jne.com.model.bean.Favorite;
import jne.com.ui.adapter.holder.FavoriteItemViewHolder;


public class FavoriteListAdapter extends BaseAdapter<Favorite> {

    @Override
    public int getViewLayoutId(int viewType) {
        return R.layout.adapter_favorite_item;
    }

    @Override
    public FavoriteItemViewHolder createViewHolder(View view, int viewType) {
        return new FavoriteItemViewHolder(view);
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder holder, Favorite item, int position) {
        if (holder instanceof FavoriteItemViewHolder) {
            ((FavoriteItemViewHolder) holder).bind(item);
        }
    }
}
