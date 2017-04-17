package jne.com.model.event;

public class UiCausedEvent {

    public final int callingId;

    public UiCausedEvent(int callingId) {
        this.callingId = callingId;
    }
}