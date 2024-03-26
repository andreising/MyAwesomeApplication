package com.andreising.myawesomeapplication.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andreising.myawesomeapplication.data.room.dao.BurgerItemDao
import com.andreising.myawesomeapplication.data.room.entities.BurgerItemRoom

@Database(
    entities = [BurgerItemRoom::class],
    version = 1
)
abstract class MainDataBase: RoomDatabase() {
    abstract val burgerItemDao: BurgerItemDao
}