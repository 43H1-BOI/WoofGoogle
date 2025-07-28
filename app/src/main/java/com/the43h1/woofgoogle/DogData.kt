package com.the43h1.woofgoogle

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class DogData(
    @DrawableRes val image: Int,
    @StringRes val name: Int,
    var hobbies: MutableList<String>,
    var age: Int
)

class Data {
    fun getData(): List<DogData> {
        return listOf(
            DogData(R.drawable.dog_1_koda, R.string.dog_name_1, R.string.dog_description_1, 8),
            DogData(R.drawable.dog_2_lola, R.string.dog_name_2, R.string.dog_description_2, 11),
            DogData(R.drawable.dog_3_frankie, R.string.dog_name_3, R.string.dog_description_3, 7),
            DogData(R.drawable.dog_4_nox, R.string.dog_name_4, R.string.dog_description_4, 6),
            DogData(R.drawable.dog_5_faye, R.string.dog_name_5, R.string.dog_description_5, 1),
            DogData(R.drawable.dog_6_bella, R.string.dog_name_6, R.string.dog_description_6, 2),
            DogData(R.drawable.dog_7_moana, R.string.dog_name_7, R.string.dog_description_7, 3),
            DogData(R.drawable.dog_8_tzeitel, R.string.dog_name_8, R.string.dog_description_8, 13),
            DogData(R.drawable.dog_9_leroy, R.string.dog_name_9, R.string.dog_description_9, 10)
        )
    }
}