package personagem.raca

import com.example.myapplication.backend.Personagem

class Draconato: Raca {
    override val nomeRaca: String = "Draconato"
    override var idiomas: List<String> = listOf("Comum")
    override var deslocamentoBase: Float = 0f

    override fun AprimorarAtributos(personagem: com.example.myapplication.backend.Personagem) {
        personagem.forca += 2
        personagem.carisma += 1
    }
}