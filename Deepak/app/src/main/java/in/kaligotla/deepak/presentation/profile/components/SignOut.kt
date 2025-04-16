package `in`.kaligotla.deepak.presentation.profile.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import `in`.kaligotla.deepak.components.ProgressBar
import `in`.kaligotla.deepak.core.Utils.Companion.print
import `in`.kaligotla.deepak.domain.model.Response.*
import `in`.kaligotla.deepak.presentation.profile.ProfileViewModel

@Composable
fun SignOut(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToAuthScreen: (signedOut: Boolean) -> Unit
) {
    when(val signOutResponse = viewModel.signOutResponse) {
        is Loading -> ProgressBar()
        is Success -> signOutResponse.data?.let { signedOut ->
            LaunchedEffect(signedOut) {
                navigateToAuthScreen(signedOut)
            }
        }
        is Failure -> LaunchedEffect(Unit) {
            print(signOutResponse.e)
        }
    }
}