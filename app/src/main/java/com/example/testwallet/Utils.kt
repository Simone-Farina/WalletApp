package com.example.testwallet

import android.content.res.Resources

fun stringToTransactionCategory(category: String): TransactionCategories {
    return TransactionCategories.values().find {
        it.category == category
    } ?: TransactionCategories.DEFAULT
}

fun convertAmountToFormatted(amount: Double, res: Resources): String {
    return res.getString(R.string.amount_string, amount.toFloat())
}