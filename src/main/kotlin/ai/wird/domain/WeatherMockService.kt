package ai.wird.domain

class WeatherMockService {

    private fun createValues(): Values {
        return Values(
            cloudBaseAvg = 1000.0,
            cloudBaseMax = 1500.0,
            cloudBaseMin = 500.0,
            cloudCeilingAvg = 2000.0,
            cloudCeilingMax = 2500.0,
            cloudCeilingMin = 1500.0,
            cloudCoverAvg = 50.0,
            cloudCoverMax = 70.0,
            cloudCoverMin = 30.0,
            dewPointAvg = 10.0,
            dewPointMax = 15.0,
            dewPointMin = 5.0,
            evapotranspirationAvg = 0.1,
            evapotranspirationMax = 0.15,
            evapotranspirationMin = 0.05,
            evapotranspirationSum = 1.0,
            freezingRainIntensityAvg = 0.0,
            freezingRainIntensityMax = 0.0,
            freezingRainIntensityMin = 0.0,
            humidityAvg = 80.0,
            humidityMax = 90.0,
            humidityMin = 70.0,
            iceAccumulationAvg = 0.0,
            iceAccumulationLweAvg = 0.0,
            iceAccumulationLweMax = 0.0,
            iceAccumulationLweMin = 0.0,
            iceAccumulationLweSum = 0.0,
            iceAccumulationMax = 0.0,
            iceAccumulationMin = 0.0,
            iceAccumulationSum = 0.0,
            moonriseTime = "",
            moonsetTime = "",
            precipitationProbabilityAvg = 20.0,
            precipitationProbabilityMax = 30.0,
            precipitationProbabilityMin = 10.0,
            pressureSurfaceLevelAvg = 1013.0,
            pressureSurfaceLevelMax = 1018.0,
            pressureSurfaceLevelMin = 1008.0,
            rainAccumulationAvg = 0.0,
            rainAccumulationLweAvg = 0.0,
            rainAccumulationLweMax = 0.0,
            rainAccumulationLweMin = 0.0,
            rainAccumulationMax = 0.0,
            rainAccumulationMin = 0.0,
            rainAccumulationSum = 0.0,
            rainIntensityAvg = 0.0,
            rainIntensityMax = 0.0,
            rainIntensityMin = 0.0,
            sleetAccumulationAvg = 0.0,
            sleetAccumulationLweAvg = 0.0,
            sleetAccumulationLweMax = 0.0,
            sleetAccumulationLweMin = 0.0,
            sleetAccumulationLweSum = 0.0,
            sleetAccumulationMax = 0.0,
            sleetAccumulationMin = 0.0,
            sleetIntensityAvg = 0.0,
            sleetIntensityMax = 0.0,
            sleetIntensityMin = 0.0,
            snowAccumulationAvg = 0.0,
            snowAccumulationLweAvg = 0.0,
            snowAccumulationLweMax = 0.0,
            snowAccumulationLweMin = 0.0,
            snowAccumulationLweSum = 0.0,
            snowAccumulationMax = 0.0,
            snowAccumulationMin = 0.0,
            snowAccumulationSum = 0.0,
            snowDepthAvg = null,
            snowDepthMax = null,
            snowDepthMin = null,
            snowDepthSum = null,
            snowIntensityAvg = 0.0,
            snowIntensityMax = 0.0,
            snowIntensityMin = 0.0,
            sunriseTime = "",
            sunsetTime = "",
            temperatureApparentAvg = 25.0,
            temperatureApparentMax = 30.0,
            temperatureApparentMin = 20.0,
            temperatureAvg = 22.0,
            temperatureMax = 28.0,
            temperatureMin = 16.0,
            uvHealthConcernAvg = null,
            uvHealthConcernMax = null,
            uvHealthConcernMin = null,
            uvIndexAvg = null,
            uvIndexMax = null,
            uvIndexMin = null,
            visibilityAvg = 10.0,
            visibilityMax = 15.0,
            visibilityMin = 5.0,
            weatherCodeMax = 1000.0,
            weatherCodeMin = 1000.0,
            windDirectionAvg = 180.0,
            windGustAvg = 20.0,
            windGustMax = 25.0,
            windGustMin = 15.0,
            windSpeedAvg = 10.0,
            windSpeedMax = 12.0,
            windSpeedMin = 8.0
        )
    }

    private fun createDaily(): Daily {
        return Daily(
            time = "",
            values = createValues()
        )
    }

    private fun createHourly(): Hourly {
        return Hourly(
            time = "",
            values = mapOf("temperature" to 22.0, "humidity" to 80.0)
        )
    }

    private fun createTimelines(): Timelines {
        return Timelines(
            minutely = listOf(createHourly()),
            hourly = listOf(createHourly()),
            daily = listOf(createDaily())
        )
    }

    private fun createLocation(): Location {
        return Location(
            lat = 35.6895,
            lon = 139.6917,
            name = "Tokyo",
            type = "city"
        )
    }

    fun createTemperatures(): Temperatures {
        return Temperatures(
            timelines = createTimelines(),
            location = createLocation()
        )
    }
}