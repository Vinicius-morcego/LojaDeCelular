/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.OrdemServicoDAO;
import java.sql.ResultSet;

/**
 *
 * @author Batman
 */
public class OrdemServicoCTR {
    OrdemServicoDAO objordemDAO = new OrdemServicoDAO();
    ResultSet rs;
    public boolean CadastrarOrdemServicoCTR(String... parametros){
        try {
            objordemDAO.setCliente(parametros[0]);
            objordemDAO.setData(parametros[1]);
            objordemDAO.setDescricao(parametros[2]);
            objordemDAO.setSituacao(parametros[3]);
            objordemDAO.setModelo(parametros[4]);
            objordemDAO.setMarca(parametros[5]);
            objordemDAO.setCor(parametros[6]);
            objordemDAO.setNumeroSerie(parametros[7]);
            
            objordemDAO.CadastrarOrdemServicoDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarOrdemServicoCTR "+e.getMessage());
            return false;
        }
    }
    
    public ResultSet ConsultarOrdemServicoCTR(String... parametros){
        try {
            objordemDAO.setSituacao(parametros[0]);
            objordemDAO.setCliente(parametros[1]);
            rs = objordemDAO.ConsultarOrdemServicoDAO();
            return rs;
            
        } catch (Exception e) {
            return rs;
        }
    }
    
    public ResultSet ConsultarMaiorCodigoOrdemServicoCTR(){
        try {
            rs = objordemDAO.ConsultarMaiorCodigoOrdemServicoDAO();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarMaiorCodigoOrdemServicoCTR "+e.getMessage());
            return rs;
        }
    }
}
