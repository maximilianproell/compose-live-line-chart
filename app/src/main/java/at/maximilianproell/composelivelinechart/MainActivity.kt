package at.maximilianproell.composelivelinechart

import android.os.Bundle
import android.text.format.DateUtils
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import at.maximilianproell.composelivelinechart.ui.theme.ComposeLiveLinechartTheme
import at.maximilianproell.linechart.LineChart
import at.maximilianproell.linechart.common.axis.AxisConfigDefaults
import at.maximilianproell.linechart.config.LineConfigDefaults
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToLong

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLiveLinechartTheme {
                val chartData by viewModel.chartData.collectAsState(initial = emptyList())

                Surface(modifier = Modifier.fillMaxSize()) {
                    LineChart(
                        modifier = Modifier.padding(16.dp),
                        lineDataSets = { chartData },
                        minVisibleYValue = 40f,
                        maxVisibleYValue = 80f,
                        lineConfig = LineConfigDefaults.lineConfigDefaults().copy(
                            showLineDots = false,
                        ),
                        xAxisConfig = AxisConfigDefaults.xAxisConfigDefaults().copy(
                            numberOfLabels = 2,
                            labelsFormatter = { xValue ->
                                DateUtils.formatElapsedTime(xValue.roundToLong())
                            },
                            labelsYOffset = 16.dp
                        ),
                        yAxisConfig = AxisConfigDefaults.yAxisConfigDefaults().copy(
                            showLines = false,
                            numberOfLabels = 5
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeLiveLinechartTheme {

    }
}