package com.g.vicprojetointegrador.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    val id : Int = 0,
    val name : String = ""
) : Parcelable
