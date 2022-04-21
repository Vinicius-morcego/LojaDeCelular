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
 * @author Vinicius
 */
public class UnidadeMedidaDAO {

    String codigo, descricao, situacao, comando;

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

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    PreparedStatement pst;
    ResultSet rs;

    public boolean CadastrarUnidadeDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "insert into unidademedida (descricao, situacao) values(?,?)";
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            pst.setString(1, getDescricao());
            pst.setString(2, getSituacao());
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
        } catch (Exception e) {

            return false;
        }
    }

    public boolean AlterarUnidadeDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "update unidademedida set descricao = ?, situacao = ? where codigo = ?";
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            pst.setString(1, getDescricao());
            pst.setString(2, getSituacao());
            pst.setInt(3, Integer.parseInt(getCodigo()));
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
        } catch (SQLException | NumberFormatException e) {

            return false;
        }
    }

    public ResultSet ConsultarUnidadeDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select codigo, descricao from unidademedida "
                    + "where situacao in('ATIVO') order by descricao";
            pst = ConectaBanco.con.prepareCall(comando,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarUnidadeDAO " + e.getMessage());
            return rs;
        }
    }
}
