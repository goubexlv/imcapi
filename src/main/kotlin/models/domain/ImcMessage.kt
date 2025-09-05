package cm.daccvo.models.domain

enum class ImcMessage(val message: String) {
    MAIGREUR("Maigreur"),
    NORMAL("Corpulence normale"),
    SURPOIDS("Surpoids"),
    OBESITE_MODEREE("Obésité modérée (Classe I)"),
    OBESITE_SEVERE("Obésité sévère (Classe II)"),
    OBESITE_MORBIDE("Obésité morbide (Classe III)")
}