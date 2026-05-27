package ru.itis.android.uprising26.presentation.customview

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.itis.android.uprising26.R
import kotlin.math.sqrt

@Composable
fun CircularRingsChart(
    sectors: List<ChartSector>,
    modifier: Modifier = Modifier
) {
    val sectorsCountError = stringResource(R.string.chart_error_sectors_count)
    val sectorValueError = stringResource(R.string.chart_error_sector_value)
    val uniqueColorsError = stringResource(R.string.chart_error_unique_colors)

    require(sectors.size in 2..7) { sectorsCountError }
    require(sectors.all { it.value in 1..100 }) { sectorValueError }
    require(sectors.map { it.color }.distinct().size == sectors.size) { uniqueColorsError }

    var selectedIndex by remember { mutableIntStateOf(0) }

    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Canvas(
            modifier = Modifier
                .size(280.dp)
                .pointerInput(sectors) {
                    awaitPointerEventScope {
                        while (true) {
                            val down = awaitFirstDown()
                            val click = down.position

                            val canvasWidth = size.width.toFloat()
                            val canvasHeight = size.height.toFloat()

                            val center = Offset(
                                x = canvasWidth / 2f,
                                y = canvasHeight / 2f
                            )

                            val dx = click.x - center.x
                            val dy = click.y - center.y
                            val distance = sqrt(dx * dx + dy * dy)

                            val strokeWidth = 28.dp.toPx()
                            val gap = 8.dp.toPx()
                            val minCanvasSide = minOf(canvasWidth, canvasHeight)
                            val outerRadius = minCanvasSide / 2f - strokeWidth

                            sectors.forEachIndexed { index, _ ->
                                val radius = outerRadius - index * (strokeWidth + gap)
                                val inner = radius - strokeWidth / 2f
                                val outer = radius + strokeWidth / 2f

                                if (distance in inner..outer) {
                                    selectedIndex = index
                                }
                            }
                        }
                    }
                }
        ) {
            val strokeWidth = 28.dp.toPx()
            val gap = 8.dp.toPx()
            val minCanvasSide = minOf(size.width, size.height)
            val outerRadius = minCanvasSide / 2f - strokeWidth
            val center = Offset(size.width / 2f, size.height / 2f)

            sectors.forEachIndexed { index, sector ->
                val radius = outerRadius - index * (strokeWidth + gap)

                val topLeft = Offset(
                    x = center.x - radius,
                    y = center.y - radius
                )

                val arcSize = Size(
                    width = radius * 2,
                    height = radius * 2
                )

                val sweepAngle = 360f * sector.value / 100f

                val color = if (index == selectedIndex) {
                    sector.color
                } else {
                    lerp(sector.color, androidx.compose.ui.graphics.Color.Gray, 0.35f)
                }

                drawArc(
                    color = color,
                    startAngle = 90f,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    topLeft = topLeft,
                    size = arcSize,
                    style = Stroke(
                        width = strokeWidth,
                        cap = StrokeCap.Butt
                    )
                )
            }
        }

        Spacer(modifier = Modifier.width(24.dp))

        Column(
            modifier = Modifier.padding(top = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            sectors.forEachIndexed { index, sector ->
                Text(
                    text = stringResource(
                        R.string.chart_percent_format,
                        sector.value
                    ),
                    color = if (index == selectedIndex) {
                        sector.color
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    },
                    fontSize = if (index == selectedIndex) 32.sp else 22.sp
                )
            }
        }
    }
}