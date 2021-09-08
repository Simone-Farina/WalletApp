package com.example.testwallet.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * Defines methods for using the SleepNight class with Room.
 */
@Dao
interface TransactionDatabaseDao {

    @Insert
    suspend fun insert(transaction: Transaction)

    /**
     * When updating a row with a value already set in a column,
     * replaces the old value with the new one.
     *
     * @param transaction new value to write
     */
    @Update
    suspend fun update(transaction: Transaction)

    /**
     * Selects and returns the row that matches the supplied start time, which is our key.
     *
     * @param key startTimeMilli to match
     */
    @Query("SELECT * from transactions_table WHERE transactionId = :key")
    suspend fun get(key: Long): Transaction?

    /**
     * Deletes all values from the table.
     *
     * This does not delete the table, only its contents.
     */
    @Query("DELETE FROM transactions_table")
    suspend fun clear()

    /**
     * Selects and returns all rows in the table,
     *
     * sorted by start time in descending order.
     */
    @Query("SELECT * FROM transactions_table ORDER BY transactionId DESC")
    fun getAllTransactions(): LiveData<List<Transaction>>

    /**
     * Selects and returns all amount data
     *
     * sorting is not needed
     */
    @Query("SELECT transaction_amount FROM transactions_table")
    fun getAllAmounts(): LiveData<List<Double>>

    /**
     * Selects and returns the latest night.
     */
    @Query("SELECT * FROM transactions_table ORDER BY transactionId DESC LIMIT 1")
    suspend fun getLastTransaction(): Transaction?

}