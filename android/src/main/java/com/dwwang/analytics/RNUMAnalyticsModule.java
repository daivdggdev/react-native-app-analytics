package com.dwwang.analytics;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class RNUMAnalyticsModule extends ReactContextBaseJavaModule {
    private static final String TAG = "RNUMAnalyticsModule";
    ReactApplicationContext mContext;

    private final ActivityEventListener mActivityEventListener = new BaseActivityEventListener() {
        @Override
        public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
            UMShareAPI.get(mContext).onActivityResult(requestCode, resultCode, data);
        }
    };

    public RNUMAnalyticsModule(ReactApplicationContext context) {
        super(context);
        this.mContext = context;

        context.addActivityEventListener(mActivityEventListener);
    }

    @Override
    public String getName() {
        return "RNUMAnalytics";
    }

    @ReactMethod
    public void setUmSocialAppkey(String appKey) {
        UMShareAPI.init(mContext, appKey);

        // 三方获取用户资料时每次都要进行授权
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(mContext).setShareConfig(config);
    }

    @Override
    public Map<String, Object> getConstants() {
        UMShareAPI umShareAPI = UMShareAPI.get(mContext);
        Activity activity = mContext.getCurrentActivity();

        final Map<String, Object> constants = new HashMap<>();
        constants.put("isWXSupport", umShareAPI.isSupport(activity, SHARE_MEDIA.WEIXIN));
        constants.put("isQQSupport", umShareAPI.isSupport(activity, SHARE_MEDIA.QQ));
        constants.put("isSinaSupport", umShareAPI.isSupport(activity, SHARE_MEDIA.SINA));
        return constants;
    }
}
