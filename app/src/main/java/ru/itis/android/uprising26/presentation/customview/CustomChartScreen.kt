package ru.itis.android.uprising26.presentation.customview

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.itis.android.uprising26.R

@Composable
fun CustomChartScreen() {
    val sectors = listOf(
        ChartSector(80, Color(0xFF2196F3)),
        ChartSector(70, Color(0xFF00695C)),
        ChartSector(60, Color(0xFF00BCD4)),
        ChartSector(50, Color(0xFF8BC34A))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(24.dp)
    ) {
        Text(
            text = stringResource(R.string.custom_chart_title),
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(32.dp))

        CircularRingsChart(sectors = sectors)
    }
}
