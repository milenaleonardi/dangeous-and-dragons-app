package personagem.dados

import kotlin.random.Random

class D8: Dado {
    override val nome: String = "D8"
    override val pontosDeVida: Int = 8

    override fun jogarDado(dado: Dado): Int {
        return Random.nextInt(1, pontosDeVida + 1)
    }
}