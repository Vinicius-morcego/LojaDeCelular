/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasDAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Batman
 */
public class OrdemServicoDAO {

    String codigo, cliente, data, descricao, situacao, comando, modelo, marca, cor,
            numeroSerie;

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

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

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    PreparedStatement pst;
    ResultSet rs;

    public boolean CadastrarOrdemServicoDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "insert into ordem_servico (cliente, data, descricao, situacao,datacadastro,"
                    + "modelo, marca, cor, numero_serie) "
                    + "values(?,?,?,?,now(),?,?,?,?)";
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setInt(1, Integer.parseInt(getCliente()));
            pst.setDate(2, Date.valueOf(getData()));
            pst.setString(3, getDescricao());
            pst.setString(4, getSituacao());
            pst.setString(5, getModelo());
            pst.setString(6, getMarca());
            pst.setString(7, getCor());
            pst.setString(8, getNumeroSerie());
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;

        } catch (SQLException | NumberFormatException e) {
            System.out.println("Erro ao CadastrarOrdemServicoDAO " + e.getMessage());

            return false;
        }
    }

    public ResultSet ConsultarOrdemServicoDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select o.codigo, p.nome, p.telefone1, to_char(o.data, 'dd/mm/yyyy') as dat, o.descricao, "
                    + "o.situacao, o.modelo, o.marca, o.cor, o.numero_serie "
                    + "from ordem_servico o inner join pessoa p on p.codigo = o.cliente "
                    + "where o.situacao in('" + getSituacao() + "') and "
                    + "p.nome like '" + getCliente() + "%' order by o.codigo desc limit 100";
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarOrdemServicoDAO " + e.getMessage());
            return rs;
        }
    }

    public ResultSet ConsultarMaiorCodigoOrdemServicoDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select lpad(cast(coalesce((max(codigo)+1), '1') as varchar), 8, '0') "
                    + "from ordem_servico";
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println("Erro ao ConsultarMaiorCodigoOrdemServicoDAO " + e.getMessage());
            return rs;
        }
    }

}
