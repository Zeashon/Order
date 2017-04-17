package jne.com.ui.adapter.holder;

import android.view.View;
import android.widget.TextView;

import jne.com.base.BaseViewHolder;
import jne.com.model.bean.SettleResult;

import butterknife.Bind;


public class SendTimeItemViewHolder extends BaseViewHolder<SettleResult.BookingTime> {

    @Bind(android.R.id.text1)
    TextView mTitleTxt;

    public SendTimeItemViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(SettleResult.BookingTime data) {
        mTitleTxt.setText(data.getViewTime());
    }
}
