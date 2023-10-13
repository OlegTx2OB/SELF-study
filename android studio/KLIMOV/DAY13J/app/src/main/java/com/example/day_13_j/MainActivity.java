package com.example.day_13_j;

import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.day_13_j.R;

public class MainActivity extends AppCompatActivity {

    public SeekBar seekBar;
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);

        int brightness = getBrightness();
        seekBar.setProgress(brightness);

        textView.setText("Screen Brightness: " + brightness);

        boolean canWrite = Settings.System.canWrite(this);

        // If app has no permission to write system settings
        if (!canWrite)
        {
            seekBar.setEnabled(false);
            allowWritePermission();
        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                textView.setText("Screen Brightness: " + progress);
                setBrightness(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }

    private void allowWritePermission()
    {
        Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    private int getBrightness() {
        return Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, 0);
    }

    private void setBrightness(int value) {
        Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, value);
    }
}