package com.example.note.adaptor

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.note.R
import com.example.note.databinding.ActivityItemBinding
import com.example.note.viewModel.NoteViewModel
import model.ItemData
import ui.UpDateActivity
import kotlin.random.Random


class NoteAdaptor(private var dataList: List<ItemData>) :
    RecyclerView.Adapter<NoteAdaptor.NoteViewHolder>() {


    private lateinit var filterList: List<ItemData>

    inner class NoteViewHolder(itemView: ActivityItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val title: TextView = itemView.tvTitleItem
        val content: TextView = itemView.tvContentItem
        val date: TextView = itemView.tvDateItem
        val fav: ImageButton = itemView.ivFavButtonItem
        val archive: ImageButton = itemView.ivArchiveButtonItem
    }


    private val diffCallBack = object : DiffUtil.ItemCallback<ItemData>() {
        override fun areItemsTheSame(oldItem: ItemData , newItem: ItemData): Boolean {
            return oldItem.noteId == newItem.noteId &&
                    oldItem.title == newItem.title &&
                    oldItem.content == newItem.content &&
                    oldItem.date == newItem.date &&
                    oldItem.isFav == newItem.isFav &&
                    oldItem.isArchive == newItem.isArchive
        }

        override fun areContentsTheSame(oldItem: ItemData , newItem: ItemData): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this , diffCallBack)


    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ActivityItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: NoteViewHolder , position: Int) {

        val current = differ.currentList[position]
        val note = current

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context , UpDateActivity::class.java).apply {
                putExtra("note" , note.noteId)
            }
            holder.itemView.context.startActivity(intent)
            //   val clickedItem = dataList[holder.adapterPosition]
        }
        holder.title.text = current.title
        holder.content.text = current.content
        holder.date.text = " this note created at : ${current.date}"
        holder.fav.isActivated = current.isFav
        holder.archive.isActivated = current.isArchive

        if (current.isFav) {
            holder.fav.setImageResource(R.drawable.ic_favorite_on_red) // Replace with your filled favorite icon
        } else {
            holder.fav.setImageResource(R.drawable.ic_favorite_off_red) // Replace with your empty favorite icon
        }

        holder.fav.setOnClickListener() {
            current.isFav = !(current.isFav)
            if (current.isFav) {
                holder.fav.setImageResource(R.drawable.ic_favorite_on_red) // Replace with your filled favorite icon
            } else {
                holder.fav.setImageResource(R.drawable.ic_favorite_off_red) // Replace with your empty favorite icon
            }
            notifyItemChanged(position)
        }


        if (!current.isArchive) {
            holder.archive.setImageResource(R.drawable.ic_archive_off) // Replace with your filled archive icon
        } else {

        }
        holder.archive.setOnClickListener()
        {
            current.isArchive = !(current.isArchive ?: false)
            if (!current.isArchive) {
                holder.archive.setImageResource(R.drawable.ic_archive_off) // Replace with your filled archive icon
            } else {

            }
            notifyItemChanged(position)
        }

        holder.itemView.findViewById<ImageButton>(R.id.iv_delete_item).setOnClickListener {
            NoteViewModel(,rep)
        }

    }


    @SuppressLint("NotifyDataSetChanged")
    fun setList(dataList: List<ItemData>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    private fun getRandomColor(): Int {
        val colorCode = arrayListOf<Int>()
        val random = Random
        colorCode.add(R.color.color1_1)
        colorCode.add(R.color.color1_2)
        colorCode.add(R.color.color1_3)
        colorCode.add(R.color.color1_4)
        colorCode.add(R.color.color2_1)
        colorCode.add(R.color.color2_2)
        colorCode.add(R.color.color2_3)
        return colorCode[random.nextInt(colorCode.size)]
    }


    @SuppressLint("NotifyDataSetChanged")
    fun filterList(query: String) {
        filterList = dataList.filter { item ->
            item.title?.contains(query , ignoreCase = true) == true
        }
        notifyDataSetChanged()
    }

}