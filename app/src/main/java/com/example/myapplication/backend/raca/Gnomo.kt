package personagem.raca

import com.example.myapplication.backend.Personagem

class Gnomo: Raca {
    override val nomeRaca: String = "Gnomo"
    override var idiomas: List<String> = listOf("Comum")
    override var deslocamentoBase: Float = 0f

    override fun AprimorarAtributos(personagem: com.example.myapplication.backend.Personagem) {
        personagem.inteligencia += 2
    }
}