package com.example.kinolenta.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.kinolenta.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MovieAddActivity extends AppCompatActivity {

    EditText edTitle;
    EditText edPoster;
    EditText edYear;
    EditText edReleased;
    EditText edRuntime;
    EditText edDirector;
    EditText edWriter;
    EditText edActors;
    EditText edImagefirstMovie;
    EditText edImageSecondMovie;
    //    EditText imagefirstMovie;
    public DatabaseReference mDatabase;
    private String group_key = "movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_add);
        init();

        ImageView bacImage = findViewById(R.id.backImg);
        bacImage.setOnClickListener(v -> finish());
    }

    private void init() {
        edTitle = findViewById(R.id.titleMovie);
        edPoster = findViewById(R.id.posterMovie);
        edYear = findViewById(R.id.yearMovie);
        edReleased = findViewById(R.id.releasedMovie);
        edRuntime = findViewById(R.id.releasedMovie);
        edDirector = findViewById(R.id.directorMovie);
        edWriter = findViewById(R.id.writerMovie);
        edActors = findViewById(R.id.actorsMovie);
        edImagefirstMovie = findViewById(R.id.imagefirstMovie);
        edImageSecondMovie = findViewById(R.id.imageSecondMovie);

        mDatabase = FirebaseDatabase.getInstance().getReference(group_key);

    }

    public void onClickSave(View view) {
        String id = mDatabase.getKey();

        String title = edTitle.getText().toString();
        String poster = edPoster.getText().toString();
        String year = edYear.getText().toString();
        String released = edReleased.getText().toString();
        String runtime = edRuntime.getText().toString();
        String director = edDirector.getText().toString();
        String writer = edWriter.getText().toString();
        String actors = edActors.getText().toString();
        String imagefirstMovie = edImagefirstMovie.getText().toString();
        String imageSecondMovie = edImageSecondMovie.getText().toString();

        MovieClass newMovie = new MovieClass(id, title, poster, year, released, runtime, director, writer, actors, imagefirstMovie, imageSecondMovie);

        mDatabase.push().setValue(newMovie);
    }

}