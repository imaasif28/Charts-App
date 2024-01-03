package com.example.chartapp

import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


object AESEncryption {
    const val key = "p0\$@dm!nseCur!tY"
//    @JvmStatic
//    fun main(args: Array<String>) {
//        val aa = EncryptStringAES(
//            "{\"mobileNo\": \"7972721529\",\"applicationType\": \"POS\",\"templateId\": \"1\"}",
//            "p0$@dm!nseCur!tY"
//        )
//        println(aa)
//        val aa1 = DecryptStringAES(
//            "+EOsoNua88Up9aHvRSL92Wym7yZ6xHjverIcAKSb1j3QZrB/Qv7y4TuFOd3xHDKdN4oMax05+r+8G07k+yv+0TUHL97tailgNsbAUKP18OQ=",
//            "p0$@dm!nseCur!tY"
//        )
//        println(aa1)
//    }

    fun EncryptStringAES(plainText: String?, secretKey: String = key): String {
//        val text = Gson().toJson(plainText)
        return try {
            val keybytes = secretKey.toByteArray(charset("UTF8"))
            val iv = secretKey.toByteArray(charset("UTF8"))
            val encFromJavascript = EncryptStringToBytes(plainText, keybytes, iv)
            android.util.Base64.encodeToString(encFromJavascript, android.util.Base64.DEFAULT)
        } catch (ex: Exception) {
            println(ex)
            ""
        }
    }

    private fun EncryptStringToBytes(
        plainText: String?,
        key: ByteArray?,
        iv: ByteArray?
    ): ByteArray? {
        return try {
            // Check arguments.
            require(!(plainText == null || plainText.length <= 0)) { "plainText" }
            require(!(key == null || key.size <= 0)) { "key" }
            require(!(iv == null || iv.size <= 0)) { "key" }
            var encrypted: ByteArray
            // Create a RijndaelManaged object
            // with the specified key and IV.
            val cipher =
                Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(
                Cipher.ENCRYPT_MODE,
                SecretKeySpec(key, "AES"),
                IvParameterSpec(iv)
            )

            cipher.doFinal(plainText.toByteArray(charset("UTF8")))
        } catch (ex: Exception) {
            println(ex)
            null
        }
    }

    fun DecryptStringAES(cipherText: String, secretKey: String = key): String {
        return try {
            val keybytes = secretKey.toByteArray(charset("UTF8"))
            val iv = secretKey.toByteArray(charset("UTF8"))
            val cipherTextByte = cipherText.toByteArray(charset("UTF8"))
            DecryptStringFromBytes(cipherTextByte, keybytes, iv)
        } catch (ex: Exception) {

            println(ex)
            ""
        }
    }

    private fun DecryptStringFromBytes(
        cipherText: ByteArray?,
        key: ByteArray?,
        iv: ByteArray?
    ): String {
        return try {
            // Check arguments.
            require(!(cipherText == null || cipherText.size <= 0)) { "cipherText" }
            require(!(key == null || key.size <= 0)) { "key" }
            require(!(iv == null || iv.size <= 0)) { "key" }

            // Declare the String used to hold
            // the decrypted text.
            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.DECRYPT_MODE, SecretKeySpec(key, "AES"), IvParameterSpec(iv))

            val plainText =
                cipher.doFinal(android.util.Base64.decode(cipherText, android.util.Base64.DEFAULT))
            String(plainText)

        } catch (ex: Exception) {
            println(ex)
            ""
        }
    }
}
