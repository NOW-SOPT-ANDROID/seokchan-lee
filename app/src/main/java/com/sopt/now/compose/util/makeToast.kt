package com.sopt.now.compose.util

import android.content.Context
import android.widget.Toast

fun makeToastMessage(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}