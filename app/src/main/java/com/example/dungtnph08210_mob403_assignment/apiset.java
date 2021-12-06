package com.example.dungtnph08210_mob403_assignment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface apiset {
    @GET("api_select_restaurant")
    Call<List<food>> getdata();

}
