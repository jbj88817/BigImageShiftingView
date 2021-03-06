package us.bojie.bigimageview;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TextView tvIntro;
    private LinearLayout dotLayout;
    private ArrayList<Ad> list = new ArrayList<>();
    private Handler handle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            handle.sendEmptyMessageDelayed(0, 4000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
        initData();
    }


    private void initView() {
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tvIntro = (TextView) findViewById(R.id.tv_intro);
        dotLayout = (LinearLayout) findViewById(R.id.dot_layout);
    }

    private void initListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updateIntroAndDot();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void initData() {
        list.add(new Ad(R.drawable.first, "李荣浩：我的爱情不在歌里"));
        list.add(new Ad(R.drawable.second, "百合大法好"));
        list.add(new Ad(R.drawable.third, "周杰伦最新单曲：英雄"));
        list.add(new Ad(R.drawable.fourth, "9积分抢票"));
        list.add(new Ad(R.drawable.five, "我就是喜欢那只萌宠"));

        initDots();
        viewPager.setAdapter(new MyPagerAdapter());

        int centerValue = Integer.MAX_VALUE / 2;
        int offsetValue = centerValue % list.size();
        viewPager.setCurrentItem(centerValue - offsetValue);

        updateIntroAndDot();

        handle.sendEmptyMessageDelayed(0, 4000);
    }

    private void initDots() {
        for (int i = 0; i < list.size(); i++) {
            View view = new View(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(16, 16);
            if (i != 0) {
                params.leftMargin = 8;
            }
            view.setLayoutParams(params);
            view.setBackgroundResource(R.drawable.selector_dot);
            dotLayout.addView(view); //init dot
        }
    }

    // Update text
    private void updateIntroAndDot() {
        int currentPage = viewPager.getCurrentItem() % list.size();
        tvIntro.setText(list.get(currentPage).getIntro());

        for (int i = 0; i < dotLayout.getChildCount(); i++) {
            dotLayout.getChildAt(i).setEnabled(i == currentPage);
        }
    }

    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        /**
         * true: don't re-create, use cache  false: to create
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * Similar to BaseAdapter's getView
         * Set data to view
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(MainActivity.this, R.layout.adapter_ad, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.image);
            imageView.setImageResource(list.get(position % list.size()).getIconResId());

            container.addView(view);
            return view;
        }

        /**
         * @param container viewPager self
         * @param position  for destroy
         * @param object
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }

    @Override
    protected void onDestroy() {
        viewPager.clearOnPageChangeListeners();
        super.onDestroy();
    }
}
