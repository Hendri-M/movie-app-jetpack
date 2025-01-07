package com.tutor.movieapp.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tutor.movieapp.model.Movie
import com.tutor.movieapp.model.getMovie
import com.tutor.movieapp.navigation.MovieScreen
import com.tutor.movieapp.widgets.RowMovie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(
            modifier = Modifier.heightIn(max = 45.dp),
            title = { Text(text = "Movies") },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent)
        )
    }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            MainContent(navController = navController)
        }
    }
}

@Composable
fun MainContent(
    navController: NavController,
    movieList: List<Movie> = getMovie()
) {
    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn {
            items(items = movieList) {
                RowMovie(movie = it) {
                        movie ->
                    navController.navigate(MovieScreen.DetailsScreen.name+"/$movie")
                }
            }
        }
    }
}