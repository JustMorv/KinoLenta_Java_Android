package com.example.kinolenta.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
//import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kinolenta.Adapter.FilmListAdapter;
import com.example.kinolenta.Domain.FilmItem;
import com.example.kinolenta.Domain.ListFilm;
import com.example.kinolenta.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterNewMovies, adapterUpComming;
    private RecyclerView recyclerViewNewMovies, recyclerViewUpComming;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest,mStringRequest2;
    private ProgressBar loading, loading2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }

        initView();
        sendRequest1();
        sendRequest2();
    }

    private void sendRequest1() {
        mRequestQueue = Volley.newRequestQueue(this);
        loading.setVisibility(View.VISIBLE);
        mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=1", response -> {
            Gson gson = new Gson();
            loading.setVisibility(View.GONE);

            ListFilm items = gson.fromJson(response, ListFilm.class);
            adapterNewMovies = new FilmListAdapter(items);
            recyclerViewNewMovies.setAdapter(adapterNewMovies);
        }, error -> {
            Log.i("JustMorv","sendRequest1: " +error.toString());
            loading.setVisibility(View.GONE);
        });
        mRequestQueue.add(mStringRequest);
    }


    private void sendRequest2() {
        mRequestQueue = Volley.newRequestQueue(this);
        loading2.setVisibility(View.VISIBLE);
        mStringRequest2 = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=3", response -> {
            Gson gson = new Gson();
            loading2.setVisibility(View.GONE);

            ListFilm items = gson.fromJson(response, ListFilm.class);
            adapterUpComming = new FilmListAdapter(items);
            recyclerViewUpComming.setAdapter(adapterUpComming);
        }, error -> {
            loading2.setVisibility(View.GONE);

        });

        mRequestQueue.add(mStringRequest2);

    }

    private void initView() {
        recyclerViewNewMovies = findViewById(R.id.view1);
        recyclerViewNewMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        recyclerViewUpComming = findViewById(R.id.view2);
        recyclerViewUpComming.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        loading  = findViewById(R.id.loading1);
        loading2  = findViewById(R.id.loading2);


    }
}