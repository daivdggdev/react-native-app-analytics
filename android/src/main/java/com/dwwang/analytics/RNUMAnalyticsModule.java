package com.dwwang.analytics;

import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.MobclickAgent.UMAnalyticsConfig;

import java.util.HashMap;
import java.util.Map;


public class RNUMAnalyticsModule extends ReactContextBaseJavaModule {
    private static final String TAG = "RNUMAnalyticsModule";
    ReactApplicationContext mContext;

    private LifecycleEventListener mListener = new LifecycleEventListener() {
        @Override
        public void onHostResume() {
            MobclickAgent.onResume(mContext);
        }

        @Override
        public void onHostPause() {
            MobclickAgent.onPause(mContext);
        }

        @Override
        public void onHostDestroy() {

        }
    };

    public RNUMAnalyticsModule(ReactApplicationContext context) {
        super(context);

        this.mContext = context;
        this.mContext.addLifecycleEventListener(mListener);
    }

    @Override
    public String getName() {
        return "RNUMAnalytics";
    }

    @ReactMethod
    public void startWithConfigure(String appKey, String channelId) {
        UMAnalyticsConfig config = new UMAnalyticsConfig(mContext, appKey, channelId);
        MobclickAgent.startWithConfigure(config);
    }

    @ReactMethod
    public void onEvent(String eventId) {
        MobclickAgent.onEvent(mContext, eventId);
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        return constants;
    }
}
