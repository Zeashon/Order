package jne.com.repository;

import jne.com.model.bean.PaymentPlatform;
import jne.com.model.bean.Setting;
import jne.com.util.PreferenceUtil;

import java.util.List;

public class SettingManager {

    private static final Class<Setting> CLAZZ = Setting.class;

    private Setting mSetting;

    public void saveOrUpdate(Setting setting) {
        if (setting == null) {
            return;
        }
        mSetting = setting;
        PreferenceUtil.set(CLAZZ.getName(), mSetting);
    }

    public Setting getSetting() {
        if (mSetting == null) {
            mSetting = PreferenceUtil.getObject(CLAZZ.getName(), CLAZZ);
        }

        return mSetting;
    }

    public List<String> getCommonRemarks() {
        if (getSetting() != null) {
            return getSetting().getCommonRemarks();
        }
        return null;
    }

    public List<PaymentPlatform> getPaymentPlatform() {
        if (getSetting() != null) {
            return getSetting().getPaymentPlatforms();
        }
        return null;
    }

    public void clear() {
        mSetting = null;
        PreferenceUtil.set(CLAZZ.getName(), "");
    }
}
