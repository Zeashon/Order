package jne.com.post;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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

import jne.com.R;

public class AdviceActivity extends Activity {

    private Button advice_ok_btn;
    private Button advice_cancel_btn;
    private EditText advice_title_et;
    private EditText advice_content_et;
    final int STATE_OK = 1;
    final int STATE_NO = 0;
    private AdviceDao advicesDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_advice);

        initComponent();

        advicesDao = new AdviceDao(this);
        if (!advicesDao.isDataExist()) {
            advicesDao.initTable();
        }

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
                } else if (content.length() < 5) {
                    Toast.makeText(AdviceActivity.this, "内容不少于5字。", Toast.LENGTH_LONG).show();
                    return;
                }
                //        get Time
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                String time = formatter.format(curDate);

                //      get user
                String user = "Zeashon";

                //   send to server
                //   write to comfrence
                //1、打开Preferences，名称为advice，如果存在则打开它，否则创建新的Preferences
                SharedPreferences advice = getSharedPreferences("advice", Activity.MODE_PRIVATE);
                //2、让addSetting处于编辑状态
                SharedPreferences.Editor editor = advice.edit();
                //3、存放数据
                editor.putString("checked", "Y");
                editor.putString("user", user);
                editor.putString("title",title);
                editor.putString("content",content);
                editor.putString("time", time);
                editor.putInt("state",STATE_NO);
                //4、完成提交
                editor.commit();

                //写入数据库

                Advice myAdvice = new Advice(time,user,title,content,STATE_NO);
                boolean res;
                res = advicesDao.insertData(myAdvice);
                if (res) {
                    Toast.makeText(getApplicationContext(), "感谢您的反馈", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AdviceActivity.this, PersonActivity.class);
                    finish();
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "系统出错，请稍候重试", Toast.LENGTH_SHORT).show();
                    return;
                }
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
