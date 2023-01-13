package com.example.spendless.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.spendless.R
import com.example.spendless.databinding.ItemExpenseBinding

class ExpenseAdapter : ListAdapter<ExpenseItem, ExpenseAdapter.ExpenseViewHolder>(ContactsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        return ExpenseViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class ExpenseViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemExpenseBinding.bind(item)

        fun bind(expense: ExpenseItem) = with (binding) {
            tvExpenseAmount.text = expense.amount.toString()
            tvExpenseDate.text = expense.date
        }

        companion object {
            fun create(parent: ViewGroup): ExpenseViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_expense, parent, false)
                return ExpenseViewHolder(view)
            }
        }
    }

    class ContactsComparator : DiffUtil.ItemCallback<ExpenseItem>() {
        override fun areItemsTheSame(oldItem: ExpenseItem, newItem: ExpenseItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ExpenseItem, newItem: ExpenseItem): Boolean {
            return oldItem.date == newItem.date
        }
    }
}