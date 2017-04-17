package jne.com.ui.adapter.holder;

import android.view.View;
import android.widget.TextView;

import jne.com.R;
import jne.com.base.BaseViewHolder;
import jne.com.model.bean.ShoppingEntity;
import jne.com.util.StringFetcher;

import butterknife.Bind;


public class ShoppingProductItemViewHolder extends BaseViewHolder<ShoppingEntity> {

    @Bind(R.id.txt_name)
    TextView nameTxt;

    @Bind(R.id.txt_quantity)
    TextView quantityTxt;

    @Bind(R.id.txt_price)
    TextView priceTxt;

    public ShoppingProductItemViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(ShoppingEntity item) {
        nameTxt.setText(item.getName());
        quantityTxt.setText(StringFetcher.getString(R.string.label_quantity,
                item.getQuantity()));
        priceTxt.setText(StringFetcher.getString(R.string.label_price,
                item.getTotalPrice()));
    }
}
