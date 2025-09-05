package cm.daccvo.models.reponse

import kotlinx.serialization.Serializable

@Serializable
data class ImcResponse(
    val imc : Double,
    val message : String
)
