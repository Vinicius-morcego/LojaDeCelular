/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.MarcaDAO;
import java.sql.ResultSet;

/**
 *
 * @author Vinicius
 */
public class MarcaCTR {

    MarcaDAO objmarcaDAO = new MarcaDAO();
    ResultSet rs;

    public boolean CadastrarMarcaCTR(String... parametros) {
        try {
            objmarcaDAO.setDescricao(parametros[0]);
            objmarcaDAO.setSituacao(parametros[1]);
            objmarcaDAO.CadastrarMarcaDAO();
            return true;
        } catch (Exception e) {

            return false;
        }
    }

    public boolean AlterarMarcaCTR(String... parametros) {
        try {
            objmarcaDAO.setDescricao(parametros[0]);
            objmarcaDAO.setSituacao(parametros[1]);
            objmarcaDAO.setCodigo(parametros[2]);

            objmarcaDAO.AlterarMarcaDAO();
            return true;
        } catch (Exception e) {

            return false;
        }
    }
    
    public ResultSet ConsultarMarcaCTR(String descricao){
        try {
            objmarcaDAO.setDescricao(descricao);
            rs = objmarcaDAO.ConsultarMarcaDAO();
            return rs;
        } catch (Exception e) {
            
            return rs;
        }
    }
}
