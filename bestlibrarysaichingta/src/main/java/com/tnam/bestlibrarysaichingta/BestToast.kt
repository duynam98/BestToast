package com.tnam.bestlibrarysaichingta

import android.content.Context
import android.widget.Toast

class BestToast {

    companion object{
        fun toastLib(context: Context?){
            Toast.makeText(context, "Best Lib Toast Android", Toast.LENGTH_LONG).show()
        }
    }

}