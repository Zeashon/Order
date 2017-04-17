package jne.com.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import jne.com.R;
import jne.com.base.BaseActivity;
import jne.com.base.BaseController;
import jne.com.context.AppContext;
import jne.com.controller.MainController;
import jne.com.ui.Display;
import jne.com.ui.fragment.OrdersFragment;
import jne.com.ui.fragment.ShopFragment;
import jne.com.ui.fragment.UserCenterFragment;
import jne.com.util.ActivityStack;
import jne.com.util.ContentView;
import jne.com.util.DoubleExitUtil;
import jne.com.util.MainTab;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import butterknife.Bind;


@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity<MainController.MainUiCallbacks>
        implements MainController.MainHomeUi {

    @Bind(R.id.viewpager)
    ViewPager mViewPager;

    @Bind(R.id.viewpager_tab)
    SmartTabLayout mViewpagerTab;

    private DoubleExitUtil mDoubleClickExit;

    @Override
    protected BaseController getController() {
        return AppContext.getContext().getMainController();
    }

    @Override
    protected void initializeViews(Bundle savedInstanceState) {
        final LayoutInflater inflater = LayoutInflater.from(this);
        final int[] tabIcons = {R.drawable.tab_ic_home, R.drawable.tab_ic_orders, R.drawable.tab_ic_me};
        final int[] tabTitles = {R.string.tab_home, R.string.tab_orders, R.string.tab_me};
        FragmentPagerItems pages = FragmentPagerItems.with(this)
                .add(R.string.tab_home, ShopFragment.class)
                .add(R.string.tab_orders, OrdersFragment.class)
                .add(R.string.tab_me, UserCenterFragment.class)
                .create();
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(),
                pages);

        mViewPager.setOffscreenPageLimit(pages.size());
        mViewPager.setAdapter(adapter);
        mViewpagerTab.setCustomTabView(new SmartTabLayout.TabProvider() {
            @Override
            public View createTabView(ViewGroup container, int position, PagerAdapter adapter) {
                View view = inflater.inflate(R.layout.layout_navigation_bottom_item, container, false);
                ImageView iconView = (ImageView) view.findViewById(R.id.img_icon);
                iconView.setBackgroundResource(tabIcons[position % tabIcons.length]);
                TextView titleView = (TextView) view.findViewById(R.id.txt_title);
                titleView.setText(tabTitles[position % tabTitles.length]);
                return view;
            }
        });
        mViewpagerTab.setViewPager(mViewPager);
        mDoubleClickExit = new DoubleExitUtil(this);
    }

    @Override
    protected void handleIntent(Intent intent, Display display) {
        if (intent != null && intent.hasExtra(Display.PARAM_OBJ)) {
            MainTab tab = (MainTab) intent.getSerializableExtra(Display.PARAM_OBJ);
            switch (tab) {
                default:
                case SHOP:
                    mViewPager.setCurrentItem(0);
                    break;
                case ORDERS:
                    mViewPager.setCurrentItem(1);
                    break;
                case PERSON:
                    mViewPager.setCurrentItem(2);
                    break;
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 是否退出应用
            boolean exit = mDoubleClickExit.onKeyDown(keyCode, event);
            if (exit) {
                ActivityStack.create().appExit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
