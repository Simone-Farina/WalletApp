package com.example.testwallet.home

import androidx.lifecycle.*
import com.example.testwallet.database.TransactionDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val database: TransactionDatabaseDao): ViewModel() {

    private val transactionsAmount = database.getAllAmounts()

    val transactionsAmountString = Transformations.map(transactionsAmount) { transactions ->
        transactions.sum().toString()
    }

    fun onClearTransactionData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database.clear()
            }
        }
    }
}