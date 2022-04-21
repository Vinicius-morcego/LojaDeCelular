/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.CategoriaDAO;
import java.sql.ResultSet;

/**
 *
 * @author Vinicius
 */
public class CategoriaCTR {
    CategoriaDAO objcategoriaDAO = new CategoriaDAO();
    ResultSet rs;
    
    public boolean CadastrarCategoriaCTR(String... parametros){
        try {
            objcategoriaDAO.setDescricao(parametros[0]);
            objcategoriaDAO.setSituacao(parametros[1]);
            objcategoriaDAO.CadastrarCategoriaDAO();
            return true;
        } catch (Exception e) {
            
            return false;
        }
    }
    
    public boolean AlterarCategoriaCTR(String... parametros){
        try {
            objcategoriaDAO.setDescricao(parametros[0]);
            objcategoriaDAO.setSituacao(parametros[1]);
            objcategoriaDAO.setCodigo(parametros[2]);
            
            objcategoriaDAO.AlterarCategoriaDAO();
            return true;
        } catch (Exception e) {
            
            return false;
        }
    }
    
    public ResultSet ConsultarCategoriaCTR(String descricao){
        try {
            objcategoriaDAO.setDescricao(descricao);
            rs = objcategoriaDAO.ConsultarCategoriaDAO();
            return rs;
        } catch (Exception e) {
            
            return rs;
        }
    }
}
