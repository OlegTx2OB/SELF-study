package com.example.coin.view

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import com.example.coin.R
import com.example.coin.databinding.FragmentAddCategoryBinding
import com.example.coin.databinding.FragmentAddNoteBinding
import com.example.coin.viewmodel.AddCategoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCategoryFragment : Fragment(R.layout.fragment_add_category) {
    private val mVM: AddCategoryViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentAddCategoryBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_category, container, false)

        setupClickListeners(binding, mVM)
        setupObservers(binding, mVM)

        return binding.root
    }

    private fun setupObservers(binding: FragmentAddCategoryBinding, mVM: AddCategoryViewModel) {

    }

    private fun setupClickListeners(
        binding: FragmentAddCategoryBinding,
        mVM: AddCategoryViewModel
    ) {
        val x =5
        colorCardsListener(binding, mVM)
    }

    private fun colorCardsListener( //todo не работает с нихуя
        binding: FragmentAddCategoryBinding,
        mVM: AddCategoryViewModel
    ) {
        val container = binding.cardviewColorsTest.colorCardsParent
        val colorCards = ArrayList<CardView>()

        for (i in 0 until container.childCount) {
            val child = container.getChildAt(i)
            colorCards.add(child as CardView)
        }
        val clickListener = View.OnClickListener {
            it as CardView

            for (colorCard in colorCards) {
               colorCard.getChildAt(0).visibility = View.INVISIBLE
            }
            it.getChildAt(0).visibility = View.VISIBLE
        }
        for (child in colorCards) {
            child.setOnClickListener(clickListener)
        }
    }
}