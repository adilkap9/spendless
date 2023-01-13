package com.example.spendless.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spendless.R
import com.example.spendless.data.toExpenseItem
import com.example.spendless.databinding.FragmentHistoryBinding
import com.example.spendless.presentation.SpendlessApp
import com.example.spendless.presentation.adapters.ExpenseAdapter
import com.example.spendless.presentation.adapters.MarginItemDecoration
import com.example.spendless.presentation.viewmodels.HistoryViewModel
import com.example.spendless.presentation.viewmodels.HistoryViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class HistoryFragment : BottomSheetDialogFragment() {

    private val viewModel: HistoryViewModel by viewModels {
        HistoryViewModelFactory((activity?.application as SpendlessApp).repository)
    }

    private lateinit var bindingHistory : FragmentHistoryBinding

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingHistory = FragmentHistoryBinding.inflate(inflater)
        return bindingHistory.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = bindingHistory.recyclerview
        val adapter = ExpenseAdapter()
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.itemMargin))
        )
        recyclerView.layoutManager = LinearLayoutManager(activity)

        viewModel.allExpenses.observe(viewLifecycleOwner) { expenses ->
            expenses?.let { items -> adapter.submitList(items.map { it.toExpenseItem() }) }
        }
    }
}