package ai.wird.plugins

import ai.wird.infrastructure.cache.WeatherCacheManager
import ai.wird.infrastructure.repositories.ApiWeatherRepositoryImpl
import ai.wird.infrastructure.scheduler.WeatherUpdateScheduler
import io.ktor.server.application.*

fun Application.configureScheduler(
    weatherCacheManager: WeatherCacheManager,
    maxRetries: Int = 5,
    apiKey: String,
    baseUrl: String,
    schedulerTimePeriod: Long

) {
    val weatherRepository = ApiWeatherRepositoryImpl(weatherCacheManager , maxRetries ,  apiKey , baseUrl)
    val scheduler = WeatherUpdateScheduler(weatherRepository , schedulerTimePeriod)
    scheduler.startWeatherUpdateTimer()
}
