package com.tutor.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tutor.movieapp.screens.details.DetailsScreen
import com.tutor.movieapp.screens.home.HomeScreen

@Composable
fun MovieNavigation () {
    val navigation = rememberNavController()
    NavHost(navController = navigation, startDestination = MovieScreen.HomeScreen.name) {
        composable(MovieScreen.HomeScreen.name) {
            HomeScreen(navController = navigation)
        }

        composable(MovieScreen.DetailsScreen.name+"/{idMovie}", arguments = listOf(navArgument(name = "idMovie"){type=
            NavType.StringType})) { navBackState ->
            DetailsScreen(navController = navigation, navBackState.arguments?.getString("idMovie"))
        }
    }
}