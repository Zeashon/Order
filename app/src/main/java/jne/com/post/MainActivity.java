package jne.com.post;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

import jne.com.R;

public class MainActivity extends Activity {

    private String TAG = "MainActivity";
    private OrderDao ordersDao;
    private ListView showPostListView;
    private List<Order> orderList;
    private OrderListAdapter adapter;
    private LinearLayout SearchPageBtn;
    private Button NewPostBtn;
    private Button MessagePageBtn;
    private Button PersonalPageBtn;
    private Button MainPageBtn;
    private ImageView PromotionPageBtn;
    private Button NearbyPageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pre);

        ordersDao = new OrderDao(this);
        if (!ordersDao.isDataExist()) {
            ordersDao.initTable();
        }

        initComponent();

        orderList = ordersDao.getAllDate();
        if (orderList != null) {
            adapter = new OrderListAdapter(this, orderList);
            showPostListView.setAdapter(adapter);
        }

        SearchPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        NewPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewPostActivity.class);
                startActivity(intent);
            }
        });

        MainPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        PersonalPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PersonActivity.class);
                startActivity(intent);
            }
        });
        MessagePageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CollectionActivity.class);
                startActivity(intent);
            }
        });
        NearbyPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NearbyActivity.class);
                startActivity(intent);
            }
        });
        PromotionPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PromotionActivity.class);
                startActivity(intent);
            }
        });
        showPostListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "你点击了第" + position + "项条目");
            }
        });


    }

    private void initComponent() {
        PromotionPageBtn = (ImageView) findViewById(R.id.AD_image);
        showPostListView = (ListView) findViewById(R.id.showPostListView);
//        showPostListView.addHeaderView(LayoutInflater.from(this).inflate(R.layout.show_post_item, null), null, false);
        SearchPageBtn = (LinearLayout) findViewById(R.id.main_top);
        NewPostBtn = (Button) findViewById(R.id.NewPostPageBtn);
        MessagePageBtn = (Button) findViewById(R.id.messagePageBtn);
        PersonalPageBtn = (Button) findViewById(R.id.personPageBtn);
        MainPageBtn = (Button) findViewById(R.id.IndexPageBtn);
        NearbyPageBtn = (Button) findViewById(R.id.NearbyPageBtn);

    }

}
