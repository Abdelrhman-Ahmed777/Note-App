package model

import android.os.Parcel
import android.os.Parcelable



 data class ItemData(
   val id : Int ,
   val title : String? ,
   val note : String? ,
   var isFav : Boolean? ,
   var isArchive : Boolean?
)