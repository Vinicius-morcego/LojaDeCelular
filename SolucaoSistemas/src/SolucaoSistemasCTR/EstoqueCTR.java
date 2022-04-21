/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.EstoqueDAO;

/**
 *
 * @author Batman
 */
public class EstoqueCTR {
    EstoqueDAO objestoqueDAO = new EstoqueDAO();
    
    public boolean CadastrarEstoqueCTR(String estoqueAtual){
        try {
            objestoqueDAO.setEstoqueAtual(estoqueAtual);
            
            objestoqueDAO.CadastrarEstoqueDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarEstoqueCTR "+e.getMessage());
            return false;
        }
    }
    
    public boolean AlterarEstoqueCTR(String... parametros){
        try {
            objestoqueDAO.setEstoqueAtual(parametros[0]);
            objestoqueDAO.setProduto(parametros[1]);
            objestoqueDAO.AlterarEstoqueDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao AlterarEstoqueCTR "+e.getMessage());
            return false;
        }
    }
}
