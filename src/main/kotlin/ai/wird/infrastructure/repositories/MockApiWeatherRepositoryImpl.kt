package ai.wird.infrastructure.repositories

import ai.wird.domain.Temperatures
import ai.wird.domain.WeatherRepository
import ai.wird.domain.WeatherMockService // solo para probar despues cambiar a la api real.

class MockApiWeatherRepositoryImpl : WeatherRepository {
    override suspend fun getWeather(city: String): Temperatures {
        val temperatureService = WeatherMockService()
        val temperatures = temperatureService.createTemperatures()
        return temperatures
    }

}