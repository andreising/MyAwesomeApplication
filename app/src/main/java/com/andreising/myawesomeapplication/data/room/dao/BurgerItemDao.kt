package com.andreising.myawesomeapplication.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andreising.myawesomeapplication.data.room.entities.BurgerItemRoom

@Dao
interface BurgerItemDao {
    @Query("DELETE FROM burger_item")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: BurgerItemRoom)

    @Query("SELECT * FROM burger_item")
    suspend fun getAllItems(): List<BurgerItemRoom>
}