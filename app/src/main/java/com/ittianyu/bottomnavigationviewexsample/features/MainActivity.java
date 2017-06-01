package com.ittianyu.bottomnavigationviewexsample.features;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.ittianyu.bottomnavigationviewexsample.R;
import com.ittianyu.bottomnavigationviewexsample.databinding.ActivityMainBinding;
import com.ittianyu.bottomnavigationviewexsample.easyindicator.EasyIndicatorMainActivity;
import com.ittianyu.bottomnavigationviewexsample.features.badgeview.BadgeViewActivity;
import com.ittianyu.bottomnavigationviewexsample.features.setupwithviewpager.SetupWithViewPagerActivity;
import com.ittianyu.bottomnavigationviewexsample.features.style.StyleActivity;
import com.ittianyu.bottomnavigationviewexsample.features.viewpager.WithViewPagerActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding bind;
    private Button btnStyle,btnWithViewPager,btnSetupWithViewPager,btnBadgeView,btnEasyindicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_main);
        btnStyle = (Button)findViewById(R.id.btn_style);
        btnWithViewPager = (Button)findViewById(R.id.btn_with_view_pager);
        btnSetupWithViewPager = (Button)findViewById(R.id.btn_setup_with_view_pager);
        btnBadgeView = (Button)findViewById(R.id.btn_badge_view);
        btnEasyindicator = (Button)findViewById(R.id.btn_easyindicator);
        //bind = DataBindingUtil.setContentView(this, R.layout.activity_bottom_main);

        init();
    }

    private void init() {
        /*bind.btnStyle.setOnClickListener(this);
        bind.btnWithViewPager.setOnClickListener(this);
        bind.btnSetupWithViewPager.setOnClickListener(this);
        bind.btnBadgeView.setOnClickListener(this);
        bind.btnEasyindicator.setOnClickListener(this);*/

        btnStyle.setOnClickListener(this);
        btnWithViewPager.setOnClickListener(this);
        btnSetupWithViewPager.setOnClickListener(this);
        btnBadgeView.setOnClickListener(this);
        btnEasyindicator.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_style:
                startActivity(new Intent(this, StyleActivity.class));
                break;
            case R.id.btn_with_view_pager:
                startActivity(new Intent(this, WithViewPagerActivity.class));
                break;
            case R.id.btn_setup_with_view_pager:
                startActivity(new Intent(this, SetupWithViewPagerActivity.class));
                break;
            case R.id.btn_badge_view:
                startActivity(new Intent(this, BadgeViewActivity.class));
                break;
            case R.id.btn_easyindicator:
                startActivity(new Intent(this, EasyIndicatorMainActivity.class));
                break;

        }
    }
}
