/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.HistoricoReceberDAO;
import java.sql.ResultSet;

/**
 *
 * @author Batman
 */
public class HistoricoReceberCTR {
    HistoricoReceberDAO objhistoricoDAO = new HistoricoReceberDAO();
    ResultSet rs;
    
    public boolean CadastrarHistoricoReceberCTR(String... parametros){
        try {
            objhistoricoDAO.setParcela(parametros[0]);
            objhistoricoDAO.setValorPago(parametros[1]);
            objhistoricoDAO.setFormaPgto(parametros[2]);
            objhistoricoDAO.setQuitar(parametros[3]);
            objhistoricoDAO.CadastrarHistoricoReceberDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarHistoricoReceberCTR "+e.getMessage());
            return false;
        }
    }
}
