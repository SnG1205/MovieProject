package com.example.leariningdiary2

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import models.Movie

class MovieViewModel : ViewModel() {
    private var movies = mutableStateListOf<Movie>()

    fun addMovie(movie: Movie){
        movies.add(movie)
    }

    fun deleteMovie(movie: Movie){
        movies.remove(movie)
    }

    fun getMovies(): List<Movie>{
        return movies
    }

    fun checkMovie(movie: Movie){

    }
}