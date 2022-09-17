package kotlinapp.model

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object User: Table("users") {
    val id = integer("id").autoIncrement()
    val username = varchar("username", 40).uniqueIndex()
    val email = varchar("email", 60)
    val avatar = varchar("avatar", 255)
    val password = varchar("password", 120)
    val createAt = datetime("created_at")
    val updatedAt = datetime("update_at")

    override val primaryKey = PrimaryKey(id, name = "PK_User_ID") // name is optional here
}

