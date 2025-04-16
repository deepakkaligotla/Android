package `in`.kaligotla.deepak.presentation.auth.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.kaligotla.deepak.R

@Composable
fun AuthContent(
    padding: PaddingValues,
    oneTapSignIn: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        contentAlignment = Alignment.TopCenter,
    ) {
        Spacer(
            modifier = Modifier.height(100.dp)
        )
        Column(modifier = Modifier
            .fillMaxHeight()
            .padding(padding)
            .width(IntrinsicSize.Max)
            .padding(start =10.dp, end = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Please login with Google account to see my profile",
                fontSize = 20.sp,
                modifier = Modifier.padding(end=10.dp)
            )
            Spacer(
                    modifier = Modifier.height(100.dp)
                    )
            Image(
                painter = painterResource(id = R.drawable.ic_logo_google),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .width(100.dp)
                    .height(100.dp)
            )
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        contentAlignment = Alignment.BottomCenter
    ) {
        SignInButton(
            onClick = oneTapSignIn
        )
    }
}