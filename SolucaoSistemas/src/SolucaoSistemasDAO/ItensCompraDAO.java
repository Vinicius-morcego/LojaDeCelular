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
public class ItensCompraDAO {
    String codigo, produto, quantidade, subtotal, unitario, compra, comando;

    public String getCompra() {
        return compra;
    }

    public void setCompra(String compra) {
        this.compra = compra;
    }

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
        
    PreparedStatement pst;
    ResultSet rs;
    
    public boolean CadastrarItensCompraDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "insert into itenscompra (produto, quantidade, unitario, subtotal, compra) "
                    + "values(?,?,?,?,(select max(codigo) from compra))";
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setInt(1, Integer.parseInt(getProduto()));
            pst.setDouble(2, Double.parseDouble(getQuantidade().replace(",", ".")));
            pst.setDouble(3, Double.parseDouble(getUnitario().replace("R$", "").replace(",", ".")));
            pst.setDouble(4, Double.parseDouble(getSubtotal().replace("R$", "").replace(".", "").
                    replace(",", ".")));
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
                    
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Erro ao CadastrarItensCompraDAO "+e.getMessage());
            return false;
        }
    }
    
    public ResultSet ConsultarItensCompraDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select produto, quantidade, unitario, subtotal, compra "
                    + "from itenscompra where compra = "+getCompra();
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
