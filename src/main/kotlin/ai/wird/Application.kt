package ai.wird

import ai.wird.application.GetWeatherUseCase
import ai.wird.infrastructure.cache.WeatherCacheManager
import ai.wird.infrastructure.controllers.WeatherController
import ai.wird.infrastructure.repositories.ApiWeatherRepositoryImpl
import ai.wird.plugins.configureHTTP
import ai.wird.plugins.configureRouting
import ai.wird.plugins.configureScheduler
import ai.wird.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {

    val cacheManager = WeatherCacheManager("redis://localhost:6379")
    val weatherRepository = ApiWeatherRepositoryImpl(cacheManager)
    val getWeatherUseCase = GetWeatherUseCase(weatherRepository)
    val weatherController = WeatherController(getWeatherUseCase)


    configureSerialization()
    configureHTTP()
    configureScheduler(cacheManager)
    configureRouting(weatherController)
}
