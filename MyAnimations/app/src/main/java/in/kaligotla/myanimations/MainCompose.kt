package `in`.kaligotla.myanimations

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainCompose(
    viewModel: MainActivityViewModel = hiltViewModel()
) {
    viewModel.runMyChallenge()
}