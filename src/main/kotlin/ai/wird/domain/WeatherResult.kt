package ai.wird.domain

import kotlinx.serialization.Serializable

@Serializable
data class WeatherResult(
    val success: Boolean,
    val data: Temperatures? = null,
    val error: String? = null
)