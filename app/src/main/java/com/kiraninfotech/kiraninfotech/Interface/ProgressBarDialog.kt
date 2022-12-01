package com.kiraninfotech.kiraninfotech.Interface

import android.app.Activity
import android.app.Dialog
import com.kiraninfotech.kiraninfotech.R

interface ProgressBarDialog {


    companion object{
        fun openProgressBarDialog(activity: Activity) : Dialog{
            val progressBarDialog = Dialog(activity).apply {
                setContentView(R.layout.progress_bar_dialog)

            }
            return progressBarDialog
        }
    }
}