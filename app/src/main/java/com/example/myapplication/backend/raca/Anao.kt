package personagem.raca

import com.example.myapplication.backend.Personagem

open class Anao() : Raca {
    override val nomeRaca: String = "Anão"
    override var idiomas: List<String> = listOf("Comum", "Anão")
    override var deslocamentoBase: Float = 7.5f

    override fun AprimorarAtributos(personagem: com.example.myapplication.backend.Personagem) {
        personagem.constituicao += 2
    }
}