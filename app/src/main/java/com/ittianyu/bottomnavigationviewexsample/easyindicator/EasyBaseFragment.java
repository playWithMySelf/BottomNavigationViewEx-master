package com.ittianyu.bottomnavigationviewexsample.easyindicator;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ittianyu.bottomnavigationviewexsample.R;
import com.luck.indicator.EasyIndicator;

/**
 * Created by yu on 2016/11/11.
 */

public class EasyBaseFragment extends Fragment {

    public final static String TAG = "RecyclerViewActivity";
    private EasyIndicator easy_indicator;
    private RecyclerView recyclerView;
    private Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.activity_recycler_view, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        easy_indicator = (EasyIndicator) view.findViewById(R.id.easy_indicator);
        easy_indicator.setTabTitles(new String[]{"已完成", "已评价"});
        easy_indicator.setOnTabClickListener(new EasyIndicator.onTabClickListener() {
            @Override
            public void onTabClick(String title, int position) {
                Log.i(TAG, title + "::" + position);
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new RecycleViewDivider(
                mContext, LinearLayoutManager.HORIZONTAL, dip2px(mContext, 0.5f), ContextCompat.getColor(mContext, R.color.line_color)));
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new MyAdapter(mContext));
        return view;
    }

    /**
     * dpתpx
     */
    public static int dip2px(Context ctx, float dpValue) {
        final float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
