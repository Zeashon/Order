package jne.com.model.event;

import jne.com.model.bean.User;


public class AccountChangedEvent {

    private User user;

    public AccountChangedEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}