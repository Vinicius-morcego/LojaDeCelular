/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.ComissaoOrcamentoDAO;
import java.sql.ResultSet;

/**
 *
 * @author Batman
 */
public class ComissaoOrcamentoCTR {
    ComissaoOrcamentoDAO objcomissaoDAO = new ComissaoOrcamentoDAO();
    ResultSet rs;
    
    public boolean CadastrarComissaoOrcamentoCTR(String... parametros){
        try {
            objcomissaoDAO.setValor(parametros[0]);
            objcomissaoDAO.setProduto(parametros[1]);
            objcomissaoDAO.setQuantidade(parametros[2]);           
            objcomissaoDAO.setFuncionario(parametros[3]);
            
            objcomissaoDAO.CadastrarComissaoOrcamentoDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarComissaoOrcamentoCTR "+e.getMessage());
            return false;
        }
    }
    
    public ResultSet ConsultarComissaoOrcamentoCTR(String orcamento){
        try {
            objcomissaoDAO.setOrcamento(orcamento);
            rs = objcomissaoDAO.ConsultarComissaoOrcamentoDAO();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarComissaoOrcamentoCTR "+e.getMessage());
            return rs;
        }
    }
}
