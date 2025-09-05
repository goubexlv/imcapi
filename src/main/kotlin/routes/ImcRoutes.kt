package cm.daccvo.routes

import cm.daccvo.models.Endpoint
import cm.daccvo.models.domain.Users
import cm.daccvo.models.request.ImcRequest
import cm.daccvo.repository.ImcRepository
import cm.daccvo.utils.RedisManager
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post

fun Route.imcRoutes(imcRepository: ImcRepository){

    get(Endpoint.User.path) {
        val name = call.request.queryParameters["name"]
        if (name.isNullOrBlank() || name.length > 50 || name.contains("$") || name.contains("{") || name.contains("}")) {
            call.respond(HttpStatusCode.BadRequest, "Nom invalide")
            return@get
        }
      
        try {
            val response = imcRepository.saveUser(name.trim())
            val user = Users(userId = response, name = name.trim())
            println("Réponse envoyée au client: $user") // 👈 log serveur
            call.respond(user)
        } catch (e : Exception) {
            e.printStackTrace()
            call.respond(
                message = "Erreur inattendue: ${e.localizedMessage}",
                status = HttpStatusCode.InternalServerError
            )
        }

    }

    post(Endpoint.Imc.path){
        val request = call.receive<ImcRequest>()
        try {

            if (request.poids != null && request.taille != null && request.uuidUser != null) {
                val response  = imcRepository.calculImc(uuid = request.uuidUser, taille = request.taille, poids = request.poids)
                RedisManager.clearAll()
                println("Réponse envoyée au client: $response")
                call.respond(
                    message = response,
                    status = HttpStatusCode.OK
                )
            } else {
                call.respond(HttpStatusCode.BadRequest, "Paramètres manquants")
            }

        } catch (e : Exception) {
            call.respond(
                message = "Erreur inattendue: ${e.localizedMessage}",
                status = HttpStatusCode.BadRequest
            )
        }

    }

    get(Endpoint.History.path){
        val uuidUser = call.request.queryParameters["uuidUser"]
        if (RedisManager.testConnection()) {
            val cachedData = RedisManager.getHistory(uuidUser!!)
            if (cachedData != null) {
                call.respond(HttpStatusCode.OK, cachedData)
                return@get
            }
        } else {
            call.respond(HttpStatusCode.InternalServerError, "Échec de la connexion à Redis ❌")
        }
        try {
            if (uuidUser != null) {
                val response = imcRepository.getHistory(uuidUser)
                RedisManager.saveHistory(key = uuidUser, response)
                call.respond(
                    message = response,
                    status = HttpStatusCode.OK
                )
            } else {
                call.respond(HttpStatusCode.BadRequest, "Paramètres manquants")
            }
        } catch (e : Exception) {
            call.respond(
                message = "Erreur inattendue: ${e.message}",
                status = HttpStatusCode.BadRequest
            )
        }

    }

}