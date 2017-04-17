package jne.com.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import jne.com.R;
import jne.com.base.BaseAdapter;
import jne.com.model.bean.PaymentPlatform;
import jne.com.ui.adapter.holder.PlatformItemViewHolder;


public class PlatformListAdapter extends BaseAdapter<PaymentPlatform> {

    private int mSelectedPosition = -1;

    public int getSelectedPosition() {
        return mSelectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        mSelectedPosition = selectedPosition;
        notifyDataSetChanged();
    }

    @Override
    public int getViewLayoutId(int viewType) {
        return R.layout.adapter_platform_item;
    }

    @Override
    public PlatformItemViewHolder createViewHolder(View view, int viewType) {
        return new PlatformItemViewHolder(view);
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder holder, PaymentPlatform item, int position) {
        if (holder instanceof PlatformItemViewHolder) {
            boolean isShowDivider = position != getItemCount() - 1;
            boolean isChecked = position == mSelectedPosition;
            ((PlatformItemViewHolder) holder).bind(isChecked, isShowDivider);
        }
    }
}
