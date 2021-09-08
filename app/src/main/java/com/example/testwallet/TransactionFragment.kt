package com.example.testwallet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.testwallet.database.TransactionDatabase
import com.example.testwallet.databinding.TransactionFragmentBinding
import com.example.testwallet.viewmodels.TransactionViewModel
import com.example.testwallet.viewmodels.TransactionViewModelFactory

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class TransactionFragment : Fragment() {

    lateinit var binding: TransactionFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<TransactionFragmentBinding>(
            inflater, R.layout.transaction_fragment, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = TransactionDatabase.getInstance(application).transactionDatabaseDao

        val viewModelFactory = TransactionViewModelFactory(dataSource)

        val transactionViewModel = ViewModelProvider(this, viewModelFactory).get(TransactionViewModel::class.java)

        binding.transactionViewModel = transactionViewModel

        binding.confirmButton.setOnClickListener {
            if (binding.amountText.text?.isNotEmpty()!!) {
                val amount = binding.amountText.text.toString().toDouble()
                val category = stringToTransactionCategory(binding.categoryDropdown.text.toString())
                transactionViewModel.onConfirmTransaction(amount, category)
                findNavController().navigate(R.id.action_TransactionFragment_to_HomeFragment)
            }
        }

        binding.categoryDropdown.setOnItemClickListener { parent, _, position, _ ->
            val item = parent.getItemAtPosition(position).toString()
        }

        // Exposed dropdown menu
        val arrayAdapter = ArrayAdapter<TransactionCategories>(requireContext(), R.layout.transaction_category_item,
                TransactionCategories.values())
        binding.categoryDropdown.setAdapter(arrayAdapter)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        val arrayAdapter = ArrayAdapter<TransactionCategories>(requireContext(), R.layout.transaction_category_item, TransactionCategories.values())
        binding.categoryDropdown.setAdapter(arrayAdapter)
    }
}