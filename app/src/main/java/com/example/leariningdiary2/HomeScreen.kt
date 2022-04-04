package com.example.leariningdiary2

import androidx.compose.foundation.clickable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController){
    var showMenu by remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Movies")},
                actions = {
                    IconButton(onClick = {showMenu = !showMenu}) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
                    }

                    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                        DropdownMenuItem(onClick = { navController.navigate("favoritescreen") }) {
                            Icon(imageVector = Icons.Default.Favorite, contentDescription = "My favourites")
                            Text(text = "Favourites")
                        }
                    }
                }
            )


        }
    ) {
        MainContent(navController = navController)
    }


}
