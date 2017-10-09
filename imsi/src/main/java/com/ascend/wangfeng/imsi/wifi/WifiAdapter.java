package com.ascend.wangfeng.imsi.wifi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ascend.wangfeng.imsi.R;

import java.util.List;

/**
 * Created by fengye on 2017/9/12.
 * email 1040441325@qq.com
 */

public class WifiAdapter extends BaseAdapter{
    private final List<WifiInfo> mData;
    private final Context mContext;

    public WifiAdapter(List<WifiInfo> data,Context context) {
        mData = data;
        this.mContext =context;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup group) {
        ViewHolder holder;
        if (view ==null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_wifi,null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.nameTv.setText(mData.get(i).getName());
        holder.passwordTv.setText(mData.get(i).getPassword());
        return view;
    }
    class ViewHolder{
        final TextView nameTv;
        final TextView passwordTv;

        public ViewHolder(View view) {
            this.nameTv = view.findViewById(R.id.name);
            this.passwordTv = view.findViewById(R.id.password);
        }
    }
}
