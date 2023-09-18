import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var stopwatchTextView: TextView
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var resetButton: Button
    private var isRunning = false
    private var seconds = 0
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stopwatchTextView = findViewById(R.id.stopwatchTextView)
        startButton = findViewById(R.id.startButton)
        stopButton = findViewById(R.id.stopButton)
        resetButton = findViewById(R.id.resetButton)

        startButton.setOnClickListener { startStopwatch() }
        stopButton.setOnClickListener { stopStopwatch() }
        resetButton.setOnClickListener { resetStopwatch() }
    }

    private val runnable = object : Runnable {
        override fun run() {
            if (isRunning) {
                seconds++
                val hours = seconds / 3600
                val minutes = (seconds % 3600) / 60
                val secs = seconds % 60
                val time = String.format("%02d:%02d:%02d", hours, minutes, secs)
                stopwatchTextView.text = time
            }
            handler.postDelayed(this, 1000)
        }
    }

    private fun startStopwatch() {
        isRunning = true
        handler.post(runnable)
    }

    private fun stopStopwatch() {
        isRunning = false
        handler.removeCallbacks(runnable)
    }

    private fun resetStopwatch() {
        isRunning = false
        handler.removeCallbacks(runnable)
        seconds = 0
        stopwatchTextView.text = "00:00:00"
    }
}