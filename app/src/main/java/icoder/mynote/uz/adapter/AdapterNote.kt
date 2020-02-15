package icoder.mynote.uz.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import icoder.mynote.uz.R
import icoder.mynote.uz.model.Note
import kotlinx.android.synthetic.main.note_item.view.*

class AdapterNote(private var notes: List<Note>, private val clickListener: (Note, View) -> Unit) :
    RecyclerView.Adapter<AdapterNote.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(note: Note, clickListener: (Note, View) -> Unit) {
            itemView.title.text = note.title
            itemView.description.text = note.description
            itemView.date.text = note.date
            itemView.prioritry.text = note.priority.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterNote.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false))
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun setNotes(notes: List<Note>){
        this.notes = notes
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: AdapterNote.ViewHolder, position: Int) {
        holder.onBind(notes[position], clickListener)
    }
}