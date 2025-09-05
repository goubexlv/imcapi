package cm.daccvo.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun generateDate() : String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val createdAt: String = LocalDateTime.now().format(formatter)
    return createdAt
}