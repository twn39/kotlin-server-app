package kotlinapp

import io.javalin.Javalin
import kotlinapp.model.User
import kotlinapp.plugin.ExposedPlugin
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime


fun main() {
    val app = Javalin.create {config -> 
        config.enableCorsForAllOrigins()
        config.enableDevLogging()
        config.registerPlugin(ExposedPlugin())
    }.start(7000)

    app.get("/") { ctx ->
        val current = LocalDateTime.now()

        transaction {
            User.insert {
                it[username] = "curry"
                it[password] = "1234556"
                it[avatar] = ""
                it[email] = "twn39@163.com"
                it[createAt] = current
                it[updatedAt] = current
            }
        }
        ctx.json(mapOf(
            "code" to 0,
            "data" to null,
            "msg" to "ok"
        ))
    }
}