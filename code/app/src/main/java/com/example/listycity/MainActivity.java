package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    String selected = null;
    Button addButton;
    LinearLayout textWindow;
    EditText inputCity;
    Button confirmButton;
    Button deleteButton;
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);
        addButton = findViewById(R.id.add_button);
        deleteButton = findViewById(R.id.delete_button);
        textWindow = findViewById(R.id.text_window);
        confirmButton = findViewById(R.id.confirm_button);
        inputCity = findViewById(R.id.city_text);

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected = ((TextView) view.findViewById(R.id.content_view)).getText().toString();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textWindow.getVisibility() == View.GONE)
                    textWindow.setVisibility(View.VISIBLE);
                else if (textWindow.getVisibility() == View.VISIBLE)
                    textWindow.setVisibility(View.GONE);
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = inputCity.getText().toString();
                if (!city.isBlank() && !dataList.contains(city)) {
                    dataList.add(city);
                    cityAdapter.notifyDataSetChanged();
                    inputCity.getText().clear();
                    textWindow.setVisibility(View.GONE);
                    selected = city;
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected != null) {
                    dataList.remove(selected);
                    cityAdapter.notifyDataSetChanged();
                    selected = null;
                }
            }
        });
    }
}