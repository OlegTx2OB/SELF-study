package com.example.coin.ui.incomesOutcomes

import com.example.coin.R.layout.fragment_incomes_outcomes
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.coin.databinding.FragmentIncomesOutcomesBinding


class IncomesOutcomesFragment : Fragment()
{

    private var _binding: FragmentIncomesOutcomesBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        val rootView: View = inflater.inflate(fragment_incomes_outcomes, container, false)


        return rootView
    }
}