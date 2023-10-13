package com.example.day11_j; // Добавьте эту строку с объявлением пакета

import android.content.ClipData;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        TextView textView = findViewById(R.id.textView);
        ImageView imageView = findViewById(R.id.imageView);

        button.setOnClickListener(viewClickListener);
        textView.setOnClickListener(viewClickListener);
        imageView.setOnClickListener(viewClickListener);
    }

    View.OnClickListener viewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showPopupMenu(v);
        }
    };

    private void showPopupMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.menu_main);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {

                //ClipData.Item
                switch (item.getItemId()) {
                    case R.id.menu1:
                        Toast.makeText(getApplicationContext(),
                                "Вы выбрали PopupMenu 1",
                                Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.menu2:
                        Toast.makeText(getApplicationContext(),
                                "Вы выбрали PopupMenu 2",
                                Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.menu3:
                        Toast.makeText(getApplicationContext(),
                                "Вы выбрали PopupMenu 3",
                                Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;

                        if(true)
                }
            }
        });

        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                Toast.makeText(getApplicationContext(), "onDismiss",
                        Toast.LENGTH_SHORT).show();
            }
        });

        popupMenu.show();
    }
}