package model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NoteDataBaseHelper(context: Context ) :SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION)
{
    companion object {
        private const val DATABASE_VERSION = 2
        private const val DATABASE_NAME = "NotesDB"
        private const val TABLE_NAME = "AllNotes"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_FAV = "fav"
        private const val COLUMN_ARCH = "arch"
        private const val COLUMN_NOTE = "note"
    }

    override fun onCreate(db: SQLiteDatabase?) {
           val CreatTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY , $COLUMN_TITLE TEXT , $COLUMN_NOTE TEXT , $COLUMN_FAV BOOLEAN , $COLUMN_ARCH BOOLEAN)"
            db?.execSQL(CreatTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase? , oldVersion: Int , newVersion: Int) {
        if(newVersion>oldVersion){
             val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
             db?.execSQL(dropTableQuery)
             onCreate(db)}
    }

    fun insertNote(item : ItemData){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE , item.title)
            put(COLUMN_NOTE , item.note)
            put(COLUMN_FAV , item.isFav)
            put(COLUMN_ARCH , item.isArchive)
        }
        db.insert(TABLE_NAME , null , values)
        db.close()
    }

   fun getAllNotes(): List<ItemData> {
       val notesList = mutableListOf<ItemData>()
       val db = readableDatabase
       val query = "SELECT * FROM $TABLE_NAME"
       val cursor = db.rawQuery(query , null)
       while (cursor.moveToNext()){
           val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
           val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
           val note = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTE))
           val isFav = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FAV)) == 1
           val isArchive = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ARCH)) == 1
           val item = ItemData(id , title , note , isFav , isArchive)
           notesList.add(item)
       }
       cursor.close()
       db.close()
       return notesList
   }
    fun update(item: ItemData){
     val db = writableDatabase
     val values = ContentValues().apply {
         put(COLUMN_TITLE , item.title)
         put(COLUMN_NOTE , item.note)
         put(COLUMN_FAV , item.isFav)
         put(COLUMN_ARCH , item.isArchive)
     }
        db.update(TABLE_NAME , values , "$COLUMN_ID = ?" , arrayOf(item.id.toString()))
        db.close()
    }

    fun getNoteById(noteId: Int): ItemData {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = ?"
        val cursor = db.rawQuery(query, arrayOf(noteId.toString()))
        cursor.moveToFirst()
        val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
        val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)).toString()
        val note = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTE)).toString()
        val isFav = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FAV)) == 1
        val isArchive = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ARCH)) == 1
        val item = ItemData(id , title , note , isFav , isArchive)
        cursor.close()
        db.close()
        return item
    }

    fun deleteNote(noteId: Int) {
        val db = writableDatabase
        db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(noteId.toString()))
        db.close()
    }
}




