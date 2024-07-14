package ai.wird.infrastructure.routes

import ai.wird.domain.WeatherResult
import ai.wird.infrastructure.controllers.WeatherController
import ai.wird.infrastructure.repositories.ApiWeatherRepositoryImpl
import ai.wird.application.GetWeatherUseCase
import ai.wird.domain.Location
import ai.wird.infrastructure.repositories.MockApiWeatherRepositoryImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

fun Route.weatherRoutes() {
    val weatherRepository =
        if (System.getenv("WEATHER_APP_ENV") == "DEV") MockApiWeatherRepositoryImpl() else ApiWeatherRepositoryImpl()
    val getWeatherUseCase = GetWeatherUseCase(weatherRepository)
    val weatherController = WeatherController(getWeatherUseCase)

    route("/weather") {
        get("/city/{cityName}") {
            val cityName = call.parameters["cityName"] ?: return@get call.respond(
                HttpStatusCode.BadRequest,
                "Missing or malformed cityCode"
            )
            val location = Location(0.0, 0.0, cityName, "", true)
            val weather = withContext(Dispatchers.IO) { weatherController.getWeather(location) }
            call.respondWeatherResult(weather)
        }
    }
}

suspend fun ApplicationCall.respondWeatherResult(weather: WeatherResult) {
    if (weather.success) {
        respond(weather.data ?: HttpStatusCode.NotFound)
    } else {
        respond(HttpStatusCode.InternalServerError, weather.error ?: "Unknown error")
    }
}