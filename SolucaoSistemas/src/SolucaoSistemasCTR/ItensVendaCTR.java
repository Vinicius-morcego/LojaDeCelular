/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.ItensVendaDAO;
import java.sql.ResultSet;

/**
 *
 * @author Batman
 */
public class ItensVendaCTR {
   
    ItensVendaDAO objitensDAO = new ItensVendaDAO();
    ResultSet rs;
    
    public boolean CadastrarItensVendaCTR(String... parametros){
        try {
            objitensDAO.setProduto(parametros[0]);
            objitensDAO.setQuantidade(parametros[1]);
            objitensDAO.setUnitario(parametros[2]);
            objitensDAO.setSubtotal(parametros[3]);
            objitensDAO.CadastrarItensVendaDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarItensVendaCTR "+e.getMessage());
            return false;
        }
    }
    
    public ResultSet ConsultarItensVendaCTR(String venda){
        try {
            objitensDAO.setOrcamento(venda);
            rs = objitensDAO.ConsultarItensVendaDAO();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarItensVendaCTR "+e.getMessage());
            return rs;
        }
    }
}
