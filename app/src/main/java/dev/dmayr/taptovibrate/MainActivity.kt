package dev.dmayr.taptovibrate

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dev.dmayr.taptovibrate.databinding.ActivityMainBinding
import java.time.Duration

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var textView: TextView
    private var vibrationLong: Long = 180
    private var vibrationShort: Long = 80

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        textView = findViewById(R.id.textView)
        binding.apply {
            listOf(
                textView,
                buttonShort.setOnClickListener {
                    vibratePhoneShort()
                    // onFinish(textView)
                },
                buttonLong.setOnClickListener {
                    vibratePhoneLong()
                    // onFinish(textView)
                }
            )
        }
    }

    @Suppress("DEPRECATION")
    private fun vibratePhoneShort() {
        val vibration = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (vibration.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibration.vibrate(
                    VibrationEffect.createOneShot(
                        vibrationShort,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                ) // 80 min
            } else vibration.vibrate(vibrationShort) // 80 min
            textView.setTextColor(Color.GREEN)
            textView.text = "bzzz"
        } else noVibratorWarning(textView)
    }

    @Suppress("DEPRECATION")
    private fun vibratePhoneLong() {
        val vibration = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (vibration.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibration.vibrate(
                    VibrationEffect.createOneShot(
                        vibrationLong,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                ) // 160 min
            } else vibration.vibrate(vibrationLong) // 160 min
            textView.setTextColor(Color.RED)
            textView.text = "B Z Z Z"
        } else noVibratorWarning(textView)
    }
}

fun onTick(duration: Duration) {
// TODO: implement method to add a timer
}

fun onFinish(view: View) {
    view.visibility = View.INVISIBLE
}

private fun noVibratorWarning(textView: TextView) {
    textView.setTextColor(Color.RED)
    textView.text = "No vibrator device detected"
}