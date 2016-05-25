package com.rameshmkll.dnd;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.lang.reflect.Method;

/**
 * Created by MRamesh on 18-05-2016.
 */
public class IncomingCallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        telephony.listen(new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                super.onCallStateChanged(state, incomingNumber);
                System.out.println("incomingNumber : " + incomingNumber);
                Toast.makeText(context, "incomingNumber : " + incomingNumber, Toast.LENGTH_LONG).show();
                compareIncomingNumberAndBlockedNumber(incomingNumber, context);
            }
        }, PhoneStateListener.LISTEN_CALL_STATE);
    }

    private void compareIncomingNumberAndBlockedNumber(String incomingNumber, Context context) {
        SQLiteDatabase sqLiteDatabase = MyApplication.getDatabase((Activity) context);
        Cursor cursor = sqLiteDatabase.query("PHONE_NUMBERS", new String[]{"PHONE_NUMBER"}, null, null, null, null, null);
        if (cursor != null)
            Toast.makeText(context, cursor.getCount() + "", Toast.LENGTH_SHORT).show();
        if (cursor != null)
            while (cursor.moveToNext()) {
                String number = cursor.getString(cursor.getColumnIndex("PHONE_NUMBER"));
                if (incomingNumber.equals(number)) {
                    killCall(context);
                }
            }
    }

    public boolean killCall(Context context) {
        try {
            // Get the boring old TelephonyManager
            TelephonyManager telephonyManager =
                    (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

            // Get the getITelephony() method
            Class classTelephony = Class.forName(telephonyManager.getClass().getName());
            Method methodGetITelephony = classTelephony.getDeclaredMethod("getITelephony");

            // Ignore that the method is supposed to be private
            methodGetITelephony.setAccessible(true);

            // Invoke getITelephony() to get the ITelephony interface
            Object telephonyInterface = methodGetITelephony.invoke(telephonyManager);

            // Get the endCall method from ITelephony
            Class telephonyInterfaceClass =
                    Class.forName(telephonyInterface.getClass().getName());
            Method methodEndCall = telephonyInterfaceClass.getDeclaredMethod("endCall");

            // Invoke endCall()
            methodEndCall.invoke(telephonyInterface);

        } catch (Exception ex) { // Many things can go wrong with reflection calls
            // Log.d(TAG, "PhoneStateReceiver **" + ex.toString());
            return false;
        }
        return true;
    }

}