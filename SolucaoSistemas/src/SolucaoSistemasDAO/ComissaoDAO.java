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
public class ComissaoDAO {
    String codigo, funcionario, valor, produto, quantidade, total, comando;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    
    PreparedStatement pst;
    ResultSet rs;
    
    public boolean CadastrarComissaoDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "insert into comissao (funcionario, valor, datacadastro, produto, quantidade, venda) "
                    + "values((select codigo from pessoa where nome = '"+getFuncionario()+"' "
                    + "and tipo in('FUNCIONARIO')),?,now(),?,?,"
                    + "(select max(codigo) from venda))";
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setDouble(1, Double.parseDouble(getValor().replace("R$", "").replace(",", ".")));
            pst.setInt(2, Integer.parseInt(getProduto()));
            pst.setDouble(3, Double.parseDouble(getQuantidade().replace(",", ".")));
           
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Erro ao CadastrarComissaoDAO "+e.getMessage());
            return false;
        }
    }
}
