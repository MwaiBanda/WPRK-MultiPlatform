package com.muse.wprk.core.exts

import androidx.compose.ui.graphics.Color

fun Color.Companion.parse(colorString: String): Color = Color(color = android.graphics.Color.parseColor(colorString))