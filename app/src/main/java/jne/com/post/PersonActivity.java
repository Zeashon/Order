package jne.com.post;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import jne.com.R;

public class PersonActivity extends Activity {

    private OrderDao ordersDao;
    private ListView showPostListView;
    private List<Order> orderList;
    private OrderListAdapter adapter;
    private Button NearbyPageBtn;
    private Button NewPostBtn;
    private Button MessagePageBtn;
    private Button PersonalPageBtn;
    private Button MainPageBtn;
    private LinearLayout MyPostBtn;
    private Button SearchBtn;
    private EditText SearchEditText;
    private TextView PostNumTextView;

    public PersonActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        initComponent();

        MainPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        NewPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonActivity.this, NewPostActivity.class);
                startActivity(intent);
            }
        });
        NearbyPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonActivity.this, NearbyActivity.class);
                startActivity(intent);
            }
        });
        MessagePageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonActivity.this, CollectionActivity.class);
                startActivity(intent);
            }
        });
        MyPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonActivity.this, MyPostActivity.class);
                startActivity(intent);
            }
        });
    }


    private void initComponent() {
        MyPostBtn = (LinearLayout) findViewById(R.id.mypost_btn);
        NearbyPageBtn = (Button) findViewById(R.id.NearbyPageBtn);
        NewPostBtn = (Button) findViewById(R.id.NewPostPageBtn);
        MessagePageBtn = (Button) findViewById(R.id.messagePageBtn);
        MainPageBtn = (Button) findViewById(R.id.IndexPageBtn);
    }

}
