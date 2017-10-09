package com.ascend.wangfeng.imsi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.imei)
    TextView mImei;
    @BindView(R.id.imsi)
    TextView mImsi;
    @BindView(R.id.country)
    TextView mCountry;
    @BindView(R.id.mcn)
    TextView mMcn;
    @BindView(R.id.imei2)
    TextView mImei2;
    @BindView(R.id.imsi2)
    TextView mImsi2;
    @BindView(R.id.country2)
    TextView mCountry2;
    @BindView(R.id.mcn2)
    TextView mMcn2;
    @BindView(R.id.cart)
    LinearLayout mCart;
    @BindView(R.id.cart2)
    LinearLayout mCart2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        boolean isDouble = Imsi.isMultiSim(getBaseContext());
        if (!isDouble) {//一个卡槽
            String imsi = Imsi.getDefaultSubscriberId(getBaseContext());
            mImei.setText(Imsi.getDefaultDeviced(getBaseContext()));
            reSetExtra(imsi);
            mCart2.setVisibility(View.GONE);
        } else {
            int soltId = 0;
            int subId = Imsi.getSubId(soltId, getBaseContext());
            String imsi = Imsi.getSubscriberId(getBaseContext(), subId);
            Log.i("test", "initView: " + subId);
            mImei.setText(Imsi.getDeviced(getBaseContext(), soltId));
            reSetExtra(imsi);

            int soltId2 = 1;
            int subId2 = Imsi.getSubId(soltId2, getBaseContext());
            String imsi2 = Imsi.getSubscriberId(getBaseContext(), subId2);
            mImei2.setText(Imsi.getDeviced(getBaseContext(), soltId2));
            reSetExtra2(imsi2);

        }

    }

    private void reSetExtra(String imsi) {
        if (imsi != null && imsi.length() >= 6) {
            mImsi.setText(imsi);
            mCountry.setText(getImsiCuntry(imsi));
            mMcn.setText(getImsiOperator(imsi));
        } else {
            mImsi.setText("");
            mCountry.setText("");
            mMcn.setText("");
        }
    }

    private void reSetExtra2(String imsi) {
        if (imsi != null && imsi.length() >= 6) {
            mImsi2.setText(imsi);
            mCountry2.setText(getImsiCuntry(imsi));
            mMcn2.setText(getImsiOperator(imsi));
        } else {
            mImsi2.setText("");
            mCountry2.setText("");
            mMcn2.setText("");
        }
    }

    private String getImsiCuntry(String imsi) {
        String country = null;
        String mcc = imsi.substring(0, 3);
        if (mcc.equals("460")) {
            country = "CHINA";
        }
        return country;
    }

    private String getImsiOperator(String imsi) {
        String mnc = imsi.substring(3, 5);
        String operator = null;
        if (equals(mnc, new String[]{"00", "02", "04", "07"})) {
            operator = "中国移动";
        } else if (equals(mnc, new String[]{"01", "06", "09"})) {
            operator = "中国联通";
        } else if (equals(mnc, new String[]{"03", "05"})) {
            operator = "中国电信";
        } else if (equals(mnc, new String[]{"20"})) {
            operator = "中国铁通";
        } else {
            operator = "未知";
        }
        return operator;
    }

    private boolean equals(String target, String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            if (target.equals(strings[i])) return true;
        }
        return false;
    }

    @OnClick(R.id.cart)
    public void onViewClicked() {
    }
}