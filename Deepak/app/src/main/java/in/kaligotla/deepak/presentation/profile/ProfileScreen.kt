package `in`.kaligotla.deepak.presentation.profile

import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph
import `in`.kaligotla.deepak.core.Constants.REVOKE_ACCESS_MESSAGE
import `in`.kaligotla.deepak.core.Constants.SIGN_OUT
import `in`.kaligotla.deepak.navigation.Screen
import `in`.kaligotla.deepak.presentation.profile.components.ProfileContent
import `in`.kaligotla.deepak.presentation.profile.components.ProfileTopBar
import `in`.kaligotla.deepak.presentation.profile.components.RevokeAccess
import `in`.kaligotla.deepak.presentation.profile.components.SignOut
import `in`.kaligotla.deepak.ui.theme.DeepakTheme
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToAuthScreen: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            ProfileTopBar(
                photoUrl = viewModel.photoUrl,
                displayName = viewModel.displayName,
                signOut = {
                    viewModel.signOut()
                },
                revokeAccess = {
                    viewModel.revokeAccess()
                }
            )
        },
        content = { padding ->
            ProfileContent(
                padding = padding,
            )
        },
        scaffoldState = scaffoldState
    )

    SignOut(
        navigateToAuthScreen = { signedOut ->
            if (signedOut) {
                navigateToAuthScreen()
            }
        }
    )

    fun showSnackBar() = coroutineScope.launch {
        val result = scaffoldState.snackbarHostState.showSnackbar(
            message = REVOKE_ACCESS_MESSAGE,
            actionLabel = SIGN_OUT
        )
        if (result == SnackbarResult.ActionPerformed) {
            viewModel.signOut()
        }
    }

    RevokeAccess(
        navigateToAuthScreen = { accessRevoked ->
            if (accessRevoked) {
                navigateToAuthScreen()
            }
        },
        showSnackBar = {
            showSnackBar()
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    DeepakTheme {
        Screen.ProfileScreen
    }
}