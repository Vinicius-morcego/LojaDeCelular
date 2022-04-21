/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.ItensCompraDAO;
import java.sql.ResultSet;

/**
 *
 * @author Batman
 */
public class ItensCompraCTR {
     ItensCompraDAO objitensDAO = new ItensCompraDAO();
     ResultSet rs;
    
    public boolean CadastrarItensCompraCTR(String... parametros){
        try {
            objitensDAO.setProduto(parametros[0]);
            objitensDAO.setQuantidade(parametros[1]);
            objitensDAO.setUnitario(parametros[2]);
            objitensDAO.setSubtotal(parametros[3]);
            objitensDAO.CadastrarItensCompraDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarItensCompraCTR "+e.getMessage());
            return false;
        }
    }
    public ResultSet ConsultarItensCompraCTR(String compra){
        try {
            objitensDAO.setCompra(compra);
            rs = objitensDAO.ConsultarItensCompraDAO();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarItensCompraCTR "+e.getMessage());
            return rs;
        }
    }
}
