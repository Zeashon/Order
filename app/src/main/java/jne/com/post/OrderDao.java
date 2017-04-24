package jne.com.post;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jne.com.R;

/**
 * Created by Jne
 * Date: 2015/1/6.
 */
public class OrderDao {
    private static final String TAG = "OrdersDao";

    // 列定义
    private final String[] ORDER_COLUMNS = new String[]{"Id", "CustomName", "PhoneNum", "Country", "FinishTime", "FinishPlace", "PostDetail", "Remuneration", "Type"};

    private Context context;
    private OrderDBHelper ordersDBHelper;

    public OrderDao(Context context) {
        this.context = context;
        ordersDBHelper = new OrderDBHelper(context);
    }

    /**
     * 判断表中是否有数据
     */
    public boolean isDataExist() {
        int count = 0;

        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = ordersDBHelper.getReadableDatabase();
            // select count(Id) from Orders
            cursor = db.query(OrderDBHelper.TABLE_NAME, new String[]{"COUNT(Id)"}, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
            if (count > 0) return true;
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return false;
    }

    /**
     * 初始化数据
     */
    public void initTable() {
        SQLiteDatabase db = null;

        try {
            db = ordersDBHelper.getWritableDatabase();
            db.beginTransaction();

            db.execSQL("insert into " + OrderDBHelper.TABLE_NAME + " (Id, CustomName, PhoneNum, Country,FinishTime,FinishPlace,PostDetail,Remuneration,Type) values ('20160531191902', 'Zeashon', '18659595959', '南京站','20171221','03车D1234','希望有人能帮我带一下南京的咸水鸭，我1号会坐D1234次动车经过南京','新疆大枣400g',1)");
            db.execSQL("insert into " + OrderDBHelper.TABLE_NAME + " (Id, CustomName, PhoneNum, Country,FinishTime,FinishPlace,PostDetail,Remuneration,Type) values ('20160531193423', 'Bor', '18659595959', '南京站','20170101','03车D1234','希望有人能帮我带一下南京的咸水鸭，我1号会坐D1234次动车经过南京','新疆大枣400g',0)");
            db.execSQL("insert into " + OrderDBHelper.TABLE_NAME + " (Id, CustomName, PhoneNum, Country,FinishTime,FinishPlace,PostDetail,Remuneration,Type) values ('20160531191476', 'Cut', '18659595959', '南京站','20170603','03车D1237','希望有人能帮我带一下南京的咸水鸭，我1号会坐D1234次动车经过南京','新疆大枣400g',0)");
            db.execSQL("insert into " + OrderDBHelper.TABLE_NAME + " (Id, CustomName, PhoneNum, Country,FinishTime,FinishPlace,PostDetail,Remuneration,Type) values ('20160133414455', 'Bor', '18659595959', '南京站','20170604','03车D1235','希望有人能帮我带一下南京的咸水鸭，我1号会坐D1234次动车经过南京','新疆大枣400g',0)");
            db.execSQL("insert into " + OrderDBHelper.TABLE_NAME + " (Id, CustomName, PhoneNum, Country,FinishTime,FinishPlace,PostDetail,Remuneration,Type) values ('20160531191905', 'Arc', '18659595959', '南京站','20170601','03车D1235','希望有人能帮我带一下南京的咸水鸭，我1号会坐D1234次动车经过南京','新疆大枣400g',0)");
            db.execSQL("insert into " + OrderDBHelper.TABLE_NAME + " (Id, CustomName, PhoneNum, Country,FinishTime,FinishPlace,PostDetail,Remuneration,Type) values ('20160531431902', 'Doom', '18659595959', '南京站','20170604','03车D1237','希望有人能帮我带一下南京的咸水鸭，我1号会坐D1234次动车经过南京','新疆大枣400g',0)");

            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
    }

    /**
     * 执行自定义SQL语句
     */
    public void execSQL(String sql) {
        SQLiteDatabase db = null;

        try {
            if (sql.contains("select")) {
                Toast.makeText(context, R.string.strUnableSql, Toast.LENGTH_SHORT).show();
            } else if (sql.contains("insert") || sql.contains("update") || sql.contains("delete")) {
                db = ordersDBHelper.getWritableDatabase();
                db.beginTransaction();
                db.execSQL(sql);
                db.setTransactionSuccessful();
                Toast.makeText(context, R.string.strSuccessSql, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, R.string.strErrorSql, Toast.LENGTH_SHORT).show();
            Log.e(TAG, "", e);
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
    }

    /**
     * 查询数据库中所有数据
     * 第一层过滤：过滤结束时间早于当前时间的数据
     */
    public List<Order> getAllDate() {
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = ordersDBHelper.getReadableDatabase();
            // select * from Orders
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date curDate = new Date(System.currentTimeMillis());//获取当前时间
            String str = formatter.format(curDate);
            Log.e(TAG, str);
            cursor = db.query(OrderDBHelper.TABLE_NAME, ORDER_COLUMNS,
                    "FinishTime > ?",
                    new String[]{str},
                    null, null, "Id DESC");//降序拍列

            if (cursor.getCount() > 0) {
                List<Order> orderList = new ArrayList<Order>(cursor.getCount());
                while (cursor.moveToNext()) {
                    orderList.add(parseOrder(cursor));
                }
                return orderList;
            }
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return null;
    }

    /**
     * 新增一条数据
     */
    public boolean insertDate() {
        SQLiteDatabase db = null;

        try {
            db = ordersDBHelper.getWritableDatabase();
            db.beginTransaction();

            // insert into Orders(Id, CustomName, OrderPrice, Country) values (7, "Jne", 700, "China");
            ContentValues contentValues = new ContentValues();
            contentValues.put("Id", 7);
            contentValues.put("CustomName", "Jne");
            contentValues.put("OrderPrice", 700);
            contentValues.put("Country", "China");
            db.insertOrThrow(OrderDBHelper.TABLE_NAME, null, contentValues);

            db.setTransactionSuccessful();
            return true;
        } catch (SQLiteConstraintException e) {
            Toast.makeText(context, "主键重复", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
        return false;
    }

    /**
     * 删除一条数据  此处删除Id为7的数据
     */
    public boolean deleteOrder() {
        SQLiteDatabase db = null;

        try {
            db = ordersDBHelper.getWritableDatabase();
            db.beginTransaction();

            // delete from Orders where Id = 7
            db.delete(OrderDBHelper.TABLE_NAME, "Id = ?", new String[]{String.valueOf(7)});
            db.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
        return false;
    }

    /**
     * 修改一条数据  此处将Id为6的数据的OrderPrice修改了800
     */
    public boolean updateOrder() {
        SQLiteDatabase db = null;
        try {
            db = ordersDBHelper.getWritableDatabase();
            db.beginTransaction();

            // update Orders set OrderPrice = 800 where Id = 6
            ContentValues cv = new ContentValues();
            cv.put("PhoneNum", 800);
            db.update(OrderDBHelper.TABLE_NAME,
                    cv,
                    "Id = ?",
                    new String[]{String.valueOf(6)});
            db.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }

        return false;
    }

    /**
     * 数据查询  此处将用户名为"Bor"的信息提取出来
     */
    public List<Order> getBorOrder() {
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = ordersDBHelper.getReadableDatabase();

            // select * from Orders where CustomName = 'Bor'
            cursor = db.query(OrderDBHelper.TABLE_NAME,
                    ORDER_COLUMNS,
                    "CustomName = ?",
                    new String[]{"Bor"},
                    null, null, null);

            if (cursor.getCount() > 0) {
                List<Order> orderList = new ArrayList<Order>(cursor.getCount());
                while (cursor.moveToNext()) {
                    Order order = parseOrder(cursor);
                    orderList.add(order);
                }
                return orderList;
            }
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return null;
    }

    /**
     * 统计查询  此处查询Country为China的用户总数
     */
    public int getChinaCount() {
        int count = 0;

        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = ordersDBHelper.getReadableDatabase();
            // select count(Id) from Orders where Country = 'China'
            cursor = db.query(OrderDBHelper.TABLE_NAME,
                    new String[]{"COUNT(Id)"},
                    "Country = ?",
                    new String[]{"China"},
                    null, null, null);

            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return count;
    }

    /**
     * 比较查询  此处查询单笔数据中OrderPrice最高的
     */
    public Order getMaxOrderPrice() {
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = ordersDBHelper.getReadableDatabase();
            // select Id, CustomName, Max(OrderPrice) as OrderPrice, Country from Orders
            cursor = db.query(OrderDBHelper.TABLE_NAME, new String[]{"Id", "CustomName", "Max(OrderPrice) as OrderPrice", "Country"}, null, null, null, null, null);

            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    return parseOrder(cursor);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return null;
    }

    //    查询车次（格式：03车D1234）|| 时间 （2016.06.01）
    public List<Order> getPlaceTimeOrder(String keyWord) {
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = ordersDBHelper.getReadableDatabase();

            // select * from Orders where CustomName = 'Bor'
            cursor = db.query(OrderDBHelper.TABLE_NAME,
                    ORDER_COLUMNS,
                    "FinishPlace = ? or FinishTime = ?",
                    new String[]{keyWord, keyWord},
                    null, null, null);

            if (cursor.getCount() > 0) {
                List<Order> orderList = new ArrayList<Order>(cursor.getCount());
                while (cursor.moveToNext()) {
                    Order order = parseOrder(cursor);
                    orderList.add(order);
                }
                return orderList;
            }
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return null;
    }

    //    插入新数据
    public boolean insertData(String Id, String CustomName, String PhoneNum, String Country, String FinishTime, String FinishPlace, String PostDetail, String Remuneration, int Type) {
        SQLiteDatabase db = null;

        try {
            db = ordersDBHelper.getWritableDatabase();
            db.beginTransaction();

            ContentValues contentValues = new ContentValues();
            contentValues.put("Id", Id);
            contentValues.put("CustomName", CustomName);
            contentValues.put("PhoneNum", PhoneNum);
            contentValues.put("Country", Country);
            contentValues.put("FinishTime", FinishTime);
            contentValues.put("FinishPlace", FinishPlace);
            contentValues.put("PostDetail", PostDetail);
            contentValues.put("Remuneration", Remuneration);
            contentValues.put("Type", Type);
            db.insertOrThrow(OrderDBHelper.TABLE_NAME, null, contentValues);

            db.setTransactionSuccessful();
            return true;
        } catch (SQLiteConstraintException e) {
            Toast.makeText(context, "主键重复", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
        return false;
    }


//       查询我的帖子

    /**
     * 查询数据库中所有数据
     */
    public List<Order> getTypePost(int Type) {
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = ordersDBHelper.getReadableDatabase();
            // select * from Orders
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date curDate = new Date(System.currentTimeMillis());//获取当前时间
            String str = formatter.format(curDate);
            Log.e(TAG, str);
            cursor = db.query(OrderDBHelper.TABLE_NAME, ORDER_COLUMNS,
                    "Type = ?",
                    new String[]{Type + ""},
                    null, null, "Id DESC");//降序拍列

            if (cursor.getCount() > 0) {
                List<Order> orderList = new ArrayList<Order>(cursor.getCount());
                while (cursor.moveToNext()) {
                    orderList.add(parseOrder(cursor));
                }
                return orderList;
            }
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return null;
    }

    //    将帖子设为收藏 或 取消收藏
    public boolean addPost(String id, int type) {
        SQLiteDatabase db = null;
        try {
            db = ordersDBHelper.getWritableDatabase();
            db.beginTransaction();

            // update Orders set Type = 2 where Id = id
            ContentValues cv = new ContentValues();
            cv.put("Type", (2-type) );
            db.update(OrderDBHelper.TABLE_NAME,
                    cv,
                    "Id = ?",
                    new String[]{id});
            db.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }

        return false;
    }


    /**
     * 将查找到的数据转换成Order类
     */
    private Order parseOrder(Cursor cursor) {
        Order order = new Order();
        order.id = (cursor.getString(cursor.getColumnIndex("Id")));
        order.customName = (cursor.getString(cursor.getColumnIndex("CustomName")));
        order.phoneNum = (cursor.getString(cursor.getColumnIndex("PhoneNum")));
        order.country = (cursor.getString(cursor.getColumnIndex("Country")));
        order.finishTime = (cursor.getString(cursor.getColumnIndex("FinishTime")));
        order.finishPlace = (cursor.getString(cursor.getColumnIndex("FinishPlace")));
        order.postDetail = (cursor.getString(cursor.getColumnIndex("PostDetail")));
        order.remuneration = (cursor.getString(cursor.getColumnIndex("Remuneration")));
        order.country = (cursor.getString(cursor.getColumnIndex("Country")));
        order.type = (cursor.getInt(cursor.getColumnIndex("Type")));
        return order;
    }
}
