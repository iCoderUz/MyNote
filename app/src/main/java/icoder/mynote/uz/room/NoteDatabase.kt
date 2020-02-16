package icoder.mynote.uz.room

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import icoder.mynote.uz.model.Note


@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao?

    companion object {
        private lateinit var instance: NoteDatabase
        @Synchronized
        fun getInstance(context: Context): NoteDatabase? {
            if (instance == null) {
                Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java, "note_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()
            }
            return instance
        }

        private val roomCallback: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance!!).execute()
            }
        }

        private class PopulateDbAsyncTask(private val noteDatabase: NoteDatabase): AsyncTask<Void, Void, Void>(){

            override fun doInBackground(vararg params: Void?): Void? {
                val noteDao = noteDatabase.noteDao()!!
                noteDao.insert(Note("Title1", "Description1", "15.05.2020", 1))
                noteDao.insert(Note("Title2", "Description2", "15.05.2020", 1))
                noteDao.insert(Note("Title3", "Description3", "15.05.2020", 1))
                noteDao.insert(Note("Title4", "Description4", "15.05.2020", 1))
                return null
            }
        }

    }

}