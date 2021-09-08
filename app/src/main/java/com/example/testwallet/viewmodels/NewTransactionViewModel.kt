package com.example.testwallet.viewmodels

import android.util.Log
import android.widget.ArrayAdapter
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testwallet.R
import com.example.testwallet.TransactionCategories
import com.example.testwallet.database.Transaction
import com.example.testwallet.database.TransactionDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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