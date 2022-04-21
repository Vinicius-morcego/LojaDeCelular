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
public class ItensOrcamentoCompraDAO {
    String codigo, produto, quantidade, subtotal, unitario, orcamentoCompra, comando;

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

    public String getOrcamentoCompra() {
        return orcamentoCompra;
    }

    public void setOrcamentoCompra(String orcamentoCompra) {
        this.orcamentoCompra = orcamentoCompra;
    }
    
    PreparedStatement pst;
    ResultSet rs;
    
    public boolean CadastrarItensOrcamentoCompraDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "insert into itensorcamento_compra (produto, quantidade, unitario, subtotal, "
                    + "orcamento_compra) "
                    + "values(?,?,?,?,(select max(codigo) from orcamento_compra))";
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setInt(1, Integer.parseInt(getProduto()));
            pst.setDouble(2, Double.parseDouble(getQuantidade().replace(",", ".")));
            pst.setDouble(3, Double.parseDouble(getUnitario().replace("R$", "").replace(",", ".")));
            pst.setDouble(4, Double.parseDouble(getSubtotal().replace("R$", "").replace(".", "").replace(",", ".")));
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
                    
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Erro ao CadastrarItensOrcamentoCompraDAO "+e.getMessage());
            return false;
        }
    }
    
    public ResultSet ConsultarItensOrcamentoCompraDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select distinct i.produto, o.funcionario as func, i.quantidade, "
                    + "i.unitario, i.subtotal, "
                    + "p.nome from "
                    + "itensorcamento_compra i "
                    + "inner join "
                    + "orcamento_compra o on o.codigo = i.orcamento_compra "                    
                    + "inner join produto_servico p on p.codigo = i.produto "
                    + "where i.orcamento_compra = "+getOrcamentoCompra();
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarItensOrcamentoCompraDAO "+e.getMessage());
            
            return rs;
        }
    }
}
