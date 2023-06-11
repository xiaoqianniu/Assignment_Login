@file:OptIn(ExperimentalMaterialApi::class)

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.theme.AppTheme

/* A login screen Page with a tapBar and a registration form.
* In the login page, two textFields, a button and a text with underline which is clickable.
* If any of the two textFields is empty, the button login not enable, the color would be grey until it's enable.
* same function in the registration form. Using materialTheme to change the color,shape, and style.
* */
@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    AppTheme {
        Scaffold(
            topBar = {
                TopAppBar(title =
                {
                    Text(
                        "Welcome!",
                        color = MaterialTheme.colors.onPrimary,
                        style = MaterialTheme.typography.h4,
                        fontFamily = FontFamily.Cursive,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
                )
            })
        {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painterResource("salad.jpg"),
                    null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize(),
                    alpha = 0.8F
                )
                myLoginForm()
            }
        }
    }

}

/* add registration form background color
* add logo image clickable and display messages
* modify the textFields in one line
* */
@OptIn(ExperimentalResourceApi::class)
@Composable
fun myLoginForm() {

    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val showCard = remember { mutableStateOf(false) }
    val showRegisterForm = remember { mutableStateOf(false) }
    var isTextVisible = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isTextVisible.value) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "No more recipe worries!",
                    color = MaterialTheme.colors.primaryVariant,
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    " Discover a world of delicious possibilities now!",
                    color = MaterialTheme.colors.secondary,
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Image(
            painterResource("logo1.jpg"),
            null,
            modifier = Modifier.clip(CircleShape)
                .size(120.dp)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colors.onPrimary,
                    shape = MaterialTheme.shapes.medium
                )
                .clickable { isTextVisible.value = !isTextVisible.value }
        )

        Spacer(modifier = Modifier.height(20.dp))
        Card(modifier = Modifier.padding(15.dp), elevation = 10.dp) {
            TextField(
                value = emailValue.value,
                onValueChange = { emailValue.value = it },
                textStyle = TextStyle(textAlign = TextAlign.Center),
                label = { Text("Enter email address") },
                singleLine = true
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Card(elevation = 10.dp) {
            TextField(
                value = passwordValue.value,
                onValueChange = { passwordValue.value = it },
                textStyle = TextStyle(textAlign = TextAlign.Center),
                label = { Text("Enter password") },
                singleLine = true
            )
        }
        Spacer(modifier = Modifier.height(15.dp))

        Text(
            "New User? Click", fontSize = 20.sp,
            style = TextStyle(textDecoration = TextDecoration.Underline),
            modifier = Modifier.clickable(onClick = {
                showRegisterForm.value = true

            }, onClickLabel = "go to register")
        )

        Spacer(modifier = Modifier.height(15.dp))
        Button(
            onClick = {
                showCard.value = true
            },
            enabled = !emailValue.value.isEmpty() && !passwordValue.value.isEmpty()
        ) {
            Text("Login", fontSize = 20.sp)
        }

    }
    if (showRegisterForm.value) {
        registerForm( onDismiss={showRegisterForm.value = false},
        onDataSubmitted = {email,password ->
            emailValue.value = email
            passwordValue.value = password
        })
    }
    if (showCard.value) {
        loginInfoDisplay(emailValue.value, passwordValue.value,
            onBackClicked = {
                showCard.value = false
                emailValue.value = ""
                passwordValue.value = ""
            }
        )

    }
}

/* loginInfoDisplay method with three parameters:
for displaying the login information: email,password and dismiss the card and clear the textFields
note: do not clear textFields value after calling method, it does not work, do it in onBackClicked*/
@Composable
fun loginInfoDisplay(email: String, password: String, onBackClicked: () -> Unit) {
    Card(
        modifier = Modifier.padding(top = 16.dp).fillMaxSize(),
        elevation = 10.dp,
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Welcome back! ",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "User Information",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Email: $email",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Password: $password",
                style = MaterialTheme.typography.body1
            )
            Button(onClick = {
                onBackClicked()
            }) {
                Text("Back", fontSize = 20.sp)
            }
        }
    }
}


/*registerForm method: displaying registration from.
* note: when calling method, put it out of Column,or the size would be limited
* added lambda function onDataSubmitted to pass email and password value back to textFields in login
* */
@Composable
fun registerForm(onDismiss:() -> Unit, onDataSubmitted:(String,String) -> Unit) {
    val username = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPwd = remember { mutableStateOf("") }
    Card(
        modifier = Modifier.padding(top = 16.dp).fillMaxSize(),
        elevation = 10.dp,
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Registration ",
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(bottom = 8.dp, top = 10.dp)
            )
            Card(modifier = Modifier.padding(15.dp), elevation = 10.dp) {
                TextField(
                    value = username.value,
                    onValueChange = { username.value = it },
                    textStyle = TextStyle(textAlign = TextAlign.Center),
                    label = { Text("Enter username") },
                    singleLine = true
                )
            }
            Card(modifier = Modifier.padding(15.dp), elevation = 10.dp) {
                TextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    textStyle = TextStyle(textAlign = TextAlign.Center),
                    label = { Text("Enter email address") },
                    singleLine = true
                )
            }
            Card(modifier = Modifier.padding(15.dp), elevation = 10.dp) {
                TextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    textStyle = TextStyle(textAlign = TextAlign.Center),
                    label = { Text("Enter password") },
                    singleLine = true
                )
            }
            Card(modifier = Modifier.padding(15.dp), elevation = 10.dp) {
                TextField(
                    value = confirmPwd.value,
                    onValueChange = { confirmPwd.value = it },
                    textStyle = TextStyle(textAlign = TextAlign.Center),
                    label = { Text("Enter confirm password") },
                    singleLine = true
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {

                        onDataSubmitted(email.value,password.value)
                        onDismiss()
                    },
                    enabled = !username.value.isEmpty() && !email.value.isEmpty() && !password.value.isEmpty() && !confirmPwd.value.isEmpty()
                ) {
                    Text("Submit", fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.width(20.dp))
                Button(
                    onClick = {
                        username.value = ""
                        email.value = ""
                        password.value = ""
                        confirmPwd.value = ""
                        onDismiss()
                    }
                ) {
                    Text("Cancel", fontSize = 20.sp)
                }
            }
        }

    }

}

expect fun getPlatformName(): String