package ai.wird.infrastructure.repositories

import ai.wird.domain.CacheableCities
import ai.wird.domain.Temperatures
import ai.wird.domain.WeatherRepository
import ai.wird.infrastructure.cache.WeatherCacheManager
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlin.random.Random

class ApiWeatherRepositoryImpl(
    private val cacheManager: WeatherCacheManager,
    private val maxRetries: Int = 3,
    private val apiKey: String,
    private val baseUrl: String,
) : WeatherRepository {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                coerceInputValues = true
            })
        }
    }

    override suspend fun getWeather(city: String): Temperatures? {

        val url = "$baseUrl?location=$city&units=metric&apikey=$apiKey"

        return if (CacheableCities.cities.contains(city)) {
            val cachedData = cacheManager.get(city)
            if (cachedData != null) {
                Json.decodeFromString(cachedData)
            } else {
                fetchWeatherWithRetry(url, city)
            }
        } else {
            fetchWeatherWithRetry(url, city)
        }
    }

    private suspend fun fetchWeatherWithRetry(url: String, city: String): Temperatures? {
        var currentAttempt = 0
        while (currentAttempt < maxRetries) {
            try {
                currentAttempt++
                if (Random.nextDouble() < 0.2) {
                    throw Error("The API Request Failed")
                }

                val response: HttpResponse = client.get(url)
                val responseBody = response.bodyAsText()
                val weatherData: Temperatures = Json.decodeFromString(responseBody)

                if (CacheableCities.cities.contains(city)) {
                    cacheManager.set(city, responseBody)
                }

                return weatherData
            } catch (e: Exception) {
                println("Error fetching weather data: ${e.message}")
                cacheManager.logError("LOG_$city", "Error fetching weather data: ${e.message}")
                if (currentAttempt >= maxRetries) {
                    throw RuntimeException("Failed to fetch weather data after $currentAttempt attempts", e)
                }
            }
        }
        return null
    }

    fun close() {
        client.close()
        cacheManager.close()
    }
}