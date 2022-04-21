/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.ItensOrcamentoDAO;
import java.sql.ResultSet;

/**
 *
 * @author Batman
 */
public class ItensOrcamentoCTR {
    ItensOrcamentoDAO objitensDAO = new ItensOrcamentoDAO();
    ResultSet rs;
    
    public boolean CadastrarItensOrcamentoCTR(String... parametros){
        try {
            objitensDAO.setProduto(parametros[0]);
            objitensDAO.setQuantidade(parametros[1]);
            objitensDAO.setUnitario(parametros[2]);
            objitensDAO.setSubtotal(parametros[3]);
            objitensDAO.CadastrarItensOrcamentoDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarItensOrcamentoCTR "+e.getMessage());
            return false;
        }
    }
    
    public ResultSet ConsultarItensOrcamentoCTR(String orcamento){
        try {
            objitensDAO.setOrcamento(orcamento);
            rs = objitensDAO.ConsultarItensOrcamentoDAO();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarItensOrcamentoCTR "+e.getMessage());
            return rs;
        }
    }
}
