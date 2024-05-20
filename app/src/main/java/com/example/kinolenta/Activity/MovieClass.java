package com.example.kinolenta.Activity;

public class MovieClass {
    public String id, title, poster, year, released, runtime, director, writer, actors, imagefirstMovie, imageSecondMovie;

    public MovieClass() {
        // Пустой конструктор нужен для Firebase
    }

    public MovieClass(String id, String title, String poster, String year, String released, String runtime, String director, String writer, String actors, String imagefirstMovie, String imageSecondMovie) {
        this.id = id;
        this.title = title;
        this.poster = poster;
        this.year = year;
        this.released = released;
        this.runtime = runtime;
        this.director = director;
        this.writer = writer;
        this.actors = actors;
        this.imagefirstMovie = imagefirstMovie;
        this.imageSecondMovie = imageSecondMovie;
    }
}
