package jne.com.post;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import jne.com.R;

public class AdviceActivity extends Activity {

    private Button advice_ok_btn;
    private Button advice_cancel_btn;
    private EditText advice_title_et;
    private EditText advice_content_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_advice);

        initComponent();

        advice_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdviceActivity.this, PersonActivity.class);
                startActivity(intent);
            }
        });

        advice_ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title, content;
                title = advice_title_et.getText().toString();
                content = advice_content_et.getText().toString();

                if (title.length() == 0) {
                    Toast.makeText(AdviceActivity.this, "请填写标题。", Toast.LENGTH_LONG).show();
                    return;
                } else if (content.length() < 15) {
                    Toast.makeText(AdviceActivity.this, "内容不少于15字。", Toast.LENGTH_LONG).show();
                    return;
                }
                //        get Time
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                String time = formatter.format(curDate);

                //      get user
                String user = "Zeashon";

                //TODO   send to server

                //      turn back to PA
                Intent intent = new Intent(AdviceActivity.this, PersonActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initComponent() {

        advice_ok_btn = (Button) findViewById(R.id.advice_okBtn);
        advice_cancel_btn = (Button) findViewById(R.id.advice_cancelBtn);
        advice_title_et = (EditText) findViewById(R.id.advice_title);
        advice_content_et = (EditText) findViewById(R.id.advice_content);
    }

}
