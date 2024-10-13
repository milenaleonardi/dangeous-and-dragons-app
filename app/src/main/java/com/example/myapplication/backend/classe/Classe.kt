package personagem.classe

import personagem.dados.Dado

interface Classe {
    val nomeClasse: String
    val tipoDadoDeVida: Dado

    var ataqueComArmas: Int
    var ataqueComMagias: Int
    var testeHabilidadePericia: Int
    var testeHabilidadeFerramenta: Int
    var testesResistencia: Int
    var testResistenciaCD: Int
}