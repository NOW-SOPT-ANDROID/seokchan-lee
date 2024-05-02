package com.sopt.now.compose.util

import android.content.Intent
import android.os.Build

fun <T> Intent.getParcelable(key: String, c: Class<T>) = when {
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getParcelableExtra(key, c)
    else -> getParcelableExtra(key)
}