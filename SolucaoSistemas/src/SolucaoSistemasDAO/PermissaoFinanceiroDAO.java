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
public class PermissaoFinanceiroDAO {
    String codigo, caixa, contasReceber, contasPagar, funcionario, comando;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCaixa() {
        return caixa;
    }

    public void setCaixa(String caixa) {
        this.caixa = caixa;
    }

    public String getContasReceber() {
        return contasReceber;
    }

    public void setContasReceber(String contasReceber) {
        this.contasReceber = contasReceber;
    }

    public String getContasPagar() {
        return contasPagar;
    }

    public void setContasPagar(String contasPagar) {
        this.contasPagar = contasPagar;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }
    
    PreparedStatement pst;
    ResultSet rs;
    public boolean CadastrarPermissaoFinanceiroDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "insert into permissao_financeiro (caixa, contasreceber, contaspagar, "
                    + "funcionario) values(?,?,?,?)";
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setString(1, getCaixa());
            pst.setString(2, getContasReceber());
            pst.setString(3, getContasPagar());
            pst.setInt(4, Integer.parseInt(getFuncionario()));
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarPermissaoFinanceiroDAO "+e.getMessage());
            return false;
        }
    }
    
    public boolean AlterarPermissaoFinanceiroDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "update permissao_financeiro set caixa = ?, contasreceber = ?, "
                    + "contaspagar = ? where funcionario = ? ";             
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setString(1, getCaixa());
            pst.setString(2, getContasReceber());
            pst.setString(3, getContasPagar());
            pst.setInt(4, Integer.parseInt(getFuncionario()));
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao AlterarPermissaoFinanceiroDAO "+e.getMessage());
            return false;
        }
    }
    
    public ResultSet ConsultarPermissaoFinanceiroDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select caixa, contasreceber, contaspagar from permissao_financeiro "
                    + "where funcionario = "+getFuncionario();
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao COnsultarPermissaoFinanceiroDAO "+e.getMessage());
            return rs;
        }
    }
}
