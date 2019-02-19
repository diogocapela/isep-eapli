package print;

import models.avaliacao.Caso;
import models.avaliacao.ObjetoSeguro;
import modelsDTO.AvaliacaoDTO;

import java.util.Iterator;

public class BuildPrintAvaliacao {

    public String buildResult(Caso caso, Long idMatriz) {
        AvaliacaoDTO avaliacaoDTO;
        Integer riskTotal = 0;

        StringBuilder res = new StringBuilder("======================================================================\n");
        res.append("                           AVALIACAO                                  \n").
            append("======================================================================\n").
            append("    Caso ").append(caso.getID()).append(" avaliado com matriz ").append(idMatriz).append("\n").
            append("======================================================================\n").
            append("    DESCRIÇÃO OBJECTO SEGURO:                                         \n").
            append("----------------------------------------------------------------------\n").
            append("    DEMONSTRACAO                                           |          \n").
            append("-----------------------------------------------------------|----------\n").
            append("                                                           |  INDICE  \n");


        for (ObjetoSeguro obj : caso.objetosSegurados()) {
            res.append("======================================================================\n").
                append("   ").append(obj.getDescricao()).append("\n").
                append("----------------------------------------------------------------------\n");
            avaliacaoDTO = obj.getAvaliacao().toDTO();
            res.append(avaliacaoDTO.getDemonstracao()).append("\n").
                append("----------------------------------------------------------------------\n").
                append("                                                           |  ").append(avaliacaoDTO.getIndiceRisco()).append("\n");
            riskTotal += avaliacaoDTO.getIndiceRisco();
        }
        res.append("======================================================================\n").
            append("                                                     TOTAL |  ").append(riskTotal).append("\n").
            append("======================================================================\n");

        return res.toString();
    }

    public String buildComparedResult(Caso caso1, Caso caso2, Long idMatriz1, Long idMatriz2) {
        AvaliacaoDTO avaliacaoDTO1;
        AvaliacaoDTO avaliacaoDTO2;
        Integer riskTotal1 = 0;
        Integer riskTotal2 = 0;

        StringBuilder res = new StringBuilder("======================================================================\n");
        res.append("                           AVALIACAO                                  \n").
            append("======================================================================\n").
            append("Caso ").append(caso1.getID()).append(" avaliado com matriz ").append(idMatriz1).append("\n").
            append("----------------------------------------------------------------------\n").
            append("Caso ").append(caso2.getID()).append(" avaliado com matriz ").append(idMatriz2).append("\n").
            append("======================================================================\n").
            append("    DESCRIÇÃO OBJECTO SEGURO:                                         \n").
            append("----------------------------------------------------------------------\n").
            append("            DEMONSTRACAO 1                                  |          \n").
            append("-----------------------------------------------------------|----------\n").
            append("                                                           |  INDICE 1 \n").
            append("----------------------------------------------------------------------\n").
            append("            DEMONSTRACAO 2                                  |          \n").
            append("-----------------------------------------------------------|----------\n").
            append("                                                           |  INDICE 2 \n");


        Iterator<ObjetoSeguro> it1 = caso1.objetosSegurados().iterator();
        Iterator<ObjetoSeguro> it2 = caso2.objetosSegurados().iterator();
        while (it1.hasNext() && it2.hasNext()) {
            ObjetoSeguro obj1 = it1.next();
            ObjetoSeguro obj2 = it2.next();
            avaliacaoDTO1 = obj1.getAvaliacao().toDTO();
            avaliacaoDTO2 = obj2.getAvaliacao().toDTO();
            res.append("======================================================================\n").
                append(obj1.getDescricao()).append("\n").
                append("----------------------------------------------------------------------\n").
                append(avaliacaoDTO1.getDemonstracao()).append("\n").
                append("----------------------------------------------------------------------\n").
                append("                                                           |  ").append(avaliacaoDTO1.getIndiceRisco()).append("\n").
                append(avaliacaoDTO2.getDemonstracao()).append("\n").
                append("----------------------------------------------------------------------\n").
                append("                                                           |  ").append(avaliacaoDTO2.getIndiceRisco()).append("\n");
            riskTotal1 += avaliacaoDTO1.getIndiceRisco();
            riskTotal2 += avaliacaoDTO2.getIndiceRisco();
        }
        res.append("======================================================================\n").
            append("                                                     TOTAL |  ").append(riskTotal1).append("\n").
            append("----------------------------------------------------------------------\n").
            append("                                                     TOTAL |  ").append(riskTotal2).append("\n").
            append("======================================================================\n").
            append("OBS:    O risco é ");
        if (riskTotal1 > riskTotal2) {
            res.append("maior com a matriz ").append(idMatriz1).append(".\n");
        } else if (riskTotal1 < riskTotal2) {
            res.append("maior com a matriz ").append(idMatriz2).append(".\n");
        } else {
            res.append("igual com as duas matrizes.\n");
        }
        res.append("======================================================================\n");

        return res.toString();
    }

}
