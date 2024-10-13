package personagem.dados

import kotlin.random.Random

class D12: Dado {
    override val nome: String = "D12"
    override val pontosDeVida: Int = 12

    override fun jogarDado(dado: Dado): Int {
        return Random.nextInt(1, pontosDeVida + 1)
    }
}