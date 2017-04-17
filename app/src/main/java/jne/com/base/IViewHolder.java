package jne.com.base;

import jne.com.util.ViewEventListener;

public interface IViewHolder<T> {

    void setViewEventListener(ViewEventListener<T> viewEventListener);

    void setItem(T item);

    void setPosition(int position);
}
