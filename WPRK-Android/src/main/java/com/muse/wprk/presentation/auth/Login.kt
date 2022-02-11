package com.muse.wprk.presentation.auth

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muse.wprk.R
import com.muse.wprk.core.exts.parse
import com.muse.wprk.presentation.components.AltLoginPlaceHolder
import com.muse.wprk.presentation.components.Button
import com.muse.wprk.presentation.components.PasswordTextField
import com.muse.wprk.presentation.components.UniversalTextField

@Composable
fun Login() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        LoginHeader()
        LoginBody()
    }
}

@Composable
fun LoginHeader() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(175.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.wprklogo),
            contentDescription = "",
            modifier = Modifier
                .size(120.dp)
        )
        Text(
            text = "The Best In Basement Radio",
            color = Color.White,
            fontSize = 23.sp
        )
    }
}

@Composable
fun LoginBody() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(475.dp),
        backgroundColor = Color.parse("#473e42"),
        shape = RoundedCornerShape(15.dp)
    ) {
        BodyContent()
    }
}

@Composable
fun BodyContent() {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        UniversalTextField(
            title = "Email or R-Number",
            icon = Icons.Outlined.MailOutline,
            value = email
        ) {
            email = it
        }
        PasswordTextField(
            title = "Password",
            icon = Icons.Default.Lock,
            value = password
        ) {
            password = it
        }
        Button(text = "Login")
        AltLogin()
    }
}

@Composable
fun AltLogin() {
    Column(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
    ) {
        Text(text = "Forgot Password")
        Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "Or login with", modifier = Modifier.align(Alignment.Center))
            Spacer(modifier = Modifier.height(5.dp))
            Divider()
            Spacer(modifier = Modifier.height(5.dp))
        }
        Row (
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceAround
                ) {
            for (i in 0 until 3) {
                AltLoginPlaceHolder()
            }
        }
        Text(text = "Sign in as guest", modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "Don’t have an account? Register one", modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(5.dp))
    }
}



@Preview
@Composable
fun LoginPreview() {
    Login()
}