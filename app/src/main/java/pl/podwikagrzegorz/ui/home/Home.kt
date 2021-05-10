package pl.podwikagrzegorz.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.request.ImageRequest
import com.google.accompanist.coil.CoilImage
import pl.podwikagrzegorz.data.model.MemeListEntry

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
) {
    Surface(
        color = MaterialTheme.colors.primaryVariant,
        modifier = Modifier.fillMaxSize()
    ) {
        MemeList(viewModel)
    }
}

@Composable
private fun MemeList(
    viewModel: HomeViewModel
) {
    val memeList by remember { viewModel.memeList }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }

    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(memeList.size) {
            MemeEntry(entry = memeList[it])
        }
    }

    Box(
        contentAlignment = Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary
            )
        }

        if (loadError.isNotEmpty()) {
            RetrySection(error = loadError) {
                viewModel.loadMemes()
            }
        }
    }
}

@Composable
private fun MemeEntry(
    entry: MemeListEntry,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Center,
        modifier = modifier
            .shadow(8.dp)
            .aspectRatio(1f)
            .background(color = MaterialTheme.colors.background)
    ) {
        Column {
            CoilImage(
                request = ImageRequest.Builder(LocalContext.current)
                    .data(entry.url)
                    .build(),
                contentDescription = entry.name,
                fadeIn = true,
                modifier = Modifier
                    .size(310.dp)
                    .align(CenterHorizontally)
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.scale(0.5f)
                )
            }
            Text(
                text = entry.name,
                fontSize = 20.sp,
                color = MaterialTheme.colors.onSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
private fun RetrySection(
    error: String,
    onRetry: () -> Unit
) {
    Column {
        Text(error, color = Color.Red, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onRetry() },
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Text(text = "Retry")
        }
    }
}