package personagem.raca

import com.example.myapplication.backend.Personagem

class ElfoDaFloresta : Elfo() {
    override val nomeRaca: String = "Elfo da Floresta"
    override var idiomas: List<String> = listOf("Comum", "Anao")
    override var deslocamentoBase: Float = 7.5f

    override fun AprimorarAtributos(personagem: com.example.myapplication.backend.Personagem) {
        personagem.sabedoria += 1
    }
}