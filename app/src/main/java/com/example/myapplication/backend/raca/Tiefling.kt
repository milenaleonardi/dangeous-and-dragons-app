package personagem.raca

import com.example.myapplication.backend.Personagem

class Tiefling: Raca {
    override val nomeRaca: String = "Tiefling"
    override var idiomas: List<String> = listOf("Comum")
    override var deslocamentoBase: Float = 0f

    override fun AprimorarAtributos(personagem: com.example.myapplication.backend.Personagem) {
        personagem.inteligencia += 1
        personagem.carisma += 2
    }
}