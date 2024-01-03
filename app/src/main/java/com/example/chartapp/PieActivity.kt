package com.example.chartapp

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.anychart.APIlib
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Cartesian
import com.anychart.core.cartesian.series.Column
import com.anychart.core.cartesian.series.Line
import com.anychart.core.cartesian.series.RangeColumn
import com.anychart.data.Mapping
import com.anychart.data.Set
import com.anychart.enums.Anchor
import com.anychart.enums.HoverMode
import com.anychart.enums.MarkerType
import com.anychart.enums.Position
import com.anychart.enums.TooltipDisplayMode
import com.anychart.enums.TooltipPositionMode
import com.anychart.graphics.vector.SolidFill
import com.anychart.graphics.vector.Stroke
import com.anychart.graphics.vector.text.HAlign
import com.example.chartapp.databinding.ActivityPieBinding

class PieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPieBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /* val list: ArrayList<PieEntry> = ArrayList()
         list.add(PieEntry(100f, "100"))
         list.add(PieEntry(101f, "101"))
         list.add(PieEntry(102f, "102"))
         list.add(PieEntry(103f, "103"))
         list.add(PieEntry(104f, "104"))
         list.add(PieEntry(105f, "105"))
         val barDataSet = PieDataSet(list, "List")
         barDataSet.setColors(ColorTemplate.MATERIAL_COLORS, 255)
         barDataSet.valueTextSize = 15f
         val pieData = PieData(barDataSet)
         pieChart.data = pieData
         pieChart.description.text = "Pie Chart"
         pieChart.animateY(2000)*/


        scatterChart()

        bloodPressure()

        heartRate()

        spo2()

        sleep()
    }
    private fun scatterChart() {
        val anyChartView = binding.anyChartScatter
//        anyChartView.setProgressBar(binding.progressBarPie)
        APIlib.getInstance().setActiveAnyChartView(anyChartView)
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

    private fun heartRate() {
        val anyChartView = binding.anyChartHeartRate
//        anyChartView.setProgressBar(binding.progressBarPie)
        APIlib.getInstance().setActiveAnyChartView(anyChartView)
        val cartesian = AnyChart.line()

        cartesian.animation(true)

        cartesian.padding(10.0, 20.0, 5.0, 20.0)

        cartesian.crosshair().enabled(true)
        cartesian.crosshair()
            .yLabel(true) // TODO y stroke
            .yStroke(null as Stroke?, null, null, null as String?, null as String?)

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT)

        cartesian.title("Heart Rate")

//        cartesian.yAxis(0).title("Number of Bottles Sold (thousands)")
        cartesian.xAxis(0).labels().padding(5.0, 5.0, 5.0, 5.0)
        val seriesData: MutableList<DataEntry> = ArrayList()
        seriesData.add(LineDataEntry("1986", 3.6, 2.3, 2.8))
        seriesData.add(LineDataEntry("1987", 7.1, 4.0, 4.1))
        seriesData.add(LineDataEntry("1988", 8.5, 6.2, 5.1))
        seriesData.add(LineDataEntry("1989", 9.2, 11.8, 6.5))
        seriesData.add(LineDataEntry("1990", 10.1, 13.0, 12.5))
        seriesData.add(LineDataEntry("1991", 11.6, 13.9, 18.0))
        seriesData.add(LineDataEntry("1992", 16.4, 18.0, 21.0))
        seriesData.add(LineDataEntry("1993", 18.0, 23.3, 20.3))
        seriesData.add(LineDataEntry("1994", 13.2, 24.7, 19.2))
        seriesData.add(LineDataEntry("1995", 12.0, 18.0, 14.4))
        seriesData.add(LineDataEntry("1996", 3.2, 15.1, 9.2))
        seriesData.add(LineDataEntry("1997", 4.1, 11.3, 5.9))
        seriesData.add(LineDataEntry("1998", 6.3, 14.2, 5.2))
        seriesData.add(LineDataEntry("1999", 9.4, 13.7, 4.7))
        seriesData.add(LineDataEntry("2000", 11.5, 9.9, 4.2))
        seriesData.add(LineDataEntry("2001", 13.5, 12.1, 1.2))
        seriesData.add(LineDataEntry("2002", 14.8, 13.5, 5.4))
        seriesData.add(LineDataEntry("2003", 16.6, 15.1, 6.3))
        seriesData.add(LineDataEntry("2004", 18.1, 17.9, 8.9))
        seriesData.add(LineDataEntry("2005", 17.0, 18.9, 10.1))
        seriesData.add(LineDataEntry("2006", 16.6, 20.3, 11.5))
        seriesData.add(LineDataEntry("2007", 14.1, 20.7, 12.2))
        seriesData.add(LineDataEntry("2008", 15.7, 21.6, 10))
        seriesData.add(LineDataEntry("2009", 12.0, 22.5, 8.9))
        val set = Set.instantiate()
        set.data(seriesData)
        val series1Mapping = set.mapAs("{ x: 'x', value: 'value' }")
        val series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }")
        val series3Mapping = set.mapAs("{ x: 'x', value: 'value3' }")
        val series1: Line = cartesian.line(series1Mapping)
        series1.name("HR")
        series1.hovered().markers().enabled(true)
        series1.hovered().markers()
            .type(MarkerType.CIRCLE)
            .size(4.0)
        series1.tooltip()
            .position("right")
            .anchor(Anchor.LEFT_CENTER)
            .offsetX(5.0)
            .offsetY(5.0)
        val series2: Line = cartesian.line(series2Mapping)
        series2.name("HR 2")
        series2.hovered().markers().enabled(true)
        series2.hovered().markers()
            .type(MarkerType.CIRCLE)
            .size(4.0)
        series2.tooltip()
            .position("right")
            .anchor(Anchor.LEFT_CENTER)
            .offsetX(5.0)
            .offsetY(5.0)
        val series3: Line = cartesian.line(series3Mapping)
        series3.name("HR 3")
        series3.hovered().markers().enabled(true)
        series3.hovered().markers()
            .type(MarkerType.CIRCLE)
            .size(4.0)
        series3.tooltip()
            .position("right")
            .anchor(Anchor.LEFT_CENTER)
            .offsetX(5.0)
            .offsetY(5.0)

        cartesian.legend().enabled(true)
        cartesian.legend().fontSize(13.0)
        cartesian.legend().padding(0.0, 0.0, 10.0, 0.0)

        anyChartView.setChart(cartesian)
    }

    private fun bloodPressure() {
        val anyChartView = binding.anyChartBloodPressure
//        anyChartView.setProgressBar(binding.progressBarPie)
        APIlib.getInstance().setActiveAnyChartView(anyChartView)
        val cartesian: Cartesian = AnyChart.bar()

        cartesian.title("Blood Pressure")
        cartesian.barGroupsPadding(10)
        val data: MutableList<DataEntry> = ArrayList()
        data.add(CustomDataEntry("Jan", 5.8, 7.9))
        data.add(CustomDataEntry("Feb", 4.6, 6.1))
        data.add(CustomDataEntry("Mar", 5.9, 8.1))
        data.add(CustomDataEntry("Apr", 7.8, 10.7))
        data.add(CustomDataEntry("May", 10.5, 13.7))
//        data.add(CustomDataEntry("June", 13.8, 17))
//        data.add(CustomDataEntry("July", 16.5, 18.5))
//        data.add(CustomDataEntry("Aug", 17.8, 19))
//        data.add(CustomDataEntry("Sep", 15.4, 17.8))
//        data.add(CustomDataEntry("Oct", 12.7, 15.3))
//        data.add(CustomDataEntry("Nov", 9.8, 13))
//        data.add(CustomDataEntry("Dec", 9, 10.1))
        val set = Set.instantiate()
        set.data(data)

        val bloodPressure: Mapping = set.mapAs("{ x: 'x', high: 'bloodPressureHigh', low: 'bloodPressureLow' }")
//        val edinburgData: Mapping =
//            set.mapAs("{ x: 'x', high: 'edinburgHigh', low: 'edinburgLow' }")
        val columnLondon: RangeColumn = cartesian.rangeColumn(bloodPressure)
        columnLondon.name("Blood Pressure")
//        val columnEdinburg: RangeColumn = cartesian.rangeColumn(edinburgData)
//        columnEdinburg.name("Edinburgh")

        cartesian.xAxis(true)
        cartesian.yAxis(true)

        cartesian.yScale()
            .minimum(4.0)
            .maximum(20.0)

        cartesian.legend(true)

        cartesian.yGrid(true)
            .yMinorGrid(true)

        cartesian.tooltip().titleFormat("{%SeriesName} ({%x})")

        anyChartView.setChart(cartesian)
    }


    private val lineData: List<DataEntry>
        get() {
            val data: MutableList<DataEntry> = java.util.ArrayList()
            data.add(ValueDataEntry(1.7, 54.310454158527))
            data.add(ValueDataEntry(1.8, 55.2005091829704))
            data.add(ValueDataEntry(1.9, 56.0905642074139))
            data.add(ValueDataEntry(2, 56.9806192318574))
            data.add(ValueDataEntry(2.1, 57.8706742563008))
            data.add(ValueDataEntry(2.2, 58.7607292807443))
            data.add(ValueDataEntry(2.3, 59.6507843051877))
            data.add(ValueDataEntry(2.5, 61.4308943540747))
            data.add(ValueDataEntry(2.6, 62.3209493785181))
            data.add(ValueDataEntry(2.7, 63.2110044029616))
            data.add(ValueDataEntry(2.9, 64.9911144518485))
            data.add(ValueDataEntry(3, 65.881169476292))
            data.add(ValueDataEntry(3.1, 66.7712245007354))
            data.add(ValueDataEntry(3.2, 67.6612795251789))
            data.add(ValueDataEntry(3.3, 68.5513345496223))
            data.add(ValueDataEntry(3.4, 69.4413895740658))
            data.add(ValueDataEntry(3.5, 70.3314445985093))
            data.add(ValueDataEntry(3.6, 71.2214996229527))
            data.add(ValueDataEntry(3.7, 72.1115546473962))
            data.add(ValueDataEntry(3.8, 73.0016096718396))
            data.add(ValueDataEntry(3.9, 73.8916646962831))
            data.add(ValueDataEntry(4, 74.7817197207266))
            data.add(ValueDataEntry(4.1, 75.67177474517))
            data.add(ValueDataEntry(4.2, 76.5618297696135))
            data.add(ValueDataEntry(4.3, 77.4518847940569))
            data.add(ValueDataEntry(4.4, 78.3419398185004))
            data.add(ValueDataEntry(4.5, 79.2319948429438))
            data.add(ValueDataEntry(4.6, 80.1220498673873))
            data.add(ValueDataEntry(4.7, 81.0121048918308))
            data.add(ValueDataEntry(4.8, 81.9021599162742))
            data.add(ValueDataEntry(4.9, 82.7922149407177))
            data.add(ValueDataEntry(5, 83.6822699651611))
            data.add(ValueDataEntry(5.1, 84.5723249896046))
            data.add(ValueDataEntry(5.2, 85.4623800140481))
            return data
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

    private class CustomDataEntry(
        x: String?,
        bloodPressureHigh: Number?,
        bloodPressureLow: Number?,
        ) : DataEntry() {

        init {
            setValue("x", x)
            setValue("bloodPressureHigh", bloodPressureHigh)
            setValue("bloodPressureLow", bloodPressureLow)
        }
    }

    private class LineDataEntry(
        x: String?,
        value: Number?,
        value2: Number?,
        value3: Number?
    ) : ValueDataEntry(x, value) {

        init {
            setValue("value2", value2)
            setValue("value3", value3)
        }
    }

    private fun spo2() {
        val anyChartView = binding.anyChartSpo2
//        anyChartView.setProgressBar(binding.progressBarPie)
        APIlib.getInstance().setActiveAnyChartView(anyChartView)
        val cartesian = AnyChart.column()
        val data: MutableList<DataEntry> = ArrayList()
        data.add(ValueDataEntry("Rouge", 80540))
        data.add(ValueDataEntry("Foundation", 94190))
        data.add(ValueDataEntry("Mascara", 102610))
        data.add(ValueDataEntry("Lip gloss", 110430))
        data.add(ValueDataEntry("Lipstick", 128000))
        data.add(ValueDataEntry("Nail polish", 143760))
//        data.add(ValueDataEntry("Eyebrow pencil", 170670))
//        data.add(ValueDataEntry("Eyeliner", 213210))
//        data.add(ValueDataEntry("Eyeshadows", 249980))

        cartesian.barGroupsPadding(20)
        val column: Column = cartesian.column(data)

        column.tooltip()
            .titleFormat("{%X}")
            .position(Position.CENTER_BOTTOM)
            .anchor(Anchor.CENTER_BOTTOM)
            .offsetX(0.0)
            .offsetY(5.0)
            .format("\${%Value}{groupsSeparator: }")

        cartesian.animation(true)
        cartesian.title("SpO2")

        cartesian.yScale().minimum(0.0)

        cartesian.yAxis(0).labels().format("\${%Value}{groupsSeparator: }")

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT)
        cartesian.interactivity().hoverMode(HoverMode.BY_X)

//        cartesian.xAxis(0).title("Product")
//        cartesian.yAxis(0).title("Revenue")

        anyChartView.setChart(cartesian)
    }

    private fun sleep() {
        val anyChartView = binding.anyChartSleep
        anyChartView.setProgressBar(binding.progressBarPie)
        APIlib.getInstance().setActiveAnyChartView(anyChartView)
        val cartesian = AnyChart.line()
        // Sample data for demonstration
        val data = listOf(
            ValueDataEntry("Jan", 20),
            ValueDataEntry("Feb", 35),
            ValueDataEntry("Mar", 15),
            ValueDataEntry("Mar", 5),
            ValueDataEntry("Apr", 45),
            ValueDataEntry("May", 30)
        )
        // Set the data to the chart
        cartesian.jumpLine(data)
        cartesian.title("Sleep")

        anyChartView.setChart(cartesian)
    }
}