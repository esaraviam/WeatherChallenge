package ai.wird.domain

interface WeatherRepository {
    suspend fun getWeather(city: String): Temperatures
}