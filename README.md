# IMC API ⚖️

**IMC API** est une API créée avec **Ktor** qui permet de calculer l’IMC (Indice de Masse Corporelle) pour un utilisateur et de consulter son historique. L’API utilise **MongoDB** pour stocker les données et **Koin** pour la gestion des dépendances.

---

## 🚀 Fonctionnalités

| Nom                  | Description                                                       |
|--------------------- |-----------------------------------------------------------------|
| Calcul IMC           | Permet de calculer l’IMC à partir du poids, de la taille et de l’utilisateur |
| Historique           | Stocke et récupère l’historique des IMC pour chaque utilisateur |
| Gestion utilisateur  | Création et identification d’utilisateurs via `uuidUser`        |
| MongoDB              | Stockage persistant des données                                  |
| Koin                 | Injection de dépendances                                         |
| JSON                 | Sérialisation et désérialisation avec `kotlinx.serialization`    |

---

## 🌡️ Endpoints

### 1️⃣ Calcul IMC

**POST** `/imc`

#### Paramètres (JSON)

```json
{
  "poids": 70.0,
  "taille": 177,
  "uuidUser": "123e4567-e89b-12d3-a456-426614174000"
}
```

#### Exemple de réponse (JSON)

```json
{
  "imc": 22.36,
  "message": "Poids normal"
}
```

#### 2️⃣ Historique IMC

**GET** `/history`

#### Paramètres 


| Paramètre | Type   | Description                 |
|-----------|--------|-----------------------------|
| `uuidUser`   | string | UUID de l’utilisateur (ex: 123e4567-e89b-12d3-a456-426614174000) |

#### Exemple de requête

```bash
    curl -X GET "http://127.0.0.1:8080/history?uuidUser=123e4567-e89b-12d3-a456-426614174000"
```

#### Exemple de réponse (JSON)

```json
[
  {
    "id": "64f1fbc8e0b5e74a2a1e7c01",
    "uuid": "123e4567-e89b-12d3-a456-426614174000",
    "uuidUser": "123e4567-e89b-12d3-a456-426614174000",
    "imc": 22.36,
    "poids": 70.0,
    "taille": 177,
    "message": "Poids normal",
    "createDate": "2025-09-05T12:34:56"
  }
]
```

#### 3️⃣ Gestion utilisateur

**POST** `/register`

#### Paramètres (JSON)

```json
{
  "name": "goube"
}
```

#### Exemple de réponse (JSON)

```json
{
  "userId": "123e4567-e89b-12d3-a456-426614174000",
  "name": "goube"
}
```

#### 🏗️ Installation et lancement

```bash
    ./gradlew build         # Compile le projet
    ./gradlew test          # Lance les tests
    
    ./gradlew run           # Lance le serveur en local
    
    ./gradlew buildImage                    # Crée l'image Docker
    ./gradlew publishImageToLocalRegistry  # Publie l'image localement
    ./gradlew runDocker                     # Lance le conteneur Docker

    
    2025-09-05 12:32:45.584 [main] INFO  Application - Application started in 0.303 seconds.
    2025-09-05 12:32:45.682 [main] INFO  Application - Responding at http://0.0.0.0:8080

```






















