package com.example.cansfit_sync;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    int [] newArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        newArray = new int[]{
                R.id.boat_pose,R.id.crunch_pose,R.id.dips_pose,R.id.bicycle_pose,R.id.leg_pose,R.id.heal_pose,
                R.id.leg_up_pose,R.id.sit_pose,R.id.v_ups_pose,R.id.plank_pose,R.id.plank_leg_pose,
                R.id.russian_pose,R.id.bridge_pose,R.id.leg_crunches_pose,R.id.mill_pose,

        };

    }

    public void Imagebuttonclicked(View view) {
        for (int i = 0; i < newArray.length; i++) {
            if(view.getId() == newArray[i]){
                int value = i+1;
                Log.i("FIRST",String.valueOf(value));
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("value",String.valueOf(value));
                startActivity(intent);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.id_privacy){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://cansfitsync.blogspot.com/2024/09/policies-for-our-fitness-app-at.html"));
            startActivity(intent);

            return true;
        }
        if(id==R.id.id_terms){

            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://cansfitsync.blogspot.com/2024/09/terms-and-conditions-for-our-fitness-app.html"));
            startActivity(intent);

            return true;
        }
        if(id==R.id.id_rate){

            try {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id=" + getPackageName())));
            }catch (Exception ex){
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
            }

            return true;
        }
        if(id==R.id.id_hospitals){

            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(""));
            startActivity(intent);

            return true;
        }
        if(id==R.id.id_share){

            Intent myintent = new Intent(Intent.ACTION_SEND);
            myintent.setType("text/plain");
            String shareBody = "This is the best App for Fitness \nBy this App you stretch your body \nThis is a free App download now \n" + "https://play.google.com/store/apps/details?id=com.example.cansfit_sync&hl=en";
            String sharehub = "Fitness App";
            myintent.putExtra(Intent.EXTRA_SUBJECT,sharehub);
            myintent.putExtra(Intent.EXTRA_TEXT,sharehub);
            startActivity(Intent.createChooser(myintent,"share using"));



            return true;
        }
        return true;
    }
}