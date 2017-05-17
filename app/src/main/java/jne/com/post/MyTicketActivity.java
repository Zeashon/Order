package jne.com.post;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import jne.com.R;

public class MyTicketActivity extends Activity {

    private TextView startCity;
    private TextView finishCity;
    private TextView startDate;
    private TextView startTime;
    private TextView trainNum;
    private TextView trainRoom;
    private TextView trainSeat;
    private TextView checkWindow;
    private TextView tipsTextView;
    private TextView userName;
    private Button backToPersonBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_ticket);

        initComponent();
        refreshTicket();

        backToPersonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyTicketActivity.this, PersonActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initComponent() {

        startCity = (TextView) findViewById(R.id.startCity);
        finishCity = (TextView) findViewById(R.id.finishCity);
        startDate = (TextView) findViewById(R.id.startDate);
        startTime = (TextView) findViewById(R.id.startTime);
        trainNum = (TextView) findViewById(R.id.trainNum);
        trainRoom = (TextView) findViewById(R.id.trainRoom);
        trainSeat = (TextView) findViewById(R.id.trainSeat);
        checkWindow = (TextView) findViewById(R.id.checkWindow);
        tipsTextView = (TextView) findViewById(R.id.tipsTextView);
        userName = (TextView) findViewById(R.id.user_name);
        backToPersonBtn = (Button) findViewById(R.id.back_to_person);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MyTicketActivity.this, PersonActivity.class);
        startActivity(intent);
        super.onBackPressed();
        finish();
    }

    private void refreshTicket() {
        //1、获取Preferences
        SharedPreferences addrSetting = getSharedPreferences("addrSetting", 0);
        //2、取出数据
        String checked = addrSetting.getString("checked", "N");
        if (checked.equals("Y")) {
            String username = addrSetting.getString("user", "default");
            String startcity = addrSetting.getString("startcity","default");
            String finishcity = addrSetting.getString("finishcity","default");
            String date = addrSetting.getString("date", "default");
            String time = addrSetting.getString("time","default");
            String train = addrSetting.getString("train", "default");
            String room = addrSetting.getString("room", "default");
            String seat = addrSetting.getString("seat", "default");
            String checkwindow = addrSetting.getString("checkwindow","default");

            startCity.setText(startcity);
            finishCity.setText(finishcity);
            startDate.setText(date);
            startTime.setText(time);
            trainNum.setText(train);
            trainRoom.setText(room);
            trainSeat.setText(seat);
            checkWindow.setText(checkwindow);
            userName.setText(username);
        } else {
            tipsTextView.setText("暂无您的车票信息，请在出行信息设置中添加");
        }

    }
}
