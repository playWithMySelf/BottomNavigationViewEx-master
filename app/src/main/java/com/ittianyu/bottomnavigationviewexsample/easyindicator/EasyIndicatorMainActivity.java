package com.ittianyu.bottomnavigationviewexsample.easyindicator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.ittianyu.bottomnavigationviewexsample.R;

public class EasyIndicatorMainActivity extends AppCompatActivity {
    private TextView tab1, tab2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tab1 = (TextView) findViewById(R.id.tab1);
        tab2 = (TextView) findViewById(R.id.tab2);
        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EasyIndicatorMainActivity.this, RecyclerViewActivity.class));
            }
        });

        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EasyIndicatorMainActivity.this, ViewPageActivity.class));
            }
        });
    }
}
