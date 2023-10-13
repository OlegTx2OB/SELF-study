package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    TextView textView;
    Button button;
    int firstTerm = 0;
    int secondTerm = 0;
    char sign = 'x';
    boolean isSignPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textField);
    }

    public void clickOnNumber(View view)
    {
        button = (Button) view;
        if(!isSignPressed)
        {
            firstTerm = (10 * firstTerm) + Integer.parseInt(button.getText().toString());
            textView.setText(String.valueOf(firstTerm));
        }
        else
        {
            secondTerm = (10 * secondTerm) + Integer.parseInt(button.getText().toString());
            textView.setText(String.valueOf(secondTerm));
        }
    }

    public void clickOnSign(View view)
    {
        isSignPressed = true;
        button = (Button) view;
        sign = button.getText().toString().charAt(0);
        textView.setText(String.valueOf(sign));
        secondTerm = 0;
    }

    public void clickOnClear(View view)
    {
        firstTerm = 0;
        secondTerm = 0;
        isSignPressed = false;
        sign = 'x';
        textView.setText(" ");
    }

    public void clickOnEquals(View view)
    {
        try
        {
            if(sign == '+') textView.setText(String.valueOf(firstTerm + secondTerm));
            else if(sign == '-') textView.setText(String.valueOf(firstTerm - secondTerm));
            else if(sign == 'x') textView.setText(String.valueOf(firstTerm * secondTerm));
            else if(sign == '÷') textView.setText(String.valueOf(firstTerm / secondTerm));
        }
        catch (Exception e)
        {
            textView.setText("error");
        }
        finally
        {
            firstTerm = 0;
            secondTerm = 0;
            sign = 'x';
            isSignPressed = false;
        }
    }

    public void clickOnEmptyButton(View view)
    {
        textView.setText("Tx2OB");
    }
}