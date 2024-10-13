package personagem.dados

import kotlin.random.Random

class D20: Dado {
    override val nome: String = "D20"
    override val pontosDeVida: Int = 20

    override fun jogarDado(dado: Dado): Int {
        return Random.nextInt(1, pontosDeVida + 1)
    }
}