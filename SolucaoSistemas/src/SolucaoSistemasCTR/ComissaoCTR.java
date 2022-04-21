/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.ComissaoDAO;
import java.sql.ResultSet;

/**
 *
 * @author Batman
 */
public class ComissaoCTR {
    ComissaoDAO objcomissaoDAO = new ComissaoDAO();
    ResultSet rs;
    
    public boolean CadastrarComissaoCTR(String... parametros){
        try {
            objcomissaoDAO.setValor(parametros[0]);
            objcomissaoDAO.setProduto(parametros[1]);
            objcomissaoDAO.setQuantidade(parametros[2]);
            objcomissaoDAO.setFuncionario(parametros[3]);
            objcomissaoDAO.CadastrarComissaoDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarComissaoCTR "+e.getMessage());
            return false;
        }
    }
}
