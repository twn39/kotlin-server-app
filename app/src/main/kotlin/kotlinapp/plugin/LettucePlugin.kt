package kotlinapp.plugin

import io.javalin.Javalin
import io.javalin.core.plugin.Plugin
import io.javalin.http.Context
import io.lettuce.core.RedisClient
import io.lettuce.core.api.sync.RedisCommands


fun getRedisConnection(ctx: Context): RedisCommands<String, String> {
    return ctx.appAttribute("plugin.redis.connection")
}

class LettucePlugin: Plugin {
    override fun apply(app: Javalin) {
        val redisClient = RedisClient.create("redis://localhost/0")
        val connection = redisClient.connect().sync()

        app.attribute("plugin.redis.connection", connection)
    }
}