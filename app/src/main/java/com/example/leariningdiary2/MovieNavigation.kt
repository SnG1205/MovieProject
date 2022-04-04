package com.example.leariningdiary2

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import models.getMovies

@Composable
fun MyNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "homescreen"){
        composable("homescreen"){ HomeScreen(navController =  navController)}
        composable("favoritescreen") { FavoriteScreen(navController = navController)}
        composable("detailscreen/{movie}",
        arguments = listOf(navArgument("movie"){
            type = NavType.StringType
        })
        )
        {   backStackEntry ->
            DetailScreen(navController =  navController, movieId = backStackEntry.arguments?.getString("movie"))}
    }
}