package cm.daccvo.plugins

import cm.daccvo.repository.ImcRepository
import cm.daccvo.routes.imcRoutes
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    routing {
        val imcRepository : ImcRepository by application.inject()
        imcRoutes(imcRepository)
    }
}
