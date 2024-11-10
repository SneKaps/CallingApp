package com.example.callingapp

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.callingapp.screen.HomeScreen
import com.example.callingapp.screen.LoginScreen
import com.example.callingapp.screen.PhoneLoginScreen
import com.example.callingapp.screen.SignupScreen

@Composable
fun Navigation(
    authViewModel: AuthViewModel,
    activity: Activity
)
{
    val navController = rememberNavController()

    NavHost(navController, startDestination = "phonelogin"){
        composable("login"){
            LoginScreen(Modifier, authViewModel, navController)
        }
        composable("signup"){
            SignupScreen(Modifier, authViewModel, navController)
        }
        composable("home"){
            HomeScreen(modifier = Modifier, authViewModel, navController)
        }
        composable("phonelogin"){
            PhoneLoginScreen(Modifier, authViewModel, activity, navController)
        }
    }

}