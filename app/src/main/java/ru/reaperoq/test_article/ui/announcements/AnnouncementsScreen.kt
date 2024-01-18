package ru.reaperoq.test_article.ui.announcements

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material3.fade
import com.google.accompanist.placeholder.material3.placeholder
import org.koin.androidx.compose.koinViewModel
import ru.reaperoq.test_article.presentation.AnnouncementsViewModel
import ru.reaperoq.test_article.presentation.models.AnnouncementEvent
import ru.reaperoq.test_article.presentation.models.AnnouncementUi

@Composable
fun AnnouncementsScreen(
    modifier: Modifier = Modifier,
    viewModel: AnnouncementsViewModel = koinViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.errorMessage) {
        if (uiState.errorMessage != null) {
            val snackbarResult = snackbarHostState.showSnackbar(
                uiState.errorMessage!!,
                actionLabel = "Retry",
                false,
                SnackbarDuration.Long
            )
            when (snackbarResult) {
                SnackbarResult.ActionPerformed -> {
                    viewModel.obtainEvent(AnnouncementEvent.RetryClicked)
                }

                else -> {}
            }
        }
    }

    Scaffold(
        modifier = modifier.systemBarsPadding(),
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        }
    ) { padding ->
        Crossfade(targetState = uiState.isLoaded, label = "Announcements list fade") {
            if (it) {
                AnnouncementsList(
                    modifier = modifier
                        .consumeWindowInsets(padding)
                        .fillMaxSize(),
                    list = uiState.list
                )
            } else {
                AnnouncementPlaceholderList(modifier)
            }
        }
    }
}

@Composable
fun AnnouncementsList(
    modifier: Modifier = Modifier,
    list: List<AnnouncementUi>
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(list) { announcement ->
            AnnouncementItem(announcement = announcement)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AnnouncementItem(modifier: Modifier = Modifier, announcement: AnnouncementUi) {
    ElevatedCard(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = announcement.type,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
            )
            Text(
                text = "From ${announcement.startDate} to ${announcement.endDate}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                style = MaterialTheme.typography.titleMedium,
                text = announcement.title
            )
            Text(
                style = MaterialTheme.typography.bodyMedium,
                text = announcement.description
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                announcement.tags.forEach {
                    AssistChip(
                        onClick = { },
                        label = { Text(text = it) }
                    )
                }
            }
            Text(
                text = announcement.date
            )
        }
    }
}

@Composable
fun AnnouncementPlaceholderList(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(30) {
            AnnouncementPlaceholder()
        }
    }
}

@Suppress("DEPRECATION")
@Composable
fun AnnouncementPlaceholder(
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                modifier = Modifier.placeholder(
                    visible = true,
                    highlight = PlaceholderHighlight.fade()
                ),
                text = "Latest Activities"
            )
            Text(
                modifier = Modifier.placeholder(
                    visible = true,
                    highlight = PlaceholderHighlight.fade()
                ),
                text = "From 18.01.2023 to 20.01.2023"
            )
            Text(
                modifier = Modifier.placeholder(
                    visible = true,
                    highlight = PlaceholderHighlight.fade()
                ),
                style = MaterialTheme.typography.titleMedium,
                text = "Protect Your First Copy Trade With 100 USDT Loss Coverage"
            )
            Text(
                modifier = Modifier.placeholder(
                    visible = true,
                    highlight = PlaceholderHighlight.fade()
                ),
                style = MaterialTheme.typography.bodyMedium,
                text = "Our rejuvenated Copy Trading 101 event is designed to help you safely navigate the markets, minimize potential losses, and optimize your trading strategy with a 100 USDT Loss Coverage Voucher."
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(3) {
                    AssistChip(
                        modifier = Modifier.placeholder(
                            visible = true,
                            highlight = PlaceholderHighlight.fade()
                        ),
                        onClick = { /*TODO*/ },
                        label = { Text(text = "Copy Trading") }
                    )
                }
            }
            Text(
                modifier = Modifier.placeholder(
                    visible = true,
                    highlight = PlaceholderHighlight.fade()
                ),
                text = "11:00 18.01.2023"
            )
        }
    }
}