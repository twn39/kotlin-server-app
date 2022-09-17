package kotlinapp

import io.javalin.Javalin
import io.javalin.core.validation.ValidationException
import kotlinapp.controller.UserController
import kotlinapp.plugin.ExposedPlugin
import kotlinapp.plugin.LettucePlugin


fun main() {
    val app = Javalin.create {config -> 
        config.enableCorsForAllOrigins()
        config.enableDevLogging()
        config.registerPlugin(ExposedPlugin())
        config.registerPlugin(LettucePlugin())
    }.start(7000)

    app.exception(ValidationException::class.java) { err, ctx ->
        ctx.json(mapOf(
            "code" to 10011,
            "data" to "",
            "msg" to err.errors,
        ))
    }

    app.get("/users", UserController::showAll)
    app.post("/users", UserController::create)
}