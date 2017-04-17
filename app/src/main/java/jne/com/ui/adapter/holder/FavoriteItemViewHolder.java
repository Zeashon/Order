package jne.com.ui.adapter.holder;

import android.view.View;
import android.widget.TextView;

import jne.com.R;
import jne.com.base.BaseViewHolder;
import jne.com.model.bean.Business;
import jne.com.model.bean.Favorite;
import jne.com.util.StringFetcher;
import jne.com.widget.PicassoImageView;

import butterknife.Bind;

import static jne.com.util.Constants.ClickType.CLICK_TYPE_BUSINESS_CLICKED;


public class FavoriteItemViewHolder extends BaseViewHolder<Favorite> {

    @Bind(R.id.img_photo)
    PicassoImageView mPhotoImg;

    @Bind(R.id.txt_name)
    TextView mNameTxt;

    @Bind(R.id.txt_month_sales)
    TextView mMonthSalesTxt;

    @Bind(R.id.txt_content)
    TextView mMultiContentTxt;

    public FavoriteItemViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(Favorite favorite) {
        Business business = favorite.getBusiness();
        if (business != null) {
            mPhotoImg.loadBusinessPhoto(business);
            mNameTxt.setText(business.getName());
            mMonthSalesTxt.setText(StringFetcher.getString(R.string.label_month_sales,
                    business.getMonthSales()));
            mMultiContentTxt.setText(StringFetcher.getString(R.string.label_business_multi_content,
                    String.valueOf(business.getMinPrice()),
                    String.valueOf(business.getShippingFee()),
                    String.valueOf(business.getShippingTime())));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    notifyItemAction(CLICK_TYPE_BUSINESS_CLICKED);
                }
            });
        }
    }
}
