package com.example.chartapp

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
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
    private lateinit var pieChart : PieChart

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnBarChart = findViewById(R.id.button)
        btnPieChart = findViewById(R.id.button2)
        btnRadarChart = findViewById(R.id.button3)
        lineChart = findViewById(R.id.sleepChartView)
        pieChart = findViewById(R.id.pieChartView)

        lineChart()

        setupPieChart()
        setData()

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
        pieChart.setUsePercentValues(true)
        pieChart.description.isEnabled = false
        pieChart.isDrawHoleEnabled = true
        pieChart.setHoleColor(Color.BLUE)
        pieChart.setTransparentCircleColor(Color.BLUE)
        pieChart.setTransparentCircleAlpha(110)
        pieChart.holeRadius = 25f
        pieChart.transparentCircleRadius = 30f
        pieChart.setDrawCenterText(true)
        pieChart.rotationAngle = 0f
        pieChart.isRotationEnabled = true
    }

    private fun setData() {
        val outerEntries = mutableListOf<PieEntry>()
        val innerEntries = mutableListOf<PieEntry>()

        // Add your data for the outer pie chart
        outerEntries.add(PieEntry(30f, "Label 1"))
        outerEntries.add(PieEntry(45f, "Label 2"))
        outerEntries.add(PieEntry(25f, "Label 3"))

        // Add your data for the inner pie chart
        innerEntries.add(PieEntry(20f, "Inner Label 1"))
        innerEntries.add(PieEntry(35f, "Inner Label 2"))
        innerEntries.add(PieEntry(15f, "Inner Label 3"))

        val outerDataSet = PieDataSet(outerEntries, "Outer Pie")
        outerDataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()

        val innerDataSet = PieDataSet(innerEntries, "Inner Pie")
        innerDataSet.colors = ColorTemplate.PASTEL_COLORS.toList()

        val outerData = PieData(outerDataSet)
        val innerData = PieData(innerDataSet)

        // Set data for the outer and inner pie charts
        pieChart.data = outerData
        pieChart.invalidate()

        // Add inner pie chart to the center
        val holeRadius = 70f
        val transparentCircleRadius = 30f
        pieChart.holeRadius = holeRadius
//        pieChart.transparentCircleRadius = transparentCircleRadius
//        pieChart.setExtraOffsets(
//            holeRadius + transparentCircleRadius,
//            holeRadius + transparentCircleRadius,
//            holeRadius + transparentCircleRadius,
//            holeRadius + transparentCircleRadius
//        )
        pieChart.data = innerData
        pieChart.invalidate()
//        pieChart.notifyDataSetChanged()
    }

    private fun lineChart() {

//        val barData = LineData(barDataSet)
        val barData = LineData(listOf(heartRateDataSet1(), heartRateDataSet2(), heartRateDataSet3(), heartRateDataSet4()))
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
            xAxis.valueFormatter = CustomXAxisValueFormatter(arrayOf(" ","1PM", "2PM", "3PM", "4PM"))
            axisLeft.valueFormatter = CustomYAxisValueFormatter(arrayOf(" ", "Deep", "REM", "Core", "Awake"))

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

            setBackgroundColor(resources.getColor(android.R.color.white))
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