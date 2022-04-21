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
public class PlanocontasDAO {
    String codigo, receita, tipo, despesa, descricao, comando;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getReceita() {
        return receita;
    }

    public void setReceita(String receita) {
        this.receita = receita;
    }

    public String getDespesa() {
        return despesa;
    }

    public void setDespesa(String despesa) {
        this.despesa = despesa;
    }

   
    PreparedStatement pst;
    ResultSet rs;
    
     public boolean CadastrarPlanoContasDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "insert into planocontas (descricao, tipo) values(?,?)";
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setString(1, getDescricao());
            pst.setString(2, getTipo());
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao CadastrarPlanoContasDAO "+e.getMessage());
            return false;
        }
    }
    public boolean AtualizarPlanoContasDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "update planocontas set descricao = ? where codigo = ?";
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setString(1, getDescricao());
            pst.setInt(2, Integer.parseInt(getCodigo()));
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao AtualizarPlanoContasDAO "+e.getMessage());
            return false;
        }
    }
    public ResultSet ConsultarPlanoContasDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select codigo, descricao from planocontas "
                    + "where tipo in('"+getReceita()+"') or tipo in('"+getDespesa()+"') "
                    + "order by descricao";
            pst = ConectaBanco.ConectaPostgres().prepareCall(comando, 
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarPlanocontasDAO "+e.getMessage());
            return rs;
        }
    }
}
