
package com.ronal2do;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.telecom.Call;

import com.cocosw.bottomsheet.BottomSheet;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

import org.json.JSONObject;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class RNActionSheetModule extends ReactContextBaseJavaModule {
    private boolean opened;
    private Callback shareSuccessCallback;
    private Callback shareFailureCallback;

    private final ReactApplicationContext reactContext;

    public RNActionSheetModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNActionSheet";
    }
  
    @ReactMethod
    public void show(ReadableMap options, final Callback onSelect) {
        if (this.opened) return;

        this.opened = true;

        ReadableArray optionArray = options.getArray("options");
        final Integer cancelButtonIndex = options.getInt("cancelButtonIndex");
        String title;
        boolean dark = false;
        BottomSheet.Builder builder;

        try {
            title = options.getString("title");
            builder = new BottomSheet.Builder(this.getCurrentActivity()).title(title);
        } catch (Exception e) {
            builder = new BottomSheet.Builder(this.getCurrentActivity());
        }

        try {
            dark = options.getBoolean("dark");
            if (dark) {
                builder.darkTheme();
            }
        } catch (Exception e) {
            // Code...
        }

        Integer size = optionArray.size();
        for (int i = 0; i < size; i++) {
            builder.sheet(i, optionArray.getString(i));
        }

        builder.listener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (which != cancelButtonIndex) {
                    onSelect.invoke(which);
                }
            }
        });

        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                RNActionSheetModule.this.opened = false;
            }
        });

        builder.build().show();
    }

    @ReactMethod
    public void showShare(ReadableMap options, Callback failureCallback, Callback successCallback) {
        String url = options.getString("url");
        String message = options.getString("message");
        String subject = options.getString("subject");

        List<String> items = new ArrayList<>();
        if (message != null && !message.isEmpty()) {
            items.add(message);
        }

        final Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        Uri uri = Uri.parse(url);
        if (uri != null) {
            if (uri.getScheme() != null && "data".equals(uri.getScheme().toLowerCase())) {
                shareIntent.setType("*/*");
                shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
            } else {
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_EMAIL, url);
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                shareIntent.putExtra(Intent.EXTRA_TEXT, message);
            }
        }

        this.shareSuccessCallback = successCallback;
        this.shareFailureCallback = failureCallback;

        if (shareIntent.resolveActivity(this.getCurrentActivity().getPackageManager()) != null) {
            this.getCurrentActivity().startActivity(Intent.createChooser(shareIntent, "Share To"));
        } else {
            failureCallback.invoke(new Exception("The app you want to share is not installed."));
        }
    }
    
}