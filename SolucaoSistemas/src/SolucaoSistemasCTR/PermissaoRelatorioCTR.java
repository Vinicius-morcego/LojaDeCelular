/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.PermissaoRelatorioDAO;
import java.sql.ResultSet;

/**
 *
 * @author Batman
 */
public class PermissaoRelatorioCTR {
    PermissaoRelatorioDAO objpermissaoDAO = new PermissaoRelatorioDAO();
    ResultSet rs;
    
    public boolean CadastrarPermissaoRelatorioCTR(String... parametros){
        try {
            objpermissaoDAO.setEstoque(parametros[0]);
            objpermissaoDAO.setFinanceiro(parametros[1]);
            objpermissaoDAO.setPlanocontas(parametros[2]);
            objpermissaoDAO.setAuditoria(parametros[3]);            
            objpermissaoDAO.setFuncionario(parametros[4]);
            objpermissaoDAO.CadastrarPermissaoRelatorioDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarPermissaoRelatorioCTR "+e.getMessage());
            return false;
        }
    }
    
    public boolean AlterarPermissaoRelatorioCTR(String... parametros){
        try {
            objpermissaoDAO.setEstoque(parametros[0]);
            objpermissaoDAO.setFinanceiro(parametros[1]);
            objpermissaoDAO.setPlanocontas(parametros[2]);
            objpermissaoDAO.setAuditoria(parametros[3]);
            objpermissaoDAO.setFuncionario(parametros[4]);
            objpermissaoDAO.AlterarPermissaoRelatorioDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao AlterarPermissaoRelatorioCTR "+e.getMessage());
            return false;
        }
    }
    
    public ResultSet ConsultarPermissaoRelatorioCTR(String funcionario){
        try {
            objpermissaoDAO.setFuncionario(funcionario);
            rs = objpermissaoDAO.ConsultarPermissaoRelatorioDAO();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarPermissaoRelatorioCTR "+e.getMessage());
            return rs;
        }
    }
}
