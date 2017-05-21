package jne.com.post;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import jne.com.R;

public class AdviceDao {
    private static final String TAG = "AdviceDao";

    // 列定义
    private final String[] ORDER_COLUMNS = new String[]{"Id", "User", "Title", "Content", "State"};

    private Context context;
    private AdviceDBHelper adviceDBHelper;

    public AdviceDao(Context context) {
        this.context = context;
        adviceDBHelper = new AdviceDBHelper(context);
    }

    /**
     * 判断表中是否有数据
     */
    public boolean isDataExist() {
        int count = 0;

        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = adviceDBHelper.getReadableDatabase();
            // select count(Id) from Orders
            cursor = db.query(adviceDBHelper.TABLE_NAME, new String[]{"COUNT(Id)"}, null, null, null, null, null);

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
            db = adviceDBHelper.getWritableDatabase();
            db.beginTransaction();

            db.execSQL("insert into " + AdviceDBHelper.TABLE_NAME + " (Id, User, Title, Content,State) values ('20160531191902', 'Zeashon',  'D1237次列车卫生问题','5号车厢的卫生间特别脏！！！',0)");

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
                db = adviceDBHelper.getWritableDatabase();
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
     * 新增一条数据
     */
    public boolean insertData(Advice advice) {
        SQLiteDatabase db = null;

        try {
            db = adviceDBHelper.getWritableDatabase();
            db.beginTransaction();

            // insert into Advice(Id, User, Title, Content,State) values (id, user, title, content , state);
            ContentValues contentValues = new ContentValues();
            contentValues.put("Id", advice.id);
            contentValues.put("User", advice.user);
            contentValues.put("Title", advice.title);
            contentValues.put("Content", advice.content);
            contentValues.put("State", advice.state);
            db.insertOrThrow(AdviceDBHelper.TABLE_NAME, null, contentValues);
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
    public boolean deleteAdvice(String Keyword) {
        SQLiteDatabase db = null;

        try {
            db = adviceDBHelper.getWritableDatabase();
            db.beginTransaction();
            // delete from Advice where Id =
            db.delete(AdviceDBHelper.TABLE_NAME, "Id = ?", new String[]{Keyword});
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
     * 将查找到的数据转换成Advice类
     */
    private Advice parseAdvice(Cursor cursor) {
        Advice advice = new Advice();
        advice.id = (cursor.getString(cursor.getColumnIndex("Id")));
        advice.user = (cursor.getString(cursor.getColumnIndex("User")));
        advice.title = (cursor.getString(cursor.getColumnIndex("Title")));
        advice.content = (cursor.getString(cursor.getColumnIndex("Content")));
        advice.state = (cursor.getInt(cursor.getColumnIndex("State")));
        return advice;
    }
}
