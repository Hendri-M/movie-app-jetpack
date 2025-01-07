package com.tutor.movieapp.widgets

import android.util.Log
import android.widget.Space
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.tutor.movieapp.model.Movie
import com.tutor.movieapp.model.getMovie

@Preview
@Composable
fun RowMovie(movie: Movie = getMovie()[0], onItemClicked: (String) -> Unit = {}) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .clickable { onItemClicked(movie.id) }, elevation = CardDefaults.cardElevation(2.dp)
    ) {
        var expanded by remember {
            mutableStateOf(false)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier
                    .padding(2.dp)
                    .size(100.dp),
                shape = RoundedCornerShape(corner = CornerSize(9.dp))
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = ImageRequest.Builder(
                        LocalContext.current,

                        ).data(movie.images[0]).listener(onSuccess = { _, _ ->
                        Log.d("ImageDebug", "Image loaded successfully")
                    }, onError = { _, throwable ->
                        Log.e(
                            "ImageDebug",
                            "Error loading image: ${throwable.throwable.message}"
                        )
                    }).crossfade(true).build()
                    ),
                    contentScale = ContentScale.FillHeight,
                    contentDescription = "image",
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = movie.title, style = MaterialTheme.typography.titleLarge)
                Text(
                    text = "Released: ${movie.released}",
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = "Director: ${movie.director}",
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
        AnimatedVisibility(visible = expanded, enter = expandVertically () + scaleIn() + fadeIn(), exit = scaleOut()  + fadeOut()+ shrinkVertically()) {
            Column(modifier = Modifier.padding(8.dp),horizontalAlignment = Alignment.Start) {
                Text(buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp)){
                        append("Plot: ")
                    }
                    withStyle(style = SpanStyle(color = Color.Black, fontSize = 13.sp, fontWeight = FontWeight.Bold)){
                        append(movie.plot)
                    }
                })
                Spacer(modifier = Modifier.heightIn(4.dp))
                Text(buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp)){
                        append("Actors: ")
                    }
                    withStyle(style = SpanStyle(color = Color.Black, fontSize = 13.sp, fontWeight = FontWeight.Bold)){
                        append(movie.actors)
                    }
                })
                Spacer(modifier = Modifier.heightIn(4.dp))
                Text(buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp)){
                        append("Released: ")
                    }
                    withStyle(style = SpanStyle(color = Color.Black, fontSize = 13.sp, fontWeight = FontWeight.Bold)){
                        append(movie.released)
                    }
                })
                Spacer(modifier = Modifier.heightIn(4.dp))
                Text(buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp)){
                        append("Genre: ")
                    }
                    withStyle(style = SpanStyle(color = Color.Black, fontSize = 13.sp, fontWeight = FontWeight.Bold)){
                        append(movie.genre)
                    }
                })
            }
        }
        Icon(
            imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
            contentDescription = "Arrow",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable {
                    expanded = !expanded
                })
    }
}
