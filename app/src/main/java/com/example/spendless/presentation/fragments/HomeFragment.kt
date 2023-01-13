package com.example.spendless.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.spendless.databinding.FragmentHomeBinding
import com.example.spendless.presentation.viewmodels.MainViewModel
import com.example.spendless.presentation.SpendlessApp
import com.example.spendless.presentation.viewmodels.MainViewModelFactory

class HomeFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((activity?.application as SpendlessApp).repository)
    }
    private lateinit var bindingMainFrag : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingMainFrag = FragmentHomeBinding.inflate(inflater)
        return bindingMainFrag.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.getAllExpensesSum()
        mainViewModel.totalExpense.observe(viewLifecycleOwner) {
            bindingMainFrag.tvExpValue.text = mainViewModel.totalExpense.value.toString()
        }

        bindingMainFrag.btAdd.setOnClickListener {
            val dialog = AddExpFragment()
            dialog.show(requireActivity().supportFragmentManager, "addExp")
        }

        bindingMainFrag.cardTotalExp.setOnClickListener {
            val dialog = HistoryFragment()
            dialog.show(requireActivity().supportFragmentManager, "totalExp")
        }
    }
}