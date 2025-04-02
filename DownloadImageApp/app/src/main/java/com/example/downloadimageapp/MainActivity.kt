package com.example.downloadimageapp
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var etUrl: EditText
    private lateinit var btnDownload: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etUrl = findViewById(R.id.etUrl)
        btnDownload = findViewById(R.id.btnDownload)
        progressBar = findViewById(R.id.progressBar)
        imageView = findViewById(R.id.imageView)

        btnDownload.setOnClickListener {
            val url = etUrl.text.toString().trim()
            if (url.isNotEmpty()) {
                DownloadImageTask().execute(url)
            } else {
                Toast.makeText(this, "Vui lòng nhập URL hợp lệ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @Suppress("DEPRECATION")
    inner class DownloadImageTask : AsyncTask<String, Int, Bitmap?>() {

        override fun onPreExecute() {
            super.onPreExecute()
            progressBar.visibility = View.VISIBLE
            imageView.setImageBitmap(null)
        }

        override fun doInBackground(vararg urls: String): Bitmap? {
            val imageUrl = urls[0]
            return try {
                val url = URL(imageUrl)
                val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()

                val input: InputStream = connection.inputStream
                BitmapFactory.decodeStream(input)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

        override fun onPostExecute(result: Bitmap?) {
            super.onPostExecute(result)
            progressBar.visibility = View.GONE

            if (result != null) {
                imageView.setImageBitmap(result)
            } else {
                Toast.makeText(applicationContext, "Tải ảnh thất bại", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
