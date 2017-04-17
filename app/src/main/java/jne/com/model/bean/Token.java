package jne.com.model.bean;

import com.google.gson.annotations.SerializedName;


public class Token {

    @SerializedName("access_token")
    String accessToken;

    @SerializedName("user_id")
    String userId;

    @SerializedName("expires_in")
    int expiresin;

    @SerializedName("token_type")
    String tokenType;

    @SerializedName("scope")
    String scope;

    @SerializedName("refresh_token")
    String refreshToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getExpiresin() {
        return expiresin;
    }

    public void setExpiresin(int expiresin) {
        this.expiresin = expiresin;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
