package com.g.vicprojetointegrador.presentation.model

//Director or Actors
data class Person(
    val id: Int = 0,
    val name: String,
    val profilePath: String? = null,
    val role: String,
)