package com.example.myapplication.backend

class Atributos {
    private val valoresDisponiveis = mutableListOf(15, 14, 13, 12, 10, 8)

    fun Distribuir(personagem: com.example.myapplication.backend.Personagem){
        println("\nEscolha os valores para as habilidades:")

        personagem.forca = escolherValor("Força")
        personagem.destreza = escolherValor("Destreza")
        personagem.constituicao = escolherValor("Constituição")
        personagem.inteligencia = escolherValor("Inteligência")
        personagem.sabedoria = escolherValor("Sabedoria")
        personagem.carisma = escolherValor("Carisma")
    }

    private fun escolherValor(atributo: String): Int {
        println("Valores disponíveis: $valoresDisponiveis")
        println("Escolha o valor para $atributo:")
        val valorEscolhido = readln().toInt()

        return if (valorEscolhido in valoresDisponiveis) {
            valoresDisponiveis.remove(valorEscolhido)
            valorEscolhido
        } else {
            println("Valor inválido ou já utilizado. Tente novamente.")
            escolherValor(atributo)
        }
    }

    fun mostrarAtributos(personagem: com.example.myapplication.backend.Personagem) {
        println("\nAtributos do personagem:")
        println("Força: ${personagem.forca}")
        println("Destreza: ${personagem.destreza}")
        println("Constituição: ${personagem.constituicao}")
        println("Inteligência: ${personagem.inteligencia}")
        println("Sabedoria: ${personagem.sabedoria}")
        println("Carisma: ${personagem.carisma}")
    }
}

