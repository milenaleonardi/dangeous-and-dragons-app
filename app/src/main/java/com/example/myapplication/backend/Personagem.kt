package com.example.myapplication.backend

import androidx.room.Entity
import androidx.room.ForeignKey
import personagem.classe.Classe
import personagem.dados.Dado
import personagem.raca.Raca
import kotlin.math.floor

class Personagem(
    var nomePersonagem: String,
    val raca: Raca,
    val classe: Classe,
    var nivelPersonagem: Int = 1)
{
    private var pontosExperiencia: Int = 0
    private var dadosDeVida: Int = 1
    private var pontosDeVida: Int = AtribuirPontosDeVidaInicial(classe.tipoDadoDeVida)
    private var bonusProficiencia = AtribuirBonusDeProficiencia()

    var forca: Int = 8
    var destreza: Int = 8
    var constituicao: Int = 8
    var inteligencia: Int = 8
    var sabedoria: Int = 8
    var carisma: Int = 8

    private var modificadorDeConstituicao = CalcularModificador(constituicao)

    val getPontosDeVida: Int
        get() = pontosDeVida

    fun AtribuirPontosDeVidaInicial(tipoDadoDeVida: Dado) : Int{
        return tipoDadoDeVida.pontosDeVida + modificadorDeConstituicao
    }

    fun aplicarBonusClasse() {
        when (classe.nomeClasse) {
            "Arqueiro" -> destreza += 3
            "Mago" -> inteligencia += 3
            "Guerreiro" -> forca += 3
        }
    }

    fun AtribuirBonusDeProficiencia(): Int{
        if (nivelPersonagem == 1)
            return 2
        else
            return 0
    }

    fun CalcularModificador(valorHabilidade: Int) : Int{
        return floor((valorHabilidade - 10) / 2.0).toInt()
    }

    fun AumentarXP(xp: Int){
        pontosExperiencia += xp
    }

    fun SubirNivel(xp: Int){
        //TBD
        nivelPersonagem += 1
    }

    fun Descansar(tipoDadoDeVida: Dado){
        if(dadosDeVida > 0) {
            dadosDeVida -= 1
            pontosDeVida += tipoDadoDeVida.pontosDeVida
        }
        else
            print("Você não tem Dados de Vida suficiente.")
    }

    fun fichaDePersonagem(){
        println("\n****** Ficha do com.example.myapplication.Personagem ******")
        println("\nNome da Raça: ${raca.nomeRaca}")
        println("Idiomas: ${raca.idiomas.joinToString(", ")}")
        println("Deslocamento base: ${raca.deslocamentoBase} m")
        println("Nivel: ${nivelPersonagem}")
        println("Pontos de Vida: ${pontosDeVida}")
        println("Tipo de Dado de Vida: ${classe.tipoDadoDeVida.nome}")
        println("Número de Dados de Vida: ${dadosDeVida}")

        println("\nProficiências do personagem: ")
        println("Ataque com armas: ${classe.ataqueComArmas}")
        println("Ataque com magias: ${classe.ataqueComMagias}")
        println("Testes de Habilidade de Perícia: ${classe.testeHabilidadePericia}")
        println("Testes de Habilidade de Ferramentas: ${classe.testeHabilidadeFerramenta}")
        println("Testes de Resistência: ${classe.testesResistencia}")
        println("CD de magias de conjuração: ${classe.testResistenciaCD}")
        println("Bônus de Proficiência: ${bonusProficiencia}")

    }

}