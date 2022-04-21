/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.UsuarioDAO;
import java.sql.ResultSet;

/**
 *
 * @author Vinicius
 */
public class UsuarioCTR {
    UsuarioDAO objusuarioDAO = new UsuarioDAO();
    ResultSet rs;
    
    public boolean CadastrarUsuarioCTR(String... parametros){
        try {
            objusuarioDAO.setPessoa(parametros[0]);
            objusuarioDAO.setLoguin(parametros[1]);
            objusuarioDAO.setSenha(parametros[2]);
            objusuarioDAO.CadastrarUsuarioDAO();
            return true;
        } catch (Exception e) {
            
            return false;
        }
    }
    
    public boolean AlterarUsuarioCTR(String... parametros){
        try {
            
            objusuarioDAO.setLoguin(parametros[0]);
            objusuarioDAO.setSenha(parametros[1]);
            objusuarioDAO.setPessoa(parametros[2]);
            objusuarioDAO.AlterarUsuarioDAO();
            return true;
        } catch (Exception e) {
            
            return false;
        }
    }
    
    public ResultSet ConsultarUsuarioCTR(){
        try {
            rs = objusuarioDAO.ConsultarUsuarioDAO();
            return rs;
        } catch (Exception e) {
            
            return rs;
        }
    }
    
    public ResultSet ConsultarAcessoCTR(String... parametros){
        try {
            objusuarioDAO.setLoguin(parametros[0]);
            objusuarioDAO.setSenha(parametros[1]);
            rs = objusuarioDAO.ConsultarAcessoDAO();
            return rs;
        } catch (Exception e) {
            
            return rs;
        }
    }
}
