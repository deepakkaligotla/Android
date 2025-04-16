package `in`.kaligotla.deepak.presentation.profile.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import `in`.kaligotla.deepak.components.ProgressBar
import `in`.kaligotla.deepak.core.Utils.Companion.print
import `in`.kaligotla.deepak.domain.model.Response.*
import `in`.kaligotla.deepak.presentation.profile.ProfileViewModel

@Composable
fun RevokeAccess(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToAuthScreen: (accessRevoked: Boolean) -> Unit,
    showSnackBar: () -> Unit
) {
    when(val revokeAccessResponse = viewModel.revokeAccessResponse) {
        is Loading -> ProgressBar()
        is Success -> revokeAccessResponse.data?.let { accessRevoked ->
            LaunchedEffect(accessRevoked) {
                navigateToAuthScreen(accessRevoked)
            }
        }
        is Failure -> LaunchedEffect(Unit) {
            print(revokeAccessResponse.e)
            showSnackBar()
        }
    }
}