package com.andreising.myawesomeapplication.domain.usecases

import com.andreising.myawesomeapplication.data.retrofit.repository.BurgerListRepository
import com.andreising.myawesomeapplication.data.room.entities.BurgerItemRoom
import com.andreising.myawesomeapplication.data.room.repository.BurgerListRoomRepository
import com.andreising.myawesomeapplication.domain.model.BurgerItem
import com.andreising.myawesomeapplication.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetBurgerListUseCase @Inject constructor(
    private val retrofitRepository: BurgerListRepository,
    private val roomRepository: BurgerListRoomRepository
) {
    operator fun invoke(): Flow<Resource<List<BurgerItem>>> = flow {
        try {
            emit(Resource.Loading<List<BurgerItem>>())
            val burgerList = retrofitRepository.getBurgerList()
            roomRepository.deleteAll()
            roomRepository.insertList(burgerList.map {
                BurgerItemRoom(
                    null,
                    it.name,
                    desc = it.desc,
                    image = it.image,
                    price = it.price
                )
            })
            emit(Resource.Success<List<BurgerItem>>(burgerList))
        } catch (e: IOException) {
            val savedList = roomRepository.getAllItems()
            emit(Resource.Error<List<BurgerItem>>(data = savedList.map {
                BurgerItem(it.name, desc = it.desc, it.image, it.price)
            }, message = "Something went wrong"))
        } catch (e: HttpException) {
            val savedList = roomRepository.getAllItems()
            emit(Resource.Error<List<BurgerItem>>(data = savedList.map {
                BurgerItem(it.name, desc = it.desc, it.image, it.price)
            }, message = "More than 10 requests"))
        }
    }
}