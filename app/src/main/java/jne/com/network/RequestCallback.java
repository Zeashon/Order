package jne.com.network;

import android.util.Log;

import jne.com.model.bean.ResponseError;

import rx.Subscriber;


public abstract class RequestCallback<T> extends Subscriber<T> {

    private static final String TAG = "RequestCallback";

    @Override
    public final void onNext(T t) {
        onResponse(t);
    }

    @Override
    public final void onError(Throwable throwable) {
        if (throwable instanceof ResponseError) {
            onFailure((ResponseError) throwable);
        } else {
            Log.e(TAG, "throwable isn't instance of ResponseError");
        }
    }

    @Override
    public void onStart() {

    }

    public void onResponse(T response) {}

    public void onFailure(ResponseError error) {}

    @Override
    public void onCompleted() {}
}