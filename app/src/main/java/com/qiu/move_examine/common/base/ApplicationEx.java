package com.qiu.move_examine.common.base;

import android.app.Activity;
import android.app.Application;

import com.qiu.move_examine.common.AppContext;
import com.qiu.move_examine.netty.PushClient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author qiushengtao
 */
public class ApplicationEx extends Application {
    private static final String TAG = "ApplicationEx";

    public List<Activity> getActivities() {
        return activities;
    }

    //所有的activity列表
    private List<Activity> activities;

    public void addActivities(Activity a) {
        activities.add(a);
    }

    public void removeActivities(Activity a) {
        activities.remove(a);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        activities = new ArrayList<>();
        PushClient.create();
        AppContext.self().init(this);
    }

    public synchronized void closeAllActivity() {
        synchronized (activities) {
            Iterator<Activity> iter = activities.iterator();
            while (iter.hasNext()) {
                Activity activity = iter.next();
                iter.remove();
                if (activity != null && !activity.isFinishing()) {
                    activity.finish();
                }
            }
        }
    }
}
