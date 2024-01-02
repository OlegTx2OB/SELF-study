package com.example.coin.ui.IncomesOutcomes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.coin.databinding.FragmentIncomesOutcomesBinding

class IncomesOutcomesFragment : Fragment()
{

    private var _binding: FragmentIncomesOutcomesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val incomesOutcomesViewModel =
            ViewModelProvider(this).get(IncomesOutcomesViewModel::class.java)

        _binding = FragmentIncomesOutcomesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        incomesOutcomesViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}