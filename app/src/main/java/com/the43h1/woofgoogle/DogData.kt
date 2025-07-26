package com.the43h1.woofgoogle

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class DogData(
    @DrawableRes val image: Int,
    @StringRes val name: Int,
    var hobbies: MutableList<String>,
    var age: Int
)