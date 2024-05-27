
package com.example.kinolenta.Activity;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.GridLayoutManager;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.content.Intent;
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
        import com.google.gson.Gson;

public class NewFilmActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterNewMovies;
    private RecyclerView recyclerViewNewMovies;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        initView();
        sendRequest1();


        ImageView movieAddActiv = findViewById(R.id.movieAddActiv);

        ImageView startAccount = findViewById(R.id.startAccount);

        ImageView imageView2 =  findViewById(R.id.imageView2);

        ImageView imageView =  findViewById(R.id.imageView);


        startAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewFilmActivity.this, AccountMainActivity.class));
            }
        });

        movieAddActiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewFilmActivity.this, ContactActivity.class));
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewFilmActivity.this, FavoritesActivity.class));
            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewFilmActivity.this, NewFilmActivity.class));
            }
        });

        ImageView bacImage = findViewById(R.id.backImg);
        bacImage.setOnClickListener(v -> finish());


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
        recyclerViewNewMovies.setLayoutManager(new GridLayoutManager(this, 2));

        loading = findViewById(R.id.loading1);


    }
}