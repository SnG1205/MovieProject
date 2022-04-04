package com.example.leariningdiary2

import android.os.Bundle
import android.widget.AdapterView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.leariningdiary2.ui.theme.LeariningDiary2Theme
import models.Movie
import models.getMovies

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


                    MyApp{
                        MyNavigation()
                    }



        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun MyApp(content: @Composable ()-> Unit){
    LeariningDiary2Theme{
        content()
    }

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieRow(movieName: String, movieDirector: String, movieYear: String, moviePlot: String, movieGenre: String, movieActors: String, movieRating: String, movieId:String, movieImages: List<String>, onItemClick: (String) -> Unit = {} ){
    var showmore by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            //.fillMaxHeight()
            .clickable {
                onItemClick(movieId)
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp


    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(modifier = Modifier.size(100.dp)){
                //Icon(imageVector = Icons.Default.AccountBox, contentDescription = "movie pic")
                Image(painter = rememberImagePainter(data = movieImages[0],
                builder = { transformations(CircleCropTransformation() ) }),
                    contentDescription = "Image Preview" )
            }
            Column( modifier = Modifier.padding(4.dp)) {
                Text(text= movieName, style = MaterialTheme.typography.h5)
                Text(text = "Director: $movieDirector", style = MaterialTheme.typography.caption)
                Text(text = "Released: $movieYear")
                IconButton(onClick = { showmore =!showmore }) {
                    if(showmore){
                        Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "arrow up")
                    }
                    else{
                        Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "arrow down")
                    }

                }
                AnimatedVisibility(visible = showmore) {
                    Column(modifier = Modifier
                        .height(240.dp)

                        .padding(4.dp)) {
                        Text(text = "Plot: $moviePlot")
                        Text(text = "Actors: $movieActors")
                        Text(text = "Genre: $movieGenre")
                        Text(text = "Rating: $movieRating ")
                    }


                }
            }


        }
    }
}

@Composable
fun HorizontalImageView(movie: Movie = getMovies()[0]){
    LazyRow{
        items(movie.images) {image ->
            Card(modifier = Modifier.padding(12.dp).size(240.dp)) {
                Image(
                    painter = rememberImagePainter(data = image),
                    contentDescription = "movie image"
                )
            }
        }
    }
}

@Composable
fun MainContent(movieList: List<Movie> = getMovies(), navController: NavController){
    Surface(color = MaterialTheme.colors.background

    ) {
        LazyColumn{
            items(items=movieList){ movie ->
                MovieRow(movieName = movie.title, movieDirector = movie.director, movieYear = movie.year, moviePlot = movie.plot, movieActors = movie.actors, movieGenre = movie.genre, movieRating = movie.rating, movieId = movie.id, movieImages = movie.images){
                    movieId -> navController.navigate("detailscreen/$movieId")
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LeariningDiary2Theme {
        //MainContent()
    }
}