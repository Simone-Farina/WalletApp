package com.example.testwallet

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.testwallet.database.Transaction

@BindingAdapter("transactionAmountFormatted")
fun TextView.setTransactionAmount(item: Transaction?) {
    item?.let {
        text = convertAmountToFormatted(item.transactionAmount, context.resources)
    }
}

@BindingAdapter("transactionImage")
fun ImageView.setTransactionImage(item: Transaction) {
    setImageResource(when (item.transactionCategory) {
        TransactionCategories.DEFAULT.category -> R.drawable.ic_home
        else -> R.drawable.ic_person
    })
}