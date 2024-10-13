package personagem.raca

import com.example.myapplication.backend.Personagem

open class Elfo() : Raca {
    override val nomeRaca: String = "Elfo"
    override var idiomas: List<String> = listOf("Comum")
    override var deslocamentoBase: Float = 0f

    override fun AprimorarAtributos(personagem: com.example.myapplication.backend.Personagem) {
        personagem.destreza += 2
    }
}