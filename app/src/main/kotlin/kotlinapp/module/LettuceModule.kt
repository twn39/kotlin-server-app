package kotlinapp.module

import io.lettuce.core.RedisClient
import io.lettuce.core.api.sync.RedisCommands
import org.kodein.di.DI
import org.kodein.di.bindSingleton

object LettuceModule {
    val module = DI.Module("Lettuce Module") {
        bindSingleton<RedisCommands<String, String>> {
            val redisClient = RedisClient.create("redis://localhost/0")
            redisClient.connect().sync()
        }
    }
}
