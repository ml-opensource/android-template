package com.monstarlab.features.resources.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.monstarlab.core.domain.model.Resource

@Composable
fun ResourcesList(items: List<Resource>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items.count()) {
            ResourceItem(items[it], modifier = Modifier.fillMaxWidth().padding(16.dp))
        }
    }
}

@Preview
@Composable
fun PreviewResourcesList() {
    ResourcesList(items = List(3) {
        Resource(it, it.toString(), it, it.toString(), "")
    })
}