package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.myapplication.adpter.CustomAdepter;
import com.example.myapplication.models.UserResponseModel;
import com.example.myapplication.models.UserResultResponseModel;
import com.example.myapplication.networks.ApiClient;
import com.example.myapplication.networks.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowDataViaRetro extends AppCompatActivity {
    ListView listView;
    ArrayList<UserResultResponseModel> userlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data_via_retro);
        listView = findViewById(R.id.listViewData);


        getUSerTask();


    }

    private void getUSerTask() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponseModel> call = apiService.getUser();
        call.enqueue(new Callback<UserResponseModel>() {
            @Override
            public void onResponse(Call<UserResponseModel> call, Response<UserResponseModel> response) {

                UserResponseModel userResponseModel = response.body();
                userlist.addAll(userResponseModel.response);

                CustomAdepter customAdepter = new CustomAdepter(ShowDataViaRetro.this, userlist);
                listView.setAdapter(customAdepter);


            }

            @Override
            public void onFailure(Call<UserResponseModel> call, Throwable t) {

            }


        });
    }
}

