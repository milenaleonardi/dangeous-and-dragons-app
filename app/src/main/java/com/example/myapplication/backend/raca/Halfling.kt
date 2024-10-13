package personagem.raca

import com.example.myapplication.backend.Personagem

class Halfling() : Raca {
    override val nomeRaca: String = "Halfling"
    override var idiomas: List<String> = listOf("Comum")
    override var deslocamentoBase: Float = 0f

    override fun AprimorarAtributos(personagem: com.example.myapplication.backend.Personagem) {
        personagem.destreza += 2
    }
}