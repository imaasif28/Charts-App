package com.example.chartapp

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Path
import android.graphics.RectF
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.github.mikephil.charting.animation.ChartAnimator
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet
import com.github.mikephil.charting.renderer.PieChartRenderer
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import com.github.mikephil.charting.utils.Utils
import com.github.mikephil.charting.utils.ViewPortHandler
import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.io.UnsupportedEncodingException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.security.Security
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.ShortBufferException
import javax.crypto.spec.SecretKeySpec


class MainActivity : AppCompatActivity() {

    lateinit var btnBarChart: Button
    lateinit var btnPieChart: Button
    lateinit var btnRadarChart: Button
    private lateinit var lineChart: LineChart
    private lateinit var pieChart: PieChart
    private lateinit var pieChart2: PieChart
    private lateinit var pieChartBG: PieChart
    private lateinit var pieChartBG2: PieChart

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnBarChart = findViewById(R.id.button)
        btnPieChart = findViewById(R.id.button2)
        btnRadarChart = findViewById(R.id.button3)
        lineChart = findViewById(R.id.sleepChartView)
        pieChart = findViewById(R.id.pieChartView)
        pieChart2 = findViewById(R.id.pieChartView2)
        pieChartBG = findViewById(R.id.pieChartBackgroundView)
        pieChartBG2 = findViewById(R.id.pieChartBackgroundView2)

        lineChart()

        setupPieChart()
        setData()
        setupPieChartBG()
        setDataBG()
        setupPieChart2()
        setData2()
        setupPieChartBG2()
        setDataBG2()

        btnBarChart.setOnClickListener {
            startActivity(Intent(this, BarActivity::class.java))
        }
        btnPieChart.setOnClickListener {
            startActivity(Intent(this, PieActivity::class.java))
            /*  val decrypt =  decryptWithAES(
                  "82ddbd6695b911eeb9d10242ac120002",
                  "U2FsdGVkX1+zpmKrwA8UCJ/MFPx6PawGiiH1f0zImUe4C+GPG/hIsKWKW0SBly+ZFzU+vWqn9lgPL1ZdCUPgz7EWZfzNhJBV6aX0cAosk7R/ubX/vG+uWYK/vebNxxLkoCEqw6iNc3PDB+jokrTFIED4QWTcwBjEdxzNrekfM9mXlbPoZi6KeF7Xsvgo3+3R3FEBj7fCwjugyRQpzdeyk0VFJ8tT2x3xB3bi2meaPFKrvfPkp8iXuQKJbD+lBJOgb7M4ShUFBLfVIDDDWg2gXtCM4l6d3/t7xS94+bEqoiAYgHJefbENFT4G4XXVixn6lY6xNfvWVkskAiW9PDy4+9XfmyM+4lOgS9AfeXIcXgg/y7TA9XWc7fDlz9e3pSGlyw90mv42mJIozRyzgsZDRLsR3zbjyA33JrjRqEsTXIMLaR4Fe6xuPTzUgnpnn+ECgrI9RnfiUNEGkHcmC8s1GitGWtXrMg+qwEqWCXuUf7iixV49+xwmByqq6NPqnar1QY1epLBTNO+JnCObDUej13NKvGKcUuLkq33PbGwOrlnxu66nBXfuXyiR/WsIXyNQz4YwrpuinDVgQHkn+jTcqK99H5AqKHX3tshpktAWvS820yy/wU2KUwh/DMIIN3LX1i3sgwXxo68n+cdhJDqcpU+tfs0xEnvr1EwIa4v6wgkhZ0W1jDHVTtt7ng5Wy2SsmBAq9iJJAVkChjeMS3LzgQi4aa7dBKvhK3PKZi8ubxg="
              )
               println(decrypt)*/
        }
        btnRadarChart.setOnClickListener {
            startActivity(Intent(this, CircularGaugeActivity::class.java))
        }
    }

    private fun setupPieChart() {

//        pieChart.layoutParams = ConstraintLayout.LayoutParams(300,300)

        pieChart.setUsePercentValues(true)
        pieChart.description.isEnabled = false
        pieChart.isDrawHoleEnabled = true
        pieChart.setHoleColor(Color.TRANSPARENT)
        pieChart.setTransparentCircleAlpha(110)
        pieChart.legend.isEnabled = false
        pieChart.holeRadius = 25f
        pieChart.setDrawCenterText(true)
        pieChart.isRotationEnabled = false
    }

    private fun setData() {
//        val outerEntries = mutableListOf<PieEntry>()
        val innerEntries = mutableListOf<PieEntry>()

        // Add your data for the outer pie chart
//        outerEntries.add(PieEntry(30f, "Label 1"))
//        outerEntries.add(PieEntry(40f, "Label 2"))
//        outerEntries.add(PieEntry(25f, "Label 3"))

        // Add your data for the inner pie chart
        innerEntries.add(PieEntry(20f, ""))
        innerEntries.add(PieEntry(80f, ""))
//        innerEntries.add(PieEntry(15f, "Inner Label 3"))

//        val outerDataSet = PieDataSet(outerEntries, "Outer Pie")
//        outerDataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()

        val innerDataSet = PieDataSet(innerEntries, "Inner Pie")
        innerDataSet.valueTextSize = 0.0f
//        innerDataSet.colors = ColorTemplate.PASTEL_COLORS.toList()
        innerDataSet.colors = listOf(
            getColor(R.color.green),
            Color.TRANSPARENT
        )
//        val outerData = PieData(outerDataSet)
        val innerData = PieData(innerDataSet)

        // Set data for the outer and inner pie charts
//        pieChart.data = outerData
//        pieChart.invalidate()

        // Add inner pie chart to the center
        val holeRadius = 85f
        val transparentCircleRadius = 90f
        pieChart.holeRadius = holeRadius
//        pieChart.transparentCircleRadius = transparentCircleRadius
//        pieChart.setExtraOffsets(
//            holeRadius + transparentCircleRadius,
//            holeRadius + transparentCircleRadius,
//            holeRadius + transparentCircleRadius,
//            holeRadius + transparentCircleRadius
//        )
//        pieChart.setDrawRoundedSlices(true)
        pieChart.renderer = RoundedSlicesPieChartRenderer(pieChart, pieChart.animator, pieChart.viewPortHandler);
        pieChart.data = innerData
        pieChart.invalidate()
//        pieChart.notifyDataSetChanged()
    }

    private fun setupPieChart2() {
        pieChart2.setUsePercentValues(true)
        pieChart2.description.isEnabled = false
        pieChart2.isDrawHoleEnabled = true
        pieChart2.setHoleColor(Color.TRANSPARENT)
        pieChart2.setTransparentCircleColor(Color.BLUE)
        pieChart2.setTransparentCircleAlpha(110)
        pieChart2.holeRadius = 25f
        pieChart2.transparentCircleRadius = 30f
        pieChart2.setDrawCenterText(true)
        pieChart2.legend.isEnabled = false
        pieChart2.animateX(1000)
//        pieChart2.rotationAngle = 0f
        pieChart2.isRotationEnabled = false
    }

    private fun setData2() {
//        val outerEntries = mutableListOf<PieEntry>()
        val innerEntries = mutableListOf<PieEntry>()

        // Add your data for the outer pie chart
//        outerEntries.add(PieEntry(30f, "Label 1"))
//        outerEntries.add(PieEntry(40f, "Label 2"))
//        outerEntries.add(PieEntry(25f, "Label 3"))

        // Add your data for the inner pie chart
        innerEntries.add(PieEntry(60f, ""))
        innerEntries.add(PieEntry(40f, ""))
//        innerEntries.add(PieEntry(15f, "Inner Label 3"))

//        val outerDataSet = PieDataSet(outerEntries, "Outer Pie")
//        outerDataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()

        val innerDataSet = PieDataSet(innerEntries, "Inner Pie")
        innerDataSet.valueTextSize = 0.0f

//        innerDataSet.colors = ColorTemplate.PASTEL_COLORS.toList()
        innerDataSet.colors = listOf(
            getColor(R.color.blue),
            Color.TRANSPARENT
        )

//        val outerData = PieData(outerDataSet)
        val innerData = PieData(innerDataSet)

        // Set data for the outer and inner pie charts
//        pieChart2.data = outerData
//        pieChart2.invalidate()

        // Add inner pie chart to the center
        val holeRadius = 80f
        val transparentCircleRadius = 50f
        pieChart2.holeRadius = holeRadius
//        pieChart2.transparentCircleRadius = transparentCircleRadius
//        pieChart2.setExtraOffsets(
//            holeRadius + transparentCircleRadius,
//            holeRadius + transparentCircleRadius,
//            holeRadius + transparentCircleRadius,
//            holeRadius + transparentCircleRadius
//        )
        pieChart2.renderer = RoundedSlicesPieChartRenderer(pieChart2, pieChart2.animator, pieChart2.viewPortHandler);

        pieChart2.data = innerData
        pieChart2.invalidate()
//        pieChart2.notifyDataSetChanged()
    }

    private fun setupPieChartBG() {
        pieChartBG.setUsePercentValues(true)
        pieChartBG.description.isEnabled = false
        pieChartBG.isDrawHoleEnabled = true
        pieChartBG.setHoleColor(Color.TRANSPARENT)
        pieChartBG.legend.isEnabled = false
        pieChartBG.holeRadius = 25f
        pieChartBG.setDrawCenterText(true)
        pieChartBG.isRotationEnabled = false
    }

    private fun setDataBG() {
//        val outerEntries = mutableListOf<PieEntry>()
        val innerEntries = mutableListOf<PieEntry>()

        // Add your data for the outer pie chart
//        outerEntries.add(PieEntry(30f, "Label 1"))
//        outerEntries.add(PieEntry(40f, "Label 2"))
//        outerEntries.add(PieEntry(25f, "Label 3"))

        // Add your data for the inner pie chart
        innerEntries.add(PieEntry(100f, ""))
//        innerEntries.add(PieEntry(80f, ""))
//        innerEntries.add(PieEntry(15f, "Inner Label 3"))

//        val outerDataSet = PieDataSet(outerEntries, "Outer Pie")
//        outerDataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()

        val innerDataSet = PieDataSet(innerEntries, "Inner Pie")
        innerDataSet.valueTextSize = 0.0f
//        innerDataSet.colors = ColorTemplate.PASTEL_COLORS.toList()
        innerDataSet.colors = listOf(
            getColor(R.color.gray),
            Color.TRANSPARENT
        )
//        val outerData = PieData(outerDataSet)
        val innerData = PieData(innerDataSet)

        // Set data for the outer and inner pie charts
//        pieChartBG.data = outerData
//        pieChartBG.invalidate()

        // Add inner pie chart to the center
        val holeRadius = 96.5f // 85f --> 100f
        val transparentCircleRadius = 90f
        pieChartBG.holeRadius = holeRadius
//        pieChartBG.transparentCircleRadius = transparentCircleRadius
//        pieChartBG.setExtraOffsets(
//            holeRadius + transparentCircleRadius,
//            holeRadius + transparentCircleRadius,
//            holeRadius + transparentCircleRadius,
//            holeRadius + transparentCircleRadius
//        )
//        pieChartBG.setDrawRoundedSlices(true)
//        pieChartBG.renderer = RoundedSlicesPieChartRenderer(pieChartBG, pieChartBG.animator, pieChartBG.viewPortHandler);
        pieChartBG.data = innerData
        pieChartBG.invalidate()
//        pieChartBG.notifyDataSetChanged()
    }

    private fun setupPieChartBG2() {
        pieChartBG2.setUsePercentValues(true)
        pieChartBG2.description.isEnabled = false
        pieChartBG2.isDrawHoleEnabled = true
        pieChartBG2.setHoleColor(Color.TRANSPARENT)
        pieChartBG2.setTransparentCircleColor(Color.BLUE)
        pieChartBG2.setTransparentCircleAlpha(110)
        pieChartBG2.holeRadius = 25f
        pieChartBG2.transparentCircleRadius = 30f
        pieChartBG2.setDrawCenterText(true)
        pieChartBG2.legend.isEnabled = false
        pieChartBG2.animateX(1000)
//        pieChartBG2.rotationAngle = 0f
        pieChartBG2.isRotationEnabled = false
    }

    private fun setDataBG2() {
//        val outerEntries = mutableListOf<PieEntry>()
        val innerEntries = mutableListOf<PieEntry>()

        // Add your data for the outer pie chart
//        outerEntries.add(PieEntry(30f, "Label 1"))
//        outerEntries.add(PieEntry(40f, "Label 2"))
//        outerEntries.add(PieEntry(25f, "Label 3"))

        // Add your data for the inner pie chart
        innerEntries.add(PieEntry(100f, ""))
//        innerEntries.add(PieEntry(40f, ""))
//        innerEntries.add(PieEntry(15f, "Inner Label 3"))

//        val outerDataSet = PieDataSet(outerEntries, "Outer Pie")
//        outerDataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()

        val innerDataSet = PieDataSet(innerEntries, "Inner Pie")
        innerDataSet.valueTextSize = 0.0f

//        innerDataSet.colors = ColorTemplate.PASTEL_COLORS.toList()
        innerDataSet.colors = listOf(
            getColor(R.color.gray),
            Color.TRANSPARENT
        )

//        val outerData = PieData(outerDataSet)
        val innerData = PieData(innerDataSet)

        // Set data for the outer and inner pie charts
//        pieChartBG2.data = outerData
//        pieChartBG2.invalidate()

        // Add inner pie chart to the center
        val holeRadius = 95f // 80f --> 95f
        val transparentCircleRadius = 50f
        pieChartBG2.holeRadius = holeRadius
//        pieChartBG2.transparentCircleRadius = transparentCircleRadius
//        pieChartBG2.setExtraOffsets(
//            holeRadius + transparentCircleRadius,
//            holeRadius + transparentCircleRadius,
//            holeRadius + transparentCircleRadius,
//            holeRadius + transparentCircleRadius
//        )
//        pieChartBG2.renderer = RoundedSlicesPieChartRenderer(pieChartBG2, pieChartBG2.animator, pieChartBG2.viewPortHandler);

        pieChartBG2.data = innerData
        pieChartBG2.invalidate()
//        pieChartBG2.notifyDataSetChanged()
    }


    class RoundedSlicesPieChartRenderer(
        chart: PieChart,
        animator: ChartAnimator?,
        viewPortHandler: ViewPortHandler?
    ) :
        PieChartRenderer(chart, animator, viewPortHandler) {
        init {
            chart.setDrawRoundedSlices(true)
        }

        override fun drawDataSet(c: Canvas, dataSet: IPieDataSet) {
            var angle = 0f
            val rotationAngle = mChart.rotationAngle
            val phaseX = mAnimator.phaseX
            val phaseY = mAnimator.phaseY
            val circleBox = mChart.circleBox
            val entryCount = dataSet.entryCount
            val drawAngles = mChart.drawAngles
            val center = mChart.centerCircleBox
            val radius = mChart.radius
            val drawInnerArc = mChart.isDrawHoleEnabled && !mChart.isDrawSlicesUnderHoleEnabled
            val userInnerRadius = if (drawInnerArc) radius * (mChart.holeRadius / 100f) else 0f
            val roundedRadius = (radius - radius * mChart.holeRadius / 100f) / 2f
            val roundedCircleBox = RectF()
            var visibleAngleCount = 0
            for (j in 0 until entryCount) {
                // draw only if the value is greater than zero
                if (Math.abs(dataSet.getEntryForIndex(j).y) > Utils.FLOAT_EPSILON) {
                    visibleAngleCount++
                }
            }
            val sliceSpace = if (visibleAngleCount <= 1) 0f else getSliceSpace(dataSet)
            val pathBuffer = Path()
            val mInnerRectBuffer = RectF()
            for (j in 0 until entryCount) {
                val sliceAngle = drawAngles[j]
                var innerRadius = userInnerRadius
                val e: Entry = dataSet.getEntryForIndex(j)

                // draw only if the value is greater than zero
                if (!(Math.abs(e.y) > Utils.FLOAT_EPSILON)) {
                    angle += sliceAngle * phaseX
                    continue
                }

                // Don't draw if it's highlighted, unless the chart uses rounded slices
                if (mChart.needsHighlight(j) && !drawInnerArc) {
                    angle += sliceAngle * phaseX
                    continue
                }
                val accountForSliceSpacing = sliceSpace > 0f && sliceAngle <= 180f
                mRenderPaint.color = dataSet.getColor(j)
                val sliceSpaceAngleOuter =
                    if (visibleAngleCount == 1) 0f else sliceSpace / (Utils.FDEG2RAD * radius)
                val startAngleOuter = rotationAngle + (angle + sliceSpaceAngleOuter / 2f) * phaseY
                var sweepAngleOuter = (sliceAngle - sliceSpaceAngleOuter) * phaseY
                if (sweepAngleOuter < 0f) {
                    sweepAngleOuter = 0f
                }
                pathBuffer.reset()
                val arcStartPointX =
                    center.x + radius * Math.cos((startAngleOuter * Utils.FDEG2RAD).toDouble())
                        .toFloat()
                val arcStartPointY =
                    center.y + radius * Math.sin((startAngleOuter * Utils.FDEG2RAD).toDouble())
                        .toFloat()
                if (sweepAngleOuter >= 360f && sweepAngleOuter % 360f <= Utils.FLOAT_EPSILON) {
                    // Android is doing "mod 360"
                    pathBuffer.addCircle(center.x, center.y, radius, Path.Direction.CW)
                } else {
                    if (drawInnerArc) {
                        val x =
                            center.x + (radius - roundedRadius) * Math.cos((startAngleOuter * Utils.FDEG2RAD).toDouble())
                                .toFloat()
                        val y =
                            center.y + (radius - roundedRadius) * Math.sin((startAngleOuter * Utils.FDEG2RAD).toDouble())
                                .toFloat()
                        roundedCircleBox[x - roundedRadius, y - roundedRadius, x + roundedRadius] =
                            y + roundedRadius
                        pathBuffer.arcTo(roundedCircleBox, startAngleOuter - 180, 180f)
                    }
                    pathBuffer.arcTo(
                        circleBox,
                        startAngleOuter,
                        sweepAngleOuter
                    )
                }

                // API < 21 does not receive floats in addArc, but a RectF
                mInnerRectBuffer[center.x - innerRadius, center.y - innerRadius, center.x + innerRadius] =
                    center.y + innerRadius
                if (drawInnerArc && (innerRadius > 0f || accountForSliceSpacing)) {
                    if (accountForSliceSpacing) {
                        var minSpacedRadius = calculateMinimumRadiusForSpacedSlice(
                            center, radius,
                            sliceAngle * phaseY,
                            arcStartPointX, arcStartPointY,
                            startAngleOuter,
                            sweepAngleOuter
                        )
                        if (minSpacedRadius < 0f) minSpacedRadius = -minSpacedRadius
                        innerRadius = Math.max(innerRadius, minSpacedRadius)
                    }
                    val sliceSpaceAngleInner =
                        if (visibleAngleCount == 1 || innerRadius == 0f) 0f else sliceSpace / (Utils.FDEG2RAD * innerRadius)
                    val startAngleInner =
                        rotationAngle + (angle + sliceSpaceAngleInner / 2f) * phaseY
                    var sweepAngleInner = (sliceAngle - sliceSpaceAngleInner) * phaseY
                    if (sweepAngleInner < 0f) {
                        sweepAngleInner = 0f
                    }
                    val endAngleInner = startAngleInner + sweepAngleInner
                    if (sweepAngleOuter >= 360f && sweepAngleOuter % 360f <= Utils.FLOAT_EPSILON) {
                        // Android is doing "mod 360"
                        pathBuffer.addCircle(center.x, center.y, innerRadius, Path.Direction.CCW)
                    } else {
                        val x =
                            center.x + (radius - roundedRadius) * Math.cos((endAngleInner * Utils.FDEG2RAD).toDouble())
                                .toFloat()
                        val y =
                            center.y + (radius - roundedRadius) * Math.sin((endAngleInner * Utils.FDEG2RAD).toDouble())
                                .toFloat()
                        roundedCircleBox[x - roundedRadius, y - roundedRadius, x + roundedRadius] =
                            y + roundedRadius
                        pathBuffer.arcTo(roundedCircleBox, endAngleInner, 180f)
                        pathBuffer.arcTo(mInnerRectBuffer, endAngleInner, -sweepAngleInner)
                    }
                } else {
                    if (sweepAngleOuter % 360f > Utils.FLOAT_EPSILON) {
                        if (accountForSliceSpacing) {
                            val angleMiddle = startAngleOuter + sweepAngleOuter / 2f
                            val sliceSpaceOffset = calculateMinimumRadiusForSpacedSlice(
                                center,
                                radius,
                                sliceAngle * phaseY,
                                arcStartPointX,
                                arcStartPointY,
                                startAngleOuter,
                                sweepAngleOuter
                            )
                            val arcEndPointX = center.x +
                                    sliceSpaceOffset * Math.cos((angleMiddle * Utils.FDEG2RAD).toDouble())
                                .toFloat()
                            val arcEndPointY = center.y +
                                    sliceSpaceOffset * Math.sin((angleMiddle * Utils.FDEG2RAD).toDouble())
                                .toFloat()
                            pathBuffer.lineTo(
                                arcEndPointX,
                                arcEndPointY
                            )
                        } else {
                            pathBuffer.lineTo(
                                center.x,
                                center.y
                            )
                        }
                    }
                }
                pathBuffer.close()
                mBitmapCanvas.drawPath(pathBuffer, mRenderPaint)
                angle += sliceAngle * phaseX
            }
            MPPointF.recycleInstance(center)
        }
    }

    private fun lineChart() {

//        val barData = LineData(barDataSet)
        val barData = LineData(
            listOf(
                heartRateDataSet1(),
                heartRateDataSet2(),
                heartRateDataSet3(),
                heartRateDataSet4()
            )
        )
        //        barChart.setFitBars(true)
        //        barChart.isAutoScaleMinMaxEnabled = true
        lineChart.isKeepPositionOnRotation = true
        lineChart.data = barData
        lineChart.xAxis.isEnabled = true
        lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        //        lineChart.axisLeft.isEnabled = false
        //remove axis lines
        lineChart.axisRight.isEnabled = false
        lineChart.xAxis.setDrawAxisLine(false)
        lineChart.axisLeft.setDrawAxisLine(false)
        lineChart.axisRight.setDrawAxisLine(false)
        //remove grid lines
        with(lineChart) {
            xAxis.valueFormatter =
                CustomXAxisValueFormatter(arrayOf(" ", "1PM", "2PM", "3PM", "4PM"))
            axisLeft.valueFormatter =
                CustomYAxisValueFormatter(arrayOf(" ", "Deep", "REM", "Core", "Awake"))

            xAxis.setDrawGridLines(false)
            axisRight.setDrawGridLines(false)
            //h lines
            axisLeft.setDrawGridLines(false)
            legend.isEnabled = false
            xAxis.axisMaximum = 5f
            xAxis.axisMinimum = 1f
            axisLeft.axisMaximum = 5f
            axisLeft.axisMinimum = 1f
            description.isEnabled = false

            setBackgroundColor(resources.getColor(android.R.color.transparent))
            // Set transparent background
            // Set background with round border
//            setBackgroundResource(R.drawable.rounded_chart_background);  // Create a rounded background drawable and set it
            // Set border
            //            setDrawBorders(true);
            setBorderColor(Color.CYAN)  // Replace Color.BLACK with your desired border color
            setBorderWidth(1f)
        }
        legend(lineChart.legend)
    }

    private fun heartRateDataSet1(): LineDataSet {
        val list: ArrayList<Entry> = ArrayList()
        list.add(Entry(2f, 2f))
        list.add(Entry(3f, 2f))
        /*
        list.add(Entry(90f, 81f))
        list.add(Entry(93f, 78f))
        list.add(Entry(99f, 99f))
        list.add(Entry(105f, 83f))
        list.add(Entry(107f, 78f))
        list.add(Entry(120f, 105f))
        */
        val barDataSet = LineDataSet(list, "List")

        barDataSet.setColors(
            intArrayOf(
                ColorTemplate.rgb("#FF039BE5"),   /* rgb("#FDC80C"), rgb("#e74c3c"), rgb("#3498db")*/
            ), 255
        )
        barDataSet.valueTextSize = 0f
        //        barDataSet.valueTextColor = Color.BLACK
        barDataSet.highlightLineWidth = 1f
        barDataSet.lineWidth = 4f
        barDataSet.setDrawCircles(true)
        barDataSet.circleRadius = 2f
        barDataSet.circleHoleColor = ColorTemplate.rgb("#FF039BE5")
        barDataSet.setCircleColor(ColorTemplate.rgb("#FF039BE5"))

        return barDataSet
    }

    private fun heartRateDataSet2(): LineDataSet {
        val list: ArrayList<Entry> = ArrayList()
        list.add(Entry(3f, 3f))
        list.add(Entry(4f, 3f))

        val barDataSet = LineDataSet(list, "List")

        barDataSet.setColors(
            intArrayOf(
                ColorTemplate.rgb("#FDC80C"),   /* rgb("#FDC80C"), rgb("#e74c3c"), rgb("#3498db")*/
            ), 255
        )
        barDataSet.valueTextSize = 0f
        //        barDataSet.valueTextColor = Color.BLACK
        barDataSet.highlightLineWidth = 1f
        barDataSet.lineWidth = 4f
        barDataSet.setDrawCircles(true)
        barDataSet.circleRadius = 2f
        barDataSet.circleHoleColor = ColorTemplate.rgb("#FDC80C")
        barDataSet.setCircleColor(ColorTemplate.rgb("#FDC80C"))
        return barDataSet
    }

    private fun heartRateDataSet3(): LineDataSet {
        val list: ArrayList<Entry> = ArrayList()
        list.add(Entry(2f, 4f))
        list.add(Entry(4f, 4f))

        val barDataSet = LineDataSet(list, "List")

        barDataSet.setColors(
            intArrayOf(
                ColorTemplate.rgb("#90D0D4"),   /* rgb("#FDC80C"), rgb("#e74c3c"), rgb("#3498db")*/
            ), 255
        )
        barDataSet.valueTextSize = 0f
        //        barDataSet.valueTextColor = Color.BLACK
        barDataSet.highlightLineWidth = 1f
        barDataSet.lineWidth = 4f
        barDataSet.setDrawCircles(true)
        barDataSet.circleRadius = 2f
        barDataSet.circleHoleColor = ColorTemplate.rgb("#90D0D4")
        barDataSet.setCircleColor(ColorTemplate.rgb("#90D0D4"))
        return barDataSet
    }

    private fun heartRateDataSet4(): LineDataSet {
        val list: ArrayList<Entry> = ArrayList()
        list.add(Entry(3f, 3.04f))
        list.add(Entry(4f, 3.04f))

        val barDataSet = LineDataSet(list, "List")

        barDataSet.setColors(
            intArrayOf(
                ColorTemplate.rgb("#90D0D4"),   /* rgb("#FDC80C"), rgb("#e74c3c"), rgb("#3498db")*/
            ), 255
        )
        barDataSet.valueTextSize = 0f
        //        barDataSet.valueTextColor = Color.BLACK
        barDataSet.highlightLineWidth = 1f
        barDataSet.lineWidth = 4f
        barDataSet.setDrawCircles(true)
        barDataSet.circleRadius = 2f
        barDataSet.circleHoleColor = ColorTemplate.rgb("#90D0D4")
        barDataSet.setCircleColor(ColorTemplate.rgb("#90D0D4"))

        return barDataSet
    }

    private fun legend(legend: Legend) {
        // Set the position of the legend to the top-right corner
        legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.orientation = Legend.LegendOrientation.HORIZONTAL
        legend.setDrawInside(false) // Ensure it doesn't overlap with the chart
        // Customize other legend attributes as needed
        legend.form = Legend.LegendForm.CIRCLE // Set the form of the legend (CIRCLE, SQUARE, etc.)
        legend.formSize = 8f // Set the size of the legend form
        legend.textSize = 12f // Set the size of the legend text
        val legendEntries: ArrayList<LegendEntry> = ArrayList()
        val entry1 = LegendEntry()
        entry1.label = "Auto"
        entry1.formColor = ColorTemplate.rgb("#FDC80C")
        legendEntries.add(entry1)
        val entry2 = LegendEntry()
        entry2.label = "Manual"
        entry2.formColor = ColorTemplate.rgb("#FF039BE5")
        legendEntries.add(entry2)
        legend.setCustom(legendEntries)
    }

    /*

        @RequiresApi(Build.VERSION_CODES.O)
        fun encrypt(data: String, keyString: String): String {
            val key = SecretKeySpec(keyString.toByteArray(), "AES")
            val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, key)

            val encryptedBytes = cipher.doFinal(data.toByteArray())
            return Base64.getEncoder().encodeToString(encryptedBytes)
        }


        @RequiresApi(Build.VERSION_CODES.O)
        fun decrypt(encryptedData: String, keyString: String): String {
            val encryptedBytes = Base64.getDecoder().decode(encryptedData)
            val key = SecretKeySpec(keyString.toByteArray(), "AES")
            val cipher = Cipher.getInstance("AES")
            cipher.init(Cipher.DECRYPT_MODE, key)

            val decryptedBytes = cipher.doFinal(encryptedBytes)
            return String(decryptedBytes)
        }
    */


    fun decryptWithAES(key: String, strToDecrypt: String?): String? {
        Security.addProvider(BouncyCastleProvider())
        var keyBytes: ByteArray

        try {
            keyBytes = key.toByteArray(charset("UTF8"))
            val skey = SecretKeySpec(keyBytes, "AES")
            val input = org.bouncycastle.util.encoders.Base64
                .decode(strToDecrypt?.trim { it <= ' ' }?.toByteArray(charset("UTF8")))

            synchronized(Cipher::class.java) {
                val cipher = Cipher.getInstance("AES")
                cipher.init(Cipher.DECRYPT_MODE, skey)

                val plainText = ByteArray(cipher.getOutputSize(input.size))
                var ptLength = cipher.update(input, 0, input.size, plainText, 0)
                ptLength += cipher.doFinal(plainText, ptLength)
                val decryptedString = String(plainText)
                return decryptedString.trim { it <= ' ' }
            }
        } catch (uee: UnsupportedEncodingException) {
            uee.printStackTrace()
        } catch (ibse: IllegalBlockSizeException) {
            ibse.printStackTrace()
        } catch (bpe: BadPaddingException) {
            bpe.printStackTrace()
        } catch (ike: InvalidKeyException) {
            ike.printStackTrace()
        } catch (nspe: NoSuchPaddingException) {
            nspe.printStackTrace()
        } catch (nsae: NoSuchAlgorithmException) {
            nsae.printStackTrace()
        } catch (e: ShortBufferException) {
            e.printStackTrace()
        }

        return null
    }
}


class CustomXAxisValueFormatter(private val xValues: Array<String>) : ValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        val index = value.toInt()
        return if (index >= 0 && index < xValues.size) {
            xValues[index]
        } else ""
    }
}


class CustomYAxisValueFormatter(private val yValues: Array<String>) : ValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        val index = value.toInt()
        return if (index >= 0 && index < yValues.size) {
            yValues[index]
        } else ""
    }
}