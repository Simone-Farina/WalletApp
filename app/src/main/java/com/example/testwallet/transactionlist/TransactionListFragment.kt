package com.example.testwallet.transactionlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testwallet.R
import com.example.testwallet.database.TransactionDatabase
import com.example.testwallet.databinding.TransactionListFragmentBinding


class TransactionListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: TransactionListFragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.transaction_list_fragment, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = TransactionDatabase.getInstance(application).transactionDatabaseDao

        val viewModelFactory = TransactionListViewModelFactory(dataSource, application)

        val transactionListViewModel = ViewModelProvider(this, viewModelFactory)
                .get(TransactionListViewModel::class.java)

        binding.transactionListViewModel = transactionListViewModel

        // RecyclerView adapter
        val adapter = TransactionAdapter()
        binding.transactionList.adapter = adapter

        transactionListViewModel.transactions.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.lifecycleOwner = this

        return binding.root
    }
}