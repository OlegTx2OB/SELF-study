package com.example.day_6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    TextView mTextView;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textView);
    }

    public void onClickButton(View view)
    {
        mTextView.setText(String.valueOf(++count));
    }

    public void onClickIntent(View view)
    {
        Intent intent = new Intent(this, AboutActivity.class);

        intent.putExtra("count", count);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        startActivity(intent);
    }


}