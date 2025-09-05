package cm.daccvo.models.domain

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.bson.Document
import org.bson.types.ObjectId
import java.util.UUID

@Serializable
data class Historique(
    val id: String = ObjectId().toHexString(),
    val uuid: String = UUID.randomUUID().toString(),
    val uuidUser: String,
    val imc: Double,
    val poids: Double,
    val taille: Int,
    val message: String,
    val createDate: String
){
    fun toDocument(): Document = Document.parse(Json.encodeToString(this))

    companion object {
        private val json = Json { ignoreUnknownKeys = true }

        fun fromDocument(document: Document): Historique = json.decodeFromString(document.toJson())
    }
}
