package ui

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.note.R
import com.example.note.databinding.ActivityUpDateBinding
import model.NoteDataBaseHelper

@Suppress("NAME_SHADOWING")
class UpDateActivity : AppCompatActivity() {

    private lateinit var upDateBinding : ActivityUpDateBinding
    private lateinit var db : NoteDataBaseHelper

    private var noteId : Int = -1
    private var isFavClicked = false
    private var isArchClicked = false
    private val tag = "hi"

    private lateinit var animationDrawable: AnimationDrawable

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

        db = NoteDataBaseHelper(this)
        noteId = intent.getIntExtra("note" , -1)
        if(noteId == -1){
            finish()
        }

          var isFav : Boolean
          var isArch : Boolean

        val note = db.getNoteById(noteId)
        upDateBinding.etTitleUpdate.setText(note.title)
        upDateBinding.etNoteUpdate.setText(note.note)
        isFav = note.isFav!!
        isArch = note.isArchive!!
        isFavClicked = !isFavClicked
        if( isFav) {
            upDateBinding.ivFavUpdate.setImageResource(R.drawable.ic_favorite_on_red)
            isFavClicked = true
        }else {
            upDateBinding.ivFavUpdate.setImageResource(R.drawable.ic_favorite_off_red)
        }
        upDateBinding.ivArchiveButtonUpdate.isActivated = note.isArchive!!
        isArchClicked = !isArchClicked
        if(isArch) {
            upDateBinding.ivArchiveButtonUpdate.setImageResource(R.drawable.ic_archive_yellow)
            isArchClicked = true
        }else {
            upDateBinding.ivArchiveButtonUpdate.setImageResource(R.drawable.ic_archive_off)
        }

        upDateBinding.ivFavUpdate.setOnClickListener {
            isFav = !isFavClicked
            if( isFavClicked) {
                upDateBinding.ivFavUpdate.setImageResource(R.drawable.ic_favorite_on_red)
                showToast("you add this note to favorite")
                isFavClicked = true
            }else {
                upDateBinding.ivFavUpdate.setImageResource(R.drawable.ic_favorite_off_red)
                showToast(" you removed this note to favorite")
            }
        }

        upDateBinding.ivArchiveButtonUpdate.setOnClickListener {
            isArch = !isArchClicked
            if(isArchClicked) {
                upDateBinding.ivArchiveButtonUpdate.setImageResource(R.drawable.ic_archive_yellow)
                showToast(" you removed this note to archive")
                isArchClicked = true
            }else {
                upDateBinding.ivArchiveButtonUpdate.setImageResource(R.drawable.ic_archive_off)
                showToast(" you added this note to archive")
            }
        }


        upDateBinding.ivBackButtonUpdate.setOnClickListener(){
            val newTitle = upDateBinding.etTitleUpdate.text.toString()
            val newNote = upDateBinding.etNoteUpdate.text.toString()
            val isFav = upDateBinding.ivFavUpdate.isActivated
            val isArch = upDateBinding.ivArchiveButtonUpdate.isActivated
            val item = model.ItemData(noteId , newTitle , newNote , isFav , isArch)
            db.update(item)

            val it = Intent(this , MainActivity::class.java)
            startActivity(it)
            finish()
        }


    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}