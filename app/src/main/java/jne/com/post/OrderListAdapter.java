package jne.com.post;

import android.content.Context;
import android.util.Log;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

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

            view.setTag(holder);
        }

        holder.user_name.setText(order.customName);
        holder.postItem_time.setText(order.id + "发布");
        holder.finishTime.setText(order.finishTime);
        holder.finishPlace.setText(order.finishPlace);
        holder.finishStation.setText(order.country);
        holder.remuneration.setText("诱惑：" + order.remuneration);
        holder.phoneNumText.setText("tel:" + order.phoneNum + "");
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
        holder.addCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "点击" + id);
                ordersDao.addPost(id, tag);
                if (2 - tag == 2) {
                    Log.e(TAG, "取消收藏了帖子" + id + "。");
                    oholder.addCollection.setBackgroundResource(R.drawable.icon_like_empty);
                } else if (2 - tag == 2) {
                    Log.e(TAG, "收藏了帖子" + id + "。");
                    oholder.addCollection.setBackgroundResource(R.drawable.icon_like_filled);
                }
            }
        });
        return view;
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
    }
}
