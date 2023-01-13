package com.example.spendless.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = arrayOf(ExpenseModel::class), version = 1, exportSchema = false)
abstract class ExpenseRoomDatabase : RoomDatabase() {

    abstract fun expenseDao(): DAO


    companion object {
        @Volatile
        private var INSTANCE: ExpenseRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): ExpenseRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExpenseRoomDatabase::class.java,
                    "expense_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}