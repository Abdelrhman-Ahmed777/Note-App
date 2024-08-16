package ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.note.R
import model.ItemData
import model.NoteDataBaseHelper
import kotlin.random.Random


class AdaptorClass(  private var dataList: List<ItemData> ): RecyclerView.Adapter<AdaptorClass.ViewHolder>() {


     private lateinit var filterList : List<ItemData>
     private lateinit var db : NoteDataBaseHelper

   inner class ViewHolder(itemView : View ) : RecyclerView.ViewHolder(itemView) {
        val title: TextView? =  itemView.findViewById<TextView>(R.id.tv_title_item)
        val note: TextView? = itemView.findViewById<TextView>(R.id.tv_note_item)
        val fav: ImageButton? = itemView.findViewById<ImageButton>(R.id.iv_fav_button_item)
        val archive: ImageButton? = itemView.findViewById<ImageButton>(R.id.iv_archive_button)
        val delete: ImageButton? = itemView.findViewById<ImageButton>(R.id.iv_delete_item)

    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): ViewHolder {
          val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_item,parent,false)
          db = NoteDataBaseHelper(parent.context)
          return ViewHolder(itemView)
    }


    override fun getItemCount(): Int {
           return dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder , position: Int) {
             val current = dataList[position]
             val note = dataList[position]

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpDateActivity::class.java).apply {
                putExtra("note", note.id )
            }
            holder.itemView.context.startActivity(intent)
         //   val clickedItem = dataList[holder.adapterPosition]
        }
        holder.title?.text = current.title
        holder.note?.text = current.note

        if (current.isFav == true) {
            holder.fav?.setImageResource(R.drawable.ic_favorite_off_red) // Replace with your filled favorite icon
        } else {
            holder.fav?.setImageResource(R.drawable.ic_favorite_on_red) // Replace with your empty favorite icon
        }
        holder.fav?.setOnClickListener(){
            current.isFav=!(current.isFav?:false)
            notifyItemChanged(position)
        }


        if (current.isArchive == true) {
            holder.archive?.setImageResource(R.drawable.ic_archive_off) // Replace with your filled archive icon
        } else {
            holder.archive?.setImageResource(R.drawable.ic_archive_yellow) // Replace with your empty archive icon
        }
       holder.archive?.setOnClickListener()
       {
           current.isArchive=!(current.isArchive?:false)
           notifyItemChanged(position)
       }

      holder.delete?.setOnClickListener(){
        db.deleteNote(note.id)
         setList(db.getAllNotes())
      }

    }
    private fun getRandomColor(): Int {
        val rnd = Random
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList (dataList: List<ItemData>){
        this.dataList=dataList
        notifyDataSetChanged()
    }

fun filterList(query: String) {
    filterList = dataList.filter { item ->
        item.title?.contains(query, ignoreCase = true) == true
    }
    notifyDataSetChanged()
}

}