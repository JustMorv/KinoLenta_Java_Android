package com.example.kinolenta.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kinolenta.Adapter.FilmListAdapter;
import com.example.kinolenta.Domain.ListFilm;
import com.example.kinolenta.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;



public class AccountMainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterNewMovies, adapterUpComming;
    private RecyclerView recyclerViewNewMovies, recyclerViewUpComming;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest, mStringRequest2;
    private ProgressBar loading, loading2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_main);

        initView();
        sendRequest1();



        ImageView vkIcon = findViewById(R.id.vk_icon);
        ImageView telegramIcon = findViewById(R.id.telegram_icon);
        ImageView instagramIcon = findViewById(R.id.instagram_icon);

        vkIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl("https://vk.com/mrmorv");
            }
        });

        telegramIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl("https://web.telegram.org/a/#-1001457945496");
            }
        });

        instagramIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl("https://www.instagram.com/mr.morvol.ru/?hl=ru");
            }
        });

        ImageView bacImage = findViewById(R.id.backImg);
        bacImage.setOnClickListener(v -> finish());
    }

    private void openUrl(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);

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
            Log.i("JustMorv", "sendRequest1: " + error.toString());
            loading.setVisibility(View.GONE);
        });
        mRequestQueue.add(mStringRequest);
    }



    private void initView() {
        recyclerViewNewMovies = findViewById(R.id.view1);
        recyclerViewNewMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        loading = findViewById(R.id.loading1);


    }
}