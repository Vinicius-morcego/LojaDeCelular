/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SolucaoSistemasCTR;


import SolucaoSistemasDAO.CaixaDAO;
import java.sql.ResultSet;

/**
 *
 * @author Vinicius
 */
public class CaixaCTR {
    CaixaDAO objcaixaDAO = new CaixaDAO();
    ResultSet rs;
    public boolean CadastrarCaixaCTR(String... parametros){
        try {
            objcaixaDAO.setFuncionario(parametros[0]);
            objcaixaDAO.setDataCadastro(parametros[1]);
            objcaixaDAO.CadastrarCaixaDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarCaixaCTR "+e.getMessage());
            return false;
        }
    }
    
    public boolean AlterarCaixaCTR(String... parametros){
        try {
            objcaixaDAO.setFuncionario(parametros[0]);
            objcaixaDAO.setDataCadastro(parametros[1]);
            objcaixaDAO.setCodigo(parametros[2]);
            objcaixaDAO.AlterarCaixaDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao AlterarCaixaCTR "+e.getMessage());
            return false;
        }
    }
    public ResultSet ConsultarCaixaCTR(){
        try {
            rs = objcaixaDAO.ConsultarCaixaDAO();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarCaixaCTR "+e.getMessage());
            return rs;
        }
    }
    
   
    
    public ResultSet ConsultarMaiorCodigoCaixaCTR(){
        try {
            rs = objcaixaDAO.ConsultarMaiorCodigoCaixaDAO();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarMaiorCodigoCaixaCTR "+e.getMessage());
            return rs;
        }
    }
    
    public ResultSet ConsultarSaldoAnteriorCTR(){
        try {
            rs = objcaixaDAO.ConsultarSaldoAnteriorDAO();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarSaldoAnteriorCTR "+e.getMessage());
            return rs;
        }
    }
    
    public ResultSet ConsultarMovimentoCaixaCTR(){
        try {
            rs = objcaixaDAO.ConsultarMovimentoCaixaDAO();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarMovimentoCaixaCTR "+e.getMessage());
            return rs;
        }
    }
    
    
}
