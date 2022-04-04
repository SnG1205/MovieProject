package com.example.leariningdiary2

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import models.Movie
import models.getMovies

@Composable
fun DetailScreen(navController: NavController = rememberNavController(), movieId: String?){
    var showMenu by remember {
        mutableStateOf(false)
    }

    val movie = filterMovie( movieId = movieId)

    Scaffold(
        topBar = {
            TopAppBar( elevation = 3.dp){
                Row() {
                    Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow Back",
                    modifier = Modifier.clickable{
                        navController.navigate("homescreen")
                    })

                    Spacer(modifier = Modifier.width(20.dp))

                    Text(text = movie.title, style = MaterialTheme.typography.h5)
                }
            }


        }
    ) {
        MainContent(movie = movie)
        //MainContent(navController = navController)

    }


}

fun filterMovie(movieId: String?) : Movie {
    return getMovies().filter { movie -> movie.id == movieId }[0]
}

@Composable
fun MainContent(movie: Movie){
    Surface( modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()) {
        Column {
            MovieRow(
                movieName = movie.title,
                movieDirector = movie.director,
                movieYear = movie.year,
                moviePlot = movie.plot,
                movieGenre = movie.genre,
                movieActors = movie.actors,
                movieRating = movie.rating,
                movieId = movie.id,
                movieImages = movie.images)

            Divider()

            HorizontalImageView(movie = movie)
        }
    }
}