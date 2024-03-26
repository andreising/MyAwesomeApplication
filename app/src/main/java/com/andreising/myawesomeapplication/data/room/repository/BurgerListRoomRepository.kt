package com.andreising.myawesomeapplication.data.room.repository

import com.andreising.myawesomeapplication.data.room.entities.BurgerItemRoom

interface BurgerListRoomRepository {
    suspend fun deleteAll()

    suspend fun insertList(list: List<BurgerItemRoom>)

    suspend fun getAllItems(): List<BurgerItemRoom>
}