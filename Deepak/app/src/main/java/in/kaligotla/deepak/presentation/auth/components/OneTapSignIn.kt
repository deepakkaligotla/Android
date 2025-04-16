package `in`.kaligotla.deepak.presentation.auth.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.identity.BeginSignInResult
import `in`.kaligotla.deepak.components.ProgressBar
import `in`.kaligotla.deepak.core.Utils.Companion.print
import `in`.kaligotla.deepak.domain.model.Response.*
import `in`.kaligotla.deepak.presentation.auth.AuthViewModel

@Composable
fun OneTapSignIn(
    viewModel: AuthViewModel = hiltViewModel(),
    launch: (result: BeginSignInResult) -> Unit
) {
    when(val oneTapSignInResponse = viewModel.oneTapSignInResponse) {
        is Loading -> ProgressBar()
        is Success -> oneTapSignInResponse.data?.let {
            LaunchedEffect(it) {
                launch(it)
            }
        }
        is Failure -> LaunchedEffect(Unit) {
            print(oneTapSignInResponse.e)
        }
    }
}