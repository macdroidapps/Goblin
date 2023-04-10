package ru.macdroid.goblin.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.smartro.installer.IEffHandler
import ru.smartro.installer.IFeature
import ru.smartro.installer.domain.reduce.reduce

import javax.inject.Inject
class LoginFeatureImpl @Inject constructor() : IFeature<LoginState, LoginEvents, LoginEffects> {

    private fun initialState(): LoginState = LoginState()
    private fun initialEffects(): Set<LoginEffects> = emptySet()

    private val mutations: MutableSharedFlow<LoginEvents> = MutableSharedFlow()
    private val _state: MutableStateFlow<LoginState> = MutableStateFlow(initialState())
    override val state = _state.asStateFlow()

    private lateinit var _scope: CoroutineScope

    override fun mutate(mutation: LoginEvents) {
        _scope.launch {
            mutations.emit(mutation)
        }
    }

    override fun listen(
        scope: CoroutineScope,
        handler: IEffHandler<LoginEffects, LoginEvents>
    ) {
        _scope = scope
        _scope.launch {
            mutations
                .onEach {
//                    Log.d("happy", "MUTATION: $it")
                }
                .scan(initialState() to initialEffects()) { (s, _), m ->
                    s.reduce(m)
                }
                .collect { (s, effs) ->
                    _state.emit(s)
                    effs.forEach { eff ->
                        _scope.launch {
                            handler.handle(eff) { event ->
                                mutate(event)
                            }
                        }
                    }
                }
        }
    }
}




