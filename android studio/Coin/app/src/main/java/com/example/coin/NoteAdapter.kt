package com.example.coin

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.coin.data.Note
import com.example.coin.databinding.HistoryNoteBinding

class NoteAdapter(private val context: Context) : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {
    
    inner class NoteHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = HistoryNoteBinding.bind(view)

        fun bind(note: Note) {
            val imageName = "my_image"
            val resourceId = context.resources.getIdentifier(imageName, "drawable", context.packageName)
            binding.imageView.setImageResource(resourceId)
        }
    }
}