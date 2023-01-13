package com.example.spendless.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.spendless.presentation.adapters.ExpenseItem

@Entity(tableName = "expense_table")
class ExpenseModel (
    val amount: Int,
    @ColumnInfo(name = "date") val date: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
        )

fun ExpenseModel.toExpenseItem(): ExpenseItem {
    return ExpenseItem(amount = amount, date = date)
}