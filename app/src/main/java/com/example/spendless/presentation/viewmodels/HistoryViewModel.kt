package com.example.spendless.presentation.viewmodels

import androidx.lifecycle.*
import com.example.spendless.data.ExpenseModel
import com.example.spendless.domain.db.ExpenseRepository
import kotlinx.coroutines.launch

class HistoryViewModel(repository: ExpenseRepository) : ViewModel() {

    val allExpenses: LiveData<List<ExpenseModel>> = repository.allExpenses.asLiveData()
}

class HistoryViewModelFactory(private val repository: ExpenseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HistoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}