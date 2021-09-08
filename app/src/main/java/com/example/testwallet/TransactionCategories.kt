package com.example.testwallet

enum class TransactionCategories(val category: String) {
    DEFAULT("Default"),
    HOME("Home"),
    GROCERIES("Groceries"),
    CAR("Car"),
    TAXES("Taxes"),
    SHOPPING("Shopping"),
    ENTERTAINMENT("Entertainment");

    override fun toString(): String {
        return this.category
    }
}
