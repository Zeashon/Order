package jne.com.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Setting {

    @SerializedName("common_remarks")
    List<String> commonRemarks;

    @SerializedName("payment_platforms")
    List<PaymentPlatform> paymentPlatforms;

    public List<String> getCommonRemarks() {
        return commonRemarks;
    }

    public void setCommonRemarks(List<String> commonRemarks) {
        this.commonRemarks = commonRemarks;
    }

    public List<PaymentPlatform> getPaymentPlatforms() {
        return paymentPlatforms;
    }

    public void setPaymentPlatforms(List<PaymentPlatform> paymentPlatforms) {
        this.paymentPlatforms = paymentPlatforms;
    }
}
