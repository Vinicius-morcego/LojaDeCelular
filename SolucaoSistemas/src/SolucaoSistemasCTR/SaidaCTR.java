/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SolucaoSistemasCTR;


import SolucaoSistemasDAO.SaidaDAO;
import java.sql.ResultSet;

/**
 *
 * @author Vinicius
 */
public class SaidaCTR {
    SaidaDAO objsaidaDAO = new SaidaDAO();
    ResultSet rs;
    public boolean CadastrarSaidaCTR(String... parametros){
        try {
            objsaidaDAO.setPlanoContas(parametros[0]);
            objsaidaDAO.setValor(parametros[1]);
            objsaidaDAO.setTipo(parametros[2]);
            objsaidaDAO.setFormaPgto(parametros[3]);            
            objsaidaDAO.setObservacao(parametros[4]);
            objsaidaDAO.CadastrarSaidaDAO();
            
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarSaidaCTR "+e.getMessage());
            return false;
        }
    }
    
     public boolean AlterarSaidaCTR(String... parametros){
        try {
            objsaidaDAO.setValor(parametros[0]);
            objsaidaDAO.setFormaPgto(parametros[1]);
            objsaidaDAO.setObservacao(parametros[2]);
            objsaidaDAO.setCaixa(parametros[3]);
            objsaidaDAO.AlterarSaidaDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao AlterarSaidaCTR "+e.getMessage());
            return false;
        }
    }
}
