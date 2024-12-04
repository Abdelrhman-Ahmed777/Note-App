package model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "note_table")
@Parcelize
data class ItemData(
    @PrimaryKey(autoGenerate = true) val noteId: Int ,
    @ColumnInfo(name = "titles") var title: String? ,
    @ColumnInfo(name = "content") var content: String? ,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "favorite items") var isFav: Boolean = false ,
    @ColumnInfo(name = "archived items") var isArchive: Boolean = false
) : Parcelable