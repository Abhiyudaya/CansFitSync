package com.example.cansfit_sync;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ThirdActivity extends AppCompatActivity {

    String buttonvalue;
    Button startBtn;
    private CountDownTimer countDownTimer;
    TextView mtextview;
    private boolean MTimeRunning;
    private long MTimeLeftInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        Intent intent = getIntent();
        buttonvalue = intent.getStringExtra("value");

        int intvalue = Integer.parseInt(buttonvalue);

        switch (intvalue){
            case 1:
                setContentView(R.layout.activity_climber);
                break;
            case 2:
                setContentView(R.layout.activity_crunch);
                break;
            case 3:
                setContentView(R.layout.activity_dips);
                break;
            case 4:
                setContentView(R.layout.activity_bicycle_crunch);
                break;
            case 5:
                setContentView(R.layout.activity_leg_raise);
                break;
            case 6:
                setContentView(R.layout.activity_heal_touch);
                break;
            case 7:
                setContentView(R.layout.activity_leg_up_crunch);
                break;
            case 8:
                setContentView(R.layout.activity_sit_up);
                break;
            case 9:
                setContentView(R.layout.activity_v_ups);
                break;
            case 10:
                setContentView(R.layout.activity_rotation);
                break;
            case 11:
                setContentView(R.layout.activity_plank_leg_lift);
                break;
            case 12:
                setContentView(R.layout.activity_russian_twist);
                break;
            case 13:
                setContentView(R.layout.activity_bridge);
                break;
            case 14:
                setContentView(R.layout.activity_vertical_leg_crunch);
                break;
            default:
                setContentView(R.layout.activity_windmill);
                break;

        }

        startBtn = findViewById(R.id.startButton);
        mtextview = findViewById(R.id.time);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MTimeRunning){
                    stopTimer();
                }
                else{
                    startTimer();
                }
            }
        });


    }
    private void stopTimer(){
        countDownTimer.cancel();
        MTimeRunning=false;
        startBtn.setText("START");
    }
    private void startTimer(){
        final CharSequence value1 = mtextview.getText();
        String num1 = value1.toString();
        String num2 = num1.substring(0,2);
        String num3 = num1.substring(3,5);

        final int num = Integer.valueOf(num2)*60 + Integer.valueOf(num3);
        MTimeLeftInMillis = num*1000;


        countDownTimer = new CountDownTimer(MTimeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                MTimeLeftInMillis = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                int newValue = Integer.valueOf(buttonvalue)+1;
                if(newValue<=7){
                    Intent intent = new Intent(ThirdActivity.this,ThirdActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value",String.valueOf(newValue));
                    startActivity(intent);
                }
                else{
                    newValue = 1;
                    Intent intent = new Intent(ThirdActivity.this,ThirdActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value",String.valueOf(newValue));
                    startActivity(intent);
                }
            }
        }.start();
        startBtn.setText("Pause");
        MTimeRunning=true;
    }

    private void updateTimer(){
        int minutes = (int)MTimeLeftInMillis / 60000;
        int seconds = (int)MTimeLeftInMillis % 60000 / 1000;

        String timeLeftText = "";
        if(minutes<10)
            timeLeftText="0";
        timeLeftText = timeLeftText+minutes+":";
        if(seconds<10)
            timeLeftText+="0";
        timeLeftText+=seconds;
        mtextview.setText(timeLeftText);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}