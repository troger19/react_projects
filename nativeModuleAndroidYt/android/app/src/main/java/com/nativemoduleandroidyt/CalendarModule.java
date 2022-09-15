package com.nativemoduleandroidyt;

import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class CalendarModule extends ReactContextBaseJavaModule {
    private int eventCount = 0;

    CalendarModule(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "CalendarModule";
    }

    @ReactMethod
    public void createCalendarEvent() {
        Log.i("CalendarModule", "Log from Calendar Module");
    }

    @ReactMethod
    public void createCalendarEventCallback(Callback callback) {
        Log.i("CalendarModule", "Log from Calendar Module adnroid callback");
        callback.invoke("Data returned from Native calendar module");
    }

    @ReactMethod
    public void createCalendarPromise(Promise promise) {
        try {
            promise.resolve("Data returned from promise");
            eventCount+=1;
            sendEvent(getReactApplicationContext(), "EventCount", eventCount);
        } catch (Exception e) {
            promise.reject("Error returned from promise");
        }
    }

    private void sendEvent(ReactContext reactContext, String eventName, int params) {
        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }
}