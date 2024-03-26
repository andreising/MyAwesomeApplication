package com.andreising.myawesomeapplication.di

import android.app.Application
import androidx.room.Room
import com.andreising.myawesomeapplication.data.retrofit.instance.retrofit
import com.andreising.myawesomeapplication.data.retrofit.remote.BurgerAPI
import com.andreising.myawesomeapplication.data.retrofit.repository.BurgerListRepository
import com.andreising.myawesomeapplication.data.retrofit.repository.implementation.BurgerListRepositoryImpl
import com.andreising.myawesomeapplication.data.room.MainDataBase
import com.andreising.myawesomeapplication.data.room.repository.BurgerListRoomRepository
import com.andreising.myawesomeapplication.data.room.repository.implementation.BurgerListRoomRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    @Singleton
    fun provideMainDatabase(app: Application): MainDataBase {
        return Room.databaseBuilder(
            app,
            MainDataBase::class.java,
            "burger_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBurgerRepository(db: MainDataBase): BurgerListRoomRepository{
        return BurgerListRoomRepositoryImpl(db.burgerItemDao)
    }

    @Provides
    @Singleton
    fun provideBurgerApi(): BurgerAPI {
        return retrofit
    }

    @Provides
    @Singleton
    fun provideBurgerListRepository(api: BurgerAPI): BurgerListRepository {
        return BurgerListRepositoryImpl(api)
    }
}