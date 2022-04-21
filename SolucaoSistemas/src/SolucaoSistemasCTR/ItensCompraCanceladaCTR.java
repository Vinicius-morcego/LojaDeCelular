/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.ItensCompraCanceladaDAO;
import java.sql.ResultSet;

/**
 *
 * @author Batman
 */
public class ItensCompraCanceladaCTR {
     ItensCompraCanceladaDAO objitensDAO = new ItensCompraCanceladaDAO();
     ResultSet rs;
    
    public boolean CadastrarItensCompraCanceladaCTR(String... parametros){
        try {
            objitensDAO.setProduto(parametros[0]);
            objitensDAO.setQuantidade(parametros[1]);
            objitensDAO.setUnitario(parametros[2]);
            objitensDAO.setSubtotal(parametros[3]);
            objitensDAO.setCompra(parametros[4]);
            objitensDAO.CadastrarItensCompraCanceladaDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarItensCompraCanceladaCTR "+e.getMessage());
            return false;
        }
    }
}
