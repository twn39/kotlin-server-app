package kotlinapp.plugin

import io.javalin.Javalin
import io.javalin.plugin.Plugin
import kotlinapp.model.User
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class ExposedPlugin: Plugin {
    override fun apply(app: Javalin) {
        Database.connect("jdbc:h2:./db.h2", "org.h2.Driver")

        transaction {
            SchemaUtils.create(User)
        }
    }
}