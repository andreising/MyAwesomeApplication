package com.andreising.myawesomeapplication.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "burger_item")
data class BurgerItemRoom(
    @PrimaryKey
    val id: Int? = null,
    val name: String,
    val desc: String,
    val image: String,
    val price: Double
)
