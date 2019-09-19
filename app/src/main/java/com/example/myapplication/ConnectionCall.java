package com.example.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.widget.Button;


public class ConnectionCall {
    private Context context;

    public ConnectionCall(Context context) {
        this.context = context;
    }

    public boolean isConnectingToInternet() {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }

    public boolean connectiondetect() {
        if (isConnectingToInternet()) {
            return true;
        } else {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_connectionfailed);
            Button ok = (Button) dialog.findViewById(R.id.dialog_ok);
            Button cancel = (Button) dialog.findViewById(R.id.dialog_cancel);
            dialog.show();
            dialog.setCancelable(false);

            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    connectiondetect();
                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    context.startActivity(new Intent(Settings.ACTION_SETTINGS));

                }
            });
            return false;
        }
    }
}