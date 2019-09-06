package com.example.gettingvediopath;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Config;
import android.util.Log;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.gettingvediopath.webservices.API;
import com.example.gettingvediopath.webservices.RestClient;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    VideoView videoView;
    VideoResponses videoResponses;
    String corp_code;
    ArrayList<VideoResponses.data> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView=findViewById(R.id.videoView);
        corp_code="sram";
        getVideoPath();


    }


    public void getVideoPath() {
        Log.d("TAG", "getVideoPath1233: llll");
        try {

                OkHttpClient okHttpClient = new OkHttpClient();
                RestClient.client = new Retrofit.Builder().baseUrl(RestClient.baseUrl).
                        client(okHttpClient).
                        addConverterFactory(GsonConverterFactory
                                .create()).build();
                API api = RestClient.client.create(API.class);
                Call<VideoResponses> call = api.getVideo(corp_code);
            Log.d("TAG", "getVideoPath1233: ggg");
                call.enqueue(new Callback<VideoResponses>() {
                    @Override
                    public void onResponse(Call<VideoResponses> call,
                                           Response<VideoResponses> response) {
                        //  Config.closeLoader();
                        videoResponses = response.body();
                        data = new ArrayList<VideoResponses.data>();
                        Log.d("TAG", "getVideoPath1233: ggg"+videoResponses.getStatus());
                        if (videoResponses.getStatus().equalsIgnoreCase("true")) {

                            if(videoResponses.getData()==null){
                                Toast.makeText(getApplicationContext(),"No Data Getting",Toast.LENGTH_LONG).show();
                            }else {

                                for (int i = 0; i < videoResponses.getData().length; i++) {
                                    data.add(videoResponses.getData()[i]);
                                    String s= videoResponses.getData()[i].getPath();
                                    Uri uri = Uri.parse(s);
                                    videoView.setVideoURI(uri);
                                    videoView.start();
                                    Log.d("TAG", "onResponse:completed1234" + videoResponses.getData()[i].getPath()+" "+s);
                                }
                            }
                        }


                    }

                    @Override
                    public void onFailure(Call<VideoResponses> call, Throwable t) {
                        t.getMessage();
                        Toast.makeText(
                                getApplicationContext(), "Try Again!", Toast.LENGTH_LONG).show();
                    }
                });
        } catch (Exception e) {
            System.out.println("Exception e" + e);
        }
    }

}
