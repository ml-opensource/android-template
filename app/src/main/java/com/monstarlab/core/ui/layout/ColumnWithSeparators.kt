package com.monstarlab.core.ui.layout

import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ColumnWithSeparators(
    modifier: Modifier = Modifier,
    separator: @Composable () -> Unit = { Divider() },
    content: @Composable () -> Unit
) {

    SubcomposeLayout(modifier) { constraints ->
        val contentsMesuarebles = subcompose("content", content)
        val separators = contentsMesuarebles.map { subcompose("separator$it", separator).first() }
        val separatorsPlaceables = separators.map { it.measure(constraints) }
        val contentPlaceables = contentsMesuarebles.map { it.measure(constraints) }

        val totalHeight =
            listOf(separatorsPlaceables, contentPlaceables).flatten().sumOf { it.height }

        layout(constraints.maxWidth, totalHeight) {
            val dividerHeight = separatorsPlaceables.maxOfOrNull { it.height } ?: 0
            var y = 0

            val itemHeights = contentPlaceables.mapIndexed { index, placeable ->
                if (index != 0) y += dividerHeight

                placeable.place(x = 0, y = y)
                y += placeable.height
                ContentChild(y)
            }

            separatorsPlaceables.forEachIndexed { index, placeable ->
                if (index != contentPlaceables.size - 1) {
                    placeable.place(x = 0, y = itemHeights[index].height)
                }
            }
        }
    }
}

private data class ContentChild(val height: Int)

@Preview(name = "Column With Separators", showBackground = true)
@Composable
private fun PreviewColumnWithSeparators() {
    ColumnWithSeparators() {
        repeat(10) { Text(text = "Hello $it") }
    }
}
