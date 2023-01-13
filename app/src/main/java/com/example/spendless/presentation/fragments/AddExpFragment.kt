package com.example.spendless.presentation.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.spendless.R
import com.example.spendless.data.ExpenseModel
import com.example.spendless.presentation.viewmodels.MainViewModel
import com.example.spendless.databinding.FragmentAddExpBinding
import com.example.spendless.presentation.SpendlessApp
import com.example.spendless.presentation.viewmodels.MainViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.util.*

class AddExpFragment : BottomSheetDialogFragment() {

    private lateinit var bindingAddExp: FragmentAddExpBinding
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((activity?.application as SpendlessApp).repository)
    }
    private var expense: Int = 0
    @SuppressLint("SimpleDateFormat")
    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    private val currentDate: String = sdf.format(Date())


    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingAddExp = FragmentAddExpBinding.inflate(inflater)

        return bindingAddExp.root
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        
        bindingAddExp.btApply.setOnClickListener {

            mainViewModel.loggingExpense()

            val editTextValue = bindingAddExp.editText.text.toString()


            if (editTextValue.isNotEmpty()) {
                expense = bindingAddExp.editText.text.toString().toInt()
                if ( editTextValue.toInt() in 1..Int.MAX_VALUE) {
                    if (mainViewModel.totalExpense.value == null) {
                        mainViewModel.totalExpense.value = expense
                        mainViewModel.insert(ExpenseModel(editTextValue.toInt(), currentDate))
                    } else {
                        mainViewModel.totalExpense.value = mainViewModel.totalExpense.value?.plus(expense)
                        mainViewModel.insert(ExpenseModel(editTextValue.toInt(), currentDate))
                    }
                    Toast.makeText(context, "Yep, expense applied!", Toast.LENGTH_SHORT).show()
                    dismiss()
                }
            } else {
                Toast.makeText(context, "Just type your last expense amount", Toast.LENGTH_SHORT).show()
            }
        }

    }

}