package jne.com.post;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import java.util.List;

import jne.com.R;

public class NearbyActivity extends Activity {

    private OrderDao ordersDao;
    private ListView showPostListView;
    private List<Order> orderList;
    private OrderListAdapter adapter;
    private Button NewPostBtn;
    private Button MessagePageBtn;
    private Button PersonalPageBtn;
    private Button MainPageBtn;
    private LinearLayout TestPostBtn;
    private Button SearchBtn;
    private EditText SearchEditText;
    private TextView PostNumTextView;

    public NearbyActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);

        initComponent();

        MainPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NearbyActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        NewPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NearbyActivity.this, NewPostActivity.class);
                startActivity(intent);
            }
        });
        MessagePageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NearbyActivity.this, CollectionActivity.class);
                startActivity(intent);
            }
        });
        TestPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NearbyActivity.this, PromotionActivity.class);
                startActivity(intent);
            }
        });
    }


    private void initComponent() {
        TestPostBtn = (LinearLayout) findViewById(R.id.test_post);
        NewPostBtn = (Button) findViewById(R.id.NewPostPageBtn);
        MessagePageBtn = (Button) findViewById(R.id.messagePageBtn);
        MainPageBtn = (Button) findViewById(R.id.IndexPageBtn);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
