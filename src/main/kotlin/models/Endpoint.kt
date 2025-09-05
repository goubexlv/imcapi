package cm.daccvo.models

sealed class Endpoint (val path : String) {
    object Root: Endpoint(path = "/")
    object Imc : Endpoint(path = "/imc")
    object History : Endpoint(path = "/history")
    object User : Endpoint(path = "/register")
}