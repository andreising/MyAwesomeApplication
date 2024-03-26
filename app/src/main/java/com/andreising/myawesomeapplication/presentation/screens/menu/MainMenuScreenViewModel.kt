package com.andreising.myawesomeapplication.presentation.screens.menu

import androidx.lifecycle.ViewModel
import com.andreising.myawesomeapplication.domain.usecases.GetBurgerListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainMenuScreenViewModel@Inject constructor(
    useCase: GetBurgerListUseCase
): ViewModel() {
    val data = useCase.invoke()
}