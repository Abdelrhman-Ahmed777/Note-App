package ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.note.R
import com.example.note.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import model.NoteDataBaseHelper

@Suppress("NAME_SHADOWING")
class MainActivity : AppCompatActivity()  {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: NoteDataBaseHelper
    private lateinit var adaptor: AdaptorClass


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v , insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left , systemBars.top , systemBars.right , systemBars.bottom)
            insets
        }







        db = NoteDataBaseHelper(this)
        val noteItem = db.getAllNotes()
        adaptor = if (noteItem.isNotEmpty()) AdaptorClass(noteItem)
        else AdaptorClass(emptyList())
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = adaptor

     /*   binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return false
                }
             override fun onQueryTextChange(p0: String?): Boolean {
                adaptor.filterList(p0?:"")
                return true
            }
                })*/


                binding.btnAdd.setOnClickListener()
                {
                    val it = Intent(this , MainActivity2::class.java)
                    startActivity(it)
                }

            }
                    override fun onResume() {
                super.onResume()
                adaptor.setList(db.getAllNotes())

            }


}