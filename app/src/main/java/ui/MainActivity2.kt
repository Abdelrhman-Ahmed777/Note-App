package ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.note.R
import com.example.note.databinding.ActivityMain2Binding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.ItemData
import com.example.note.database.NotesDB
import com.example.note.database.NotesDao
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding2: ActivityMain2Binding
    private var isFavClicked = false
    private var isArchClicked = false
    private var dao: NotesDao? = null
    private val tag = "hi"


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding2 = ActivityMain2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding2.root)
        WindowCompat.setDecorFitsSystemWindows(window , false)
        ViewCompat.setOnApplyWindowInsetsListener(binding2.root) { v , insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left , systemBars.top , systemBars.right , systemBars.bottom)
            insets
        }


        dao = NotesDB.getDataBase(this).noteDao()




        binding2.ivFav.setOnClickListener {
            favButton()
        }

        binding2.ivArchiveButton.setOnClickListener {
            archiveButton()
        }

        binding2.ivBackButton.setOnClickListener {
            intent = Intent(this , MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding2.ivDone.setOnClickListener {
            attachDataToUi()
        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun favButton() {
        isFavClicked = !isFavClicked
        if (isFavClicked) {
            binding2.ivFav.setImageResource(R.drawable.ic_favorite_on_red)
            showToast("you add this note to favorite")


        } else {
            binding2.ivFav.setImageResource(R.drawable.ic_favorite_off_red)
            showToast(" you removed this note to favorite")
            isFavClicked = false


        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun archiveButton() {
        isArchClicked = !isArchClicked
        if (isArchClicked) {
            binding2.ivArchiveButton.setImageResource(R.drawable.ic_archive_off)
            showToast(" you removed this note to archive")


        } else {
            binding2.ivArchiveButton.setImageResource(R.drawable.ic_archive_24)
            showToast(" you added this note to archive")


        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun attachDataToUi() {
        val item = ItemData(
            noteId = 0 ,
            title = binding2.etTitle.text.toString() ,
            content = binding2.etNote.text.toString() ,
            date = getDate() ,
            isFav = isFavClicked ,
            isArchive = isArchClicked
        )
        // add note to database
        lifecycleScope.launch(Dispatchers.IO) {
            dao?.addNote(item)
            dao?.update(item)
        }
        // moving to the main activity
        intent = Intent(this , MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDate(): String {
        val current = LocalDateTime.now()
        val format = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return current.format(format)
    }

    private fun showToast(message: String) {
        Toast.makeText(this , message , Toast.LENGTH_SHORT).show()
    }
}



