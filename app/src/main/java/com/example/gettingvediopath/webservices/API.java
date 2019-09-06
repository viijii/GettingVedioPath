package com.example.gettingvediopath.webservices;

import com.example.gettingvediopath.VideoResponses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("gettingvideo.php")//getting customer details
    Call<VideoResponses> getVideo(@Query("corp_code") String crop);
}
