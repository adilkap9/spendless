package com.example.spendless.domain.db

import androidx.annotation.WorkerThread
import com.example.spendless.data.DAO
import com.example.spendless.data.ExpenseModel
import kotlinx.coroutines.flow.Flow

class ExpenseRepository(private val expenseDao: DAO) {

    val allExpenses: Flow<List<ExpenseModel>> = expenseDao.getSortedExpenses()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(expense: ExpenseModel) {
        expenseDao.createExpense(expense)
    }



}
