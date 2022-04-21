/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.PermissaoFinanceiroDAO;
import java.sql.ResultSet;

/**
 *
 * @author Batman
 */
public class PermissaoFinanceiroCTR {
    PermissaoFinanceiroDAO objpermissaoDAO = new PermissaoFinanceiroDAO();
    ResultSet rs;
    
    public boolean CadastrarPermissaoFinanceiroCTR(String... parametros){
        try {
            objpermissaoDAO.setCaixa(parametros[0]);
            objpermissaoDAO.setContasReceber(parametros[1]);
            objpermissaoDAO.setContasPagar(parametros[2]);
            objpermissaoDAO.setFuncionario(parametros[3]);
            objpermissaoDAO.CadastrarPermissaoFinanceiroDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarPermissaoFinanceiroCTR "+e.getMessage());
            return false;
        }
    }
    
    public boolean AlterarPermissaoFinanceiroCTR(String... parametros){
        try {
            objpermissaoDAO.setCaixa(parametros[0]);
            objpermissaoDAO.setContasReceber(parametros[1]);
            objpermissaoDAO.setContasPagar(parametros[2]);
            objpermissaoDAO.setFuncionario(parametros[3]);
            objpermissaoDAO.AlterarPermissaoFinanceiroDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao AlterarPermissaoFinanceiroCTR "+e.getMessage());
            return false;
        }
    }
    
    public ResultSet ConsultarPermissaoFinanceiroCTR(String funcionario){
        try {
            objpermissaoDAO.setFuncionario(funcionario);
            rs = objpermissaoDAO.ConsultarPermissaoFinanceiroDAO();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarPermissaoFinanceiroCTR "+e.getMessage());
            return rs;
        }
    }
}
