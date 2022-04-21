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
public class ItensVendaDAO {
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
    
    public boolean CadastrarItensVendaDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "insert into itensvenda (produto, quantidade, unitario, subtotal, venda) "
                    + "values(?,?,?,?,(select max(codigo) from venda))";
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setInt(1, Integer.parseInt(getProduto()));
            pst.setDouble(2, Double.parseDouble(getQuantidade().replace(",", ".")));
            pst.setDouble(3, Double.parseDouble(getUnitario().replace("R$", "").replace(".", "").replace(",", ".")));
            pst.setDouble(4, Double.parseDouble(getSubtotal().replace("R$", "").replace(".", "").replace(",", ".")));
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
                    
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Erro ao CadastrarItensVendaDAO "+e.getMessage());
            return false;
        }
    }
    
    public ResultSet ConsultarItensVendaDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select produto, quantidade, unitario, subtotal, venda "
                    + "from itensvenda where venda = "+getOrcamento();
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            return rs;
                    
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarItensVendaDAO "+e.getMessage());
            return rs;
        }
    }
}
