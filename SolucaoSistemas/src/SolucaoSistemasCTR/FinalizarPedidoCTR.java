/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.FinalizarPedidoDAO;

/**
 *
 * @author Batman
 */
public class FinalizarPedidoCTR {
    FinalizarPedidoDAO objfinalizarDAO = new FinalizarPedidoDAO();
    
    public boolean CadastrarFinalizarPedidoCTR(String... parametros){
        try {
            objfinalizarDAO.setDataFechamento(parametros[0]);
            objfinalizarDAO.setHora(parametros[1]);
            objfinalizarDAO.setSituacao(parametros[2]);
            objfinalizarDAO.setCodigo(parametros[3]);
            objfinalizarDAO.CadastrarFinalizarPedidoDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarFinalizarPedidoCTR "+e.getMessage());
            return false;
        }
    }
}
