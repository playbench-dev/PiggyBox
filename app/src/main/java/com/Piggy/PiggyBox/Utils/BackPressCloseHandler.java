package com.Piggy.PiggyBox.Utils;
import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.Piggy.PiggyBox.R;

public class BackPressCloseHandler{
    private long backKeyPressedTime = 0;
    private Toast toast;
    private Activity activity;

    public  BackPressCloseHandler(Activity context){
        this.activity = context;
    }
    public void onBackPressed(){
        if(System.currentTimeMillis()>backKeyPressedTime +2000){
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if(System.currentTimeMillis()<= backKeyPressedTime + 2000){
            activity.finish();
            toast.cancel();
        }
    }

    public void showGuide(){
        String back = activity.getResources().getString(R.string.back);
        toast = Toast.makeText(activity,back, Toast.LENGTH_SHORT);
        ViewGroup group  = (ViewGroup)toast.getView();
        TextView msTextView = (TextView) group.getChildAt(0);
        msTextView.setTextSize(17);
        toast.show();
    }
}
