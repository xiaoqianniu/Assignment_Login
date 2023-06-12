import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.theme.AppTheme


@OptIn(ExperimentalResourceApi::class)
@Composable
fun mainScreen(count:Int) {
        Scaffold(
            topBar = {
                TopAppBar(title =
                {

                        Text(
                            "Welcome!  $count",
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
                myLoginForm(count)
            }
        }
    }
