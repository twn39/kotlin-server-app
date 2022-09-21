package kotlinapp.controller

import io.javalin.http.Context
import kotlinapp.model.User
import kotlinapp.repository.UserRepo
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder
import java.time.LocalDateTime


data class CreateBodyParams(
    val username: String = "",
    val email: String = "",
    val avatar: String = "",
    val password: String = "",
)

class UserController(private val userRepo: UserRepo) {

    fun showAll(ctx: Context) {
        val users = userRepo.findAll()
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
            .check({ it.password.isNotEmpty() && it.password.length >= 8 && it.password.length <= 30 }, "Password is required and length between 8 and 30.")
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