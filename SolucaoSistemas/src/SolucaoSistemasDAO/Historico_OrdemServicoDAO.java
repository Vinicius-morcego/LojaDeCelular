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
public class Historico_OrdemServicoDAO {

    String codigo, numeroOS, observacao, comando, situacao;
    PreparedStatement pst;
    ResultSet rs;

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNumeroOS() {
        return numeroOS;
    }

    public void setNumeroOS(String numeroOS) {
        this.numeroOS = numeroOS;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public boolean CadastrarHistoricoOrdemDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "insert into historico_ordem_servico (numero_os, observacao, datacadastro, situacao) "
                    + "values(?,?,now(),?)";
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setString(1, getNumeroOS());
            pst.setString(2, getObservacao());
            pst.setString(3, getSituacao());
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro CadastrarHistoricoOrdemDAO " + e.getMessage());
            return false;
        }
    }

    public boolean AlterarHistoricoOrdemDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "update historico_ordem_servico set numero_os = ?, observacao = ?, situacao = ? where codigo = ? ";
            pst.setString(1, getNumeroOS());
            pst.setString(2, getObservacao());
            pst.setString(3, getSituacao());
            pst.setString(4, getCodigo());
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro AlterarHistoricoOrdemDAO " + e.getMessage());
            return false;
        }
    }

    public ResultSet ConsultarHistoricoOrdemDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select situacao, observacao, to_char(datacadastro, 'dd/mm/yyyy') as dia from historico_ordem_servico where numero_os = ? order by codigo desc";
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, getNumeroOS());
            rs = pst.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println("Erro ao ConsultarHistoricoOrdemDAO " + e.getMessage());
            return rs;
        }
    }
}
