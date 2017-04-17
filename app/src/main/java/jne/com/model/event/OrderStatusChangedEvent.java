package jne.com.model.event;


public class OrderStatusChangedEvent extends BaseArgumentEvent<String> {

    public OrderStatusChangedEvent(int callingId, String orderId) {
        super(callingId, orderId);
    }

    public String getOrderId() {
        return arg;
    }
}