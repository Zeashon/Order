package jne.com.post;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jne.com.R;
import jne.com.utils.HttpUtils;
import jne.com.utils.WeatherInfo;

import com.google.gson.Gson;

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
    private Button testBtn;
    private TextView city;
    private TextView updatetime;
    private TextView nowdate;
    private TextView temp;
    private TextView tempH;
    private TextView tempL;
    private TextView weathertype;
    private TextView cloth;
    private WeatherInfo weatherInfo;
    private TextView windpower;
    private TextView winddirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_ticket);

        initComponent();

        new Thread(weatherQueryTask).start();

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
//ticket
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
        testBtn = (Button) findViewById(R.id.test);
//weather
        city = (TextView) findViewById(R.id.city);
        updatetime = (TextView) findViewById(R.id.updatetime);
        nowdate = (TextView) findViewById(R.id.nowdate);
        temp = (TextView) findViewById(R.id.temp);
        tempH = (TextView) findViewById(R.id.tempHigh);
        tempL = (TextView) findViewById(R.id.tempLow);
        weathertype = (TextView) findViewById(R.id.weatherType);
        cloth = (TextView) findViewById(R.id.cloth);
        winddirect = (TextView) findViewById(R.id.winddirect);
        windpower = (TextView) findViewById(R.id.windpower);

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
            String startcity = addrSetting.getString("startcity", "default");
            String finishcity = addrSetting.getString("finishcity", "default");
            String date = addrSetting.getString("date", "default");
            String time = addrSetting.getString("time", "default");
            String train = addrSetting.getString("train", "default");
            String room = addrSetting.getString("room", "default");
            String seat = addrSetting.getString("seat", "default");
            String checkwindow = addrSetting.getString("checkwindow", "default");

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

    public void refreshWeather(WeatherInfo weatherInfo) {
        //天气查询和UI更新
        if (true) {//weatherInfo != null && weatherInfo.result != null
            city.setText(finishCity.getText().toString().trim());
            updatetime.setText("更新于" + weatherInfo.result.getUpdatetime());
            temp.setText(weatherInfo.result.getTemp());
            tempH.setText(weatherInfo.result.getTemphigh());
            tempL.setText(weatherInfo.result.getTemplow());
            cloth.setText( weatherInfo.result.index.get(6).getIvalue() +";"+ weatherInfo.result.index.get(6).getDetail() );
            windpower.setText(weatherInfo.result.getWindpower());
            winddirect.setText(weatherInfo.result.getWinddirect());
            weathertype.setText(weatherInfo.result.getWeather());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date curDate = new Date(System.currentTimeMillis());//获取当前时间
            nowdate.setText(formatter.format(curDate));
        } else {
            Toast.makeText(this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
        }
    }

    public void weatherQuery(String mcity, WeatherInfo weatherInfo) {
        String host = "http://jisutqybmf.market.alicloudapi.com";
        String path = "/weather/query";
        String method = "GET";
        String appcode = "ebf82f7ed7ce4bd1acc468d1fe1b78e4";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("city", mcity);
        querys.put("citycode", "citycode");
        querys.put("cityid", "cityid");
        querys.put("ip", "ip");
        querys.put("location", "location");

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            System.out.println(response.toString());
            //获取response的body
//            String json = EntityUtils.toString(response.getEntity());//文本内容过长被截断会导致json格式出错
//            System.out.println(json);
//            Log.e("weather data:",EntityUtils.toString(response.getEntity()));
            Gson gson = new Gson();
            final WeatherInfo wInfo = gson.fromJson(EntityUtils.toString(response.getEntity()), WeatherInfo.class);//
            final String jsonResult = gson.toJson(wInfo);
            if (wInfo == null) {
                Log.e("test", "failed");
                Log.e("weather"," 获取天气信息failed" );
            } else {
                MyTicketActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(MyTicketActivity.this, "Success",
                                Toast.LENGTH_LONG).show();
                        refreshWeather(wInfo);
                    }
                });
                Log.e("weather data:",jsonResult);
                //Log.e("test", wInfo.result.getCity() + " " + wInfo.result.getWeather() + " " + wInfo.result.getTemp() + " 更新于" + wInfo.result.getUpdatetime() + " 穿衣建议：" + wInfo.result.getIndex().get(6).getDetail());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            Log.i("mylog", "请求结果为-->" + val);
        }
    };

    Runnable weatherQueryTask = new Runnable() {

        @Override
        public void run() {
            // 在这里进行 http request.网络请求相关操作
            weatherQuery(finishCity.getText().toString(), weatherInfo);
            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("value", "test");
            msg.setData(data);
            handler.sendMessage(msg);
        }
    };

}
