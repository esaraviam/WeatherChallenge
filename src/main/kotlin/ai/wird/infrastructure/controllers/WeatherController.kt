package ai.wird.infrastructure.controllers

import ai.wird.application.GetWeatherUseCase
import ai.wird.domain.WeatherResult
import ai.wird.domain.Location

class WeatherController(private val getWeatherUseCase: GetWeatherUseCase) {

    suspend fun getWeather(city: Location): WeatherResult {
        return try {
            val weatherData = getWeatherUseCase.execute(city.name)
            WeatherResult(success = true, data = weatherData)
        } catch (e: Exception) {
            WeatherResult(success = false, error = e.message)
        }
    }
}