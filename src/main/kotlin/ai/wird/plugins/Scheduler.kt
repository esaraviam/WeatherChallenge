package ai.wird.plugins

import ai.wird.infrastructure.cache.WeatherCacheManager
import ai.wird.infrastructure.repositories.ApiWeatherRepositoryImpl
import ai.wird.infrastructure.scheduler.WeatherUpdateScheduler
import io.ktor.server.application.*

fun Application.configureScheduler(weatherCacheManager : WeatherCacheManager) {
    val weatherRepository = ApiWeatherRepositoryImpl(weatherCacheManager)
    val scheduler = WeatherUpdateScheduler(weatherRepository)
    scheduler.startWeatherUpdateTimer()
}
