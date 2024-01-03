package com.example.chartapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.example.chartapp.AESEncryption.DecryptStringAES
import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.io.UnsupportedEncodingException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.security.Security
import java.util.Base64
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.SecretKey
import javax.crypto.ShortBufferException
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class MainActivity : AppCompatActivity() {

    lateinit var btnBarChart: Button
    lateinit var btnPieChart: Button
    lateinit var btnRadarChart: Button

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnBarChart = findViewById(R.id.button)
        btnPieChart = findViewById(R.id.button2)
        btnRadarChart = findViewById(R.id.button3)

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