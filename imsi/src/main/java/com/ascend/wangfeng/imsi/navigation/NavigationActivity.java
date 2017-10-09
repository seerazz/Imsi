package com.ascend.wangfeng.imsi.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ascend.wangfeng.imsi.MainActivity;
import com.ascend.wangfeng.imsi.R;
import com.ascend.wangfeng.imsi.wifi.WifiActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationActivity extends AppCompatActivity {

    @BindView(R.id.list)
    RecyclerView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);
        final List<ActivityItem> list =initData();
        NavigationAdapter adapter = new NavigationAdapter(list);
        adapter.setOnClickListener(new NavigationAdapter.ClickListen() {
            @Override
            public void onClick(View view, int position) {
                startActivity(new Intent(NavigationActivity.this, list.get(position).getUrl()));
            }
        });
        mList.setLayoutManager(new LinearLayoutManager(this));
        mList.setAdapter(adapter);
        mList.addItemDecoration(new MyDecoration(this));
    }

    private List<ActivityItem> initData() {
        ArrayList<ActivityItem> list =new ArrayList<>();
        list.add(new ActivityItem(getString(R.string.imsi_title), MainActivity.class));
        list.add(new ActivityItem(getString(R.string.wifi_title), WifiActivity.class));

        return list;
    }
}
