package com.example.callingapp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CommonComponent(
    pageTitle: String,
    email: String,
    password: String,
    onEmailChange: (String)->Unit,
    onPasswordChange: (String)->Unit,
    onButtonClicked: ()->Unit,
    buttonTitle: String,
    bottomTitle: String,
    onBottomTextClicked: ()->Unit,
    textButtonTitle: String
){

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(pageTitle)

        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = {Text("Email")}
        )

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = {Text("Password")}
        )

        Button(modifier = Modifier.padding(6.dp),
            onClick = {
            onButtonClicked()
        })
        {
            Text(text = buttonTitle)
        }

        Text(bottomTitle)

        TextButton(onClick = {
            onBottomTextClicked()
        }) {
            Text(text = textButtonTitle)
        }
    }
}