package com.ascend.wangfeng.imsi.navigation;

/**
 * Created by fengye on 2017/9/12.
 * email 1040441325@qq.com
 */

public class ActivityItem {
    private String title;
    private Class url;

    public ActivityItem(String title, Class url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class getUrl() {
        return url;
    }

    public void setUrl(Class url) {
        this.url = url;
    }
}
