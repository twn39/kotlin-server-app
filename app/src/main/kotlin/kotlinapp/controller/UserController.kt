package kotlinapp.controller

import io.javalin.http.Context
import kotlinapp.model.User
import kotlinapp.plugin.getRedisConnection
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


data class CreateBodyParams(
    val username: String = "",
    val email: String = "",
    val avatar: String = "",
    val password: String = "",
)

object UserController {
    fun showAll(ctx: Context) {
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
        ctx.json(mapOf(
            "code" to 0,
            "data" to users,
            "msg" to "ok"
        ))
    }

    fun create(ctx: Context) {
        val bodyParams = ctx.bodyValidator<CreateBodyParams>()
            .check({ it.username.isNotEmpty() && it.username.length < 40 }, "Username is required and max length is less than 40.")
            .check({ it.email.isNotEmpty() && it.email.matches(Regex("""^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*""")) }, "Email is invalid.")
            .check({it.password.isNotEmpty() && it.password.length >= 8 && it.password.length <= 30}, "Password is required and length between 8 and 30.")
            .get()

        transaction {
            User.insert {
                it[username] = bodyParams.username
                it[password] =  Argon2PasswordEncoder().encode(bodyParams.password)
                it[avatar] = bodyParams.avatar
                it[email] = bodyParams.email
                it[createAt] = LocalDateTime.now()
                it[updatedAt] = LocalDateTime.now()
            }
        }
        ctx.json(mapOf(
            "code" to 0,
            "data" to bodyParams,
            "msg" to "ok",
        ))
    }
}