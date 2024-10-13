package personagem.raca

import com.example.myapplication.backend.Personagem

class HalflingRobusto: Raca {
    override val nomeRaca: String = "Halfling Robusto"
    override var idiomas: List<String> = listOf("Comum")
    override var deslocamentoBase: Float = 0f

    override fun AprimorarAtributos(personagem: com.example.myapplication.backend.Personagem) {
        personagem.constituicao += 1
    }
}