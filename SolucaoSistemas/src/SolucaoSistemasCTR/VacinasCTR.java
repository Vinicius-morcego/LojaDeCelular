/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.VacinasDAO;
import java.sql.ResultSet;

/**
 *
 * @author Vinicius
 */
public class VacinasCTR {
    VacinasDAO objvacinasDAO = new VacinasDAO();
    
    ResultSet rs;
    
    public boolean CadastrarVacinasCTR(String... parametros){
        try {
            objvacinasDAO.setDescricao(parametros[0]);
            objvacinasDAO.setSituacao(parametros[1]);
            objvacinasDAO.CadastrarVacinasDAO();
            return true;
        } catch (Exception e) {
            
            return false;
        }
    }
    
    public boolean AlterarVacinasCTR(String... parametros){
        try {
            objvacinasDAO.setDescricao(parametros[0]);
            objvacinasDAO.setSituacao(parametros[1]);
            objvacinasDAO.setCodigo(parametros[2]);
            
            objvacinasDAO.AlterarVacinasDAO();
            return true;
        } catch (Exception e) {
            
            return false;
        }
    }
    
    public ResultSet ConsultarVacinasCTR(String descricao){
        try {
            objvacinasDAO.setDescricao(descricao);
            rs = objvacinasDAO.ConsultarVacinasDAO();
            return rs;
        } catch (Exception e) {
            
            return rs;
        }
    }
    
    public ResultSet ConsultarMaiorCodigoVacinasCTR(){
        try {
            
            rs = objvacinasDAO.ConsultarMaiorCodigoVacinasDAO();
            return rs;
        } catch (Exception e) {
            
            return rs;
        }
    }
}
