package jne.com.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import jne.com.R;
import jne.com.base.BaseAdapter;
import jne.com.model.bean.Address;
import jne.com.ui.adapter.holder.AddressItemViewHolder;


public class AddressListAdapter extends BaseAdapter<Address> {

    @Override
    public int getViewLayoutId(int viewType) {
        return R.layout.adapter_address_item;
    }

    @Override
    public AddressItemViewHolder createViewHolder(View view, int viewType) {
        return new AddressItemViewHolder(view);
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder holder, Address address, int position) {
        if (holder instanceof AddressItemViewHolder) {
            ((AddressItemViewHolder) holder).bind(address);
        }
    }
}