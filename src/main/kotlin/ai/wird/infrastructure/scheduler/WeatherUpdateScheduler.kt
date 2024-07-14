package ai.wird.infrastructure.scheduler

import ai.wird.domain.CacheableCities
import ai.wird.infrastructure.repositories.ApiWeatherRepositoryImpl
import kotlinx.coroutines.*
import java.time.Duration

class WeatherUpdateScheduler(private val weatherRepository: ApiWeatherRepositoryImpl) {

    fun startWeatherUpdateTimer() {
        CoroutineScope(Dispatchers.IO).launch {
            while (isActive) {
                updateWeatherData()
                delay(Duration.ofMinutes(5).toMillis())
            }
        }
    }

    private suspend fun updateWeatherData() {
        for (city in CacheableCities.cities) {
            try {
                val temperatures = weatherRepository.getWeather(city)
                if (temperatures != null) {
                    println("Weather data for $city updated in Redis.")
                }
            } catch (e: Exception) {
                println("Error updating weather data for $city: ${e.message}")
            }
        }
    }
}