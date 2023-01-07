package kotlinapp

import io.javalin.Javalin
import io.javalin.validation.ValidationException
import kotlinapp.controller.UserController
import kotlinapp.module.LettuceModule
import kotlinapp.module.UserModule
import kotlinapp.plugin.ExposedPlugin
import org.kodein.di.DI
import org.kodein.di.instance


fun main() {
    val app = Javalin.create {config ->
        config.http
        config.routing
        config.jetty
        config.compression.gzipOnly(6)
        config.requestLogger
        config.plugins.register(ExposedPlugin())
    }.start(7000)

    app.exception(ValidationException::class.java) { err, ctx ->
        ctx.json(mapOf(
            "code" to 10011,
            "data" to "",
            "msg" to err.errors,
        ))
    }

    val kodein = DI {
        import(UserModule.module)
        import(LettuceModule.module)
    }

    val userCtrl: UserController by kodein.instance()

    app.get("/") { ctx ->
        ctx.json(
            mapOf(
                "code" to 0,
                "data" to "Javelin 5 server.",
                "msg" to "ok"
            )
        )
    }
    app.get("/users", userCtrl::showAll)
    app.post("/users", userCtrl::create)
}