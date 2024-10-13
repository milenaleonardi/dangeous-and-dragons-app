package personagem.raca

import com.example.myapplication.backend.Personagem

class AnaoDaMontanha : Raca {
    override val nomeRaca: String = "Anão da Montanha"
    override var idiomas: List<String> = listOf("Comum", "Anao")
    override var deslocamentoBase: Float = 7.5f

    override fun AprimorarAtributos(personagem: com.example.myapplication.backend.Personagem) {
        personagem.forca += 2
        personagem.constituicao += 2
    }
}