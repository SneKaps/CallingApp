package com.example.callingapp.screen

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.callingapp.AuthViewModel

@Composable
fun PhoneLoginScreen(
    modifier: Modifier,
    authViewModel: AuthViewModel,
    activity: Activity,
    navController: NavController
){
    var phoneNumber by remember{
        mutableStateOf("")
    }

    var otpCode by remember {
        mutableStateOf("")
    }

    val authState by authViewModel.authState.observeAsState()
    LaunchedEffect(authState) {
        if (authState is AuthViewModel.AuthState.Authenticated) {
            //"Verification Successful"
            navController.navigate("home"){
                popUpTo("phonelogin"){inclusive = true}
            }

            // Navigate to the home screen or show a success message
            // For example, navigate to another screen
        }
    }

    Column(modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){

        Text("Login with phone number")

        TextField(value = phoneNumber,
            onValueChange ={
                if(it.length <= 10)
                    phoneNumber.trim()
                phoneNumber = it
            },
            placeholder = {Text("Enter your phone number")},
            modifier = modifier.fillMaxWidth().padding(8.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        Button(onClick = {
            authViewModel.requestOTP(phoneNumber, activity)
        }){
            Text("Generate Otp")
        }
        
        Spacer(modifier = modifier.height(16.dp))

        if (authState is AuthViewModel.AuthState.CodeSent) {
            TextField(
                value = otpCode,
                onValueChange = { otpCode = it },
                placeholder = { Text("Enter OTP") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val verificationId = (authState as AuthViewModel.AuthState.CodeSent).verificationId
                    authViewModel.verifyOtp(verificationId, otpCode)
                }
            ) {
                Text("Verify OTP")
            }
        }
        if (authState is AuthViewModel.AuthState.Loading) {
            CircularProgressIndicator()
        }
        if (authState is AuthViewModel.AuthState.Error) {
            val errorMessage = (authState as AuthViewModel.AuthState.Error).message
            Text(
                text = errorMessage,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}