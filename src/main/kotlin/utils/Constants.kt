package cm.daccvo.utils

import io.github.cdimascio.dotenv.dotenv
import kotlin.text.toInt

object Constants {

    //val dotenv = dotenv()

    // MongoDB variable
    val MONGO_URL: String = System.getenv("MONGO_URL").toString()
    val REDIS_URL: String = System.getenv("REDIS_URL").toString()
    //System.getenv("REDIS_HOST").toString()
    val DATABASE_NAME: String = System.getenv("MONGO_DB").toString()
//    val MONGO_USER: String = dotenv["MONGO_USER"]
//    val MONGO_PASSWORD: String = dotenv["MONGO_PASSWORD"]
//    val MONGO_HOST: String = dotenv["MONGO_HOST"]
//    val MONGO_PORT: Int = dotenv["MONGO_PORT"].toInt()
//    val MAX_POOL_SIZE: Int = dotenv["MAX_POOL_SIZE"].toInt()
    val HISTORIQUE_COLLECTION: String = System.getenv("MONGO_HISTORIQUE_COLLECTION").toString()
    val USER_COLLECTION: String = System.getenv("MONGO_USER_COLLECTION").toString()


    // Redis variable
//    val REDIS_PORT: Int = dotenv["REDIS_PORT"].toInt()
//    val REDIS_TIME: Long = dotenv["REDIS_TIME"].toLong()
//    val REDIS_HOST: String = dotenv["REDIS_HOST"]
    const val TIME = 1800L
}