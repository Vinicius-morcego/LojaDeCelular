/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.ContasPagarDAO;
import java.sql.ResultSet;

/**
 *
 * @author Batman
 */
public class ContasPagarCTR {
    ContasPagarDAO objcontasDAO = new ContasPagarDAO();
    ResultSet rs;
    public boolean CadastrarContasPagarCTR(String... parametros){
        try {
            objcontasDAO.setFornecedor(parametros[0]);
            objcontasDAO.setFaturamento(parametros[1]);
            objcontasDAO.setHistorico(parametros[2]);
            objcontasDAO.setValorParcela(parametros[3]);
            objcontasDAO.setVencimento(parametros[4]);
            objcontasDAO.setSituacao(parametros[5]);
            objcontasDAO.setNumeroParcela(parametros[6]);
            objcontasDAO.setPorExtenso(parametros[7]);
            objcontasDAO.setFormaPagamento(parametros[8]);
            objcontasDAO.CadastrarContasPagarDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarContasPagarCTR "+e.getMessage());
            return false;
        }
    }
    
    public boolean CancelarContasPagarCTR(String codigo){
        try {
            objcontasDAO.setCodigo(codigo);
            objcontasDAO.CancelarContasPagarDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CancelarContasPagarCTR "+e.getMessage());
            return false;
        }
    }
    public ResultSet ConsultarContasPagarCTR(String... parametros){
        try {
            objcontasDAO.setDataInicial(parametros[0]);
            objcontasDAO.setDataFinal(parametros[1]);
            rs = objcontasDAO.ConsultarContasPagarDAO();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarContasPagarCTR "+e.getMessage());
            return rs;
        }
    }
}
