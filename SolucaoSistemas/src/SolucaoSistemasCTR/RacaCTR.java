/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.RacaDAO;
import java.sql.ResultSet;

/**
 *
 * @author Vinicius
 */
public class RacaCTR {
    RacaDAO objracaDAO = new RacaDAO();
    
    ResultSet rs;

    public boolean CadastrarRacaCTR(String... parametros) {
        try {
            objracaDAO.setDescricao(parametros[0]);
            objracaDAO.setSituacao(parametros[1]);
            objracaDAO.CadastrarRacaDAO();
            return true;
        } catch (Exception e) {

            return false;
        }
    }

    public boolean AlterarRacaCTR(String... parametros) {
        try {
            objracaDAO.setDescricao(parametros[0]);
            objracaDAO.setSituacao(parametros[1]);
            objracaDAO.setCodigo(parametros[2]);

            objracaDAO.AlterarRacaDAO();
            return true;
        } catch (Exception e) {

            return false;
        }
    }
    
    public ResultSet ConsultarRacaCTR(String descricao){
        try {
            objracaDAO.setDescricao(descricao);
            rs = objracaDAO.ConsultarRacaDAO();
            return rs;
        } catch (Exception e) {
            System.out.println(""+e.getMessage());
            return rs;
        }
    }
}
