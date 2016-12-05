package com.yatatsu.expiresmemo.presentation

import android.content.Intent

fun Intent.getStringExtraOrNull(name: String): String?
 = if (hasExtra(name)) getStringExtra(name) else null