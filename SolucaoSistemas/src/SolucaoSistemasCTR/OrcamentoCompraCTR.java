/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.OrcamentoCompraDAO;
import java.sql.ResultSet;

/**
 *
 * @author Batman
 */
public class OrcamentoCompraCTR {
    OrcamentoCompraDAO objorcamentoDAO = new OrcamentoCompraDAO();
    ResultSet rs;
    
    public boolean CadastrarOrcamentoCompraCTR(String... parametros){
        try {
            objorcamentoDAO.setFornecedor(parametros[0]);
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
            objorcamentoDAO.CadastrarOrcamentoCompraDAO();
            return true;
            
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarOrcamentoCompraCTR "+e.getMessage());
            return false;
        }
    }
    
    public boolean CancelarOrcamentoCompraCTR(String... parametros){
        try {
            objorcamentoDAO.setSituacao(parametros[0]);
            objorcamentoDAO.setCodigo(parametros[1]);
            objorcamentoDAO.CancelarOrcamentoCompraDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CancelarOrcamentoCompraCTR "+e.getMessage());
            return false;
        }
    }
    public ResultSet ConsultarOrcamentoCompraCTR(String... parametro){
        try {
            objorcamentoDAO.setFornecedor(parametro[0]);
            objorcamentoDAO.setAtendimento(parametro[1]);
           
            rs = objorcamentoDAO.ConsultarOrcamentoCompraDAO();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarOrcamentoCompraCTR "+e.getMessage());
            return rs;
        }
    }
    
    public ResultSet FecharOrcamentoCompraCTR(String codigo){
        try {
            objorcamentoDAO.setCodigo(codigo);
            rs = objorcamentoDAO.FecharOrcamentoCompraDAO();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao FecharOrcamentoCompraCTR "+e.getMessage());
            return rs;
        }
    }
    
    public ResultSet ConsultarMaiorCodigoOrcamentoCompraCTR(){
        try {
            rs = objorcamentoDAO.ConsultarMaiorCodigoOrcamentoCompraDAO();
            return rs;
        } catch (Exception e) {
            
            return rs;
        }
    }
}
