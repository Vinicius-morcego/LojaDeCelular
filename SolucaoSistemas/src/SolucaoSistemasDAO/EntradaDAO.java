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
public class EntradaDAO {
    String codigo, planoContas, valor, tipo, formaPgto, comando, caixa, observacao;

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getCaixa() {
        return caixa;
    }

    public void setCaixa(String caixa) {
        this.caixa = caixa;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPlanoContas() {
        return planoContas;
    }

    public void setPlanoContas(String planoContas) {
        this.planoContas = planoContas;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFormaPgto() {
        return formaPgto;
    }

    public void setFormaPgto(String formaPgto) {
        this.formaPgto = formaPgto;
    }
    
    ResultSet rs;
    PreparedStatement pst;
    public boolean CadastrarEntradaDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "insert into entrada (planocontas, valor, tipo, formapgto, "
                    + "caixa, observacao, datacadastro) "
                    + "values((select codigo from planocontas where descricao = '"+getPlanoContas()+"' "
                    + "and tipo in('1. ATIVO')),?,?,(select codigo from planocontas "
                    + "where descricao = '" +getFormaPgto()+"'"
                    + " and tipo in('3. RECEITA')),(select max(codigo) from caixa), ?, now())";
            pst = ConectaBanco.con.prepareStatement(comando);
          //  pst.setInt(1, Integer.parseInt(getPlanoContas()));
            pst.setDouble(1, Double.parseDouble(getValor().
                    replace("R$", "").replace(".", "").replace(",", ".")));
            pst.setString(2, getTipo());
           // pst.setString(4, getFormaPgto());
            pst.setString(3, getObservacao());
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
        } catch (NumberFormatException | SQLException e) {
            System.out.println("Erro ao CadastrarEntradaDAO "+e.getMessage());
            return false;
        }
    }
    
    public boolean AlterarEntradaDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "update entrada set valor = ?, formapgto = (select codigo from planocontas "
                    + "where descricao = '"+getFormaPgto()+"' and tipo in('3. RECEITA')), observacao = ? "
                    + "where caixa = "+getCaixa();
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setDouble(1, Double.parseDouble(getValor().replace("R$", "").replace(".", "").replace(",", ".")));
            pst.setString(2, getObservacao());
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Erro ao AlterarEntradaDAO "+e.getMessage());
            return false;
        }
    }
}
