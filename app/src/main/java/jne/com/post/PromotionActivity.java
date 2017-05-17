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

public class PromotionActivity extends Activity {

    private LinearLayout NewPostBtn;
    private LinearLayout MessagePageBtn;
    private LinearLayout PersonalPageBtn;
    private LinearLayout MainPageBtn;
    private LinearLayout ShopPageBtn;

    public PromotionActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion);

        initComponent();

        MainPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PromotionActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        NewPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PromotionActivity.this, NewPostActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ShopPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PromotionActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
                finish();
            }
        });
        MessagePageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PromotionActivity.this, CollectionActivity.class);
                startActivity(intent);
                finish();
            }
        });
        PersonalPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PromotionActivity.this, PersonActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void initComponent(){
        ShopPageBtn = (LinearLayout) findViewById(R.id.ShopPageBtn);
        NewPostBtn = (LinearLayout) findViewById(R.id.NewPostPageBtn);
        MessagePageBtn = (LinearLayout) findViewById(R.id.messagePageBtn);
        MainPageBtn = (LinearLayout) findViewById(R.id.IndexPageBtn);
        PersonalPageBtn = (LinearLayout) findViewById(R.id.personPageBtn);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PromotionActivity.this, MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
        finish();
    }

}
