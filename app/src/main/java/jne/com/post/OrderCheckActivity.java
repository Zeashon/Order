package jne.com.post;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import jne.com.R;
import jne.com.qr_codescan.MipcaActivityCapture;

public class OrderCheckActivity extends ShoppingCartActivity {

    private static final String TAG = "zeashon";
    private RecyclerView rvSelected;
    private TextView tvCount, tvCost, tvSubmit;
    private Button BTShopBtn;
    private Button DeliverOK;

    private GoodsItem item;
    private SparseArray<GoodsItem> selectedList;
    private GoodOrderedAdapter selectAdapter;
    private LinearLayout qrScanBtn;
    private final static int SCANNIN_GREQUEST_CODE = 1;

    private  TextView finishTime;
    private  TextView finishCity;
    private  TextView finishPlace;
    private  TextView hintOfCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去掉窗口标题
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_order_checking);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        double eachPrice[] = bundle.getDoubleArray("eachPrice");
        List<Integer> eachCount = bundle.getIntegerArrayList("eachCount");
        List<String> eachName = bundle.getStringArrayList("eachName");
        selectedList = new SparseArray<>();
        for (int i = 0; i < eachCount.size(); i++) {
            Log.e(TAG, "OrderCheckActivity i = " + i);
            item = new GoodsItem(i, eachName.get(i), eachPrice[i], eachCount.get(i));
            selectedList.append(item.id, item);
        }
        initView();
        tvCount.setText(bundle.getInt("size", 0) + "");
        tvCost.setText(bundle.getString("cost"));
    }

    private void initView() {
        rvSelected = (RecyclerView) findViewById(R.id.selectRecyclerView);
        tvCount = (TextView) findViewById(R.id.tvCount);
        tvCost = (TextView) findViewById(R.id.tvCost);
        tvSubmit = (TextView) findViewById(R.id.tvSubmit);
        BTShopBtn = (Button) findViewById(R.id.back_to_shop);
        qrScanBtn = (LinearLayout) findViewById(R.id.qrScaner);
        finishTime = (TextView) findViewById(R.id.finishTime);
        finishPlace = (TextView) findViewById(R.id.finishPlace);
        finishCity = (TextView) findViewById(R.id.finishCity);
        hintOfCheck = (TextView) findViewById(R.id.hintOfCheck);
        DeliverOK = (Button) findViewById(R.id.DeliverOK);
        showOrderInfo();

        BTShopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderCheckActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
                finish();
            }
        });
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrScanBtn.setVisibility(View.GONE);
                BTShopBtn.setVisibility(View.GONE);
                tvSubmit.setVisibility(View.GONE);
                hintOfCheck.setText("订单已下达，请等待配送");
                DeliverOK.setVisibility(View.VISIBLE);
            }
        });

        DeliverOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrderCheckActivity.this, "感谢使用我们的服务", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(OrderCheckActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        qrScanBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i(TAG, "click to scan qrscan");
                Intent intent = new Intent(OrderCheckActivity.this, MipcaActivityCapture.class);
                // deal with the result of scaner.
//                startActivityForResult(intent, Bitmap.Config.MainToMipca);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
            }
        });
    }

    private void showOrderInfo() {
        rvSelected.setLayoutManager(new LinearLayoutManager(this));
        selectAdapter = new GoodOrderedAdapter(OrderCheckActivity.this, selectedList);
        rvSelected.setAdapter(selectAdapter);
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
                        String date, train, room, seat;
                        if (checked.equals("Y")) {
                            try {
                                date = bundle.getString("date");
                                train = bundle.getString("train");
                                room = bundle.getString("room");
                                seat = bundle.getString("seat");
                                finishPlace.setText(train +"/"+room+"车"+"/"+seat);
                                String year, month, day;
                                year = date.substring(0, 4);
                                month = date.substring(4, 6);
                                day = date.substring(6, 8);
                                Log.e(TAG, year + ":" + month + ":" + day);
                                finishTime.setText(Integer.parseInt(year)+"."+Integer.parseInt(month)+"."+ Integer.parseInt(day));
                                finishCity.setText(bundle.getString("finishcity"));
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


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(OrderCheckActivity.this, ShoppingCartActivity.class);
        startActivity(intent);
        super.onBackPressed();
        finish();
    }

}
