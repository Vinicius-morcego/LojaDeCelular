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
public class ItensOrcamentoDAO {
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
    
    public boolean CadastrarItensOrcamentoDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "insert into itensorcamento (produto, quantidade, unitario, subtotal, orcamento) "
                    + "values(?,?,?,?,(select max(codigo) from orcamento))";
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
            System.out.println("Erro ao CadastrarItensOrcamentoDAO "+e.getMessage());
            return false;
        }
    }
    
    public ResultSet ConsultarItensOrcamentoDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select i.produto, e.nome as func, i.quantidade, i.unitario, i.subtotal, "
                    + "p.nome from "
                    + "itensorcamento i "
                    + "inner join comissao_orcamento c "
                    + "on c.orcamento = i.orcamento "
                    + "inner join produto_servico p on p.codigo = i.produto "
                    + "inner join pessoa e on e.codigo = c.funcionario "
                    + "where i.orcamento = "+getOrcamento();
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarItensOrcamentoDAO "+e.getMessage());
            
            return rs;
        }
    }
            
}
