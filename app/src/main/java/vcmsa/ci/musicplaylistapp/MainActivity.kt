 package vcmsa.ci.musicplaylistapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // 4 Parallel arrays
    private val SongTitle = mutableListOf<String>()
    private val ArtistName = mutableListOf<String>()
    private val Rating = mutableListOf<Int>()
    private val comments = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.addSong).setOnClickListener {
            showAddItemDialog()
        }

        findViewById<Button>(R.id.viewListBtn).setOnClickListener {
            val intent = Intent(this, DetailedView::class.java)
            intent.putStringArrayListExtra("SongTitle", ArrayList(SongTitle))
            intent.putStringArrayListExtra("ArtistName", ArrayList(ArtistName))
            intent.putIntegerArrayListExtra("Rating", ArrayList(Rating))
            intent.putStringArrayListExtra("comments", ArrayList(comments))
            startActivity(intent)
        }
        findViewById<Button>(R.id.exitBtn).setOnClickListener {
            finish()

        }

    }

    private fun showAddItemDialog() {
        // Create a vertical LinearLayout to hold our input fields
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(50, 20, 50, 10)

        // Create input fields programmatically
        val songTitleinput = EditText(this)
        songTitleinput .hint = "Song Title"
        layout.addView(songTitleinput)

        val artistNameinput = EditText(this)
        artistNameinput.hint = "Artist Name"
        layout.addView(artistNameinput)

        val ratingInput = EditText(this)
        ratingInput.hint = "Rating out of 10"
        ratingInput.inputType = android.text.InputType.TYPE_CLASS_NUMBER
        layout.addView(ratingInput)

        val commentInput = EditText(this)
        commentInput.hint = "Comments (optional)"
        layout.addView(commentInput)

        // Build the dialog
        AlertDialog.Builder(this)
            .setTitle("Add New Song")
            .setView(layout)
            .setPositiveButton("Add") { _, _ ->
                val songTitle = songTitleinput.text.toString()
                val artistName =artistNameinput .text.toString()
                val ratingStr = ratingInput.text.toString()
                val comment = commentInput.text.toString()

                if (songTitle.isBlank() || artistName.isBlank() || ratingStr.isBlank()) {
                    Toast.makeText(this, "Please fill missing fields", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                try {
                    val rating = ratingStr.toInt()
                    if (rating <= 0) {
                        Toast.makeText(this, "Your rating must be positive", Toast.LENGTH_SHORT).show()
                        return@setPositiveButton
                    }

                    // Add to arrays
                    SongTitle.add(songTitle)
                    ArtistName.add(artistName)
                    Rating.add(rating)
                    comments.add(comment)

                    Toast.makeText(this, "Song added successfully", Toast.LENGTH_SHORT).show()
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Please enter a valid number for your Rating", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}


