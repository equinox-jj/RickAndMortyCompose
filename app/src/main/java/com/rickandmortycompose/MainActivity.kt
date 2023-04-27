package com.rickandmortycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.compose.rememberNavController
import com.core.common.utils.Destinations
import com.rickandmortycompose.navigation.SetupNavGraph
import com.rickandmortycompose.ui.theme.RickAndMortyComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyComposeTheme {
                val navController = rememberNavController()

                SetupNavGraph(
                    startDestination = Destinations.CharacterListRoute.route,
                    navHostController = navController
                )
            }
        }
    }
}