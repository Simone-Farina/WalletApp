package com.example.testwallet.transactionlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.testwallet.database.TransactionDatabaseDao

class TransactionListViewModel(
        val database: TransactionDatabaseDao,
        application: Application): AndroidViewModel(application) {

    val transactions = database.getAllTransactions()

}