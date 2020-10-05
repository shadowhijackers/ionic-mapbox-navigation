package com.shadow.hijackers.mapbox.navigation;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.PluginResult;

@NativePlugin()
public class MapboxNav extends Plugin {

    protected static final int MAPBOX_NAVIGATION_UI = 123; // Unique request code

    @PluginMethod
    public void init(PluginCall call) {
        String accessToken = call.getString("accessToken");
        double destLong = Double.parseDouble(call.getString("destLong"));
        double destLat =  Double.parseDouble(call.getString("destLat"));

        Intent intent = new Intent("com.shadow.hijackers.mapbox.navigation.MapboxNavigationActivity");

        Bundle bundle = new Bundle();
        bundle.putString("accessToken", accessToken);
        bundle.putDouble("destLong", destLong);
        bundle.putDouble("destLat", destLat);

        intent.putExtras(bundle);
        startActivityForResult(call, intent, MAPBOX_NAVIGATION_UI);
    }

    @PluginMethod
    public void startNavigation(PluginCall call) {

    }

    // in order to handle the intents result, you have to @Override handleOnActivityResult
    @Override
    protected void handleOnActivityResult(int requestCode, int resultCode, Intent data) {
        super.handleOnActivityResult(requestCode, resultCode, data);

        // Get the previously saved call
        PluginCall savedCall = getSavedCall();

        if (savedCall == null) {
            return;
        }
        if (requestCode == MAPBOX_NAVIGATION_UI) {
            // Do something with the data
            PluginResult ret = new PluginResult();
            ret.put("value", data.getStringExtra("Data"));
            savedCall.successCallback(ret);
            Log.d("result", data.getStringExtra("Data").toString());
        }
    }
}
