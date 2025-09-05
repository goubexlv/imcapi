package cm.daccvo.utils

import cm.daccvo.utils.Constants.DATABASE_NAME
import cm.daccvo.utils.Constants.HISTORIQUE_COLLECTION
import cm.daccvo.utils.Constants.MONGO_URL
import cm.daccvo.utils.Constants.USER_COLLECTION
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.bson.Document

object DatabaseConfig {

    private val database: MongoDatabase
    val historyMongo: MongoCollection<Document>
    val userMongo: MongoCollection<Document>

    init {
        // MongoDB
//        val credentials =
//            if (MONGO_USER.isNotBlank() && MONGO_PASSWORD.isNotBlank())
//                "$MONGO_USER:$MONGO_PASSWORD@"
//            else ""
//        val uri = "mongodb://$credentials$MONGO_HOST:$MONGO_PORT/?maxPoolSize=$MAX_POOL_SIZE&w=majority&retryWrites=true&connectTimeoutMS=10000&serverSelectionTimeoutMS=10000"
        val mongoClient = MongoClients.create(MONGO_URL)
        database = mongoClient.getDatabase(DATABASE_NAME)
        historyMongo = database.getCollection(HISTORIQUE_COLLECTION)
        userMongo = database.getCollection(USER_COLLECTION)

    }
}