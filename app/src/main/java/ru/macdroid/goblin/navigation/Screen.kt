package ru.macdroid.goblin.navigation

sealed class Screen(val route: String) {
    object Login : Screen(Route.LOGIN)

}

object Route {
    const val ROOT = "root"
    const val LOGIN = "login"
}