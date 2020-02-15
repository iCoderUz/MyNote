package icoder.mynote.uz.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import icoder.mynote.uz.model.Note
import icoder.mynote.uz.repository.NoteRepository

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NoteRepository = NoteRepository(application)
    private val allNotes: LiveData<List<Note>>

    init {
        allNotes = repository.getAllNotes()
    }

    fun insert(note: Note){
        repository.insert(note)
    }

    fun update(note: Note){
        repository.update(note)
    }

    fun delete(note: Note){
        repository.delete(note)
    }

    fun deleteAllNotes(){
        repository.deleteAllNotes()
    }

    fun getAllNotes(): LiveData<List<Note>>{
        return allNotes
    }
}