/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.Historico_OrdemServicoDAO;
import java.sql.ResultSet;

/**
 *
 * @author Vinicius
 */
public class Historico_OrdemServicoCTR {
    Historico_OrdemServicoDAO objHistorico = new Historico_OrdemServicoDAO();
    ResultSet rs;
    public boolean CadastrarHistoricoOrdemCTR(String... parametros){
        try {
            objHistorico.setNumeroOS(parametros[0]);
            objHistorico.setObservacao(parametros[1]);
            objHistorico.setSituacao(parametros[2]);
            objHistorico.CadastrarHistoricoOrdemDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarHistoricoOrdemCTR "+e.getMessage());
            return false;
        }
    }
    
    public boolean AlterarHistoricoOrdemCTR(String... parametros){
        try {
            objHistorico.setNumeroOS(parametros[0]);
            objHistorico.setObservacao(parametros[1]);
            objHistorico.setSituacao(parametros[2]);
            objHistorico.setCodigo(parametros[3]);
            objHistorico.AlterarHistoricoOrdemDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao AlterarHistoricoOrdemCTR "+e.getMessage());
            return false;
        }
    }
    
    public ResultSet ConsultarHistoricoOrdemCTR(String codigo){
        try {
            objHistorico.setNumeroOS(codigo);
            rs = objHistorico.ConsultarHistoricoOrdemDAO();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarHistoricoOrdemCTR "+e.getMessage());
            return rs;
        }
    }
}
