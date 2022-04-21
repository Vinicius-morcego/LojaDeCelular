/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.ProdutoServicoDAO;
import java.sql.ResultSet;

/**
 *
 * @author Vinicius
 */
public class ProdutoServicoCTR {
    ProdutoServicoDAO objprodutoDAO = new ProdutoServicoDAO();
    ResultSet rs;
    
    public boolean CadastrarProdutoCTR(String... parametros){
        try {
            objprodutoDAO.setNome(parametros[0]);
            objprodutoDAO.setCodigobarras(parametros[1]);
            objprodutoDAO.setUnidade(parametros[2]);
            objprodutoDAO.setReferencia(parametros[3]);
            objprodutoDAO.setLocalizacao(parametros[4]);
            objprodutoDAO.setMarca(parametros[5]);
            objprodutoDAO.setCategoria(parametros[6]);
            objprodutoDAO.setControlaestoque(parametros[7]);
            objprodutoDAO.setAtualizarCusto(parametros[8]);
            objprodutoDAO.setVenderZerado(parametros[9]);
            objprodutoDAO.setEstoqueMinimo(parametros[10]);
            objprodutoDAO.setValorCusto(parametros[11]);
            objprodutoDAO.setMargem(parametros[12]);
            objprodutoDAO.setValorVenda(parametros[13]);
            objprodutoDAO.setObservacao(parametros[14]);
            objprodutoDAO.setTipo(parametros[15]);
            objprodutoDAO.setImagem(parametros[16]);
            objprodutoDAO.setDataValidade(parametros[17]);
            objprodutoDAO.CadastrarProdutoDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarProdutoCTR "+e.getMessage());
            return false;
        }
    }
    
     public boolean AlterarProdutoCTR(String... parametros){
        try {
            objprodutoDAO.setNome(parametros[0]);
            objprodutoDAO.setUnidade(parametros[1]);
            objprodutoDAO.setReferencia(parametros[2]);
            objprodutoDAO.setLocalizacao(parametros[3]);
            objprodutoDAO.setMarca(parametros[4]);
            objprodutoDAO.setCategoria(parametros[5]);
            objprodutoDAO.setControlaestoque(parametros[6]);
            objprodutoDAO.setAtualizarCusto(parametros[7]);
            objprodutoDAO.setVenderZerado(parametros[8]);
            objprodutoDAO.setEstoqueMinimo(parametros[9]);
            objprodutoDAO.setValorCusto(parametros[10]);
            objprodutoDAO.setMargem(parametros[11]);
            objprodutoDAO.setValorVenda(parametros[12]);
            objprodutoDAO.setObservacao(parametros[13]);
            objprodutoDAO.setTipo(parametros[14]);
            objprodutoDAO.setDataValidade(parametros[15]);
            objprodutoDAO.setCodigo(parametros[16]);
            objprodutoDAO.AlterarProdutoDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao AlterarProdutoCTR "+e.getMessage());
            return false;
        }
    }
     
     public boolean AlterarImagemProdutoCTR(String foto, String codigo){
         try {
             objprodutoDAO.setImagem(foto);
             objprodutoDAO.setCodigo(codigo);
             objprodutoDAO.AlterarImagemProdutoDAO();
             return true;
         } catch (Exception e) {
             System.out.println("Erro ao AlterarImagemProdutoCTR "+e.getMessage());
             return false;
         }
     }
     public ResultSet ConsultarProdutoCTR(String... parametros){
         try {
             objprodutoDAO.setNome(parametros[0]);
             objprodutoDAO.setReferencia(parametros[1]);
             objprodutoDAO.setCodigobarras(parametros[2]);
             objprodutoDAO.setCodigo(parametros[3]);
             objprodutoDAO.setMargem(parametros[4]);
             rs = objprodutoDAO.ConsultarProdutoDAO();
             return rs;
         } catch (Exception e) {
             System.out.println("Erro ao ConsularProdutoCTR "+e.getMessage());
             return rs;
         }
     }
     
     public ResultSet ConsultarMaiorCodigoProdutoCTR(){
         try {
             rs = objprodutoDAO.ConsultarMaiorCodigoProdutoDAO();
             return rs;
         } catch (Exception e) {
             System.out.println("Erro ao ConsultarMaiorCodigoProdutoCTR "+e.getMessage());
             return rs;
         }
     }
     
     public ResultSet GerarCodigoBarrasCTR(String nome){
         try {
             objprodutoDAO.setNome(nome);
             rs = objprodutoDAO.GerarCodigoBarrasDAO();
             return rs;
         } catch (Exception e) {
             System.out.println("Erro ao GerarCodigoBarrasCTR "+e.getMessage());
             return rs;
         }
     }
}
