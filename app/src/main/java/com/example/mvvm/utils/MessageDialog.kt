package com.example.mvvm.utils

import android.app.Activity
import com.example.mvvm.R
import com.example.mvvm.databinding.MessageDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class MessageDialog(activity: Activity) {
    val inflater = activity.layoutInflater
    val binding = MessageDialogBinding.inflate(inflater)
    val bottomSheetDialog = BottomSheetDialog(activity)

    fun showMessage(){
        bottomSheetDialog.setContentView(binding.root)
        bottomSheetDialog.show()
    }

    fun isDismiss(){
        bottomSheetDialog.setContentView(binding.root)
        bottomSheetDialog.dismiss()
    }
}