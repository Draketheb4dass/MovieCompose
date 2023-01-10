package com.jephtecolin.moviecompose.extensions

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import com.jephtecolin.moviecompose.data.remote.Api
import kotlinx.coroutines.flow.StateFlow

inline fun <T> LazyGridScope.paging(
    items: List<T>,
    currentIndexFlow: StateFlow<Int>,
    threshold: Int = 4,
    pageSize: Int = Api.PAGING_SIZE,
    crossinline fetch: () -> Unit,
    crossinline itemContent: @Composable (item: T) -> Unit,
) {
    val currentIndex = currentIndexFlow.value

    itemsIndexed(items) { index, item ->

        itemContent(item)

        if ((index + threshold + 1) >= pageSize * (currentIndex - 1)) {
            fetch()
        }
    }
}

