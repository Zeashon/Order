package jne.com.post;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import jne.com.R;

public class MyPostActivity extends Activity {

    private OrderDao ordersDao;
    private ListView showPostListView;
    private List<Order> orderList;
    private OrderListAdapter adapter;
    private Button ShopPageBtn;
    private Button NewPostBtn;
    private Button MessagePageBtn;
    private Button PersonalPageBtn;
    private Button MainPageBtn;
    private TextView PostNumTextView;
    private Button showFinishedPostBtn;
    private Button showDoingPostBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypost);

        ordersDao = new OrderDao(this);
        if (! ordersDao.isDataExist()){
            ordersDao.initTable();
        }

        initComponent();

        int resNum=0;
        //获取未完成的任务
        orderList=ordersDao.getTypePost(1);
        if (orderList != null){
            adapter = new OrderListAdapter(this, orderList);
            showPostListView.setAdapter(adapter);
            resNum = adapter.getCount();
        }
        if(resNum > 0)
        {   PostNumTextView.setText("你共有" + resNum + "条未完成任务");   }
        else
        {   PostNumTextView.setText("你一条任务都没发布，快去发布任务吧");    }


        MainPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPostActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        NewPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPostActivity.this, NewPostActivity.class);
                startActivity(intent);
            }
        });
        ShopPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPostActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });
        MessagePageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPostActivity.this, CollectionActivity.class);
                startActivity(intent);
            }
        });
        showDoingPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDoingPostBtn.setBackgroundColor(0xff17abe3);
                showFinishedPostBtn.setBackgroundColor(Color.WHITE);
                orderList=ordersDao.getTypePost(1);
                int resNum = 0;
                if (orderList != null){
                    adapter = new OrderListAdapter(MyPostActivity.this, orderList);
                    showPostListView.setAdapter(adapter);
                    resNum = adapter.getCount();
                }
                if(resNum > 0)
                {   PostNumTextView.setText("你共有" + resNum + "条未完成任务");   }
                else
                {   PostNumTextView.setText("你一条任务都没发布，快去发布任务吧");    }

            }
        });
        showFinishedPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDoingPostBtn.setBackgroundColor(Color.WHITE);
                showFinishedPostBtn.setBackgroundColor(0xff17abe3);
                orderList=ordersDao.getTypePost(3);
                int resNum = 0;
                if (orderList != null){
                    adapter = new OrderListAdapter(MyPostActivity.this, orderList);
                    showPostListView.setAdapter(adapter);
                    resNum = adapter.getCount();
                }
                if(resNum > 0)
                {   PostNumTextView.setText("你共有" + resNum + "条已完成任务");   }
                else
                {   PostNumTextView.setText("你一条任务都没发布，快去发布任务吧");    }

            }
        });

    }

    private void initComponent(){

        showPostListView = (ListView)findViewById(R.id.showPostListView);
//        showPostListView.addHeaderView(LayoutInflater.from(this).inflate(R.layout.show_post_item_nocoll, null), null, false);
        ShopPageBtn = (Button) findViewById(R.id.ShopPageBtn);
        NewPostBtn = (Button) findViewById(R.id.NewPostPageBtn);
        MessagePageBtn = (Button) findViewById(R.id.messagePageBtn);
        PersonalPageBtn = (Button) findViewById(R.id.personPageBtn);
        MainPageBtn = (Button) findViewById(R.id.IndexPageBtn);
        PostNumTextView = (TextView) findViewById(R.id.PostNumTextView);
        showDoingPostBtn = (Button) findViewById(R.id.showDoingPost);
        showFinishedPostBtn = (Button) findViewById(R.id.showFinishedPost);

        showDoingPostBtn.setBackgroundColor(0xff17abe3);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MyPostActivity.this, PersonActivity.class);
        startActivity(intent);
        super.onBackPressed();
        finish();
    }
}
