/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.FinalizarCompraDAO;

/**
 *
 * @author Batman
 */
public class FinalizarCompraCTR {
    FinalizarCompraDAO objfinalizarDAO = new FinalizarCompraDAO();
     public boolean CadastrarFinalizarCompraCTR(String... parametros){
        try {
            objfinalizarDAO.setDataFechamento(parametros[0]);
            objfinalizarDAO.setHora(parametros[1]);
            objfinalizarDAO.setSituacao(parametros[2]);
            objfinalizarDAO.setCompra(parametros[3]);
            objfinalizarDAO.CadastrarFinalizarCompraDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarFinalizarCompraCTR "+e.getMessage());
            return false;
        }
    }
}
