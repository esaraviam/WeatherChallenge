package ai.wird.infrastructure.repositories

import ai.wird.domain.Temperatures
import ai.wird.domain.WeatherRepository
import ai.wird.service.TemperatureService // solo para probar despues cambiar a la api real.

class ApiWeatherRepositoryImpl : WeatherRepository {
    override suspend fun getWeather(city: String): Temperatures {
        // agregar llamada a la

        val temperatureService = TemperatureService()
        val temperatures = temperatureService.createTemperatures()
        return temperatures
    }

}