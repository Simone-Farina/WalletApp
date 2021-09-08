package com.example.testwallet

fun stringToTransactionCategory(category: String): TransactionCategories {
    return TransactionCategories.values().find {
        it.category == category
    } ?: TransactionCategories.DEFAULT
}