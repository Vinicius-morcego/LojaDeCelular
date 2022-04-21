/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.UnidadeMedidaDAO;
import java.sql.ResultSet;

/**
 *
 * @author Vinicius
 */
public class UnidadeMedidaCTR {

    UnidadeMedidaDAO objunidadeDAO = new UnidadeMedidaDAO();

    ResultSet rs;

    public boolean CadastrarUnidadeCTR(String... parametros) {
        try {
            objunidadeDAO.setDescricao(parametros[0]);
            objunidadeDAO.setSituacao(parametros[1]);
            objunidadeDAO.CadastrarUnidadeDAO();
            return true;
        } catch (Exception e) {

            return false;
        }
    }

    public boolean AlterarUnidadeCTR(String... parametros) {
        try {
            objunidadeDAO.setDescricao(parametros[0]);
            objunidadeDAO.setSituacao(parametros[1]);
            objunidadeDAO.setCodigo(parametros[2]);

            objunidadeDAO.AlterarUnidadeDAO();
            return true;
        } catch (Exception e) {

            return false;
        }
    }
    
    public ResultSet ConsultarUnidadeCTR(){
        try {
            rs = objunidadeDAO.ConsultarUnidadeDAO();
            return rs;
        } catch (Exception e) {
            
            return rs;
        }
    }
}
