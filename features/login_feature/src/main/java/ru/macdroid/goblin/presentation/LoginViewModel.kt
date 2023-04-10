package ru.macdroid.goblin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.macdroid.goblin.domain.LoginEvents
import ru.macdroid.goblin.domain.LoginFeatureImpl
import ru.macdroid.goblin.domain.reduce.LoginEFFHandler
import javax.inject.Inject

@HiltViewModel
class LoginViewModel  @Inject constructor(
    handler: LoginEFFHandler,
    val feature: LoginFeatureImpl
) : ViewModel() {

    init {
        feature.listen(viewModelScope, handler)
    }

    fun onEvent(event: LoginEvents) {
        feature.mutate(event)
    }

}