package com.andreising.myawesomeapplication.data.room.repository.implementation

import com.andreising.myawesomeapplication.data.room.dao.BurgerItemDao
import com.andreising.myawesomeapplication.data.room.entities.BurgerItemRoom
import com.andreising.myawesomeapplication.data.room.repository.BurgerListRoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BurgerListRoomRepositoryImpl(private val dao: BurgerItemDao): BurgerListRoomRepository {
    override suspend fun deleteAll() {
        dao.deleteAll()
    }

    override suspend fun insertList(list: List<BurgerItemRoom>) {
        list.forEach {
            withContext(Dispatchers.IO){
                dao.insertItem(it)
            }
        }
    }

    override suspend fun getAllItems(): List<BurgerItemRoom> {
        return dao.getAllItems()
    }
}