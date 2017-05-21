package jne.com.post;

//public class Order {
//    public int id;
//    public String customName;
//    public int orderPrice;
//    public String country;
//
//    public Order() {
//    }
//
//    public Order(int id, String customName, int orderPrice, String country) {
//        this.id = id;
//        this.customName = customName;
//        this.orderPrice = orderPrice;
//        this.country = country;
//    }
//}

public class Advice {
    public String id; // id time  20160506210133
    public String user; // 用户
    public String title;//标题
    public String content; // 内容
    public int state;//0 ,1

    public Advice() {
    }

    public Advice(String id, String user, String title, String content , int state) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.state = state;
    }
}
