@file:OptIn(ExperimentalMaterialApi::class)

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.DismissState
import androidx.compose.material.DrawerValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.launch
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

@OptIn(ExperimentalResourceApi::class)
@Composable
fun myLoginForm() {
    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val usernameValue = remember { mutableStateOf("") }
    val registrationEmail = remember { mutableStateOf("") }
    val registrationPassword = remember { mutableStateOf("") }
    val showCard = remember { mutableStateOf(false) }
    val showRegisterForm = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
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
        )
        Spacer(modifier = Modifier.height(20.dp))
        Card(modifier = Modifier.padding(15.dp), elevation = 10.dp) {
            TextField(
                value = emailValue.value,
                onValueChange = { emailValue.value = it },
                textStyle = TextStyle(textAlign = TextAlign.Center),
                label = { Text("Enter email address") }
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Card(elevation = 10.dp) {
            TextField(
                value = passwordValue.value,
                onValueChange = { passwordValue.value = it },
                textStyle = TextStyle(textAlign = TextAlign.Center),
                label = { Text("Enter password") }
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
        if (showRegisterForm.value) {
            Card(
                modifier = Modifier.padding(top = 16.dp).fillMaxSize(),
                elevation = 10.dp
            ) {
                Column(
                    modifier = Modifier.padding(16.dp).fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Registration ",
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    TextField(
                        value = usernameValue.value,
                        onValueChange = { usernameValue.value = it },
                        textStyle = TextStyle(textAlign = TextAlign.Center),
                        label = { Text("Enter username") })
                    Spacer(modifier = Modifier.height(10.dp))
                    TextField(
                        value = registrationEmail.value,
                        onValueChange = { registrationEmail.value = it },
                        textStyle = TextStyle(textAlign = TextAlign.Center),
                        label = { Text("Enter email address") })
                    Spacer(modifier = Modifier.height(10.dp))
                    TextField(
                        value = registrationPassword.value,
                        onValueChange = { registrationPassword.value = it },
                        textStyle = TextStyle(textAlign = TextAlign.Center),
                        label = { Text("Enter password") })
                    Spacer(modifier = Modifier.height(15.dp))

                    Button(
                        onClick = { showRegisterForm.value = false },
                        enabled = !usernameValue.value.isEmpty() && !registrationEmail.value.isEmpty() && !registrationPassword.value.isEmpty()
                    ) {
                        Text("Submit", fontSize = 20.sp)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(15.dp))
        Button(
            onClick = { showCard.value = true },
            enabled = !emailValue.value.isEmpty() && !passwordValue.value.isEmpty()
        ) {
            Text("Login", fontSize = 20.sp)
        }

    }
    if (showCard.value) {
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
                    text = "Email: ${emailValue.value}",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "Password: ${passwordValue.value}",
                    style = MaterialTheme.typography.body1
                )
                Button(onClick = { showCard.value = false }) {
                    Text("Back", fontSize = 20.sp)
                }
            }
        }
    }
}


expect fun getPlatformName(): String