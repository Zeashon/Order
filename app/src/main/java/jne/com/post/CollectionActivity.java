package jne.com.post;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import jne.com.R;

public class CollectionActivity extends Activity {

    private OrderDao ordersDao;
    private ListView showPostListView;
    private List<Order> orderList;
    private OrderListAdapter adapter;
    private Button NearbyPageBtn;
    private Button NewPostBtn;
    private Button MessagePageBtn;
    private Button PersonalPageBtn;
    private Button MainPageBtn;
    private Button SearchBtn;
    private EditText SearchEditText;
    private TextView PostNumTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        ordersDao = new OrderDao(this);
        if (!ordersDao.isDataExist()) {
            ordersDao.initTable();
        }

        initComponent();

        int resNum = 0;
        orderList = ordersDao.getTypePost(2);
        if (orderList != null) {
            adapter = new OrderListAdapter(this, orderList);
            showPostListView.setAdapter(adapter);
            resNum = adapter.getCount();
        }
        if (resNum > 0) {
            PostNumTextView.setText("你已收藏" + resNum + "条任务");
        } else {
            PostNumTextView.setText("你一条任务都没收藏，快去做任务吧");
        }

        MainPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CollectionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        NewPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CollectionActivity.this, NewPostActivity.class);
                startActivity(intent);
            }
        });
        NearbyPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CollectionActivity.this, NearbyActivity.class);
                startActivity(intent);
            }
        });
        MessagePageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CollectionActivity.this, CollectionActivity.class);
                startActivity(intent);
            }
        });
        PersonalPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CollectionActivity.this, PersonActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initComponent() {

        showPostListView = (ListView) findViewById(R.id.showPostListView);
//        showPostListView.addHeaderView(LayoutInflater.from(this).inflate(R.layout.show_post_item_nocoll, null), null, false);
        NearbyPageBtn = (Button) findViewById(R.id.NearbyPageBtn);
        NewPostBtn = (Button) findViewById(R.id.NewPostPageBtn);
        MessagePageBtn = (Button) findViewById(R.id.messagePageBtn);
        PersonalPageBtn = (Button) findViewById(R.id.personPageBtn);
        MainPageBtn = (Button) findViewById(R.id.IndexPageBtn);
        PostNumTextView = (TextView) findViewById(R.id.PostNumTextView);
    }

}
