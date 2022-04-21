/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.FinalizarAtendimentoDAO;

/**
 *
 * @author Batman
 */
public class FinalizarAtendimentoCTR {
    FinalizarAtendimentoDAO objfinalizarDAO = new FinalizarAtendimentoDAO();
    
    public boolean CadastrarFinalizarCTR(String... parametros){
        try {
            objfinalizarDAO.setDataFechamento(parametros[0]);
            objfinalizarDAO.setHora(parametros[1]);
            objfinalizarDAO.setSituacao(parametros[2]);
            objfinalizarDAO.setCodigo(parametros[3]);
            objfinalizarDAO.CadastrarFinalizarDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarFinalizarCTR "+e.getMessage());
            return false;
        }
    }
}
