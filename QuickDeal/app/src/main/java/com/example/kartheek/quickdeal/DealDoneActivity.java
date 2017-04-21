package com.example.kartheek.quickdeal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class DealDoneActivity extends AppCompatActivity {

    private Button notYet;

    private Button yes;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_done);

        notYet = (Button) findViewById(R.id.not_yet);
        yes = (Button) findViewById(R.id.yes);
        final int position = getIntent().getIntExtra("position",0);
        notYet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean deal = false;
                if (getIntent().getBooleanExtra("dealdone", false)) {
                    setDeal(position,deal);
                }
                NavUtils.navigateUpFromSameTask(DealDoneActivity.this);
                notYet.setTextColor(getApplicationContext().getResources().getColor(R.color.yes));
                notYet.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.unreadMessageTitleColor));
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean deal = true;
                if(getIntent().getBooleanExtra("dealdone",false)==false) {
                    setDeal(position,deal);
                }
                NavUtils.navigateUpFromSameTask(DealDoneActivity.this);
                yes.setTextColor(getApplicationContext().getResources().getColor(R.color.yes));
                yes.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.unreadMessageTitleColor));
            }
        });
    }

    public void setDeal(int position, boolean deal) {
        Log.e("DealDoneActivity",ChatActivity.ls.get(position).getmDealDone()+"");
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Deals",MODE_PRIVATE);
        //getting the length of the no of deals confirmed
        int length = sharedPreferences.getInt("position_size",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // putting in the current value of position at which the deal is confirmed and corresponding boolean
        editor.putInt("position" + "_" + length,position);
        editor.putBoolean("deals" + "_" + length,deal);
        length++;
        editor.putInt("position_size",length);
        editor.commit();
    }

}
