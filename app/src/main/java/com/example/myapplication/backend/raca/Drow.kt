package personagem.raca

import com.example.myapplication.backend.Personagem

class Drow: Raca {
    override val nomeRaca: String = "Drow"
    override var idiomas: List<String> = listOf("Comum")
    override var deslocamentoBase: Float = 0f

    override fun AprimorarAtributos(personagem: com.example.myapplication.backend.Personagem) {
        personagem.carisma += 1
    }
}