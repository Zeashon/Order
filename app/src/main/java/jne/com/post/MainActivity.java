package jne.com.post;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

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
    private Button NewPostBtn;
    private Button MessagePageBtn;
    private Button PersonalPageBtn;
    private Button MainPageBtn;
    private ImageView PromotionPageBtn;
    private Button ShopPageBtn;
    private Button qrScanBtn;

    //    top Ad
    private ViewPager viewPager;

    private ImageView[] tips;//提示性点点数组

    private int[] images;//图片ID数组

    private int currentPage = 0;//当前展示的页码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ordersDao = new OrderDao(this);
        if (!ordersDao.isDataExist()) {
            ordersDao.initTable();
        }

        initComponent();

//        top AD
        //图片ID数组

        images = new int[]{R.drawable.mypic_1, R.drawable.mypic_2, R.drawable.mypic_3, R.drawable.mypic_4, R.drawable.mypic_5};
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

                return im;

            }

        };

        viewPager.setAdapter(imgAdapter);
        
        
        orderList = ordersDao.getAllDate();
        if (orderList != null) {
            adapter = new OrderListAdapter(this, orderList);
            showPostListView.setAdapter(adapter);
        }

        SearchPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        NewPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewPostActivity.class);
                startActivity(intent);
            }
        });

        MainPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        PersonalPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PersonActivity.class);
                startActivity(intent);
            }
        });
        MessagePageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CollectionActivity.class);
                startActivity(intent);
            }
        });
        ShopPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });
        PromotionPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PromotionActivity.class);
                startActivity(intent);
            }
        });
        showPostListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "你点击了第" + position + "项条目");
            }
        });
        qrScanBtn = (Button) findViewById(R.id.qrScaner);
        qrScanBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i(TAG, "click to scan qrscan");
                Intent intent = new Intent(MainActivity.this, MipcaActivityCapture.class);
                //TODO deal with the result of scaner.
//                startActivityForResult(intent, Bitmap.Config.MainToMipca);
            }
        });

    }

    private void initComponent() {
        PromotionPageBtn = (ImageView) findViewById(R.id.AD_image);
        showPostListView = (ListView) findViewById(R.id.showPostListView);
//        showPostListView.addHeaderView(LayoutInflater.from(this).inflate(R.layout.show_post_item, null), null, false);
        SearchPageBtn = (LinearLayout) findViewById(R.id.main_top);
        NewPostBtn = (Button) findViewById(R.id.NewPostPageBtn);
        MessagePageBtn = (Button) findViewById(R.id.messagePageBtn);
        PersonalPageBtn = (Button) findViewById(R.id.personPageBtn);
        MainPageBtn = (Button) findViewById(R.id.IndexPageBtn);
        ShopPageBtn = (Button) findViewById(R.id.ShopPageBtn);
        viewPager = (ViewPager)findViewById(R.id.viewpager1);
    }

}
