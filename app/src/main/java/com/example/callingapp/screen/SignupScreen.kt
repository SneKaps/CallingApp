package com.example.callingapp.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.callingapp.AuthViewModel


@Composable
fun SignupScreen(
    modifier: Modifier,
    authViewModel: AuthViewModel,
    navController : NavController
){

    var email by remember {
        mutableStateOf("")
    }

    var password by remember{
        mutableStateOf("")
    }

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthViewModel.AuthState.Authenticated->navController.navigate("login"){
                popUpTo("signup"){ inclusive = true }
            }
            is AuthViewModel.AuthState.Error-> Toast.makeText(context,
                (authState.value as AuthViewModel.AuthState.Error).message,Toast.LENGTH_LONG).show()
            else->Unit
        }
    }


    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        CommonComponent(
            pageTitle = "Create Account",
            email = email,
            password = password,
            onEmailChange = {
                email = it
            },
            onPasswordChange = {
                password = it
            },
            onButtonClicked = {
                authViewModel.signup(email, password)
            },
            buttonTitle = "Signup",
            bottomTitle = "Already have an account?",
            onBottomTextClicked = {
                navController.navigate("login"){
                    popUpTo("signup"){inclusive = true}
                }
            },
            textButtonTitle = "Login"
        )
    }



}