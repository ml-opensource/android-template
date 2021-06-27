package com.monstarlab.features.resources.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.monstarlab.core.domain.model.Resource

@Composable
fun ResourceItem(resource: Resource, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = resource.name)
            Text(text = resource.color, style = MaterialTheme.typography.caption)
        }
    }
}

@Preview
@Composable
fun PreviewResourceItem() {
    ResourceItem(resource = Resource(0, "Name", 1222, "color", pantoneValue = "value"))
}
