package personagem.dados

import kotlin.random.Random

class D6 : Dado {
    override val nome: String = "D6"
    override val pontosDeVida: Int = 6

    override fun jogarDado(dado: Dado): Int {
        return Random.nextInt(1, pontosDeVida + 1)
    }
}