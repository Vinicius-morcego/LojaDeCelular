/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Batman
 */
public class PermissaoRelatorioDAO {
    String codigo, estoque, financeiro, planocontas, auditoria, funcionario, comando;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEstoque() {
        return estoque;
    }

    public void setEstoque(String estoque) {
        this.estoque = estoque;
    }

    public String getFinanceiro() {
        return financeiro;
    }

    public void setFinanceiro(String financeiro) {
        this.financeiro = financeiro;
    }

    public String getPlanocontas() {
        return planocontas;
    }

    public void setPlanocontas(String planocontas) {
        this.planocontas = planocontas;
    }

    public String getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(String auditoria) {
        this.auditoria = auditoria;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }
    
    PreparedStatement pst;
    ResultSet rs;
    public boolean CadastrarPermissaoRelatorioDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "insert into permissao_relatorio (estoque, financeiro, planocontas, "
                    + "auditoria, funcionario) values(?,?,?,?,?)";
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setString(1, getEstoque());
            pst.setString(2, getFinanceiro());
            pst.setString(3, getPlanocontas());
            pst.setString(4, getAuditoria());
            pst.setInt(5, Integer.parseInt(getFuncionario()));
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarPermissaoRelatorioDAO "+e.getMessage());
            return false;
        }
    }
    
    public boolean AlterarPermissaoRelatorioDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "update permissao_relatorio set estoque = ?, financeiro = ?, "
                    + "planocontas = ?, auditoria = ? where funcionario = ? ";             
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setString(1, getEstoque());
            pst.setString(2, getFinanceiro());
            pst.setString(3, getPlanocontas());
            pst.setString(4, getAuditoria());
            pst.setInt(5, Integer.parseInt(getFuncionario()));
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao AlterarPermissaoRelatorioDAO "+e.getMessage());
            return false;
        }
    }
    
    public ResultSet ConsultarPermissaoRelatorioDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select estoque, financeiro, planocontas, auditoria from permissao_relatorio "
                    + "where funcionario = "+getFuncionario();
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao COnsultarPermissaoRelatorioDAO "+e.getMessage());
            return rs;
        }
    }
    
    
}
