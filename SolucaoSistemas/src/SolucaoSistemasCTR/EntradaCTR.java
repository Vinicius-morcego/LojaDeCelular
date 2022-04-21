/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SolucaoSistemasCTR;


import SolucaoSistemasDAO.EntradaDAO;
import java.sql.ResultSet;

/**
 *
 * @author Vinicius
 */
public class EntradaCTR {
    EntradaDAO objentradaDAO = new EntradaDAO();
    ResultSet rs;
    public boolean CadastrarEntradaCTR(String... parametros){
        try {
            objentradaDAO.setPlanoContas(parametros[0]);
            objentradaDAO.setValor(parametros[1]);
            objentradaDAO.setTipo(parametros[2]);
            objentradaDAO.setFormaPgto(parametros[3]);
            objentradaDAO.setObservacao(parametros[4]);
           
            objentradaDAO.CadastrarEntradaDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarEntradaCTR "+e.getMessage());
            return false;
        }
    }
    
    public boolean AlterarEntradaCTR(String... parametros){
        try {
            objentradaDAO.setValor(parametros[0]);
            objentradaDAO.setFormaPgto(parametros[1]);
            objentradaDAO.setObservacao(parametros[2]);
            objentradaDAO.setCaixa(parametros[3]);
            objentradaDAO.AlterarEntradaDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao AlterarEntradaCTR "+e.getMessage());
            return false;
        }
    }
}
