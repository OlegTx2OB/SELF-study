package com.example.coin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coin.data.Note
import com.example.coin.databinding.HistoryNoteBinding
import java.time.LocalDate

class NoteAdapter() : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    private val noteArray = ArrayList<Note>()
    class NoteHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = HistoryNoteBinding.bind(view)

        fun bind(note: Note) = with(binding){
            val resourceId = binding.root.resources.getIdentifier(note.imageName, "drawable", binding.root.context.packageName)
            val date = LocalDate.ofEpochDay(note.epochDay!!)

            tvDate.text = "${date.dayOfMonth}.${date.monthValue}.${date.year}"
            binding.imageView.setImageResource(resourceId)
            tvCategoryName.text = note.categoryName
            tvAmount.text = note.amount.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_note, parent, false)
        return NoteHolder(view)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        holder.bind(noteArray[position])
    }
    override fun getItemCount(): Int {
        return noteArray.size
    }

    fun addNote(note: Note) {
        noteArray.add(note)
        notifyItemInserted(noteArray.size)
    }

//    fun updateData(notes: List<Note>) {
//        noteArray.clear()
//        noteArray.addAll(notes)
//        notifyDataSetChanged()
//    }
}