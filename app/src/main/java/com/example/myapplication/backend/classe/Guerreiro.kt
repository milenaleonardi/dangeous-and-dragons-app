package personagem.classe

import personagem.dados.D10
import personagem.dados.Dado

class Guerreiro : Classe {
    override val nomeClasse: String = "Guerreiro"
    override val tipoDadoDeVida: Dado = D10()

    override var ataqueComArmas: Int = 0
    override var ataqueComMagias: Int = 0
    override var testeHabilidadePericia: Int = 0
    override var testeHabilidadeFerramenta: Int = 0
    override var testesResistencia: Int = 0
    override var testResistenciaCD: Int = 0
}