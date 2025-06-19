package vcmsa.ci.musicplaylistapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailedView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detailed_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Get data from intent
        val SongTitle = intent.getStringArrayListExtra("songTitle") ?: arrayListOf()
        val ArtistName = intent.getStringArrayListExtra("ArtistName") ?: arrayListOf()
        val Rating = intent.getIntegerArrayListExtra("Rating") ?: arrayListOf()
        val comments = intent.getStringArrayListExtra("comments") ?: arrayListOf()

        val resultsTextView = findViewById<TextView> (R.id.resultsTextView)


        // Back button
        findViewById<Button>(R.id.backBtn).setOnClickListener {
            finish()
        }
    }
}
