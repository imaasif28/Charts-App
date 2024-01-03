package com.example.chartapp

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.utils.ColorTemplate

class RadarActivity2 : AppCompatActivity() {

    lateinit var radarChart: RadarChart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_radar2)

        radarChart = findViewById(R.id.radarChartView)
        val list: ArrayList<RadarEntry> = ArrayList()
        list.add(RadarEntry(100f, 100f))
        list.add(RadarEntry(101f, 101f))
        list.add(RadarEntry(102f, 102f))
        list.add(RadarEntry(103f, 103f))
        list.add(RadarEntry(104f, 104f))
        list.add(RadarEntry(105f, 105f))
        val barDataSet = RadarDataSet(list, "List")


//        val entries = mutableListOf<RadarEntry>()
//        entries.add(RadarEntry(4f))  // Value for the gauge
//
//        val dataSet = RadarDataSet(entries, "Gauge")
//        dataSet.color = Color.BLUE
//        dataSet.setDrawFilled(true)
//        dataSet.fillColor = Color.BLUE
//        dataSet.fillAlpha = 150
//
//        val radarData = RadarData(dataSet)
//
//        radarChart.data = radarData
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS, 255)
        barDataSet.valueTextSize = 15f
        barDataSet.valueTextColor = Color.RED
        barDataSet.lineWidth = 2f
        val barData = RadarData(barDataSet)
        radarChart.data = barData
        radarChart.description.text = "Radar Chart"



        val xAxis: XAxis = radarChart.xAxis
        xAxis.isEnabled = false  // Hide the X-axis labels

        val legend: Legend = radarChart.legend
        legend.isEnabled = false  // Hide the legend

        radarChart.description.isEnabled = false  // Hide the chart description

        radarChart.invalidate()

        radarChart.animateY(2000)


    }
}