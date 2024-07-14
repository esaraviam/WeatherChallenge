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

    val apiKey = System.getenv("WEATHER_API_KEY") ?: throw IllegalStateException("WEATHER_API_KEY is not set")
    val baseUrl =
        System.getenv("WEATHER_API_BASE_URL") ?: throw IllegalStateException("WEATHER_API_BASE_URL is not set")
    val redisUrl = System.getenv("WEATHER_REDIS_URL") ?: throw IllegalStateException("WEATHER_REDIS_URL is not set")
    val maxRetried =
        System.getenv("WEATHER_MAX_RETRIES") ?: throw IllegalStateException("WEATHER_MAX_RETRIES is not set")
    val schedulerTimePeriod =
        System.getenv("WEATHER_TIME_PERIOD") ?: throw IllegalStateException("WEATHER_REDIS_URL is not set")

    val cacheManager = WeatherCacheManager(redisUrl)
    val weatherRepository = ApiWeatherRepositoryImpl(cacheManager, maxRetried.toInt(), apiKey, baseUrl)
    val getWeatherUseCase = GetWeatherUseCase(weatherRepository)
    val weatherController = WeatherController(getWeatherUseCase)


    configureSerialization()
    configureHTTP()
    configureScheduler(cacheManager, maxRetried.toInt(), apiKey, baseUrl, schedulerTimePeriod.toLong())
    configureRouting(weatherController)
}
