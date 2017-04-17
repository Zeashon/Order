package jne.com.post;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import jne.com.R;

public class SearchActivity extends Activity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

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

        MainPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        NewPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, NewPostActivity.class);
                startActivity(intent);
            }
        });
        PersonalPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, PersonActivity.class);
                startActivity(intent);
            }
        });
        NearbyPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, NearbyActivity.class);
                startActivity(intent);
            }
        });
        MessagePageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, CollectionActivity.class);
                startActivity(intent);
            }
        });

        SearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyWord;
                keyWord = SearchEditText.getText().toString();
                if (keyWord == "") {
                    Toast.makeText(getApplicationContext(), "查询不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    int resNum;
                    orderList = ordersDao.getPlaceTimeOrder(keyWord);
                    if (orderList != null) {
                        adapter = new OrderListAdapter(getApplicationContext(), orderList);
                        resNum = adapter.getCount();
                        Toast.makeText(getApplicationContext(), "已为你查询到" + resNum + "记录", Toast.LENGTH_SHORT).show();
                        showPostListView.setAdapter(adapter);
                    } else {
                        Toast.makeText(getApplicationContext(), "暂无结果", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void initComponent() {

        showPostListView = (ListView) findViewById(R.id.showPostListView);
//        showPostListView.addHeaderView(LayoutInflater.from(this).inflate(R.layout.show_post_item, null), null, false);
        NearbyPageBtn = (Button) findViewById(R.id.NearbyPageBtn);
        NewPostBtn = (Button) findViewById(R.id.NewPostPageBtn);
        MessagePageBtn = (Button) findViewById(R.id.messagePageBtn);
        PersonalPageBtn = (Button) findViewById(R.id.personPageBtn);
        MainPageBtn = (Button) findViewById(R.id.IndexPageBtn);
        SearchBtn = (Button) findViewById(R.id.main_search_button);
        SearchEditText = (EditText) findViewById(R.id.SearchEditText);
    }

}
