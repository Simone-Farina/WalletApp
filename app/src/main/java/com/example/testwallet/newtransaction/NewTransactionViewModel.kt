package com.example.testwallet.newtransaction

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testwallet.TransactionCategories
import com.example.testwallet.database.Transaction
import com.example.testwallet.database.TransactionDatabaseDao
import kotlinx.coroutines.launch

class NewTransactionViewModel(private val database: TransactionDatabaseDao): ViewModel() {

    fun onConfirmTransaction(amount: Double, category: TransactionCategories) {
        viewModelScope.launch {
            val newTransaction = Transaction(
                    transactionAmount = amount,
                    transactionCategory = category.toString())

            Log.d("onConfirmTransaction", "Created new transaction: transactionAmount = $amount, transactionCategory = $category")

            insert(newTransaction)
        }
    }

    private suspend fun insert(transaction: Transaction) {
        database.insert(transaction)
    }
}