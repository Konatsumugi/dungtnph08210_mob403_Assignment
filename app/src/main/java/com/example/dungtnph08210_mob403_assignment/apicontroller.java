package com.example.dungtnph08210_mob403_assignment;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apicontroller {
    private static final String url ="https://nguyentiendungph08210.000webhostapp.com/MOB403/";
    private static apicontroller clientobject;
    private static Retrofit retrofit;

    apicontroller()
    {
        retrofit =new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized apicontroller getInstance()
    {
        if (retrofit==null)
            clientobject=new apicontroller();

        return clientobject;
    }
     apiset getapi()
    {
        return retrofit.create(apiset.class);
    }
}
