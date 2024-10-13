package com.example.myapplication.backend

class DistribuicaoDeAtributos {
    private fun tabelaCustoPonto(valor: Int): Int{
        return when (valor) {
            8 -> 0
            9 -> 1
            10 -> 2
            11 -> 3
            12 -> 4
            13 -> 5
            14 -> 7
            15 -> 9
            else -> 0
        }
    }

    fun escolherValores(personagem: Personagem) {
        val valoresPossiveis = mutableListOf(8, 9, 10, 11, 12, 13, 14, 15)
        var pontosRestantes = 27

        println("\nDistribua os 27 pontos entre suas habilidades:")

        personagem.forca = escolherValor("Força", valoresPossiveis, pontosRestantes).also { pontosRestantes -= tabelaCustoPonto(it) }
        personagem.destreza = escolherValor("Destreza", valoresPossiveis, pontosRestantes).also { pontosRestantes -= tabelaCustoPonto(it) }
        personagem.constituicao = escolherValor("Constituição", valoresPossiveis, pontosRestantes).also { pontosRestantes -= tabelaCustoPonto(it) }
        personagem.inteligencia = escolherValor("Inteligência", valoresPossiveis, pontosRestantes).also { pontosRestantes -= tabelaCustoPonto(it) }
        personagem.sabedoria = escolherValor("Sabedoria", valoresPossiveis, pontosRestantes).also { pontosRestantes -= tabelaCustoPonto(it) }
        personagem.carisma = escolherValor("Carisma", valoresPossiveis, pontosRestantes).also { pontosRestantes -= tabelaCustoPonto(it) }

        if (pontosRestantes < 0) {
            println("Você excedeu o limite de pontos. Tente novamente.")
            return escolherValores(personagem) // Reinicia a escolha se os pontos forem excedidos
        }

        println("Pontos restantes: $pontosRestantes")
    }

    fun escolherValor(atributo: String, valoresPossiveis: MutableList<Int>, pontosRestantes: Int): Int {
        println("Valores disponíveis: ${valoresPossiveis.filter { tabelaCustoPonto(it) <= pontosRestantes }}")
        println("Escolha o valor para $atributo (8-15):")
        val valorEscolhido = readln().toInt()

        if (valorEscolhido in valoresPossiveis && tabelaCustoPonto(valorEscolhido) <= pontosRestantes) {
            valoresPossiveis.remove(valorEscolhido)
            return valorEscolhido
        } else {
            println("Valor inválido ou pontos insuficientes. Tente novamente.")
            return escolherValor(atributo, valoresPossiveis, pontosRestantes)
        }
    }
}