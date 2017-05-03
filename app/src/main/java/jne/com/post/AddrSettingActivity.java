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
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import jne.com.R;
import jne.com.qr_codescan.MipcaActivityCapture;

public class AddrSettingActivity extends Activity {

    private String TAG = "AddrSettingActivity";
    private Button addr_ok_btn;
    private EditText no_of_train_et;
    private EditText no_of_room_et;
    private EditText no_of_seat_et;
    private DatePicker checkTime_dp;
    private Button qrScanBtn;

    private final static int SCANNIN_GREQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_addr_setting);

        initComponent();

        addr_ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String train, room, seat, time;
                train = no_of_train_et.getText().toString();
                room = no_of_room_et.getText().toString();
                seat = no_of_seat_et.getText().toString();
                //      get Time
                String month, day;
                if (checkTime_dp.getMonth() + 1 < 10) {
                    month = "0" + (checkTime_dp.getMonth() + 1);
                } else {
                    month = (checkTime_dp.getMonth() + 1) + "";
                }
                if (checkTime_dp.getDayOfMonth() < 10) {
                    day = "0" + checkTime_dp.getDayOfMonth();
                } else {
                    day = checkTime_dp.getDayOfMonth() + "";
                }
                time = checkTime_dp.getYear() + "" + month + day;

                Log.e(TAG, time);
                //        get nowTime
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                String nowTime = formatter.format(curDate);

                if (time.compareTo(nowTime) < 0) {
                    Toast.makeText(AddrSettingActivity.this, "发车时间不可晚于今天。", Toast.LENGTH_LONG).show();
                    return;
                } else if (train.length() == 0) {
                    Toast.makeText(AddrSettingActivity.this, "请填写车次。", Toast.LENGTH_LONG).show();
                    return;
                } else if (room.length() == 0) {
                    Toast.makeText(AddrSettingActivity.this, "请填写车厢。", Toast.LENGTH_LONG).show();
                    return;
                } else if (seat.length() == 0) {
                    Toast.makeText(AddrSettingActivity.this, "请填写座位号。", Toast.LENGTH_LONG).show();
                    return;
                }

                //      get user
                String user = "Zeashon";

                //TODO   write to comfrence
                //1、打开Preferences，名称为addSetting，如果存在则打开它，否则创建新的Preferences
                SharedPreferences addrSetting = getSharedPreferences("addrSetting", Activity.MODE_PRIVATE);
                //2、让addSetting处于编辑状态
                SharedPreferences.Editor editor = addrSetting.edit();
                //3、存放数据
                editor.putString("checked", "Y");
                editor.putString("user", user);
                editor.putString("time", time);
                editor.putString("train", train);
                editor.putString("room", room);
                editor.putString("seat", seat);
                //4、完成提交
                editor.commit();

                Toast.makeText(AddrSettingActivity.this, user + " " + time + " " + train + " " + room + " " + seat, Toast.LENGTH_LONG).show();
                //      turn back to PA
                Intent intent = new Intent(AddrSettingActivity.this, PersonActivity.class);
                startActivity(intent);
            }
        });
        qrScanBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i(TAG, "click to scan qrscan");
                Intent intent = new Intent(AddrSettingActivity.this, MipcaActivityCapture.class);
                // deal with the result of scaner.
//                startActivityForResult(intent, Bitmap.Config.MainToMipca);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
            }
        });


    }

    private void initComponent() {
        checkTime_dp = (DatePicker) findViewById(R.id.checkTime);
        addr_ok_btn = (Button) findViewById(R.id.addr_okBtn);
        no_of_train_et = (EditText) findViewById(R.id.no_of_train);
        no_of_room_et = (EditText) findViewById(R.id.no_of_room);
        no_of_seat_et = (EditText) findViewById(R.id.no_of_seat);
        qrScanBtn = (Button) findViewById(R.id.qrScaner);
    }

    //回调
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle = null;
        switch (requestCode) {
            case SCANNIN_GREQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    try {
                        bundle = data.getExtras();//防止空返回
                        //显示扫描到的内容
                        String scanInfo = bundle.getString("result").toString();
                        Log.i(TAG, "Main-resultString" + scanInfo);
                        Toast.makeText(this, scanInfo, Toast.LENGTH_LONG).show();
                        String checked = bundle.getString("checked");
                        String time, train, room, seat;
                        if (checked.equals("Y")) {
                            try {
                                time = bundle.getString("time");
                                train = bundle.getString("train");
                                room = bundle.getString("room");
                                seat = bundle.getString("seat");
                                no_of_train_et.setText(train);
                                no_of_room_et.setText(room);
                                no_of_seat_et.setText(seat);
                                String year, month, day;
                                year = time.substring(0, 4);
                                month = time.substring(4, 6);
                                day = time.substring(6, 8);
                                Log.e(TAG,year+":"+month+":"+day);
                                checkTime_dp.updateDate(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day));
                            } catch (Exception e) {
                                return;
                            }
                        } else {
                            Toast.makeText(this, "获取车票信息失败", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        return;
                    }
                }
                break;
        }


    }

}
