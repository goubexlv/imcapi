package cm.daccvo

import cm.daccvo.plugins.configureFrameworks
import cm.daccvo.plugins.configureHTTP
import cm.daccvo.plugins.configureRouting
import cm.daccvo.plugins.configureSerialization
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureFrameworks()
    configureSerialization()
    configureHTTP()
    configureRouting()
}
