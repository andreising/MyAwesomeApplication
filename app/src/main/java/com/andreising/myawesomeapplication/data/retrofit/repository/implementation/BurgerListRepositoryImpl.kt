package com.andreising.myawesomeapplication.data.retrofit.repository.implementation

import com.andreising.myawesomeapplication.data.retrofit.remote.BurgerAPI
import com.andreising.myawesomeapplication.data.retrofit.remote.dto.toDomainItem
import com.andreising.myawesomeapplication.data.retrofit.repository.BurgerListRepository
import com.andreising.myawesomeapplication.domain.model.BurgerItem

class BurgerListRepositoryImpl(private val api: BurgerAPI): BurgerListRepository {
    override suspend fun getBurgerList(): List<BurgerItem> {
        return api.getBurgerList().map {
            it.toDomainItem()
        }
    }
}