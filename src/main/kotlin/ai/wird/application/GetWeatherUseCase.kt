package ai.wird.application

import ai.wird.domain.WeatherRepository
import ai.wird.domain.Temperatures

class GetWeatherUseCase(private val weatherRepository: WeatherRepository) {
    suspend fun execute(city: String): Temperatures? {
        return weatherRepository.getWeather(city)
    }
}