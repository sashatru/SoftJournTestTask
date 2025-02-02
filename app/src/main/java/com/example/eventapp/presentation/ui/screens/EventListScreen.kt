package com.example.eventapp.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.eventapp.domain.model.Event
import com.example.eventapp.domain.model.Organizer
import com.example.eventapp.domain.model.Poster
import com.example.eventapp.domain.model.PosterLocation
import com.example.eventapp.domain.model.PosterSize
import com.example.eventapp.presentation.ui.theme.EventAppTheme
import com.example.eventapp.presentation.viewmodel.EventViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.getViewModel
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventListScreen(viewModel: EventViewModel = getViewModel()) {
    val events by viewModel.events.collectAsState()
    val listState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Events",
                    style = MaterialTheme.typography.displayMedium
                )
            })
        }
    ) { innerPadding ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(events.size) { index ->
                val event = events[index]
                EventItem(event = event)
                Spacer(modifier = Modifier.height(16.dp))

                // Trigger pagination when the user scrolls to the bottom of the list
                if (index == events.size - 1) {
                    LaunchedEffect(index) {
                        Timber.tag("OkHttp").v("ELS LaunchedEffect #1")
                        viewModel.fetchEvents()
                    }
                }
            }
        }
    }

    // Observe list state to trigger pagination
/*    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }.collectLatest { index ->
            if (index >= events.size - 1) {
                Timber.tag("OkHttp").v("ELS LaunchedEffect #2")
                viewModel.fetchEvents()
            }
        }
    }*/
}

@Composable
fun EventItem(event: Event) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column {
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(event.posters?.firstOrNull()?.sizes?.small?.location)
                    .crossfade(true)
                    .build()
            )
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .height(200.dp)
                    .padding(top = 4.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = event.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )
            event.organizer?.companyName?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EventItemPreview() {
    EventAppTheme {
        EventItem(
            Event(
                id = "1",
                title = "Sample Event",
                priceRangeStart = 0,
                currencyKey = "USD",
                countryKey = "US",
                organizer = Organizer("Sample Organizer"),
                posters = listOf(
                    Poster(
                        "1",
                        PosterSize(PosterLocation("https://us-east1-testing-cnt.cinewav.com/image/2022/46/resized/114947/small-0b388fd8-f402-46f0-aed7-909c96a101e5.jpg"))
                    )
                ),
                performances = listOf()
            )
        )
    }
}
