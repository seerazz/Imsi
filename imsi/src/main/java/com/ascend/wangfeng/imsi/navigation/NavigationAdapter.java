package com.ascend.wangfeng.imsi.navigation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ascend.wangfeng.imsi.R;

import java.util.List;

/**
 * Created by fengye on 2017/9/12.
 * email 1040441325@qq.com
 */

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.ViewHolder>{
    private List<ActivityItem> mTitles;
    private ClickListen  mListen;

    public NavigationAdapter(List<ActivityItem> titles) {
        mTitles = titles;
    }
    public void setOnClickListener(ClickListen clickListen){
        this.mListen =clickListen;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_navigation,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text.setText((position+1)+".  "+mTitles.get(position).getTitle());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView text ;
        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListen!=null){
                mListen.onClick(v, (Integer) itemView.getTag());
            }
        }
    }
    public interface ClickListen {
        void onClick(View view,int position);
    }
}
