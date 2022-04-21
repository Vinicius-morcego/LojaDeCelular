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
public class ItensVendaCanceladaDAO {
    String codigo, produto, quantidade, subtotal, unitario, orcamento, comando;

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

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getUnitario() {
        return unitario;
    }

    public void setUnitario(String unitario) {
        this.unitario = unitario;
    }

    public String getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(String orcamento) {
        this.orcamento = orcamento;
    }
    
    PreparedStatement pst;
    ResultSet rs;
    
    public boolean CadastrarItensVendaCanceladaDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "insert into itensvenda_cancelada "
                    + "(produto, quantidade, unitario, subtotal, venda) "
                    + "values(?,?,?,?,?)";
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setInt(1, Integer.parseInt(getProduto()));
            pst.setDouble(2, Double.parseDouble(getQuantidade()));
            pst.setDouble(3, Double.parseDouble(getUnitario()));
            pst.setDouble(4, Double.parseDouble(getSubtotal()));
            pst.setInt(5, Integer.parseInt(getOrcamento()));
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
                    
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Erro ao CadastrarItensVendaCanceladaDAO "+e.getMessage());
            return false;
        }
        
        
    }
    
    
}
