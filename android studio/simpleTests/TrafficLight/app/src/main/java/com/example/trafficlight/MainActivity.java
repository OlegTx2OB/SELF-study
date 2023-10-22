package com.example.trafficlight;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity
{
    private LinearLayout red, yellow, green;

    private boolean cycleIsAlive = false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        red = findViewById(R.id.red);
        yellow = findViewById(R.id.yellow);
        green = findViewById(R.id.green);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        cycleIsAlive = false;
    }

    public void onClickStart(View view)
    {
        cycleIsAlive = !cycleIsAlive;
        if(cycleIsAlive)
        {
            new Thread(() ->
            {
                while (cycleIsAlive) colorsSwitch();
                red.setBackgroundColor(Color.GRAY);
                yellow.setBackgroundColor(Color.GRAY);
                green.setBackgroundColor(Color.GRAY);
            }).start();
        }
    }
    public void colorsSwitch()
    {
        red.setBackgroundColor(Color.RED);
        green.setBackgroundColor(Color.GRAY);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        red.setBackgroundColor(Color.GRAY);
        yellow.setBackgroundColor(Color.YELLOW);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        yellow.setBackgroundColor(Color.GRAY);
        green.setBackgroundColor(Color.GREEN);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}