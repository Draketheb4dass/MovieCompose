package com.jephtecolin.moviecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.jephtecolin.moviecompose.data.ConnectivityObserver
import com.jephtecolin.moviecompose.data.NetworkConnectivityObserver
import com.jephtecolin.moviecompose.ui.theme.MovieComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var connectivityObserver: ConnectivityObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        Thread.sleep(2000L)
        connectivityObserver = NetworkConnectivityObserver(applicationContext)
        setContent {
            MovieComposeTheme {
                val status by connectivityObserver.observe().collectAsState(
                    initial = ConnectivityObserver.Status.Unavailable
                )
                // A surface container using the 'background' color from the theme
                Column() {
                    if (status == ConnectivityObserver.Status.Unavailable ||
                                status == ConnectivityObserver.Status.Lost) {
                        Snackbar( action = { }, containerColor = Color(0xFF7C0A0A)) { Text(text = " There's no Internet")}
                    }

                    MovieComposeApp()
                }


            }
        }
    }
}
