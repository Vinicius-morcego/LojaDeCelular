/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.ItensOrcamentoCompraDAO;
import java.sql.ResultSet;

/**
 *
 * @author Batman
 */
public class ItensOrcamentoCompraCTR {
    ItensOrcamentoCompraDAO objitensDAO = new ItensOrcamentoCompraDAO();
    ResultSet rs;
    
    public boolean CadastrarItensOrcamentoCompraCTR(String... parametros){
        try {
            objitensDAO.setProduto(parametros[0]);
            objitensDAO.setQuantidade(parametros[1]);
            objitensDAO.setUnitario(parametros[2]);
            objitensDAO.setSubtotal(parametros[3]);
            objitensDAO.CadastrarItensOrcamentoCompraDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarItensOrcamentoCompraCTR "+e.getMessage());
            return false;
        }
    }
    
    public ResultSet ConsultarItensOrcamentoCompraCTR(String orcamento){
        try {
            objitensDAO.setOrcamentoCompra(orcamento);
            rs = objitensDAO.ConsultarItensOrcamentoCompraDAO();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarItensOrcamentoCompraCTR "+e.getMessage());
            return rs;
        }
    }
}
