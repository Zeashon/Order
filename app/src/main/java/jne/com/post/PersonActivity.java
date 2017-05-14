package jne.com.post;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import jne.com.R;

public class PersonActivity extends Activity {

    private Button ShopPageBtn;
    private Button NewPostBtn;
    private Button MessagePageBtn;
    private Button MainPageBtn;
    private LinearLayout MyPostBtn;
    private LinearLayout AddrSettingPageBtn;
    private LinearLayout AdvicePageBtn;
    private LinearLayout TrainStateQueryBtn;

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
                finish();
            }
        });
        NewPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonActivity.this, NewPostActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ShopPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
                finish();
            }
        });
        MessagePageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonActivity.this, CollectionActivity.class);
                startActivity(intent);
                finish();
            }
        });
        MyPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonActivity.this, MyPostActivity.class);
                startActivity(intent);
                finish();
            }
        });
        AddrSettingPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonActivity.this, AddrSettingActivity.class);
                startActivity(intent);
            }
        });
        AdvicePageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonActivity.this, AdviceActivity.class);
                startActivity(intent);
            }
        });
        TrainStateQueryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String APIKey = "398eaa944646cfe6";
                //TODO train state query
            }
        });
    }


    private void initComponent() {
        MyPostBtn = (LinearLayout) findViewById(R.id.mypost_btn);
        ShopPageBtn = (Button) findViewById(R.id.ShopPageBtn);
        NewPostBtn = (Button) findViewById(R.id.NewPostPageBtn);
        MessagePageBtn = (Button) findViewById(R.id.messagePageBtn);
        MainPageBtn = (Button) findViewById(R.id.IndexPageBtn);
        AddrSettingPageBtn = (LinearLayout)findViewById(R.id.addr_setbtn);
        AdvicePageBtn = (LinearLayout)findViewById(R.id.advicebtn);
        TrainStateQueryBtn = (LinearLayout) findViewById(R.id.trainStateQuerybtn);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PersonActivity.this, MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
        finish();
    }


    public void getTrainState(String path[]) throws Exception {
        URL u=new URL("http://route.showapi.com/832-4?showapi_appid=myappid&train_num=&showapi_sign=mysecret");
        InputStream in=u.openStream();
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        try {
            byte buf[]=new byte[1024];
            int read = 0;
            while ((read = in.read(buf)) > 0) {
                out.write(buf, 0, read);
            }
        }  finally {
            if (in != null) {
                in.close();
            }
        }
        byte b[]=out.toByteArray( );
        System.out.println(new String(b,"utf-8"));
    }


}
