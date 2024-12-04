package ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.note.R
import com.example.note.adaptor.NoteAdaptor
import com.example.note.databinding.ActivityMainBinding

@Suppress("NAME_SHADOWING")
class MainActivity : AppCompatActivity()  {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adaptor: NoteAdaptor


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
            }


}