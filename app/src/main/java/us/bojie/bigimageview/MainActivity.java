package us.bojie.bigimageview;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ArrayList<Ad> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    private void initData() {
        list.add(new Ad(R.drawable.first, "李荣浩：我的爱情不在歌里"));
        list.add(new Ad(R.drawable.second, "百合大法好"));
        list.add(new Ad(R.drawable.third, "周杰伦最新单曲：英雄"));
        list.add(new Ad(R.drawable.fourth, "9积分抢票"));
        list.add(new Ad(R.drawable.five, "我就是喜欢那只萌宠"));
    }

    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 0;
        }

        /**
         *
         * true: don't re-create, use cache  false: to create
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         *
         * Similar to BaseAdapter's getView
         * Set data to view
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(MainActivity.this, R.layout.adapter_ad, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.image);
            return super.instantiateItem(container, position);
        }


        /**
         *
         * @param container viewPager self
         * @param position for destroy
         * @param object
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }
}
