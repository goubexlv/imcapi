package cm.daccvo.models.request

import kotlinx.serialization.Serializable

@Serializable
data class ImcRequest(
    val poids : Double,
    val taille : Int,
    val uuidUser : String
)
