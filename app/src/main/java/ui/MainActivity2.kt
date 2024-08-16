package ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.note.R
import com.example.note.databinding.ActivityMain2Binding
import model.ItemData
import model.NoteDataBaseHelper

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding2 : ActivityMain2Binding
    private var isFavClicked = false
    private var isArchClicked = false
    private val tag = "hi"
    private lateinit var db : NoteDataBaseHelper


    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding2 = ActivityMain2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding2.root)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        ViewCompat.setOnApplyWindowInsetsListener(binding2.root) { v , insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left , systemBars.top , systemBars.right , systemBars.bottom)
            insets
        }

        binding2.ivFav.setOnClickListener {
            isFavClicked = !isFavClicked
            if( isFavClicked) {
               binding2.ivFav.setImageResource(R.drawable.ic_favorite_on_red)
               showToast("you add this note to favorite")
               isFavClicked = true
           }else {
                binding2.ivFav.setImageResource(R.drawable.ic_favorite_off_red)
                 showToast(" you removed this note to favorite")
           }

       }

        binding2.ivArchiveButton.setOnClickListener {
            isArchClicked = !isArchClicked
           if(isArchClicked) {
               binding2.ivArchiveButton.setImageResource(R.drawable.ic_archive_yellow)
               showToast(" you removed this note to archive")
               isArchClicked = true
           }else {
                binding2.ivArchiveButton.setImageResource(R.drawable.ic_archive_off)
                showToast(" you added this note to archive")
           }
        }

        db = NoteDataBaseHelper(this )

        binding2.ivBackButton.setOnClickListener {

            val item = ItemData(
                id = 0,
                title = binding2.etTitle.text.toString()
                , note = binding2.etNote.text.toString()
                , isFav = isFavClicked
                , isArchive = isArchClicked)
               db.insertNote(item)
               finish()
        }


    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
   }



