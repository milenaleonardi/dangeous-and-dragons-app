package personagem.raca

import com.example.myapplication.backend.Personagem

interface Raca {
    val nomeRaca: String
    var idiomas : List<String>
    var deslocamentoBase : Float

    fun AprimorarAtributos(personagem: com.example.myapplication.backend.Personagem)
}