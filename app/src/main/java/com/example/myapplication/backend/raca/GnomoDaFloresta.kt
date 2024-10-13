package personagem.raca

import com.example.myapplication.backend.Personagem

class GnomoDaFloresta: Raca {
    override val nomeRaca: String = "Gnomo da Floresta"
    override var idiomas: List<String> = listOf("Comum")
    override var deslocamentoBase: Float = 0f

    override fun AprimorarAtributos(personagem: com.example.myapplication.backend.Personagem) {
        personagem.destreza += 1
    }
}