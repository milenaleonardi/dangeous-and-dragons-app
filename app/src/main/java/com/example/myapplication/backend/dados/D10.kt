package personagem.dados

import kotlin.random.Random

class D10: Dado {
    override val nome: String = "D10"
    override val pontosDeVida: Int = 10

    override fun jogarDado(dado: Dado): Int {
        return Random.nextInt(1, pontosDeVida + 1)
    }
}