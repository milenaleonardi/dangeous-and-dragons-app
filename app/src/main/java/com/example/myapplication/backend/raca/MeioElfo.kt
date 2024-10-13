package personagem.raca

import com.example.myapplication.backend.Personagem

class MeioElfo: Raca{
    override val nomeRaca: String = "Meio-elfo"
    override var idiomas: List<String> = listOf("Comum")
    override var deslocamentoBase: Float = 0f

    override fun AprimorarAtributos(personagem: com.example.myapplication.backend.Personagem) {
        personagem.carisma += 2
    }
}