package com.example.dungtnph08210_mob403_assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewItemActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<food> foodList;
    myadapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        recyclerView = findViewById(R.id.rcvfood);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        myadapter = new myadapter();
        foodList = new ArrayList<>();
        recyclerView.setAdapter(myadapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        processdate();

    }

    public void processdate() {
        Call<List<food>> call = apicontroller
                .getInstance()
                .getapi()
                .getdata();


        call.enqueue(new Callback<List<food>>() {
            @Override
            public void onResponse(Call<List<food>> call, Response<List<food>> response) {
                if (response.body() != null){
                    List<food> list = response.body();
                    foodList.addAll(list);
                    myadapter.setmyadapter(foodList);
                }
            }

            @Override
            public void onFailure(Call<List<food>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}