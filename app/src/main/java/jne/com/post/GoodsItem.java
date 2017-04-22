package jne.com.post;

import java.util.ArrayList;
import java.util.Random;

public class GoodsItem{
    public int id;
    public int typeId;
    public int rating;
    public String name;
    public String typeName;
    public double price;
    public int count;

    public GoodsItem(int id, double price, String name, int typeId, String typeName) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.typeId = typeId;
        this.typeName = typeName;
        rating = new Random().nextInt(5)+1;
    }

    private static ArrayList<GoodsItem> goodsList;
    private static ArrayList<GoodsItem> typeList;
    private static ArrayList<String> typeNameList;

    private static void initData(){
        goodsList = new ArrayList<>();
        typeList = new ArrayList<>();
        typeNameList = new ArrayList<String>();
        typeNameList.add("饮料   [全新上架]");
        typeNameList.add("小吃   [汇聚全国各地小吃]");
        typeNameList.add("盒饭");
        typeNameList.add("汉堡[已加入肯德基豪华套餐]");
        typeNameList.add("日用品");
        typeNameList.add("零食   [爆款零食全新上架]");
        typeNameList.add("药品   [用药请咨询工作人员]");
        typeNameList.add("药品");
        typeNameList.add("药品");

        GoodsItem item = null;
        for(int i=1;i<8;i++){
            for(int j=1;j<10;j++){
                item = new GoodsItem(100*i+j,Math.random()*20,"商品"+(100*i+j),i,typeNameList.get(i-1));
                goodsList.add(item);
            }
            typeList.add(item);
        }
    }

    public static ArrayList<GoodsItem> getGoodsList(){
        if(goodsList==null){
            initData();
        }
        return goodsList;
    }
    public static ArrayList<GoodsItem> getTypeList(){
        if(typeList==null){
            initData();
        }
        return typeList;
    }
}
