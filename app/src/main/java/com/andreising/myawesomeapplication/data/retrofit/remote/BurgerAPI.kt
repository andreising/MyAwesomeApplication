package com.andreising.myawesomeapplication.data.retrofit.remote

import com.andreising.myawesomeapplication.data.retrofit.remote.dto.DTOBurgerList
import retrofit2.http.GET

interface BurgerAPI {
    @GET("burgers")
    suspend fun getBurgerList(): DTOBurgerList
}