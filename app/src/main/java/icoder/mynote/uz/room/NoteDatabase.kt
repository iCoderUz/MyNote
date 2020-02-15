package icoder.mynote.uz.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import icoder.mynote.uz.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao?

    companion object {
        private val instance: NoteDatabase? = null
        @Synchronized
        fun getInstance(context: Context): NoteDatabase? {
            if (instance == null) {
                Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java, "note_database"
                )
                    .fallbackToDestructiveMigration().build()
            }
            return instance
        }
    }
}