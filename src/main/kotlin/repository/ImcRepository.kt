package cm.daccvo.repository

import cm.daccvo.models.domain.Historique
import cm.daccvo.models.reponse.ImcResponse

interface ImcRepository {

    suspend fun saveUser(name : String) : String
    suspend fun calculImc(uuid: String , poids : Double, taille : Int) : ImcResponse
    suspend fun getHistory(uuidUser: String): List<Historique>
}