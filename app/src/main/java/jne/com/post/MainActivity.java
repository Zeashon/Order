package jne.com.post;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import jne.com.R;
import jne.com.qr_codescan.MipcaActivityCapture;

public class MainActivity extends Activity {

    private String TAG = "Zeashon";
    private OrderDao ordersDao;
    private ListView showPostListView;
    private List<Order> orderList;
    private OrderListAdapter adapter;
    private LinearLayout SearchPageBtn;
    private LinearLayout NewPostBtn;
    private LinearLayout MessagePageBtn;
    private LinearLayout PersonalPageBtn;
    private LinearLayout MainPageBtn;
    private LinearLayout ShopPageBtn;
    private LinearLayout qrScanBtn;
    private ImageView PromotionPageBtn;

    //    top Ad
    private ViewPager viewPager;

    private ImageView[] tips;//提示性点点数组

    private int[] images;//图片ID数组

    private int currentPage = 0;//当前展示的页码

    private final static int SCANNIN_GREQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();
        ordersDao = new OrderDao(this);
        if (!ordersDao.isDataExist()) {
            ordersDao.initTable();
        }
        //刷新 列表
        refreshList();

//        top AD
        //图片ID数组

        images = new int[]{R.drawable.mypic_4, R.drawable.mypic_2, R.drawable.mypic_3, R.drawable.mypic_4, R.drawable.mypic_5};
        PagerAdapter imgAdapter = new PagerAdapter() {


            @Override
            public int getCount() {
                return images.length;

            }


            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;

            }


            @Override
            public void destroyItem(ViewGroup container, int position, Object o) {

                //container.removeViewAt(position);

            }

            //设置ViewPager指定位置要显示的view

            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                ImageView im = new ImageView(MainActivity.this);

                im.setImageResource(images[position]);

                im.setScaleType(ImageView.ScaleType.FIT_XY);

                container.addView(im);

                im.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, PromotionActivity.class);
                        startActivity(intent);
                    }
                });

                return im;

            }

        };

        viewPager.setAdapter(imgAdapter);


        SearchPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                finish();
            }
        });

        NewPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewPostActivity.class);
                startActivity(intent);
                finish();
            }
        });

        MainPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        PersonalPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PersonActivity.class);
                startActivity(intent);
                finish();
            }
        });
        MessagePageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CollectionActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ShopPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
                finish();
            }
        });
        PromotionPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PromotionActivity.class);
                startActivity(intent);
                finish();
            }
        });

        showPostListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                adapter.getView(position,view,showPostListView);
                Log.i(TAG, "你点击了第" + position + "项条目");
            }
        });

        qrScanBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i(TAG, "click to scan qrscan");
                Intent intent = new Intent(MainActivity.this, MipcaActivityCapture.class);
                // deal with the result of scaner in onActivityResult
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
            }
        });

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
                        String startcity,finishcity,date,time, train, room, seat,checkwindow;
                        if (checked.equals("Y")) {
                            try {
                                startcity = bundle.getString("startcity");
                                finishcity = bundle.getString("finishcity");
                                date = bundle.getString("date");
                                time = bundle.getString("time");
                                train = bundle.getString("train");
                                room = bundle.getString("room");
                                seat = bundle.getString("seat");
                                checkwindow = bundle.getString("checkwindow");
                                //   write to comfrence
                                //1、打开Preferences，名称为addSetting，如果存在则打开它，否则创建新的Preferences
                                SharedPreferences addrSetting = getSharedPreferences("addrSetting", Activity.MODE_PRIVATE);
                                //2、让addSetting处于编辑状态
                                SharedPreferences.Editor editor = addrSetting.edit();
                                //3、存放数据
                                editor.putString("startcity",startcity);
                                editor.putString("finishcity",finishcity);
                                editor.putString("date", date);
                                editor.putString("time", time);
                                editor.putString("train", train);
                                editor.putString("room", room);
                                editor.putString("seat", seat);
                                editor.putString("checkwindow",checkwindow);
                                //4、完成提交
                                editor.commit();
                                Toast.makeText(MainActivity.this, " " + date + " " + train + " " + room + " " + seat, Toast.LENGTH_LONG).show();
                                refreshList();
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


    private void initComponent() {
        PromotionPageBtn = (ImageView) findViewById(R.id.AD_image);
        showPostListView = (ListView) findViewById(R.id.showPostListView);
//        showPostListView.addHeaderView(LayoutInflater.from(this).inflate(R.layout.show_post_item, null), null, false);
        SearchPageBtn = (LinearLayout) findViewById(R.id.main_top);
        NewPostBtn = (LinearLayout) findViewById(R.id.NewPostPageBtn);
        MessagePageBtn = (LinearLayout) findViewById(R.id.messagePageBtn);
        PersonalPageBtn = (LinearLayout) findViewById(R.id.personPageBtn);
        MainPageBtn = (LinearLayout) findViewById(R.id.IndexPageBtn);
        ShopPageBtn = (LinearLayout) findViewById(R.id.ShopPageBtn);
        viewPager = (ViewPager) findViewById(R.id.viewpager1);
        qrScanBtn = (LinearLayout) findViewById(R.id.qrScaner);
    }

    private void refreshList() {
        //1、获取Preferences
        SharedPreferences addrSetting = getSharedPreferences("addrSetting", 0);
        //2、取出数据
        String checked = addrSetting.getString("checked", "N");
        if (checked.equals("Y")) {
            String date = addrSetting.getString("date", "default");
            String train = addrSetting.getString("train", "default");
            String room = addrSetting.getString("room", "default");
            String seat = addrSetting.getString("seat", "default");
            orderList = ordersDao.getTrainOrder(date + "&"  + train);
        } else {
            orderList = ordersDao.getAllDate();
        }
        if (orderList != null) {
            adapter = new OrderListAdapter(this, orderList);
            showPostListView.setAdapter(adapter);
        }

    }
}
