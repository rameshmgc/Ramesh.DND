package com.rameshmkll.dnd.utils;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.rameshmkll.dnd.MyApplication;
import com.rameshmkll.dnd.R;

/**
 * Created by MRamesh on 18-05-2016.
 */
public class Utilities {
    public static void shakeView(Context c,View v)
    {
        Animation utils= AnimationUtils.loadAnimation(c, R.anim.shake);
        v.startAnimation(utils);
    }



    public static Snackbar showSnackBarLongTime2(Activity act,String msg){

        return  Snackbar.make(act.findViewById(android.R.id.content),msg,Snackbar.LENGTH_LONG);

    }


}
