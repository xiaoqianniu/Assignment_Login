import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.compose.md_theme_dark_onPrimary
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun registerForm(onDismiss: () -> Unit, onDataSubmitted: (String, String,count:Int) -> Unit) {

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPwd by remember { mutableStateOf("") }
    var count by remember { mutableStateOf(0) }
    val imageObjects = arrayListOf<String>("logo1.jpg","logo1.jpg","logo1.jpg")

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
                    value = username,
                    onValueChange = { username = it },
                    textStyle = TextStyle(textAlign = TextAlign.Center),
                    label = { Text("Enter username") },
                    leadingIcon = {
                        Icon(Icons.Filled.Face, contentDescription = "username icon")
                    },
                    singleLine = true
                )
            }
            Card(modifier = Modifier.padding(15.dp), elevation = 10.dp) {
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    textStyle = TextStyle(textAlign = TextAlign.Center),
                    label = { Text("Enter email address") },
                    leadingIcon = {
                        Icon(Icons.Filled.Email, contentDescription = "email icon")
                    },
                    singleLine = true
                )
            }
            Card(modifier = Modifier.padding(15.dp), elevation = 10.dp) {
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    textStyle = TextStyle(textAlign = TextAlign.Center),
                    label = { Text("Enter password") },
                    leadingIcon = {
                        Icon(Icons.Filled.Lock, contentDescription = "password icon")
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true
                )
            }
            Card(modifier = Modifier.padding(15.dp), elevation = 10.dp) {
                TextField(
                    value = confirmPwd,
                    onValueChange = { confirmPwd = it },
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

                        onDataSubmitted(email, password,count +1)
                        onDismiss()
                    },
                    enabled = !username.isEmpty() && !email.isEmpty() && !password.isEmpty() && !confirmPwd.isEmpty()
                ) {
                    Text("Submit", fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.width(20.dp))
                Button(
                    onClick = {
                        username = ""
                        email = ""
                        password = ""
                        confirmPwd = ""
                        onDismiss()
                    }
                ) {
                    Text("Cancel", fontSize = 20.sp)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            LazyRow {
                itemsIndexed(imageObjects) { index, image ->
                    Image(
                        painter = painterResource(image),
                        contentDescription = null,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }

            }
        }

    }

}

