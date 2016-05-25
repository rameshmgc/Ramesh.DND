package com.rameshmkll.dnd;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by MRamesh on 20-05-2016.
 */
public class PowerConnected extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"power connected",Toast.LENGTH_LONG).show();
    }
}
