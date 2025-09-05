package cm.daccvo.repository

import cm.daccvo.models.domain.Historique
import cm.daccvo.models.domain.ImcMessage
import cm.daccvo.models.domain.User
import cm.daccvo.models.reponse.ImcResponse
import cm.daccvo.utils.DatabaseConfig
import cm.daccvo.utils.RedisManager
import cm.daccvo.utils.generateDate
import com.mongodb.client.model.Filters.eq
import org.bson.Document
import java.util.Locale

class ImcRepositoryImpl : ImcRepository {

    private val historyDocument = DatabaseConfig.historyMongo
    private val userDocument = DatabaseConfig.userMongo

    override suspend fun saveUser(name: String): String {
        val user = User(
            name = name
        )
        userDocument.insertOne(user.toDocument())
        return user.uuid
    }

    override suspend fun calculImc(uuid: String, poids: Double, taille: Int): ImcResponse {
        // Taille en mètre
        val tailleMetres = taille / 100.0
        val imc = poids / (tailleMetres * tailleMetres)

        val message = when {
            imc < 18.5 -> ImcMessage.MAIGREUR
            imc < 25.0 -> ImcMessage.NORMAL
            imc < 30.0 -> ImcMessage.SURPOIDS
            imc < 35.0 -> ImcMessage.OBESITE_MODEREE
            imc < 40.0 -> ImcMessage.OBESITE_SEVERE
            else -> ImcMessage.OBESITE_MORBIDE
        }

        val imcResponse = ImcResponse(
            imc = String.format(Locale.US, "%.2f", imc).toDouble(),
            message = message.message
        )

        saveHistory(uuid,poids,taille,imcResponse)

        return imcResponse
    }

    override suspend fun getHistory(uuidUser: String): List<Historique> {
        val historyList = historyDocument.find(eq("uuidUser", uuidUser)).toList()
        return historyList.map { Historique.fromDocument(it) }
    }

    private suspend fun saveHistory(uuid : String, poids: Double, taille: Int, imcResponse : ImcResponse) : Boolean {
        return try {
            val historique = Historique(
                uuidUser = uuid,
                imc = imcResponse.imc,
                message = imcResponse.message,
                createDate = generateDate(),
                poids = poids,
                taille = taille
            )

            RedisManager.clearAll()
            historyDocument.insertOne(historique.toDocument()).wasAcknowledged()
        } catch (e : Exception) {
            println("Error : ${e.message}")
            false
        }
    }



}