/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.OrcamentoDAO;
import java.sql.ResultSet;

/**
 *
 * @author Batman
 */
public class OrcamentoCTR {
    OrcamentoDAO objorcamentoDAO = new OrcamentoDAO();
    ResultSet rs;
    
    public boolean CadastrarOrcamentoCTR(String... parametros){
        try {
            objorcamentoDAO.setCliente(parametros[0]);
            objorcamentoDAO.setAnimal(parametros[1]);
            objorcamentoDAO.setDesconto(parametros[2]);
            objorcamentoDAO.setTotal(parametros[3]);
            objorcamentoDAO.setFuncionario(parametros[4]);
            objorcamentoDAO.setVeterinario(parametros[5]);
            objorcamentoDAO.setSituacao(parametros[6]);
            objorcamentoDAO.setAtendimento(parametros[7]);
            objorcamentoDAO.setDataEntrega(parametros[8]);
            objorcamentoDAO.setHoraEntrega(parametros[9]);
            objorcamentoDAO.setObservacao(parametros[10]);
            objorcamentoDAO.setDataOrcamento(parametros[11]);
            objorcamentoDAO.setHoraOrcamento(parametros[12]);      
            objorcamentoDAO.CadastrarOrcamentoDAO();
            return true;
            
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarOrcamentoCTR "+e.getMessage());
            return false;
        }
    }
    
    public boolean CancelarOrcamentoCTR(String... parametros){
        try {
            objorcamentoDAO.setSituacao(parametros[0]);
            objorcamentoDAO.setCodigo(parametros[1]);
            objorcamentoDAO.CancelarOrcamentoDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CancelarOrcamentoCTR "+e.getMessage());
            return false;
        }
    }
    public ResultSet ConsultarOrcamentoCTR(String... parametro){
        try {
            objorcamentoDAO.setCliente(parametro[0]);
            objorcamentoDAO.setAtendimento(parametro[1]);
           
            rs = objorcamentoDAO.ConsultarOrcamentoDAO();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarOrcamentoCTR "+e.getMessage());
            return rs;
        }
    }
    
    public ResultSet FecharOrcamentoCTR(String codigo){
        try {
            objorcamentoDAO.setCodigo(codigo);
            rs = objorcamentoDAO.FecharOrcamentoDAO();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao FecharOrcamentoCTR "+e.getMessage());
            return rs;
        }
    }
    
    public ResultSet ConsultarMaiorCodigoOrcamentoCTR(){
        try {
            rs = objorcamentoDAO.ConsultarMaiorCodigoOrcamentoDAO();
            return rs;
        } catch (Exception e) {
            
            return rs;
        }
    }
}
