package jne.com.post;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import jne.com.R;

public class OrderListAdapter extends BaseAdapter {
    private String TAG = "OrderListAdapter";
    private Context context;
    private List<Order> orderList;

    public OrderListAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Order order = orderList.get(position);
        if (order == null) {
            return null;
        }

        ViewHolder holder = null;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.show_post_item, null);
            holder = new ViewHolder();
            holder.user_name = (TextView) view.findViewById(R.id.user_name);
            holder.postItem_time = (TextView) view.findViewById(R.id.postItem_time);
            holder.finishTime = (TextView) view.findViewById(R.id.finishTime);
            holder.finishPlace = (TextView) view.findViewById(R.id.finishPlace);
            holder.finishStation = (TextView) view.findViewById(R.id.finishStation);
            holder.remuneration = (TextView) view.findViewById(R.id.remuneration);
            holder.phoneNumText = (TextView) view.findViewById(R.id.phoneNumText);
            holder.postDetail = (TextView) view.findViewById(R.id.postDetail);
            holder.addCollection = (Button) view.findViewById(R.id.addCollection);
            holder.callMe = (ImageButton) view.findViewById(R.id.call_me);
            holder.textMe = (ImageButton) view.findViewById(R.id.text_me);
            view.setTag(holder);
        }

        holder.user_name.setText(order.customName);
        String year, month, day;
        year = order.id.substring(0, 4);
        month = order.id.substring(4, 6);
        day = order.id.substring(6, 8);
        Log.e(TAG, year + ":" + month + ":" + day);
        holder.postItem_time.setText(year + "年" + (Integer.parseInt(month) - 1) + "月" + day + "日");
        holder.finishTime.setText(order.finishTime);
        holder.finishPlace.setText(order.finishPlace);
        holder.finishStation.setText(order.country);
        holder.remuneration.setText("价格：" + order.remuneration);
        holder.phoneNumText.setText("联系方式:" + order.phoneNum + "");
        holder.postDetail.setText(order.postDetail);
        if (order.type == 0) {
            holder.addCollection.setBackgroundResource(R.drawable.icon_like_empty);
        } else if (order.type == 2) {
            holder.addCollection.setBackgroundResource(R.drawable.icon_like_filled);
        } else if (order.type == 1) {
            holder.addCollection.setBackgroundResource(R.drawable.icon_like_filled);
            holder.addCollection.setClickable(false);
        }
        final String id = order.id;
        final int tag = order.type;
        final OrderDao ordersDao;
        final ViewHolder oholder = holder;
        ordersDao = new OrderDao(this.context);
        if (!ordersDao.isDataExist()) {
            ordersDao.initTable();
        }
        oholder.addCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "点击" + id);
                ordersDao.addPost(id, tag);
                if (2 - tag == 0) {
                    Log.e(TAG, "取消收藏了帖子" + id + "。");
                    Toast.makeText(context, "取消收藏了帖子"+ id, Toast.LENGTH_SHORT).show();
                    oholder.addCollection.setBackgroundResource(R.drawable.icon_like_empty);
                } else if (2 - tag == 2) {
                    Log.e(TAG, "收藏了帖子" + id + "。");
                    oholder.addCollection.setBackgroundResource(R.drawable.icon_like_filled);
                    Toast.makeText(context, id +"帖子已收藏", Toast.LENGTH_SHORT).show();
                }else if (2 - tag == 1) {
                    Log.e(TAG, "这是你的帖子" + id + "。");
                    Toast.makeText(context, id +"这是你的帖子", Toast.LENGTH_SHORT).show();
                }
            }
        });
        String str[] = oholder.phoneNumText.getText().toString().split(":");
        final String PhoneNum = str[1];
        oholder.textMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(PhoneNum)) {
                    sendSmsWithNumber(context, PhoneNum, "您好，我在易出行上看到你的帖子。请问");
                }
                Toast.makeText(context, "即将跳转短信编辑页", Toast.LENGTH_SHORT).show();
            }
        });
        oholder.callMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                //    设置Title的图标
                builder.setIcon(R.drawable.logo);
                //    设置Title的内容
                builder.setTitle("拨打电话确认");
                //    设置Content来显示一个信息
                builder.setMessage("确定要拨打电话给" + PhoneNum + "吗？");
                //    设置一个PositiveButton
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!TextUtils.isEmpty(PhoneNum)) {
                            callWithNumber(context,PhoneNum);
                        }
                        Toast.makeText(context, "即将跳转电话拨打界面", Toast.LENGTH_SHORT).show();
                    }
                });
                //    设置一个NegativeButton
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "已取消打电话" + which, Toast.LENGTH_SHORT).show();
                    }
                });
                //    显示出该对话框
                builder.show();
            }
        });
        return view;
    }

    /**
     * 调用系统界面，给指定的号码发送短信
     *
     * @param context
     * @param number
     */
    public void sendSmsWithNumber(Context context, String number, String body) {
        Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
        sendIntent.setData(Uri.parse("smsto:" + number));
        sendIntent.putExtra("sms_body", body);
        context.startActivity(sendIntent);
    }
    /**
     * 调用系统界面，给指定的号码打电话
     *
     * @param context
     * @param number
     */
    public void callWithNumber(Context context, String number) {
//    使用Uri.parse(String a)创建Uri。
        Uri uri = Uri.parse("tel:" + number);
//    创建打电话的意图。
        Intent callIntent = new Intent(Intent.ACTION_CALL, uri);
//    启动系统打电话页面。
        try {
            context.startActivity(callIntent);
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(context, "没有打电话的权限", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    public static class ViewHolder {
        public TextView user_name;
        public TextView postItem_time;
        public TextView finishTime;
        public TextView finishPlace;
        public TextView finishStation;
        public TextView remuneration;
        public TextView phoneNumText;
        public TextView postDetail;
        public Button addCollection;
        public ImageButton textMe;
        public ImageButton callMe;
    }


}
