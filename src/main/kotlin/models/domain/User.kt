package cm.daccvo.models.domain

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.bson.Document
import org.bson.types.ObjectId
import java.util.UUID

@Serializable
data class User(
    val id: String = ObjectId().toHexString(),
    val uuid: String = UUID.randomUUID().toString(),
    val name : String
){
    fun toDocument(): Document = Document.parse(Json.encodeToString(this))

    companion object {
        private val json = Json { ignoreUnknownKeys = true }

        fun fromDocument(document: Document): User = json.decodeFromString(document.toJson())
    }
}

@Serializable
data class Users(
    val userId : String,
    val name : String
)
