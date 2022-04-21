/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.EspecieDAO;
import java.sql.ResultSet;

/**
 *
 * @author Vinicius
 */
public class EspecieCTR {
    EspecieDAO objespecieDAO = new EspecieDAO();
    ResultSet rs;
    public boolean CadastrarEspecieCTR(String... parametros){
        try {
            objespecieDAO.setDescricao(parametros[0]);
            objespecieDAO.setSituacao(parametros[1]);
            objespecieDAO.CadastrarEspecieDAO();
            return true;
        } catch (Exception e) {
            
            return false;
        }
    }
    
    public boolean AlterarEspecieCTR(String... parametros){
        try {
            objespecieDAO.setDescricao(parametros[0]);
            objespecieDAO.setSituacao(parametros[1]);
            objespecieDAO.setCodigo(parametros[2]);
            
            objespecieDAO.AlterarEspecieDAO();
            return true;
        } catch (Exception e) {
            
            return false;
        }
    }
    
    public ResultSet ConsultarEspecieCTR(String descricao){
        try {
            objespecieDAO.setDescricao(descricao);
            rs = objespecieDAO.ConsultarEspecieDAO();
            return rs;
        } catch (Exception e) {
            System.out.println(""+e.getMessage());
            return rs;
        }
    }
}
