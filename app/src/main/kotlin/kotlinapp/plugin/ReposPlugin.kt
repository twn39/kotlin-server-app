package kotlinapp.plugin

import io.javalin.Javalin
import io.javalin.core.plugin.Plugin
import io.javalin.http.Context
import kotlinapp.repository.UserRepo


fun <T> getRepos(ctx: Context, className: Class<T>): T {
    return ctx.appAttribute(className.name)
}

class ReposPlugin: Plugin {

    override fun apply(app: Javalin) {
        app.attribute(UserRepo::class.java.name, UserRepo)
    }
}