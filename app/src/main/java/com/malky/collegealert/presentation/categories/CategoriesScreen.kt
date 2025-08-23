package com.malky.collegealert.presentation.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.Lucide
import com.malky.collegealert.app.navigation.LocalNavController
import com.malky.collegealert.app.theme.CollegeAlertTheme
import com.malky.collegealert.presentation.composables.CategoryCard
import com.malky.collegealert.presentation.composables.HeaderSection

@Composable
fun CategoriesScreen(
    viewModel: CategoriesViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    CategoriesScreenContent(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun CategoriesScreenContent(
    state: CategoriesState,
    onAction: (CategoriesAction) -> Unit,
    navController: NavHostController = LocalNavController.current
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.statusBars,
        topBar = {
            HeaderSection(
                title = "Categories",
                subtitle = "Browse events by category",
                navigationIcon = {
                    IconButton(
                        modifier = Modifier.padding(end = 4.dp),
                        onClick = {
                            navController.navigateUp()
                        }
                    ){
                        Icon(
                            modifier = Modifier
                                .size(20.dp),
                            imageVector = Lucide.ArrowLeft,
                            contentDescription = "Navigate Back",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            )
        }
    ){ innerPadding ->
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 24.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) {
            items(items = state.categories){ category ->
                CategoryCard(
                    eventType = category.first,
                    eventsCount = category.second,
                    onClick = {}
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCategoriesScreen() {
    CollegeAlertTheme {
        CategoriesScreenContent(
            state = CategoriesState(),
            onAction = {}
        )
    }
}