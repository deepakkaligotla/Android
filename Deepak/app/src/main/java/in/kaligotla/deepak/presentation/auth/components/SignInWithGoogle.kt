package `in`.kaligotla.deepak.presentation.auth.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import `in`.kaligotla.deepak.components.ProgressBar
import `in`.kaligotla.deepak.core.Utils.Companion.print
import `in`.kaligotla.deepak.domain.model.Response.*
import `in`.kaligotla.deepak.presentation.auth.AuthViewModel

@Composable
fun SignInWithGoogle(
    viewModel: AuthViewModel = hiltViewModel(),
    navigateToHomeScreen: (signedIn: Boolean) -> Unit
) {
    when(val signInWithGoogleResponse = viewModel.signInWithGoogleResponse) {
        is Loading -> ProgressBar()
        is Success -> signInWithGoogleResponse.data?.let { signedIn ->
            LaunchedEffect(signedIn) {
                navigateToHomeScreen(signedIn)
            }
        }
        is Failure -> LaunchedEffect(Unit) {
            print(signInWithGoogleResponse.e)
        }
    }
}