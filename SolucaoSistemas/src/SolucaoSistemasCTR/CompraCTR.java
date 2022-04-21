/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.CompraDAO;
import java.sql.ResultSet;

/**
 *
 * @author Batman
 */
public class CompraCTR {
      CompraDAO objcompraDAO = new CompraDAO();
      ResultSet rs;
     
    
    public boolean CadastrarCompraCTR(String... parametros){
        try {
                   
            objcompraDAO.setDesconto(parametros[0]);
            objcompraDAO.setTotal(parametros[1]);
            objcompraDAO.setFuncionario(parametros[2]);
            objcompraDAO.setFornecedor(parametros[3]);
            objcompraDAO.setSituacao(parametros[4]);            
            objcompraDAO.setDataEntrega(parametros[5]);
            objcompraDAO.setHoraEntrega(parametros[6]);
            objcompraDAO.setDataPedido(parametros[7]);
            objcompraDAO.setHoraPedido(parametros[8]);
            objcompraDAO.setObservacao(parametros[9]);
            objcompraDAO.CadastrarCompraDAO();
            return true;
            
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarCompraCTR "+e.getMessage());
            return false;
        }
    }
    
    public ResultSet ConsultarMaiorCodigoCompraCTR(){
        try {
            rs = objcompraDAO.ConsultarMaiorCodigoCompraDAO();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarMaiorCodigoCompraCTR "+e.getMessage());
            return rs;
        }
    }
    
    public ResultSet ConsultarCompraCTR(String... parametros){
        try {
            objcompraDAO.setSituacao(parametros[0]);
            objcompraDAO.setFornecedor(parametros[1]);
            rs = objcompraDAO.ConsultarCompraDAO();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarCompraCTR "+e.getMessage());
            return rs;
        }
    }
    
    
}
