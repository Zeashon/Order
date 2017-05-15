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

public class Order {
    public String id; // post id time  20160506210133
    public String customName; // user name zeashon
    public String phoneNum;//phone number  18659194822
    public String country; // station 南京站
    public String finishTime;// finish time  2016.01.01
    public String finishPlace;// 03车D1234
    public String postDetail;// 需求
    public String remuneration;//诱惑
    public int type;//1&3：自己的帖子,未完成&已完成  0：未标记  4：收藏的帖子

    public Order() {
    }

    public Order(String id, String customName, String phoneNum, String country ,String finishTime ,String finishPlace, String postDetail, String remuneration, int type) {
        this.id = id;
        this.customName = customName;
        this.phoneNum = phoneNum;
        this.country = country;
        this.finishTime = finishTime;
        this.finishPlace = finishPlace;
        this.postDetail = postDetail;
        this.remuneration = remuneration;
        this.type = type;
    }
}
