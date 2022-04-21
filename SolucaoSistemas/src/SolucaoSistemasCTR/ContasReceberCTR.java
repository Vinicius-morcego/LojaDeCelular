/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.ContasReceberDAO;
import java.sql.ResultSet;

/**
 *
 * @author Batman
 */
public class ContasReceberCTR {
    ContasReceberDAO objcontasDAO = new ContasReceberDAO();
    ResultSet rs;
    
    public boolean CadastrarContasReceberCTR(String... parametro){
        try {
            objcontasDAO.setCliente(parametro[0]);
            objcontasDAO.setFaturamento(parametro[1]);
            objcontasDAO.setHistorico(parametro[2]);
            objcontasDAO.setValorParcela(parametro[3]);
            objcontasDAO.setVencimento(parametro[4]);
            objcontasDAO.setSituacao(parametro[5]);
            objcontasDAO.setNumeroParcela(parametro[6]);
            objcontasDAO.setPorExtenso(parametro[7]);
            objcontasDAO.setFormaPagamento(parametro[8]);
            objcontasDAO.CadastrarContasReceberDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarContasReceberCTR "+e.getMessage());
            return false;
        }
    }
    
    public boolean CancelarContasReceberCTR(String codigo){
        try {
            objcontasDAO.setCodigo(codigo);
            objcontasDAO.CancelarContasReceberDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CancelarContasReceberCTR "+e.getMessage());
            return false;
        }
    }
    
    public ResultSet ConsultarContasReceberCTR(String... parametros){
        try {
            objcontasDAO.setDataInicial(parametros[0]);
            objcontasDAO.setDataFinal(parametros[1]);
            rs = objcontasDAO.ConsultarContasReceberDAO();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarContasReceberCTR "+e.getMessage());
            return rs;
        }
    }
}
