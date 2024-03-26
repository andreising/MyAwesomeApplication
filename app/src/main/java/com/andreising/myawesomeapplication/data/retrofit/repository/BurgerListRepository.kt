package com.andreising.myawesomeapplication.data.retrofit.repository

import com.andreising.myawesomeapplication.domain.model.BurgerItem

interface BurgerListRepository {
    suspend fun getBurgerList(): List<BurgerItem>
}