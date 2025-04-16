package `in`.kaligotla.deepak.presentation.profile.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import `in`.kaligotla.deepak.core.Constants.DEEPAK_KALIGOTLA
import `in`.kaligotla.deepak.core.Constants.PROFILE_SCREEN
import `in`.kaligotla.deepak.core.Constants.REVOKE_ACCESS
import `in`.kaligotla.deepak.core.Constants.SIGN_OUT

@Composable
fun ProfileTopBar(
    photoUrl: String,
    displayName: String,
    signOut: () -> Unit,
    revokeAccess: () -> Unit
) {
    var openMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(
                text = DEEPAK_KALIGOTLA
            )
        },
        actions = {
            IconButton(
                onClick = {
                    openMenu = !openMenu
                }
            ) {
                Row() {
                    Text(
                        text = displayName,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(end=10.dp)
                    )
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(photoUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(CircleShape)
                            .width(30.dp)
                            .height(30.dp)
                    )
                }
            }
            DropdownMenu(
                expanded = openMenu,
                onDismissRequest = {
                    openMenu = !openMenu
                }
            ) {
                DropdownMenuItem(
                    onClick = {
                        signOut()
                        openMenu = !openMenu
                    }
                ) {
                    Text(
                        text = SIGN_OUT
                    )
                }
                DropdownMenuItem(
                    onClick = {
                        revokeAccess()
                        openMenu = !openMenu
                    }
                ) {
                    Text(
                        text = REVOKE_ACCESS
                    )
                }
            }
        }
    )
}