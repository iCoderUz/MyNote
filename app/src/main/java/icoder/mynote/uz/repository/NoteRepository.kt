package icoder.mynote.uz.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import icoder.mynote.uz.model.Note
import icoder.mynote.uz.room.NoteDao
import icoder.mynote.uz.room.NoteDatabase

class NoteRepository {
    private lateinit var noteDao: NoteDao
    private lateinit var allNotes: LiveData<List<Note>>

    fun NoteRepository(application: Application) {
        val database: NoteDatabase = NoteDatabase.getInstance(application)!!
        noteDao = database.noteDao()!!
        allNotes = noteDao.getAllNotes()
    }

    fun insert(note: Note) {
        InsertNoteAsyncTask(noteDao).execute(note)
    }

    fun update(note: Note) {
        UpdateNoteAsyncTask(noteDao).execute(note)
    }

    fun delete(note: Note) {
        DeleteNoteAsyncTask(noteDao).execute(note)
    }

    fun deleteAllNotes() {
        DeleteAllNoteAsyncTask(noteDao).execute()
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }

    companion object {

        private class InsertNoteAsyncTask(private val noteDao: NoteDao) :
            AsyncTask<Note, Void, Void>() {
            override fun doInBackground(vararg params: Note?): Void? {
                noteDao.insert(params[0]!!)
                return null
            }
        }

        private class UpdateNoteAsyncTask(private val noteDao: NoteDao) :
            AsyncTask<Note, Void, Void>() {
            override fun doInBackground(vararg params: Note?): Void? {
                noteDao.update(params[0]!!)
                return null
            }
        }

        private class DeleteNoteAsyncTask(private val noteDao: NoteDao) :
            AsyncTask<Note, Void, Void>() {
            override fun doInBackground(vararg params: Note?): Void? {
                noteDao.delete(params[0]!!)
                return null
            }
        }

        private class DeleteAllNoteAsyncTask(private val noteDao: NoteDao) :
            AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void?): Void? {
                noteDao.deleteAll()
                return null
            }
        }

    }

}