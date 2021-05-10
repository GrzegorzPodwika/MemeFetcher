package pl.podwikagrzegorz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import pl.podwikagrzegorz.ui.home.HomeScreen
import pl.podwikagrzegorz.ui.home.HomeViewModel
import pl.podwikagrzegorz.ui.theme.MemeFetcherTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemeFetcherTheme {
                HomeScreen(viewModel)
            }
        }
    }
}
