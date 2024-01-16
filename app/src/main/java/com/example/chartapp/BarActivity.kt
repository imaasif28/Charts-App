package com.example.chartapp

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.appcompat.app.AppCompatActivity
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.SingleValueDataSet
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.enums.HoverMode
import com.anychart.enums.MarkerType
import com.anychart.enums.TooltipDisplayMode
import com.anychart.graphics.vector.Fill
import com.anychart.graphics.vector.SolidFill
import com.anychart.graphics.vector.text.HAlign
import com.ekn.gruzer.gaugelibrary.HalfGauge
import com.ekn.gruzer.gaugelibrary.Range
import com.example.chartapp.databinding.ActivityBarBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.BubbleChart
import com.github.mikephil.charting.charts.CandleStickChart
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.charts.ScatterChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.BubbleData
import com.github.mikephil.charting.data.BubbleDataSet
import com.github.mikephil.charting.data.BubbleEntry
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.data.ScatterData
import com.github.mikephil.charting.data.ScatterDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.ColorTemplate.rgb

class BarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBarBinding
    private lateinit var scatterChart: ScatterChart
    private lateinit var barChartVertical: BarChart
    private lateinit var horizontalBarChart: HorizontalBarChart
    private lateinit var lineChart: LineChart
    private lateinit var halfGauge: HalfGauge
    private lateinit var radarChart: RadarChart
    private lateinit var pieChart: PieChart
    private lateinit var bubbleChart: BubbleChart
    private lateinit var candleStickChart: LineChart
    private lateinit var cholestrolChart: LineChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        scatterChart = binding.barChartView
        barChartVertical = binding.barChart
        horizontalBarChart = binding.barChartHorizontal
        lineChart = binding.lineChartView
        bubbleChart = binding.bubbleChartView
        halfGauge = binding.halfGauge
        candleStickChart = binding.candleStickChartView
        cholestrolChart = binding.lineChartViewCholestrol

        scatterChart()
//        anyScatterChart()
        bubbleChart()
//        candleStick()
        bpChart(candleStickChart)
        lineChart()
        barChart()
        horizontalBarChart()
        halfGauge()
        radarChart()
        pieChart()
        cholestrolChart()
        circularGauge()
        bmiCalculator()
    }

    private fun lineChart() {
        val list: ArrayList<Entry> = ArrayList()
        list.add(
            Entry(
                70f,
                50f, /*AppCompatResources.getDrawable(this, R.drawable.baseline_whatshot_24)*/
            )
        )
        list.add(Entry(90f, 81f))
        list.add(Entry(93f, 78f))
        list.add(Entry(99f, 99f))
        list.add(Entry(105f, 83f))
        list.add(Entry(107f, 78f))
        list.add(Entry(120f, 105f))
        val barDataSet = LineDataSet(list, "List")

        barDataSet.setColors(
            intArrayOf(
                rgb("#FF039BE5"),   /* rgb("#FDC80C"), rgb("#e74c3c"), rgb("#3498db")*/
            ), 255
        )
//            barDataSet.valueTextSize = 0f
        //        barDataSet.valueTextColor = Color.BLACK
        barDataSet.highlightLineWidth = 1f
        barDataSet.lineWidth = 2f
        val barData = LineData(barDataSet)
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
            xAxis.setDrawGridLines(false)
            axisRight.setDrawGridLines(false)
            //h lines
            axisLeft.setDrawGridLines(true)
            legend.isEnabled = false
            xAxis.axisMaximum = 120f
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

    private fun bpChart(lineChart: LineChart) {

        val barData = LineData(BPDataSet1(), BPDataSet2(), BPDataSet3())
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
            xAxis.setDrawGridLines(false)
            axisRight.setDrawGridLines(false)
            xAxis.textColor = Color.GRAY
            axisLeft.textColor = Color.GRAY
            //h lines
            axisLeft.setDrawGridLines(true)
            legend.isEnabled = false
            xAxis.axisMaximum = 120f
            description.isEnabled = false

            xAxis.axisMaximum = 100f
            xAxis.axisMinimum = 0f
            axisLeft.axisMaximum = 100f
            axisLeft.axisMinimum = 0f

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

    private fun BPDataSet1(): LineDataSet {
        val list: ArrayList<Entry> = ArrayList()
        list.add(Entry(80f, 30f))
        list.add(Entry(80f, 70f))
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
                rgb("#FF039BE5"),   /* rgb("#FDC80C"), rgb("#e74c3c"), rgb("#3498db")*/
            ), 255
        )
        barDataSet.valueTextSize = 0f
        //        barDataSet.valueTextColor = Color.BLACK
        barDataSet.highlightLineWidth = 1f
        barDataSet.lineWidth = 4f
        barDataSet.setDrawCircles(true)
        barDataSet.circleRadius = 2f
        barDataSet.circleHoleColor = rgb("#FF039BE5")
        barDataSet.setCircleColor(rgb("#FF039BE5"))

        return barDataSet
    }

    private fun BPDataSet2(): LineDataSet {
        val list: ArrayList<Entry> = ArrayList()
        list.add(Entry(40f, 20f))
        list.add(Entry(40f, 61f))
        val barDataSet = LineDataSet(list, "List")

        barDataSet.setColors(
            intArrayOf(
                rgb("#F05123"),
            ), 255
        )
        barDataSet.valueTextSize = 0f
        //        barDataSet.valueTextColor = Color.BLACK
        barDataSet.lineWidth = 1.5f
        barDataSet.highlightLineWidth = 1f

        barDataSet.setDrawCircles(true)
        barDataSet.circleRadius = 2f
        barDataSet.circleHoleColor = rgb("#F05123")
        barDataSet.setCircleColor(rgb("#F05123"))

        return barDataSet
    }
    private fun BPDataSet3(): LineDataSet {
        val list: ArrayList<Entry> = ArrayList()
        list.add(Entry(40f, 20f))
//        list.add(Entry(40f, 61f))
        val barDataSet = LineDataSet(list, "List")

        barDataSet.setColors(
            intArrayOf(
                rgb("#FFD9BD"),
            ), 255
        )
        barDataSet.valueTextSize = 0f
        //        barDataSet.valueTextColor = Color.BLACK
        barDataSet.lineWidth = 1.5f
        barDataSet.highlightLineWidth = 1f

        barDataSet.setDrawCircles(true)
        barDataSet.circleRadius = 2f
        barDataSet.circleHoleColor = rgb("#FFD9BD")
        barDataSet.setCircleColor(rgb("#FFD9BD"))

        return barDataSet
    }


    private fun cholestrolChart() {
        val list: ArrayList<Entry> = ArrayList()

        list.add(Entry(50f, 40f))
        list.add(Entry(93f, 78f))
        list.add(Entry(99f, 99f))
        list.add(Entry(105f, 83f))
        list.add(Entry(107f, 78f))
        val barDataSet = LineDataSet(list, "List")

        barDataSet.setColors(
            intArrayOf(
                rgb("#FF039BE5"),   /* rgb("#FDC80C"), rgb("#e74c3c"), rgb("#3498db")*/
            ), 255
        )
//            barDataSet.valueTextSize = 0f
        //        barDataSet.valueTextColor = Color.BLACK
        barDataSet.highlightLineWidth = 1f
        barDataSet.lineWidth = 2f
        val barData = LineData(barDataSet)
        //        barChart.setFitBars(true)
        //        barChart.isAutoScaleMinMaxEnabled = true
        cholestrolChart.isKeepPositionOnRotation = true
        cholestrolChart.data = barData
        cholestrolChart.xAxis.isEnabled = true
        cholestrolChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        //        cholestrolChart.axisLeft.isEnabled = false
        //remove axis lines
        cholestrolChart.axisRight.isEnabled = false
        cholestrolChart.xAxis.setDrawAxisLine(false)
        cholestrolChart.axisLeft.setDrawAxisLine(false)
        cholestrolChart.axisRight.setDrawAxisLine(false)
        //remove grid lines
        with(cholestrolChart) {
            xAxis.setDrawGridLines(false)
            axisRight.setDrawGridLines(false)
            //h lines
            axisLeft.setDrawGridLines(true)
            legend.isEnabled = false
            xAxis.axisMaximum = 120f
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
        legend(cholestrolChart.legend)
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
        entry1.formColor = rgb("#FDC80C")
        legendEntries.add(entry1)
        val entry2 = LegendEntry()
        entry2.label = "Manual"
        entry2.formColor = rgb("#FF039BE5")
        legendEntries.add(entry2)
        legend.setCustom(legendEntries)
    }

    private fun horizontalBarChart() {
        val list: ArrayList<BarEntry> = ArrayList()
        list.add(
            BarEntry(
                70f,
                50f, /*AppCompatResources.getDrawable(this, R.drawable.baseline_whatshot_24)*/
            )
        )
        list.add(BarEntry(90f, 81f))
        list.add(BarEntry(93f, 78f))
        list.add(BarEntry(105f, 83f))
        list.add(BarEntry(107f, 78f))
        val barDataSet = BarDataSet(list, "List")

        barDataSet.setColors(
            intArrayOf(
                rgb("#FF039BE5"),/* rgb("#FDC80C"), rgb("#e74c3c"), rgb("#3498db")*/
            ), 255
        )
        barDataSet.valueTextSize = 0f
        barDataSet.valueTextColor = Color.BLACK
        //        barDataSet.highlightLineWidth = 2f
        val barData = BarData(barDataSet)
        //        barChart.setFitBars(true)
        //        barChart.isAutoScaleMinMaxEnabled = true
        horizontalBarChart.isKeepPositionOnRotation = true
        horizontalBarChart.data = barData
        horizontalBarChart.xAxis.isEnabled = true
        horizontalBarChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        //        horizontalBarChart.axisLeft.isEnabled = false
        //remove axis lines
        horizontalBarChart.axisRight.isEnabled = false
        horizontalBarChart.xAxis.setDrawAxisLine(false)
        horizontalBarChart.axisLeft.setDrawAxisLine(false)
        horizontalBarChart.axisRight.setDrawAxisLine(false)
        //remove grid lines
        with(horizontalBarChart) {
            xAxis.setDrawGridLines(false)
            axisRight.setDrawGridLines(false)
            axisLeft.setDrawGridLines(false)
            description.isEnabled = false

            xAxis.axisMaximum = 120f

            setBackgroundColor(resources.getColor(android.R.color.transparent))
            // Set transparent background
            // Set background with round border
//            setBackgroundResource(R.drawable.rounded_chart_background);  // Create a rounded background drawable and set it
            // Set border
            //            setDrawBorders(true);
            setBorderColor(Color.CYAN)  // Replace Color.BLACK with your desired border color
            setBorderWidth(1f)
        }
//        legend(horizontalBarChart.legend)
        // Update the chart
        horizontalBarChart.invalidate()
        horizontalBarChart.animateY(1000)
    }

    private fun barChart() {
        val list: ArrayList<BarEntry> = ArrayList()
        list.add(
            BarEntry(
                70f,
                50f, /*AppCompatResources.getDrawable(this, R.drawable.baseline_whatshot_24)*/
            )
        )
        list.add(BarEntry(90f, 81f))
        list.add(BarEntry(93f, 78f))
        list.add(BarEntry(105f, 83f))
        list.add(BarEntry(107f, 78f))
        val barDataSet = BarDataSet(list, "List")

        barDataSet.setColors(
            intArrayOf(
                rgb("#FF039BE5"),/* rgb("#FDC80C"), rgb("#e74c3c"), rgb("#3498db")*/
            ), 255
        )
        barDataSet.valueTextSize = 0f
        barDataSet.valueTextColor = Color.BLACK
        //        barDataSet.highlightLineWidth = 2f
        val barData = BarData(barDataSet)
        //        barChart.setFitBars(true)
        //        barChart.isAutoScaleMinMaxEnabled = true
        barChartVertical.isKeepPositionOnRotation = true
        barChartVertical.data = barData
        barChartVertical.xAxis.isEnabled = true
        barChartVertical.xAxis.position = XAxis.XAxisPosition.BOTTOM
        //        barChartVertical.axisLeft.isEnabled = false
        //remove axis lines
        barChartVertical.axisRight.isEnabled = false
        barChartVertical.xAxis.setDrawAxisLine(false)
        barChartVertical.axisLeft.setDrawAxisLine(false)
        barChartVertical.axisRight.setDrawAxisLine(false)
        //remove grid lines
        with(barChartVertical) {
            xAxis.setDrawGridLines(false)
            axisRight.setDrawGridLines(false)
            axisLeft.setDrawGridLines(false)

            xAxis.axisMaximum = 120f
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
//        legend(barChartVertical.legend)
        // Update the chart
        barChartVertical.invalidate()
        barChartVertical.animateY(1000)
    }

    private fun scatterChart() {
        val list: ArrayList<Entry> = ArrayList()
        list.add(
            Entry(
                70f,
                50f, /*AppCompatResources.getDrawable(this, R.drawable.baseline_whatshot_24)*/
            )
        )
        list.add(Entry(90f, 81f))
        list.add(Entry(93f, 78f))
        list.add(Entry(105f, 83f))
        list.add(Entry(107f, 78f))
        list.add(Entry(120f, 105f))
        val barDataSet = ScatterDataSet(list, "List")

        barDataSet.setColors(
            intArrayOf(
                rgb("#FDC80C"), rgb("#FF039BE5"), rgb("#e74c3c"), rgb("#3498db")
            ), 255
        )
        barDataSet.valueTextSize = 0f

        barDataSet.setScatterShape(ScatterChart.ScatterShape.CIRCLE)
        barDataSet.scatterShapeSize = 20f
        //        barDataSet.valueTextColor = Color.BLACK
        //        barDataSet.highlightLineWidth = 2f
        val barData = ScatterData(barDataSet)
        //        barChart.setFitBars(true)
        //        barChart.isAutoScaleMinMaxEnabled = true
        scatterChart.isKeepPositionOnRotation = true
        scatterChart.data = barData
        scatterChart.xAxis.isEnabled = true
        scatterChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        //        scatterChart.axisLeft.isEnabled = false
        //remove axis lines
        scatterChart.axisRight.isEnabled = false
        scatterChart.xAxis.setDrawAxisLine(false)
        scatterChart.axisLeft.setDrawAxisLine(false)
        scatterChart.axisRight.setDrawAxisLine(false)
        //remove grid lines
        with(scatterChart) {
            xAxis.setDrawGridLines(false)
            axisRight.setDrawGridLines(false)
            //horizontal lines
            axisLeft.setDrawGridLines(true)
            legend.isEnabled = false
            xAxis.axisMaximum = 120f
            setBackgroundColor(resources.getColor(android.R.color.transparent))
            // Set transparent background
            // Set background with round border
//            setBackgroundResource(R.drawable.rounded_chart_background);  // Create a rounded background drawable and set it
            // Set border
            //            setDrawBorders(true);
            description.isEnabled = false
            setBorderColor(Color.CYAN)  // Replace Color.BLACK with your desired border color
            setBorderWidth(1f)
        }
        legend(scatterChart.legend)
        // Update the chart
        scatterChart.invalidate()
        scatterChart.animateY(1000)
    }

    private fun anyScatterChart() {
        val anyChartView = binding.anyChartScatter
        anyChartView.setProgressBar(binding.progressBar)
        val scatter = AnyChart.scatter()
        scatter.animation(true)

        scatter.title("Body Weight")
        scatter.xScale()
            .minimum(1.5)
            .maximum(10.5)
        //        scatter.xScale().tick
        scatter.yScale()
            .minimum(40.0)
            .maximum(100.0)
//           Axis Label
//            scatter.yAxis(0).title("Waiting time between interruptions (Min)")
//            scatter.xAxis(0)
//                .title("Interruption duration (Min)")
//                .drawFirstLabel(false)
//                .drawLastLabel(false)
        scatter.interactivity()
            .hoverMode(HoverMode.BY_SPOT)
            .spotRadius(30.0)
        scatter.tooltip().displayMode(TooltipDisplayMode.UNION)
        val marker = scatter.marker(markerData)
        marker.type(MarkerType.CIRCLE)
            .size(3.0)
        marker.hovered()
            .size(7.0)
            .fill(SolidFill("gold", 1.0))
            .stroke("anychart.color.darken(gold)")
        marker.tooltip()
            .hAlign(HAlign.START)
            .format("Body Weight: \${%Value} Kg.\\nDuration: \${%X} min.")
//            val scatterSeriesLine = scatter.line(lineData)
//        val gradientKey = arrayOf(
//            GradientKey("#abcabc", 0.0, 1.0),
//            GradientKey("#cbacba", 40.0, 1.0)
//        )
//        val linearGradientStroke =
//            LinearGradientStroke(0.0, null, gradientKey, null, null, true, 1.0, 2.0)
//            scatterSeriesLine.stroke(linearGradientStroke, 3.0, null, null as String?, null as String?)
        anyChartView.setChart(scatter)
    }

    private val markerData: List<DataEntry>
        get() {
            val data: MutableList<DataEntry> = java.util.ArrayList()
            data.add(ValueDataEntry(8, 81))
            data.add(ValueDataEntry(3, 78))
            data.add(ValueDataEntry(5, 83))
            data.add(ValueDataEntry(7, 78))
            return data
        }


    private fun radarChart() {
        radarChart = binding.radarChartView
        val list: ArrayList<RadarEntry> = ArrayList()
        list.add(RadarEntry(100f, 100f))
        list.add(RadarEntry(101f, 101f))
        list.add(RadarEntry(102f, 102f))
        list.add(RadarEntry(103f, 103f))
        list.add(RadarEntry(104f, 104f))
        val barDataSet = RadarDataSet(list, "List")
//        val entries = mutableListOf<RadarEntry>()
//        entries.add(RadarEntry(4f))  // Value for the gauge
//        val dataSet = RadarDataSet(entries, "Gauge")
//        dataSet.color = Color.BLUE
//        dataSet.setDrawFilled(true)
//        dataSet.fillColor = Color.BLUE
//        dataSet.fillAlpha = 150
//        val radarData = RadarData(dataSet)
//        radarChart.data = radarData
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS, 200)
        barDataSet.valueTextSize = 5f
        barDataSet.valueTextColor = Color.RED
        barDataSet.lineWidth = 2f
        val barData = RadarData(barDataSet)
        radarChart.data = barData
        radarChart.description.text = "Radar Chart"
//        val xAxis: XAxis = radarChart.xAxis
//        xAxis.isEnabled = false  // Hide the X-axis labels
//        val legend: Legend = radarChart.legend
//        legend.isEnabled = false  // Hide the legend
//        radarChart.description.isEnabled = false  // Hide the chart description
//        radarChart.invalidate()
        with(radarChart) {
            xAxis.setDrawGridLines(false)
            xAxis.axisMaximum = 120f
//            setBackgroundResource(R.drawable.rounded_chart_background);  // Create a rounded background drawable and set it
        }
        radarChart.animateY(2000)
    }

    private fun halfGauge() {
        //gauge
        val range = Range()
        range.color = Color.parseColor("#ce0000")
        range.from = 0.0
        range.to = 50.0
        val range2 = Range()
        range2.color = Color.parseColor("#E3E500")
        range2.from = 50.0
        range2.to = 100.0
        val range3 = Range()
        range3.color = Color.parseColor("#00b20b")
        range3.from = 100.0
        range3.to = 150.0
        //add color ranges to gauge
        halfGauge.addRange(range)
        halfGauge.addRange(range2)
        halfGauge.addRange(range3)
        //set min max and current value
        halfGauge.minValue = 0.0
        halfGauge.maxValue = 150.0
        halfGauge.value = 80.0
    }

    private fun pieChart() {
        pieChart = binding.pieChartView
        val list: ArrayList<PieEntry> = ArrayList()
        list.add(PieEntry(100f, "100"))
        list.add(PieEntry(101f, "101"))
        list.add(PieEntry(102f, "102"))
        list.add(PieEntry(103f, "103"))
        list.add(PieEntry(104f, "104"))
        val barDataSet = PieDataSet(list, "List")
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS, 255)
        barDataSet.valueTextSize = 0f
        val pieData = PieData(barDataSet)
        pieChart.data = pieData
        with(pieChart) {
//            setBackgroundResource(R.drawable.rounded_chart_background);  // Create a rounded background drawable and set it
        }
        pieChart.description.text = "Pie Chart"
        pieChart.animateY(2000)
    }

    private fun bubbleChart() {
        val list: ArrayList<BubbleEntry> = ArrayList()
        list.add(
            BubbleEntry(
                70f,
                50f, 50f /*AppCompatResources.getDrawable(this, R.drawable.baseline_whatshot_24)*/
            )
        )
        list.add(BubbleEntry(90f, 81f, 50f))
        list.add(BubbleEntry(93f, 78f, 50f))
        list.add(BubbleEntry(105f, 83f, 50f))
        list.add(BubbleEntry(107f, 78f, 50f))
        list.add(BubbleEntry(120f, 105f, 50f))
        val barDataSet = BubbleDataSet(list, "List")

        barDataSet.setColors(
            intArrayOf(
                rgb("#FDC80C"), rgb("#FF039BE5"), /*rgb("#e74c3c"), rgb("#3498db")*/
            ), 255
        )
        barDataSet.valueTextSize = 0f
        //        barDataSet.valueTextColor = Color.BLACK
//                    barDataSet.highlightLineWidth = 2f
        val barData = BubbleData(barDataSet)
        //        barChart.setFitBars(true)
        //        barChart.isAutoScaleMinMaxEnabled = true
        bubbleChart.isKeepPositionOnRotation = true
        bubbleChart.data = barData
        bubbleChart.xAxis.isEnabled = true
        bubbleChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        //        bubbleChart.axisLeft.isEnabled = false
        //remove axis lines
        bubbleChart.axisRight.isEnabled = false
        bubbleChart.xAxis.setDrawAxisLine(false)
        bubbleChart.axisLeft.setDrawAxisLine(false)
        bubbleChart.axisRight.setDrawAxisLine(false)
        //remove grid lines
        with(bubbleChart) {
            xAxis.setDrawGridLines(false)
            axisRight.setDrawGridLines(false)
            axisLeft.setDrawGridLines(true)
            xAxis.axisMaximum = 120f
            legend.isEnabled = false
            description.isEnabled = false
//            setBackgroundResource(R.drawable.rounded_chart_background);  // Create a rounded background drawable and set it
            // Set border
            //            setDrawBorders(true);
            setBorderColor(Color.CYAN)  // Replace Color.BLACK with your desired border color
            setBorderWidth(1f)
        }
        legend(bubbleChart.legend)
    }

//    private fun candleStick() {
//        val list: ArrayList<CandleEntry> = ArrayList()
//        list.add(
//            CandleEntry(10f, 50f, 40f, 0f, 50f)
//        )
//        list.add(CandleEntry(20f, 81f, 50f, 0f, 10f))
//        list.add(CandleEntry(30f, 98f, 50f, 10f, 20f))
//        list.add(CandleEntry(40f, 83f, 50f, 20f, 30f))
//        list.add(CandleEntry(50f, 78f, 50f, 30f, 40f))
//        list.add(CandleEntry(60f, 105f, 50f, 40f, 50f))
//        val barDataSet = CandleDataSet(list, "List")
//        barDataSet.showCandleBar = true
//        barDataSet.setColors(
//            intArrayOf(
//                rgb("#FDC80C"), rgb("#FF039BE5"), /*rgb("#e74c3c"), rgb("#3498db")*/
//            ), 255
//        )
////            barDataSet.valueTextSize = 0f
//        //        barDataSet.valueTextColor = Color.BLACK
////                    barDataSet.highlightLineWidth = 2f
//        val barData = CandleData(barDataSet)
//        //        barChart.isAutoScaleMinMaxEnabled = true
//        candleStickChart.isKeepPositionOnRotation = true
//        candleStickChart.data = barData
//        candleStickChart.xAxis.isEnabled = true
//        candleStickChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
//        //        candleStickChart.axisLeft.isEnabled = false
//        //remove axis lines
//        candleStickChart.axisRight.isEnabled = false
//        candleStickChart.xAxis.setDrawAxisLine(false)
//        candleStickChart.axisLeft.setDrawAxisLine(false)
//        candleStickChart.axisRight.setDrawAxisLine(false)
//        //remove grid lines
//        with(candleStickChart) {
//            xAxis.setDrawGridLines(false)
//            axisRight.setDrawGridLines(false)
//            xAxis.axisMaximum = 120f
//            legend.isEnabled = false
//            description.isEnabled = false
////            setBackgroundResource(R.drawable.rounded_chart_background);  // Create a rounded background drawable and set it
//            // Set border
//            //            setDrawBorders(true);
//            setBorderColor(Color.CYAN)  // Replace Color.BLACK with your desired border color
//            setBorderWidth(1f)
//        }
//    }

    private fun circularGauge() {
        val anyChartView = binding.anyChartCircle
        anyChartView.setProgressBar(binding.progressBar)
        val circularGauge = AnyChart.circular()
        circularGauge.data(SingleValueDataSet(arrayOf("32", "34", "67", "93", "56", "100")))
        circularGauge.fill("#fff")
            .stroke(null)
            .padding(0.0, 0.0, 0.0, 0.0)
            .margin(20.0, 20.0, 20.0, 20.0)
        circularGauge.startAngle(0.0)
        circularGauge.sweepAngle(360.0)
        val xAxis = circularGauge.axis(0)
            .radius(100.0)
            .width(1.0)
            .fill(null as Fill?)
        xAxis.scale()
            .minimum(0.0)
            .maximum(100.0)
        xAxis.ticks("{ interval: 1 }")
            .minorTicks("{ interval: 1 }")
        xAxis.labels().enabled(false)
        xAxis.ticks().enabled(false)
        xAxis.minorTicks().enabled(false)
        val bar0 = circularGauge.bar(0.0)
        bar0.dataIndex(0.0)
        bar0.radius(100.0)
        bar0.width(10.0)
        bar0.fill(SolidFill("#90D0D4", 1.0))
        bar0.stroke(null)
        bar0.zIndex(5.0)
        val bar100 = circularGauge.bar(100.0)
        bar100.dataIndex(5.0)
        bar100.radius(100.0)
        bar100.width(2.0)
        bar100.fill(SolidFill("#C8C8C8", 0.4))
//        bar100.stroke("1 #e5e4e4")
        bar100.zIndex(4.0)
        val bar1 = circularGauge.bar(1.0)
        bar1.dataIndex(1.0)
        bar1.radius(80.0)
        bar1.width(10.0)
        bar1.fill(SolidFill("#92C83E", 1.0))
        bar1.stroke(null)
        bar1.zIndex(5.0)
        val bar101 = circularGauge.bar(101.0)
        bar101.dataIndex(5.0)
        bar101.radius(80.0)
        bar101.width(2.0)
        bar101.fill(SolidFill("#C8C8C8", 0.4))
//        bar101.stroke("1 #e5e4e4")
        bar101.zIndex(4.0)
        val bar2 = circularGauge.bar(2.0)
        bar2.dataIndex(2.0)
        bar2.radius(60.0)
        bar2.width(10.0)
        bar2.fill(SolidFill("#FFD9BD", 1.0))
        bar2.stroke(null)
        bar2.zIndex(5.0)
        val bar102 = circularGauge.bar(102.0)
        bar102.dataIndex(5.0)
        bar102.radius(60.0)
        bar102.width(2.0)
        bar102.fill(SolidFill("#C8C8C8", 0.4))
//        bar102.stroke("1 #e5e4e4")
        bar102.zIndex(4.0)
        /*  val bar3 = circularGauge.bar(3.0)
          bar3.dataIndex(3.0)
          bar3.radius(40.0)
          bar3.width(17.0)
          bar3.fill(SolidFill("#ffd54f", 1.0))
          bar3.stroke(null)
          bar3.zIndex(5.0)
          val bar103 = circularGauge.bar(103.0)
          bar103.dataIndex(5.0)
          bar103.radius(40.0)
          bar103.width(17.0)
          bar103.fill(SolidFill("#F5F4F4", 1.0))
          bar103.stroke("1 #e5e4e4")
          bar103.zIndex(4.0)

          val bar4 = circularGauge.bar(4.0)
          bar4.dataIndex(4.0)
          bar4.radius(20.0)
          bar4.width(17.0)
          bar4.fill(SolidFill("#455a64", 1.0))
          bar4.stroke(null)
          bar4.zIndex(5.0)
          val bar104 = circularGauge.bar(104.0)
          bar104.dataIndex(5.0)
          bar104.radius(20.0)
          bar104.width(17.0)
          bar104.fill(SolidFill("#F5F4F4", 1.0))
          bar104.stroke("1 #e5e4e4")
          bar104.zIndex(4.0)*/
        anyChartView.setChart(circularGauge)
    }

    private fun bmiCalculator() {
        val rotateAnimation = RotateAnimation(
            -90f,  // Start angle (in degrees)
            45f, // End angle (in degrees)
            Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point X (0.5 means center)
            Animation.RELATIVE_TO_SELF, 1.0f  // Pivot point Y (1.0 means bottom)
        )

        rotateAnimation.duration = 1000 // 1 second
        //        rotateAnimation.repeatCount = Animation.INFINITE // Infinite rotation
        val rotateAnimation2 = RotateAnimation(
            360f,  // Start angle (in degrees)
            270f, // End angle (in degrees)
            Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point X (0.5 means center)
            Animation.RELATIVE_TO_SELF, 1.0f  // Pivot point Y (1.0 means bottom)
        )
        //        rotateAnimation.duration = 1 // 1 second
        val viewToRotate = findViewById<View>(R.id.bmiNeedle) // Replace with your view ID
        viewToRotate.startAnimation(rotateAnimation2)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                viewToRotate.startAnimation(rotateAnimation)
            }, 2000
        )
    }
}