package personagem.dados

import kotlin.random.Random

class D4 : Dado{
    override val nome: String = "D4"
    override val pontosDeVida: Int = 4

    override fun jogarDado(dado: Dado): Int {
        return Random.nextInt(1, pontosDeVida + 1)
    }
}