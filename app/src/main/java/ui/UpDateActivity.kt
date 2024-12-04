package ui

import android.content.Intent
//import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.note.R
import com.example.note.databinding.ActivityUpDateBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.note.database.NotesDB
import com.example.note.database.NotesDao
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Suppress("NAME_SHADOWING")
class UpDateActivity : AppCompatActivity() {

    private lateinit var upDateBinding: ActivityUpDateBinding

    private var noteId: Int = -1
    private lateinit var dao: NotesDao
    val note = dao.getNoteById(noteId)


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        upDateBinding = ActivityUpDateBinding.inflate(layoutInflater)
        setContentView(upDateBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v , insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left , systemBars.top , systemBars.right , systemBars.bottom)
            insets
        }

        dao = NotesDB.getDataBase(this).noteDao()


        noteId = intent.getIntExtra("note" , -1)
        if (noteId == -1) {
            finish()
        }



        upDateBinding.etTitleUpdate.setText(note.title)
        upDateBinding.etNoteUpdate.setText(note.content)

        if (note.isFav) {
            upDateBinding.ivFavUpdate.setImageResource(R.drawable.ic_favorite_on_red)
        } else {
            upDateBinding.ivFavUpdate.setImageResource(R.drawable.ic_favorite_off_red)
        }


        if (note.isArchive) {
            upDateBinding.ivArchiveButtonUpdate.setImageResource(R.drawable.ic_archive_yellow)
        } else {
            upDateBinding.ivArchiveButtonUpdate.setImageResource(R.drawable.ic_archive_off)
        }



        upDateBinding.ivFavUpdate.setOnClickListener {
            favButton()
        }

        upDateBinding.ivArchiveButtonUpdate.setOnClickListener {
            archiveButton()
        }


        upDateBinding.ivBackButtonUpdate.setOnClickListener {
            attachToUi()
            val it = Intent(this , MainActivity::class.java)
            startActivity(it)
            finish()
        }


    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun attachToUi() {
        val newTitle = upDateBinding.etTitleUpdate.text.toString()
        val newNote = upDateBinding.etNoteUpdate.text.toString()
        val date = getDate()
        val isFav = note.isFav
        val isArch = note.isArchive
        val note = model.ItemData(noteId , newTitle , newNote , date , isFav , isArch)
        lifecycleScope.launch(Dispatchers.IO) {
            dao.update(note)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun favButton() {
        note.isFav = !note.isFav
        if (note.isFav) {
            upDateBinding.ivFavUpdate.setImageResource(R.drawable.ic_favorite_on_red)
            showToast("you add this note to favorite")
            lifecycleScope.launch(Dispatchers.IO) {
                dao.update(note)
            }
        } else {
            upDateBinding.ivFavUpdate.setImageResource(R.drawable.ic_favorite_off_red)
            showToast(" you removed this note to favorite")
            lifecycleScope.launch(Dispatchers.IO) {
                dao.update(note)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun archiveButton() {
        note.isArchive = !note.isArchive
        if (note.isArchive) {
            upDateBinding.ivArchiveButtonUpdate.setImageResource(R.drawable.ic_archive_off)
            showToast(" you added this note to archive")
            lifecycleScope.launch(Dispatchers.IO) {
                dao.update(note)
            }

        } else {
            upDateBinding.ivArchiveButtonUpdate.setImageResource(R.drawable.ic_archive_24)
            showToast(" you removed this note to archive")
            lifecycleScope.launch(Dispatchers.IO) {
                dao.update(note)
            }
        }
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