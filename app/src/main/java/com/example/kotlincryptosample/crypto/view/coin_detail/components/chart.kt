package com.example.kotlincryptosample.crypto.view.coin_detail.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottom
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStart
import com.patrykandpatrick.vico.compose.cartesian.cartesianLayerPadding
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLine
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.cartesian.rememberVicoZoomState
import com.patrykandpatrick.vico.compose.common.data.rememberExtraLambda
import com.patrykandpatrick.vico.compose.common.fill
import com.patrykandpatrick.vico.core.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.core.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries
import com.patrykandpatrick.vico.core.cartesian.layer.LineCartesianLayer
//import com.patrykandpatrick.vico.databinding.Chart1Binding
//import com.patrykandpatrick.vico.sample.showcase.UIFramework
//import com.patrykandpatrick.vico.sample.showcase.rememberMarker
import kotlin.random.Random
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import android.text.Layout
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.example.kotlincryptosample.crypto.view.model.CoinPriceUi
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberAxisGuidelineComponent
import com.patrykandpatrick.vico.compose.common.component.fixed
import com.patrykandpatrick.vico.compose.common.component.rememberLayeredComponent
import com.patrykandpatrick.vico.compose.common.component.rememberShapeComponent
import com.patrykandpatrick.vico.compose.common.component.rememberTextComponent
import com.patrykandpatrick.vico.compose.common.component.shadow
import com.patrykandpatrick.vico.compose.common.dimensions
import com.patrykandpatrick.vico.compose.common.shape.markerCorneredShape
import com.patrykandpatrick.vico.core.cartesian.CartesianMeasuringContext
import com.patrykandpatrick.vico.core.cartesian.HorizontalDimensions
import com.patrykandpatrick.vico.core.cartesian.axis.BaseAxis
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModel
import com.patrykandpatrick.vico.core.cartesian.data.CartesianLayerRangeProvider
import com.patrykandpatrick.vico.core.cartesian.marker.CartesianMarker
import com.patrykandpatrick.vico.core.cartesian.marker.DefaultCartesianMarker
import com.patrykandpatrick.vico.core.common.Insets
import com.patrykandpatrick.vico.core.common.LayeredComponent
import com.patrykandpatrick.vico.core.common.component.LineComponent
import com.patrykandpatrick.vico.core.common.component.Shadow
import com.patrykandpatrick.vico.core.common.component.ShapeComponent
import com.patrykandpatrick.vico.core.common.component.TextComponent
import com.patrykandpatrick.vico.core.common.shape.Corner
import com.patrykandpatrick.vico.core.common.shape.CorneredShape

@Composable
internal fun Chart1(
    prices: List<CoinPriceUi>, modifier: Modifier
) {
    val modelProducer = remember { CartesianChartModelProducer() }
    LaunchedEffect(Unit) {
        withContext(Dispatchers.Default) {
            modelProducer.runTransaction {

                /* Learn more:
                https://patrykandpatrick.com/vico/wiki/cartesian-charts/layers/line-layer#data. */
                lineSeries {
//                    prices.map {
//                        series(it.v,it.h)
//                    }
//                    series(
//                        prices.map { it.v }.toList(),
//
//                        )
//                    series(
//                        prices.map { it.h }.toList()
//                    )
//                    series(x=prices.map { it.v },y=prices.map { it.h })
                    series(y = prices.map { it.h })
//                    series(y = prices.map { it.h - prices.minOf { it.h } })
                }
            }
        }
    }
    ComposeChart1(modelProducer, modifier, prices.minOf { it.h })
}

@Composable
private fun ComposeChart1(
    modelProducer: CartesianChartModelProducer,
    modifier: Modifier,
    minY: Double
) {
    val marker = rememberMarker()
    CartesianChartHost(
        chart = rememberCartesianChart(
            rememberLineCartesianLayer(

                lineProvider = LineCartesianLayer.LineProvider.series(
                    LineCartesianLayer.rememberLine(
                        remember { LineCartesianLayer.LineFill.single(fill(Color(0xffa485e0))) })
                ),
                rangeProvider = remember { CartesianLayerRangeProvider.fixed(minY = minY) }

            ),
            startAxis = VerticalAxis.rememberStart(
//                guideline =,
                itemPlacer = remember { VerticalAxis.ItemPlacer.count() },

                ),
            bottomAxis = HorizontalAxis.rememberBottom(
//                guideline = null,
                itemPlacer = remember { HorizontalAxis.ItemPlacer.segmented() },
            ),
            marker = marker,
            layerPadding = cartesianLayerPadding(scalableStart = 16.dp, scalableEnd = 16.dp),
            persistentMarkers = rememberExtraLambda(marker) { marker.at(PERSISTENT_MARKER_X) },
        ),
        modelProducer = modelProducer,
        modifier = modifier,
        zoomState = rememberVicoZoomState(zoomEnabled = true),
    )
}


private const val PERSISTENT_MARKER_X = 19f

//private val x = (1..50).toList()


@Composable
internal fun rememberMarker(
    labelPosition: DefaultCartesianMarker.LabelPosition = DefaultCartesianMarker.LabelPosition.Top,
    showIndicator: Boolean = true,
): CartesianMarker {
    val labelBackgroundShape = markerCorneredShape(Corner.FullyRounded)
    val labelBackground = rememberShapeComponent(
        color = MaterialTheme.colorScheme.surfaceBright,
        shape = labelBackgroundShape,
        shadow = shadow(
            radius = LABEL_BACKGROUND_SHADOW_RADIUS_DP.dp, dy = LABEL_BACKGROUND_SHADOW_DY_DP.dp
        ),
    )
    val label = rememberTextComponent(
        color = MaterialTheme.colorScheme.onSurface,
        textAlignment = Layout.Alignment.ALIGN_CENTER,
        padding = dimensions(8.dp, 4.dp),
        background = labelBackground,
        minWidth = TextComponent.MinWidth.fixed(40.dp),
    )
    val indicatorFrontComponent =
        rememberShapeComponent(MaterialTheme.colorScheme.surface, CorneredShape.Pill)
    val indicatorCenterComponent = rememberShapeComponent(shape = CorneredShape.Pill)
    val indicatorRearComponent = rememberShapeComponent(shape = CorneredShape.Pill)
    val indicator = rememberLayeredComponent(
        rear = indicatorRearComponent,
        front = rememberLayeredComponent(
            rear = indicatorCenterComponent,
            front = indicatorFrontComponent,
            padding = dimensions(5.dp),
        ),
        padding = dimensions(10.dp),
    )
    val guideline = rememberAxisGuidelineComponent()
    return remember(label, labelPosition, indicator, showIndicator, guideline) {
        object : DefaultCartesianMarker(
            label = label,
            labelPosition = labelPosition,
            indicator = if (showIndicator) {
                { color ->
                    LayeredComponent(
                        rear = ShapeComponent(
                            ColorUtils.setAlphaComponent(color, 38), CorneredShape.Pill
                        ),
                        front = LayeredComponent(
                            rear = ShapeComponent(
                                color = color,
                                shape = CorneredShape.Pill,
                                shadow = Shadow(radiusDp = 12f, color = color),
                            ),
                            front = indicatorFrontComponent,
                            padding = dimensions(5.dp),
                        ),
                        padding = dimensions(10.dp),
                    )
                }
            } else {
                null
            },
            indicatorSizeDp = 36f,
            guideline = guideline,
        ) {
            override fun updateInsets(
                context: CartesianMeasuringContext,
                horizontalDimensions: HorizontalDimensions,
                model: CartesianChartModel,
                insets: Insets,
            ) {
                with(context) {
                    val baseShadowInsetDp =
                        CLIPPING_FREE_SHADOW_RADIUS_MULTIPLIER * LABEL_BACKGROUND_SHADOW_RADIUS_DP
                    var topInset = (baseShadowInsetDp - LABEL_BACKGROUND_SHADOW_DY_DP).pixels
                    var bottomInset = (baseShadowInsetDp + LABEL_BACKGROUND_SHADOW_DY_DP).pixels
                    when (labelPosition) {
                        LabelPosition.Top, LabelPosition.AbovePoint -> topInset += label.getHeight(
                            context
                        ) + tickSizeDp.pixels

                        LabelPosition.Bottom -> bottomInset += label.getHeight(context) + tickSizeDp.pixels
                        LabelPosition.AroundPoint -> {}
                    }
                    insets.ensureValuesAtLeast(top = topInset, bottom = bottomInset)
                }
            }
        }
    }
}

private const val LABEL_BACKGROUND_SHADOW_RADIUS_DP = 4f
private const val LABEL_BACKGROUND_SHADOW_DY_DP = 2f
private const val CLIPPING_FREE_SHADOW_RADIUS_MULTIPLIER = 1.4f