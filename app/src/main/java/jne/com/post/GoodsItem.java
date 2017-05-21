package jne.com.post;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

import java.util.ArrayList;
import java.util.Random;

import jne.com.R;

public class GoodsItem {
    public int id;
    public int typeId;
    public int rating;
    public String name;
    public String typeName;
    public double price;
    public int count;
    public int imgSrc;

    public GoodsItem(int id, double price, String name, int typeId, String typeName) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.typeId = typeId;
        this.typeName = typeName;
        rating = new Random().nextInt(5) + 1;
    }

    public GoodsItem(int id, String name, double price, int count) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.count = count;
    }

    private static ArrayList<GoodsItem> goodsList;
    private static ArrayList<GoodsItem> typeList;
    private static ArrayList<String> typeNameList;
    private static ArrayList<Integer> imgSrcList;

    private static void initData() {
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
        imgSrcList = new ArrayList<Integer>();
        if (true) {//Write img
            imgSrcList.add(R.drawable.shop_product_101);
            imgSrcList.add(R.drawable.shop_product_102);
            imgSrcList.add(R.drawable.shop_product_103);
            imgSrcList.add(R.drawable.shop_product_104);
            imgSrcList.add(R.drawable.shop_product_105);
            imgSrcList.add(R.drawable.shop_product_201);
            imgSrcList.add(R.drawable.shop_product_202);
            imgSrcList.add(R.drawable.shop_product_203);
            imgSrcList.add(R.drawable.shop_product_204);
            imgSrcList.add(R.drawable.shop_product_205);
            imgSrcList.add(R.drawable.shop_product_301);
            imgSrcList.add(R.drawable.shop_product_302);
            imgSrcList.add(R.drawable.shop_product_303);
            imgSrcList.add(R.drawable.shop_product_304);
            imgSrcList.add(R.drawable.shop_product_305);
            imgSrcList.add(R.drawable.shop_product_400);
            imgSrcList.add(R.drawable.shop_product_400);
            imgSrcList.add(R.drawable.shop_product_400);
            imgSrcList.add(R.drawable.shop_product_400);
            imgSrcList.add(R.drawable.shop_product_400);
            imgSrcList.add(R.drawable.shop_product_501);
            imgSrcList.add(R.drawable.shop_product_502);
            imgSrcList.add(R.drawable.shop_product_503);
            imgSrcList.add(R.drawable.shop_product_504);
            imgSrcList.add(R.drawable.shop_product_505);
            imgSrcList.add(R.drawable.shop_product_601);
            imgSrcList.add(R.drawable.shop_product_602);
            imgSrcList.add(R.drawable.shop_product_603);
            imgSrcList.add(R.drawable.shop_product_604);
            imgSrcList.add(R.drawable.shop_product_605);
            imgSrcList.add(R.drawable.shop_product_701);
            imgSrcList.add(R.drawable.shop_product_702);
            imgSrcList.add(R.drawable.shop_product_703);
            imgSrcList.add(R.drawable.shop_product_704);
            imgSrcList.add(R.drawable.shop_product_705);
        }

        GoodsItem item = null;
        int nowPoint = 0;
        for (int i = 1; i < 8; i++) {//7个种类
            for (int j = 1; j < 6; j++) {//每个种类5种商品
                item = new GoodsItem(100 * i + j, Math.random() * 6 + 1, "商品" + (100 * i + j), i, typeNameList.get(i - 1));
                item.imgSrc = imgSrcList.get(nowPoint++);
                goodsList.add(item);
            }
            typeList.add(item);
        }
    }


    public static ArrayList<GoodsItem> getGoodsList() {
        if (goodsList == null) {
            initData();
        }
        return goodsList;
    }

    public static ArrayList<GoodsItem> getTypeList() {
        if (typeList == null) {
            initData();
        }
        return typeList;
    }
}
