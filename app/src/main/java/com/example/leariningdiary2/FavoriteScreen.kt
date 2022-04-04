package com.example.leariningdiary2

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import models.Movie
import models.getMovies

@Composable
fun FavoriteScreen(navController: NavController = rememberNavController()) {
    var showMenu by remember {
        mutableStateOf(false)
    }



    Scaffold(
        topBar = {
            TopAppBar(elevation = 3.dp) {
                Row() {
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow Back",
                        modifier = Modifier.clickable {
                            navController.navigate("homescreen")
                        })

                    Spacer(modifier = Modifier.width(20.dp))

                    Text(text = "My Favorites", style = MaterialTheme.typography.h5)
                }
            }


        }
    ) {
        MainContent()

    }
}

@Composable
fun MainContent (movie1: Movie = getMovies()[0], movie2: Movie = getMovies()[2]){
    Column(modifier = Modifier.padding(20.dp)) {
        MovieRow(
            movieName = movie1.title,
            movieDirector = movie1.director,
            movieYear = movie1.year,
            moviePlot = movie1.plot,
            movieGenre = movie1.genre,
            movieActors = movie1.actors,
            movieRating = movie1.rating,
            movieId = movie1.id,
            movieImages = movie1.images
        )
        Divider()
        MovieRow(
            movieName = movie2.title,
            movieDirector = movie2.director,
            movieYear = movie2.year,
            moviePlot = movie2.plot,
            movieGenre = movie2.genre,
            movieActors = movie2.actors,
            movieRating = movie2.rating,
            movieId = movie2.id,
            movieImages = movie2.images
        )
    }

    
}