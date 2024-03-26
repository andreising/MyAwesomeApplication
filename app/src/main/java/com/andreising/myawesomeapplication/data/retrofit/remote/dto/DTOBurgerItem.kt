package com.andreising.myawesomeapplication.data.retrofit.remote.dto

import com.andreising.myawesomeapplication.domain.model.BurgerItem

data class DTOBurgerItem(
    val desc: String,
    val id: Int,
    val images: List<Image>,
    val ingredients: List<Ingredient>,
    val name: String,
    val price: Double,
    val veg: Boolean
)

fun DTOBurgerItem.toDomainItem(): BurgerItem {
    return BurgerItem(
        name = this.name, desc = this.desc, image = this.images[0].sm, price = this.price
    )
}