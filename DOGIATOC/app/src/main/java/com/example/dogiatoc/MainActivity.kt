package com.example.dogiatoc

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private lateinit var tvAcceleration: TextView
    private lateinit var ball: ImageView

    private var xPos = 0f
    private var yPos = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvAcceleration = findViewById(R.id.tv_acceleration)
        ball = findViewById(R.id.ball)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onResume() {
        super.onResume()
        accelerometer?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.values?.let { values ->
            val x = values[0]
            val y = values[1]
            val z = values[2]

            tvAcceleration.text = "Gia tốc: X=$x, Y=$y, Z=$z"

            xPos -= x * 5
            yPos += y * 5
            ball.translationX = xPos
            ball.translationY = yPos
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}
