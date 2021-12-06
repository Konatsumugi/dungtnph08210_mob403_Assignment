package com.example.dungtnph08210_mob403_assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.material.chip.Chip;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeScreen extends AppCompatActivity {
    Toolbar mtoolbar;
    FloatingActionButton flbtn1, flbtn2;
    Chip chip1, chip2, chip3, chip4;
    TextView tvHomeScreen4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        mtoolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);

        flbtn1 = findViewById(R.id.flbtn1);
        flbtn2 = findViewById(R.id.flbtn2);
        chip1 = findViewById(R.id.chip4);
        chip2 = findViewById(R.id.chip5);
        chip3 = findViewById(R.id.chip6);
        chip4 = findViewById(R.id.chip7);
        tvHomeScreen4 = findViewById(R.id.tvHomeScreen4);







        flbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this, AddFoodScreen.class));
            }
        });
        flbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeScreen.this, "bạn chọn xem bản mới", Toast.LENGTH_SHORT).show();
            }
        });
        chip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHomeScreen4.setText("Fine Dinning food");
            }
        });
        chip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHomeScreen4.setText("Fine See food");
            }
        });
        chip3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHomeScreen4.setText("Fine Chinese food");
            }
        });
        chip4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHomeScreen4.setText("Fine Fast food");
            }
        });

    }




}