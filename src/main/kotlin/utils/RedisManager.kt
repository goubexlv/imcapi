package cm.daccvo.utils

import cm.daccvo.models.domain.Historique
import cm.daccvo.utils.Constants.REDIS_URL
import cm.daccvo.utils.Constants.TIME
import kotlinx.serialization.json.Json
import redis.clients.jedis.Jedis
import java.net.URI

object RedisManager {
    private val jedis: Jedis = Jedis(URI.create(REDIS_URL))

    fun testConnection(): Boolean {
        return try {
            jedis.ping() == "PONG"
        } catch (e: Exception) {
            false
        }
    }

    fun saveHistory(key: String, history: List<Historique>) {
        val json = Json.encodeToString(history)
        jedis.set(key,json)
        jedis.expire(key, TIME)
    }

    fun getHistory(key: String): List<Historique>? {
        val json = jedis.get(key) ?: return null
        return Json.decodeFromString(json)
    }

    fun clearAll() {
        jedis.flushAll()
    }
}