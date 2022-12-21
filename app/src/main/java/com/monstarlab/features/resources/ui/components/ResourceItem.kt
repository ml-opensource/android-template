package com.monstarlab.features.resources.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.monstarlab.core.ui.extensions.fromHex
import com.monstarlab.designsystem.theme.AppTheme
import com.monstarlab.designsystem.theme.Theme
import com.monstarlab.features.resources.domain.Resource

@Composable
fun ResourceItem(
    resource: Resource,
    modifier: Modifier = Modifier
) {
    Card {
        Row(
            modifier = modifier.fillMaxWidth().padding(Theme.dimensions.medium2),
            horizontalArrangement = Arrangement.spacedBy(Theme.dimensions.medium1),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(
                        brush = Brush.verticalGradient(listOf(Color.DarkGray, Color.fromHex(resource.color))),
                        shape = RoundedCornerShape(8.dp)
                    )
            )
            Text(
                text = buildString {
                    append(resource.name)
                    append(' ')
                    append(resource.year)
                }
            )
        }
    }
}

@Preview(name = "ResourceItem")
@Composable
private fun PreviewResourceItem() {
    AppTheme {
        ResourceItem(Resource.Mock)
    }
}
