/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.PlanocontasDAO;
import java.sql.ResultSet;

/**
 *
 * @author Batman
 */
public class PlanocontasCTR {
    PlanocontasDAO objplanoDAO = new PlanocontasDAO();
    ResultSet rs;
    
    public boolean AtualizarPlanoContasCTR(String codigo, String descricao){
        try {
            objplanoDAO.setDescricao(descricao);
            objplanoDAO.setCodigo(codigo);
            objplanoDAO.AtualizarPlanoContasDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao AtualizarPlanoContasCTR "+e.getMessage());
            return false;
        }
        
    }
    
    public boolean CadastrarPlanoContasCTR(String descricao, String tipo){
        try {
            objplanoDAO.setDescricao(descricao);
            objplanoDAO.setTipo(tipo);
            objplanoDAO.CadastrarPlanoContasDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarPlanoContasCTR "+e.getMessage());
            return false;
        }
        
    }
    public ResultSet ConsultarPlanoContasCTR(String... parametros){
        try {
            objplanoDAO.setReceita(parametros[0]);
            objplanoDAO.setDespesa(parametros[1]);
            rs = objplanoDAO.ConsultarPlanoContasDAO();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarPlanoContasCTR "+e.getMessage());
            return rs;
        }
    }
}
