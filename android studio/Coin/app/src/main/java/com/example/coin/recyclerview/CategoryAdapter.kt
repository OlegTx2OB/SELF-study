package com.example.coin.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.coin.R
import com.example.coin.data.Category
import com.example.coin.data.Note
import com.example.coin.databinding.RvCategoryBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {

    private val _ldDeleteCategoryFromRoom = MutableLiveData<Category>()
    val ldDeleteCategoryFromRoom: LiveData<Category> = _ldDeleteCategoryFromRoom

    private val categoryArray = ArrayList<Category>()

    class CategoryHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RvCategoryBinding.bind(view)

        fun bind(category: Category) = with(binding) {

            val resourceId = binding.root.resources.getIdentifier(
                category.imageName, "drawable", binding.root.context.packageName
            )

            categoryText.text = category.text
            icon.setImageResource(resourceId)
            foreground.setCardBackgroundColor(category.color!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_category, parent, false)
        return CategoryHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(categoryArray[position])
    }

    override fun getItemCount(): Int {
        return categoryArray.size
    }

    fun removeAt(position: Int) {
        _ldDeleteCategoryFromRoom.value = categoryArray[position]
        categoryArray.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addCategories(categories: List<Category>) {
        categoryArray.clear()
        categoryArray.addAll(categories.reversed())
        notifyDataSetChanged()
    }

}