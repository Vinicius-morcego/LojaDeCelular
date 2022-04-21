/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.VendaDAO;
import java.sql.ResultSet;

/**
 *
 * @author Batman
 */
public class VendaCTR {
    VendaDAO objvendaDAO = new VendaDAO();
    ResultSet rs;
     
    
    public boolean CadastrarVendaCTR(String... parametros){
        try {
            objvendaDAO.setCliente(parametros[0]);
            objvendaDAO.setAnimal(parametros[1]);
            objvendaDAO.setDesconto(parametros[2]);
            objvendaDAO.setTotal(parametros[3]);
            objvendaDAO.setFuncionario(parametros[4]);
            objvendaDAO.setVeterinario(parametros[5]);
            objvendaDAO.setSituacao(parametros[6]);
            objvendaDAO.setAtendimento(parametros[7]);
            objvendaDAO.setDataEntrega(parametros[8]);
            objvendaDAO.setHoraEntrega(parametros[9]);
            objvendaDAO.setDataPedido(parametros[10]);
            objvendaDAO.setHoraPedido(parametros[11]);
            objvendaDAO.setObservacao(parametros[12]);
            objvendaDAO.CadastrarVendaDAO();
            return true;
            
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarVendaCTR "+e.getMessage());
            return false;
        }
    }
    
    public ResultSet ConsultarMaiorCodigoVendaCTR(){
        try {
            rs = objvendaDAO.ConsultarMaiorCodigoVendaDAO();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarMaiorCodigoVendaCTR "+e.getMessage());
            return rs;
        }
    }
    
    public ResultSet ConsultarVendaCTR(String... parametros){
        try {
            objvendaDAO.setCliente(parametros[0]);
            objvendaDAO.setSituacao(parametros[1]);
            
            rs = objvendaDAO.ConsultarVendaDAO();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarVendaCTR "+e.getMessage());
            return rs;
        }
    }
}
