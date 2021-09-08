package com.example.testwallet.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testwallet.TransactionCategories

@Entity(tableName = "transactions_table")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    var transactionId: Long = 0L,

    @ColumnInfo(name = "transaction_amount")
    val transactionAmount: Double = -1.0,

    @ColumnInfo(name = "transaction_timestamp")
    var transactionTimestamp: Long = -1,

    @ColumnInfo(name = "transaction_category")
    var transactionCategory: String = TransactionCategories.DEFAULT.toString()
)