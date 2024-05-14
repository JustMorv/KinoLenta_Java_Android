package com.example.kinolenta.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.kinolenta.Adapter.ImagesListAdapter;
import com.example.kinolenta.Domain.FilmItem;
import com.example.kinolenta.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.gson.Gson;

public class DetailActivity extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private ProgressBar progressBar;
    private TextView titleTxt, movieRateTxt, MovieTimeTxt, MovieDateTxt, movieSummaryInfo, movieActorsInfo;

    private NestedScrollView scrollView;
    private int idFilm;
    private ShapeableImageView pic1;
    private ImageView pic2, bacImage;
    private RecyclerView.Adapter adapterImgList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        idFilm = getIntent().getIntExtra("id", 0);
        initView();
        sendRequest();
    }

    private void sendRequest() {
        mRequestQueue = Volley.newRequestQueue(this);
//        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies/" + idFilm, response -> {
            Gson gson = new Gson();
//            progressBar.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
            FilmItem item = gson.fromJson(response, FilmItem.class);

            Glide.with(DetailActivity.this)
                    .load(item.getPoster())
                    .into(pic1);

            Glide.with(DetailActivity.this)
                    .load(item.getPoster())
                    .into(pic2);

            titleTxt.setText(item.getTitle());
            movieRateTxt.setText(item.getRated());
            MovieTimeTxt.setText(item.getRuntime());
            MovieDateTxt.setText(item.getReleased());
//            movieSummaryInfo.setText(item.getPlot());
//            movieActorsInfo.setText(item.getActors());

//            if (item.getImages() != null) {
//                adapterImgList = new ImagesListAdapter(item.getImages());
//                recyclerView.setAdapter(adapterImgList);
//            }
        }, error -> {
//            progressBar.setVisibility(View.GONE);
            Log.i("justMorv", "enErrorResponse: " + error.toString());
        });

        mRequestQueue.add(mStringRequest);
    }

    private void initView() {
        titleTxt = findViewById(R.id.movieNameTxt);
//        progressBar = findViewById(R.id.detailLoading);
        scrollView = findViewById(R.id.scrollView3);
        pic1 = findViewById(R.id.posterNormalImg);
        pic2 = findViewById(R.id.posterBigImg);
        movieRateTxt = findViewById(R.id.movieRateTxt);
        MovieTimeTxt = findViewById(R.id.movieTimeTxt);
        MovieDateTxt = findViewById(R.id.movieDateTxt);

//        movieSummaryInfo = findViewById(R.id.movieSummaryInfo);
//        movieActorsInfo = findViewById(R.id.movieActorsInfo);
        bacImage = findViewById(R.id.backImg);
//        recyclerView = findViewById(R.id.imagesRecylerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        bacImage.setOnClickListener(v -> finish());
    }

}