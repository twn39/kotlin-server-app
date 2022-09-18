package kotlinapp.repository

import kotlinapp.model.User
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.format.DateTimeFormatter

object UserRepo {

    fun findAll(): List<Map<String, Any>> {
        val users = transaction {
            User.selectAll().map {
                mapOf(
                    "id" to it[User.id],
                    "username" to it[User.username],
                    "avatar" to it[User.avatar],
                    "email" to it[User.email],
                    "created_at" to it[User.createAt].format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")),
                    "updated_at" to it[User.updatedAt].format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")),
                )
            }
        }
        return users
    }
}