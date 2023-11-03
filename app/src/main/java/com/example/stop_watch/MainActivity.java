package com.example.stop_watch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int seconds;
    private boolean running;
    private boolean wasrunning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            savedInstanceState.getInt("seconds");
            savedInstanceState.getBoolean("running");
            savedInstanceState.getBoolean("wasrunning");

        }

        runTimmer();
    }

    public void onplay(View view){
        running=true;

    }
    public void onstop(View view){
        running=false;

    }
    public void onresume(View view){
        running=false;
        seconds=0;

    }
    @Override
    protected void onStop(){
        super.onStop();
        wasrunning = running;
        running = false;
    }

    @Override
    protected void onResume(){
        super.onResume();
        if (wasrunning){
            running=true;

        }

    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState );
        outState.putInt("seconds",seconds);
        outState.putBoolean("running",running);
        outState.putBoolean("wasrunning",wasrunning);
    }

    private void runTimmer(){

        TextView timeView = findViewById(R.id.text);
        Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int min = (seconds % 3600)/60;
                int sec = (seconds%60);

                String time = String.format(Locale.getDefault(),
                                          "%d:%02d:%02d",
                                                  hours,min,sec);

                timeView.setText(time);

                if (running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }



}