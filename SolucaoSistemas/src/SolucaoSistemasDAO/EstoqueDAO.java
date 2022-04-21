/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Batman
 */
public class EstoqueDAO {
    String codigo, produto, dataAtualizacao, estoqueAtual, comando;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(String dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public String getEstoqueAtual() {
        return estoqueAtual;
    }

    public void setEstoqueAtual(String estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }
    
    PreparedStatement pst;
    ResultSet rs;
    public boolean CadastrarEstoqueDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "insert into estoque (produto, data_atualizacao, estoqueatual) "
                    + "values((select max(codigo) from produto_servico), now(), ?)";
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setDouble(1, Double.parseDouble(getEstoqueAtual().replace(",", ".")));            
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            
            return true;
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Erro ao CadastrarEstoqueDAO "+e.getMessage());
            return false;
        }
    }
    
    public boolean AlterarEstoqueDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "update estoque set estoqueatual = ?, data_atualizacao = now() "
                    + "where produto = ?";
                    
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setDouble(1, Double.parseDouble(getEstoqueAtual().replace(",", ".")));  
            pst.setInt(2, Integer.parseInt(getProduto()));
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            
            return true;
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Erro ao CadastrarEstoqueDAO "+e.getMessage());
            return false;
        }
    }
}
