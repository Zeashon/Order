package jne.com.post;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jne.com.R;

public class NewPostActivity extends Activity {

    private String TAG = "NewPostActivity";
    private OrderDao ordersDao;
    private Button ShopPageBtn;
    private Button MessagePageBtn;
    private Button PersonalPageBtn;
    private Button MainPageBtn;
    private DatePicker FnishTimeDatePicker;
    private EditText FnishPlaceEditText;
    private EditText RemunerationEditorText;
    private EditText PhoneNumText;
    private EditText PostDetailEditText;
    private Button PublishBtn;
    private TextView PublishTime;
    private EditText UserName;
    private EditText FinishStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpost);

        initComponent();
        autofillAddr();

        ordersDao = new OrderDao(this);
        if (!ordersDao.isDataExist()) {
            ordersDao.initTable();
        }


        MainPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewPostActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ShopPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewPostActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
                finish();
            }
        });
        PersonalPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewPostActivity.this, PersonActivity.class);
                startActivity(intent);
                finish();
            }
        });
        MessagePageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewPostActivity.this, CollectionActivity.class);
                startActivity(intent);
                finish();
            }
        });

        PublishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Id;
                String CustomName;
                String PhoneNum;
                String Country;
                String FinishTime;
                String FinishPlace;
                String PostDetail;
                String Remuneration;
                Id = PublishTime.getText().toString().trim();
                CustomName = UserName.getText().toString().trim();
                PhoneNum = PhoneNumText.getText().toString().trim();
                Country = FinishStation.getText().toString().trim();
                String month, day, year;
                if (FnishTimeDatePicker.getMonth() + 1 < 10) {
                    month = "0" + (FnishTimeDatePicker.getMonth() + 1);
                } else {
                    month = (FnishTimeDatePicker.getMonth() + 1) + "";
                }
                if (FnishTimeDatePicker.getDayOfMonth() < 10) {
                    day = "0" + FnishTimeDatePicker.getDayOfMonth();
                } else {
                    day = FnishTimeDatePicker.getDayOfMonth() + "";
                }
                FinishTime = FnishTimeDatePicker.getYear() + "" + month + day;
                Log.e(TAG, FinishTime);
                Log.e(TAG, FnishTimeDatePicker.getMonth() + "");
                FinishPlace = FnishPlaceEditText.getText().toString().trim();
                PostDetail = PostDetailEditText.getText().toString().trim();
                Remuneration = RemunerationEditorText.getText().toString().trim();
                if (Id.length() == 0) {
                    Toast.makeText(getApplicationContext(), "主键不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (CustomName.length() == 0) {
                    Toast.makeText(getApplicationContext(), "用户名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (PhoneNum.length() == 0) {
                    Toast.makeText(getApplicationContext(), "联系方式不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Country.length() == 0) {
                    Toast.makeText(getApplicationContext(), "交付站点不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (FinishTime.length() == 0) {
                    Toast.makeText(getApplicationContext(), "交付时间不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (FinishPlace.length() == 0) {
                    Toast.makeText(getApplicationContext(), "交付地点不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (PostDetail.length() == 0) {
                    Toast.makeText(getApplicationContext(), "详情不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Remuneration == "") {
                    Toast.makeText(getApplicationContext(), "报酬不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    boolean res;
                    res = ordersDao.insertData(Id, CustomName, PhoneNum, Country, FinishTime, FinishPlace, PostDetail, Remuneration, 1);
                    if (res) {
                        Toast.makeText(getApplicationContext(), "发布成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(NewPostActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "系统出错", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        });
    }

    private void initComponent() {

        ShopPageBtn = (Button) findViewById(R.id.ShopPageBtn);
        MessagePageBtn = (Button) findViewById(R.id.messagePageBtn);
        PersonalPageBtn = (Button) findViewById(R.id.personPageBtn);
        MainPageBtn = (Button) findViewById(R.id.IndexPageBtn);
        FnishPlaceEditText = (EditText) findViewById(R.id.finishPlace);
        FinishStation = (EditText) findViewById(R.id.finishStation);
        FnishTimeDatePicker = (DatePicker) findViewById(R.id.finishTime);
        PhoneNumText = (EditText) findViewById(R.id.phoneNumText);
        RemunerationEditorText = (EditText) findViewById(R.id.remuneration);
        PostDetailEditText = (EditText) findViewById(R.id.postDetail);
        PublishBtn = (Button) findViewById(R.id.publishBtn);
        PublishTime = (TextView) findViewById(R.id.postItem_time);
        UserName = (EditText) findViewById(R.id.user_name);

//        set Now Time
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        Log.e(TAG, str);
        PublishTime.setText(str);
    }

    private void autofillAddr() {
        //1、获取Preferences
        SharedPreferences addrSetting = getSharedPreferences("addrSetting", 0);
        //2、取出数据
        String checked = addrSetting.getString("checked", "N");
        if (checked.equals("Y")) {
            String date = addrSetting.getString("date", "default");
            String train = addrSetting.getString("train", "default");
            String room = addrSetting.getString("room", "default");
//          String seat = addrSetting.getString("seat", "default");
            FnishPlaceEditText.setText(room + "车" + train);
            String year, month, day;
            year = date.substring(0, 4);
            month = date.substring(4, 6);
            day = date.substring(6, 8);
            Log.e(TAG,year+":"+month+":"+day);
            FnishTimeDatePicker.updateDate(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day));
        } else {
            Toast.makeText(getApplicationContext(), "请手动输入交付信息", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(NewPostActivity.this, MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
        finish();
    }
}
