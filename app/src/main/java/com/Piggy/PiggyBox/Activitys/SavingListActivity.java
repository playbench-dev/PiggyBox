package com.Piggy.PiggyBox.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.Piggy.PiggyBox.Adapters.ListViewAdapter;
import com.Piggy.PiggyBox.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SavingListActivity extends AppCompatActivity {
    Button buttonOk, buttonWithdraw;
    ListView listView;
    ImageView Img_back;
    TextView textView_reset,textView_date;
    ListViewAdapter adapter = new ListViewAdapter();
    SharedPreferences pref, pref1;
    private AdView mAdView;
    SharedPreferences.Editor editor, editor1;
    DialogActivity cd;
    SimpleDateFormat format1 = new SimpleDateFormat("MM.dd.yyyy");
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving_list);


        MobileAds.initialize(this, getString(R.string.admob_app_id));
        mAdView = findViewById(R.id.adView3);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                // 광고가 문제 없이 로드시 출력됩니다.
                Log.d("@@@", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                // 광고 로드에 문제가 있을시 출력됩니다.
                Log.d("@@@", "onAdFailedToLoad " + errorCode);
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

        long time = System.currentTimeMillis();
        String txt_date = format1.format(time);
        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        pref1 = getSharedPreferences("pref1", Activity.MODE_PRIVATE);
        editor1 = pref1.edit();
        editor = pref.edit();
    /*    buttonWithdraw = (Button)findViewById(R.id.withdraw);*/
        textView_reset = (TextView) findViewById(R.id.reset);
        textView_date = (TextView)findViewById(R.id.text_time);
        listView = (ListView) findViewById(R.id.listview);
        Img_back = (ImageView)findViewById(R.id.back_btn_2);

        textView_date.setText(txt_date);
     /*   buttonWithdraw.setEnabled(false);
        textView_reset.setEnabled(false);*/
     Img_back.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(SavingListActivity.this, HomeActivity.class);
             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
             startActivity(intent);
             finish();
         }
     });

        if (LoadData("pref_dayL") != null) {
            for (int i = LoadData("pref_dayL").size() - 1; i >= 0; i--)
                adapter.addItem(LoadData("pref_dayL").get(i), LoadData("pref_moneyL").get(i), LoadData("pref_savingL").get(i));
        }
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        buttonOk = (Button) findViewById(R.id.ok);
        final Calendar pickedDate = Calendar.getInstance();
        Calendar maxDate = Calendar.getInstance();
        final DatePickerDialog datePickerDialog = new DatePickerDialog(
                SavingListActivity.this, R.style.PreLollipopDatePickerStyle,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        newDate.set(year,month,dayOfMonth);
                        date = format1.format(newDate.getTime());
                        Log.i("dtttttate",""+ date);
                        Intent intent = new Intent(SavingListActivity.this,SavingActivity.class);
                        intent.putExtra("date",""+ date);
                        startActivity(intent);
                        finish();
                    }
                },
                //2018-2-12
                pickedDate.get(Calendar.YEAR),
                pickedDate.get(Calendar.MONTH),
                pickedDate.get(Calendar.DAY_OF_MONTH)
        );
        maxDate.set(pickedDate.get(Calendar.YEAR),pickedDate.get(Calendar.MONTH),pickedDate.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.setCancelable(false);

        datePickerDialog.getDatePicker().setMaxDate(maxDate.getTimeInMillis());
        Log.i("maxDate","" + maxDate.getTimeInMillis());
        textView_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                buttonWithdraw.setEnabled(true);
                textView_reset.setEnabled(true);
            }
        },500);
*/
       /* buttonWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SavingListActivity.this,WithdrawActivity.class);
               // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });*/
        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics(); //디바이스 화면크기를 구하기위해
        int width = dm.widthPixels; //디바이스 화면 너비
        int height = dm.heightPixels; //디바이스 화면 높이


        cd = new DialogActivity(this, new DialogActivity.ResetButtonListener() {
            @Override
            public void resetButton(boolean data) {
                if(data){
                    editor.clear();
                    editor.apply();
                    int count = adapter.getCount();
                    for (int i = count - 1; i >= 0; i--) {
                        adapter.remove(i);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
        WindowManager.LayoutParams wm = cd.getWindow().getAttributes();  //다이얼로그의 높이 너비 설정하기위해
        wm.copyFrom(cd.getWindow().getAttributes());  //여기서 설정한값을 그대로 다이얼로그에 넣겠다는의미
        cd.setCancelable(false);// 배경화면터치, 뒤로가기 버튼 막기
        wm.width = 1000;
        wm.height = height / 2;  //화면 높이의 절반



        textView_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cd.show();
            }
        });
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SavingListActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

    }



    private ArrayList<String> LoadData(String key) {
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString(key, "");
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        ArrayList<String> arrayList = gson.fromJson(json, type);

        return arrayList;
    }

}


