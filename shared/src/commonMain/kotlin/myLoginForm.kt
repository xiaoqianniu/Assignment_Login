import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@OptIn(ExperimentalResourceApi::class)
@Composable
fun myLoginForm(count:Int) {

    var emailValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }
    var showCard by remember { mutableStateOf(false) }
    var showRegisterForm by remember { mutableStateOf(false) }
    var isTextVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isTextVisible) {
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
                .clickable { isTextVisible = !isTextVisible }
        )

        Spacer(modifier = Modifier.height(20.dp))
        Card(modifier = Modifier.padding(15.dp), elevation = 10.dp) {
            TextField(
                value = emailValue,
                onValueChange = { emailValue = it },
                textStyle = TextStyle(textAlign = TextAlign.Center),
                label = { Text("Enter email address") },
                leadingIcon = {
                    Icon(Icons.Filled.Email, contentDescription = "password icon")
                },
                singleLine = true
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
//        Card(elevation = 10.dp) {
        OutlinedTextField(
            value = passwordValue,
            onValueChange = { passwordValue = it },
            textStyle = TextStyle(textAlign = TextAlign.Center),
            label = { Text("Enter password") },
            leadingIcon = {
                Icon(Icons.Filled.Lock, contentDescription = "password icon")
            },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true
        )

//        }
        Spacer(modifier = Modifier.height(15.dp))

        Text(
            "New User? Click", fontSize = 20.sp,
            style = TextStyle(textDecoration = TextDecoration.Underline),
            modifier = Modifier.clickable(onClick = {
                showRegisterForm = true

            }, onClickLabel = "go to register")
        )

        Spacer(modifier = Modifier.height(15.dp))
        Button(
            onClick = {
                showCard = true
            },
            enabled = !emailValue.isEmpty() && !passwordValue.isEmpty()
        ) {
            Text("Login", fontSize = 20.sp)
        }

    }
    if (showRegisterForm) {
        registerForm(onDismiss = { showRegisterForm = false },
            onDataSubmitted = { email, password, count ->
                emailValue = email
                passwordValue = password
            })
    }
    if (showCard) {
        loginInfoDisplay(emailValue, passwordValue,
            onBackClicked = {
                showCard = false
                emailValue = ""
                passwordValue = ""
            }
        )

    }
}