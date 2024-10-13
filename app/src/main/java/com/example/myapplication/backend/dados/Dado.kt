package personagem.dados

interface Dado {
    val nome: String
    val pontosDeVida: Int

    fun jogarDado(dado: Dado): Int
}