package ai.wird.infrastructure.repositories

import ai.wird.domain.Temperatures
import ai.wird.domain.WeatherRepository
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json

class ApiWeatherRepositoryImpl : WeatherRepository {
    val client = HttpClient(CIO)


    override suspend fun getWeather(city: String): Temperatures? {
        val apiKey = "A9amWnFRSozUZG10mN0yxNB9tUTM8mU5"
        val url = "https://api.tomorrow.io/v4/weather/forecast?location=$city&units=metric&apikey=$apiKey"

       return try {
            val response: HttpResponse = client.get(url)

            val responseBody = response.bodyAsText()
            val result = Json.decodeFromString<Temperatures>(responseBody)
            result
        } catch (e: Exception) {
            throw RuntimeException("Error fetching weather data", e)
        } finally {
            client.close()
        }
    }


}
