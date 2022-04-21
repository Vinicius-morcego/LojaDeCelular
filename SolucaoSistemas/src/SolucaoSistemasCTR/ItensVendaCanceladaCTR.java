/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.ItensVendaCanceladaDAO;
import java.sql.ResultSet;

/**
 *
 * @author Batman
 */
public class ItensVendaCanceladaCTR {
    ItensVendaCanceladaDAO objitensDAO = new ItensVendaCanceladaDAO();
    ResultSet rs;
    
    public boolean CadastrarItensVendaCanceladaCTR(String... parametros){
        try {
            objitensDAO.setProduto(parametros[0]);
            objitensDAO.setQuantidade(parametros[1]);
            objitensDAO.setUnitario(parametros[2]);
            objitensDAO.setSubtotal(parametros[3]);
            objitensDAO.setOrcamento(parametros[4]);
            objitensDAO.CadastrarItensVendaCanceladaDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarItensVendaCanceladaCTR "+e.getMessage());
            return false;
        }
    }
}
