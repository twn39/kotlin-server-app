package kotlinapp.module

import kotlinapp.controller.UserController
import kotlinapp.repository.UserRepo
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

object UserModule {
    val module = DI.Module("User Module") {
        bindSingleton { UserRepo() }
        bindSingleton { UserController(instance()) }
    }
}