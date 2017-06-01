package com.ittianyu.bottomnavigationviewexsample.features.badgeview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.ittianyu.bottomnavigationviewexsample.R;
import com.ittianyu.bottomnavigationviewexsample.common.base.BaseFragment;
import com.ittianyu.bottomnavigationviewexsample.databinding.ActivityBadgeViewBinding;
import com.ittianyu.bottomnavigationviewexsample.easyindicator.EasyBaseFragment;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

import java.util.ArrayList;
import java.util.List;

public class BadgeViewActivity extends AppCompatActivity {
    private ActivityBadgeViewBinding bind;

    private BadgeViewActivity.VpAdapter adapter;
    private static final String TAG = BadgeViewActivity.class.getSimpleName();
    // collections
    private SparseIntArray items;// used for change ViewPager selected item
    private List<Fragment> fragments;// used for ViewPager adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_badge_view);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_badge_view);

        initView();
        initData();
        initEvent();
    }

    private void initView() {
        // disable all animations
        bind.bnve.enableAnimation(false);
        bind.bnve.enableShiftingMode(false);
        bind.bnve.enableItemShiftingMode(false);
        // add badge
        addBadgeAt(0, 1);//在menu菜单中第1个位置（即音乐）添加数字小红点
        addBadgeAt(2, 1);//在menu菜单中第三个位置（即朋友圈）添加数字小红点

    }

    private Badge addBadgeAt(int position, int number) {
        // add badge
        return new QBadgeView(this)
                .setBadgeNumber(number)
                .setGravityOffset(12, 2, true)
                .bindTarget(bind.bnve.getBottomNavigationItemView(position))
                .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                    @Override
                    public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                        if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState)
                            Toast.makeText(BadgeViewActivity.this, R.string.tips_badge_removed, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * create fragments
     */
    private void initData() {
        fragments = new ArrayList<>(5);
        items = new SparseIntArray(5);

        // create music fragment and add it
        EasyBaseFragment musicFragment = new EasyBaseFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", getString(R.string.music));

        // create backup fragment and add it
        BaseFragment backupFragment = new BaseFragment();
        bundle = new Bundle();
        bundle.putString("title", getString(R.string.backup));
        backupFragment.setArguments(bundle);

        // create friends fragment and add it
        BaseFragment friendsFragment = new BaseFragment();
        bundle = new Bundle();
        bundle.putString("title", getString(R.string.friends));
        friendsFragment.setArguments(bundle);

        BaseFragment FavorFragment = new BaseFragment();
        bundle = new Bundle();
        bundle.putString("title", getString(R.string.favor));
        FavorFragment.setArguments(bundle);

        BaseFragment VisibilityFragment = new BaseFragment();
        bundle = new Bundle();
        bundle.putString("title", getString(R.string.visibility));
        VisibilityFragment.setArguments(bundle);

        // add to fragments for adapter
        fragments.add(musicFragment);
        fragments.add(backupFragment);
        fragments.add(friendsFragment);
        fragments.add(FavorFragment);
        fragments.add(VisibilityFragment);

        // add to items for change ViewPager item
        items.put(R.id.i_music, 0);
        items.put(R.id.i_backup, 1);
        items.put(R.id.i_friends, 2);
        items.put(R.id.i_favor, 3);
        items.put(R.id.i_visibility, 4);

        // set adapter
        adapter = new BadgeViewActivity.VpAdapter(getSupportFragmentManager(), fragments);
        bind.vp.setAdapter(adapter);
    }

    /**
     * set listeners
     */
    private void initEvent() {
        // set listener to change the current item of view pager when click bottom nav item
        bind.bnve.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            private int previousPosition = -1;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                // you can write as above.
                // I recommend this method. You can change the item order or counts without update code here.
                int position = items.get(item.getItemId());
                if (previousPosition != position) {
                    // only set item when item changed
                    previousPosition = position;
                    Log.i(TAG, "-----bnve-------- previous item:" + bind.bnve.getCurrentItem() + " current item:" + position + " ------------------");
                    bind.vp.setCurrentItem(position);
                }
                return true;
            }
        });

        // set listener to change the current checked item of bottom nav when scroll view pager
        bind.vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, "-----ViewPager-------- previous item:" + bind.bnve.getCurrentItem() + " current item:" + position + " ------------------");
                bind.bnve.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * view pager adapter
     */
    private static class VpAdapter extends FragmentPagerAdapter {
        private List<Fragment> data;

        public VpAdapter(FragmentManager fm, List<Fragment> data) {
            super(fm);
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Fragment getItem(int position) {
            return data.get(position);
        }
    }

}
