package com.tutor.movieapp.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.tutor.movieapp.model.Movie
import com.tutor.movieapp.model.getMovie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavController, movies: String?) {
    val moviesList = getMovie().filter {
        it.id == movies
    }
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            modifier = Modifier.heightIn(max = 45.dp),
            title = { Text(text = "${moviesList[0].title} Movie") },
            navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow Back")
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                Color.Transparent
            )
        )
    }) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                HorizontalImageScrollable(moviesList)
                Spacer(modifier = Modifier.height(15.dp))
                Column(modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(8.dp), horizontalAlignment = Alignment.Start) {
                    Text(buildAnnotatedString {
                        withStyle(style = SpanStyle(fontSize = 14.sp)){
                            append("Plot Movie\n")
                        }
                        withStyle(style = SpanStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)){
                            append(moviesList[0].plot + "\n")
                        }
                        withStyle(style = SpanStyle(fontSize = 14.sp)){
                            append("Runtime\n")
                        }
                        withStyle(style = SpanStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)){
                            append(moviesList[0].runtime + "\n")
                        }
                        withStyle(style = SpanStyle(fontSize = 14.sp)){
                            append("Director\n")
                        }
                        withStyle(style = SpanStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)){
                            append(moviesList[0].director + "\n")
                        }
                        withStyle(style = SpanStyle(fontSize = 14.sp)){
                            append("Actor\n")
                        }
                        withStyle(style = SpanStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)){
                            append(moviesList[0].actors + "\n")
                        }
                        withStyle(style = SpanStyle(fontSize = 14.sp)){
                            append("Language\n")
                        }
                        withStyle(style = SpanStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)){
                            append(moviesList[0].language + "\n")
                        }
                        withStyle(style = SpanStyle(fontSize = 14.sp)){
                            append("Released\n")
                        }
                        withStyle(style = SpanStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)){
                            append(moviesList[0].released)
                        }
                    })
                }
            }
        }
    }
}

@Composable
private fun HorizontalImageScrollable(moviesList: List<Movie>) {
    LazyRow {
        items(moviesList[0].images) { image ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .width(250.dp)
                    .height(250.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(
                            LocalContext.current
                        ).data(image).crossfade(true).build(),
                        filterQuality = FilterQuality.Medium
                    ), contentDescription = "images",
                    contentScale = ContentScale.FillHeight,

                )
            }
        }
    }
}