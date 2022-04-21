/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.PermissaoCadastroDAO;
import java.sql.ResultSet;

/**
 *
 * @author Batman
 */
public class PermissaoCadastroCTR {
    PermissaoCadastroDAO objpermissaoDAO = new PermissaoCadastroDAO();
    ResultSet rs;
    
    public boolean CadastrarPermissaoCadastroCTR(String... parametros){
        try {
            objpermissaoDAO.setUsuario(parametros[0]);
            objpermissaoDAO.setPlanocontas(parametros[1]);
            objpermissaoDAO.setPermissoes(parametros[2]);
            objpermissaoDAO.setFuncionario(parametros[3]);
            objpermissaoDAO.CadastrarPermissaoCadastroDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarPermissaoCadastroCTR "+e.getMessage());
            return false;
        }
    }
    
    public boolean AlterarPermissaoCadastroCTR(String... parametros){
        try {
            objpermissaoDAO.setUsuario(parametros[0]);
            objpermissaoDAO.setPlanocontas(parametros[1]);
            objpermissaoDAO.setPermissoes(parametros[2]);
            objpermissaoDAO.setFuncionario(parametros[3]);
            objpermissaoDAO.AlterarPermissaoCadastroDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao AlterarPermissaoCadastroCTR "+e.getMessage());
            return false;
        }
    }
    
    public ResultSet ConsultarPermissaoCadastroCTR(String funcionario){
        try {
            objpermissaoDAO.setFuncionario(funcionario);
            rs = objpermissaoDAO.ConsultarPermissaoCadastroDAO();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarPermissaoCadastroCTR "+e.getMessage());
            return rs;
        }
    }
}
