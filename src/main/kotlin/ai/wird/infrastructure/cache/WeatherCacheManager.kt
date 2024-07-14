package ai.wird.infrastructure.cache

import io.lettuce.core.RedisClient
import io.lettuce.core.api.StatefulRedisConnection
import io.lettuce.core.api.sync.RedisCommands
import java.time.Instant

class WeatherCacheManager(redisUrl: String) {
    private val redisClient: RedisClient = RedisClient.create(redisUrl)
    private val connection: StatefulRedisConnection<String, String> = redisClient.connect()
    private val syncCommands: RedisCommands<String, String> = connection.sync()

    fun get(city: String): String? {
        return syncCommands.get(city)
    }

    fun set(city: String, data: String) {
        syncCommands.set(city, data)
    }

    fun logError(city: String, errorMessage: String) {
        val timestamp = Instant.now().toString()
        syncCommands.set("error:$city:$timestamp", errorMessage)
    }

    fun close() {
        connection.close()
        redisClient.shutdown()
    }
}