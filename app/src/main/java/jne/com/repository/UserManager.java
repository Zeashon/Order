package jne.com.repository;

import jne.com.context.AppConfig;
import jne.com.model.bean.User;
import jne.com.util.PreferenceUtil;
import com.tencent.bugly.crashreport.CrashReport;

public class UserManager {

    private static final Class<User> CLAZZ = User.class;

    private User user;

    public void saveUser(User user) {
        if (user == null) {
            return;
        }
        this.user = user;
        PreferenceUtil.set(CLAZZ.getName(), this.user);
        if (!AppConfig.DEBUG) {
            try {
                CrashReport.setUserId(user.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public User getUser() {
        if (user == null) {
            user = PreferenceUtil.getObject(CLAZZ.getName(), CLAZZ);
        }

        return user;
    }

    public String getToken() {
        if (getUser() != null) {
            return getUser().getToken();
        }
        return null;
    }

    public void clear() {
        user = null;
        PreferenceUtil.set(CLAZZ.getName(), "");
    }
}
