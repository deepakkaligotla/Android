package `in`.kaligotla.deepak.presentation.auth

import android.app.Activity.RESULT_OK
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider.getCredential
import `in`.kaligotla.deepak.core.Utils.Companion.print
import `in`.kaligotla.deepak.navigation.Screen
import `in`.kaligotla.deepak.presentation.auth.components.AuthContent
import `in`.kaligotla.deepak.presentation.auth.components.AuthTopBar
import `in`.kaligotla.deepak.presentation.auth.components.OneTapSignIn
import `in`.kaligotla.deepak.presentation.auth.components.SignInWithGoogle
import `in`.kaligotla.deepak.ui.theme.DeepakTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    navigateToProfileScreen: () -> Unit
) {
    DeepakTheme() {
        Scaffold(
            topBar = {
                AuthTopBar()
            },
            content = { padding ->
                AuthContent(
                    padding = padding,
                    oneTapSignIn = {
                        viewModel.oneTapSignIn()
                    }
                )
            }
        )
    }

    val launcher = rememberLauncherForActivityResult(StartIntentSenderForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            try {
                val credentials = viewModel.oneTapClient.getSignInCredentialFromIntent(result.data)
                val googleIdToken = credentials.googleIdToken
                val googleCredentials = getCredential(googleIdToken, null)
                viewModel.signInWithGoogle(googleCredentials)
            } catch (it: ApiException) {
                print(it)
            }
        }
    }

    fun launch(signInResult: BeginSignInResult) {
        val intent = IntentSenderRequest.Builder(signInResult.pendingIntent.intentSender).build()
        launcher.launch(intent)
    }

    OneTapSignIn(
        launch = {
            launch(it)
        }
    )

    SignInWithGoogle(
        navigateToHomeScreen = { signedIn ->
            if (signedIn) {
                navigateToProfileScreen()
            }
        }
    )
}

@Preview
@Composable
fun AuthScreenPreview() {
    Screen.AuthScreen
}