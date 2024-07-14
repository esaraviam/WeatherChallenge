package ai.wird.plugins

import ai.wird.infrastructure.routes.weatherRoutes
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Im Alive", ContentType.Text.Html)

        }

        weatherRoutes()

    }
}
