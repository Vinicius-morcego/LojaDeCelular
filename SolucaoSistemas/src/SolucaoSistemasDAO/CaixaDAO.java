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
 * @author Vinicius
 */
public class CaixaDAO {

    String codigo, dataCadastro, funcionario, comando;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    ResultSet rs;
    PreparedStatement pst;

    public boolean CadastrarCaixaDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "insert into caixa (datacadastro, funcionario, data_lancamento) "
                    + "values(now(), ?, ?)";
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setInt(1, Integer.parseInt(getFuncionario()));
            pst.setDate(2, Date.valueOf(getDataCadastro()));
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao CadastrarCaixaDAO " + e.getMessage());
            return false;
        }
    }

    public boolean AlterarCaixaDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "update caixa set funcionario = ?, data_lancamento = ? where codigo = ?";
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setInt(1, Integer.parseInt(getFuncionario()));
            pst.setDate(2, Date.valueOf(getDataCadastro()));
            pst.setInt(3, Integer.parseInt(getCodigo()));
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Erro ao AlterarCaixaDAO " + e.getMessage());
            return false;
        }
    }

    public ResultSet ConsultarMovimentoCaixaDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select (select coalesce(sum(e.valor), 0) from entrada e inner join caixa c "
                    + "on c.codigo = e.caixa where e.formapgto in('95') and "
                    + "to_char(c.data_lancamento, 'yyyy-mm-dd') = to_char(now(), 'yyyy-mm-dd') or "
                    + "to_char(c.data_lancamento, 'yyyy-mm-dd') = to_char(now(), 'yyyy-mm-dd') and "
                    + "e.formapgto in('144') or to_char(c.data_lancamento, 'yyyy-mm-dd') = to_char(now(), 'yyyy-mm-dd') "
                    + "and e.formapgto in('145') or "
                    + "to_char(c.data_lancamento, 'yyyy-mm-dd') = to_char(now(), 'yyyy-mm-dd') and "
                    + "e.formapgto in('146') or "
                    + "to_char(c.data_lancamento, 'yyyy-mm-dd') = to_char(now(), 'yyyy-mm-dd') and "
                    + "e.formapgto in('153')) as entradas, "
                    + "coalesce(sum(s.valor), 0) as saidas from saida s inner join caixa c "
                    + "on c.codigo = s.caixa "
                    + "where to_char(c.data_lancamento, 'yyyy-mm-dd') = to_char(now(), 'yyyy-mm-dd')";
            pst = ConectaBanco.con.prepareCall(comando,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarMovimentoCaixaDAO " + e.getMessage());
            return rs;
        }
    }

    public ResultSet ConsultarSaldoAnteriorDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select ((select coalesce(sum(e.valor), 0) from entrada e "
                    + "inner join caixa c on c.codigo = e.caixa where e.formapgto in('95') "
                    + "and to_char(c.data_lancamento, 'yyyy-mm-dd') < to_char(now(), 'yyyy-mm-dd') "
                    + "or e.formapgto in('155') "
                    + "and to_char(c.data_lancamento, 'yyyy-mm-dd') < to_char(now(), 'yyyy-mm-dd') "
                    + "or e.formapgto in('144') "
                    + "and to_char(c.data_lancamento, 'yyyy-mm-dd') < to_char(now(), 'yyyy-mm-dd') "
                    + "or e.formapgto in('145') "
                    + "and to_char(c.data_lancamento, 'yyyy-mm-dd') < to_char(now(), 'yyyy-mm-dd') "
                    + "or e.formapgto in('146') "
                    + "and to_char(c.data_lancamento, 'yyyy-mm-dd') < to_char(now(), 'yyyy-mm-dd')) - "
                    + "coalesce(sum(s.valor), 0)) "
                    + "as saldoanterior from saida s inner join caixa c on c.codigo = s.caixa "
                    + "where to_char(c.data_lancamento, 'yyyy-mm-dd') < to_char(now(), 'yyyy-mm-dd')";
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            return rs;

        } catch (Exception e) {
            System.out.println("Erro ao ConsultarSaldoAnteriorDAO " + e.getMessage());
            return rs;
        }
    }

    public ResultSet ConsultarCaixaDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select c.codigo as cod, to_char(c.data_lancamento, 'dd/mm/yyyy') as dat, "
                    + "pe.nome as funcionario, p.descricao as plano, e.valor, e.tipo, \n"
                    + "to_char(e.datacadastro, 'HH24:mi:ss') as hora, e.observacao \n"
                    + "from entrada e inner join caixa c on c.codigo = e.caixa \n"
                    + "inner join planocontas p on p.codigo = e.formapgto "
                    + "inner join pessoa pe on pe.codigo = c.funcionario "
                    + "where to_char(c.datacadastro, 'yyyy-mm-dd') \n"
                    + "between to_char(now(), 'yyyy-mm-dd') and                     \n"
                    + "to_char(now(), 'yyyy-mm-dd')                     \n"
                    + "union \n"
                    + "select c.codigo as cod, to_char(c.data_lancamento, 'dd/mm/yyyy') as dat, "
                    + "pe.nome as funcionario, p.descricao as plano, s.valor, s.tipo, \n"
                    + "to_char(s.datacadastro, 'HH24:mi:ss') as hora, s.observacao \n"
                    + "from saida s inner join caixa c on c.codigo = s.caixa \n"
                    + "inner join planocontas p on p.codigo = s.formapgto "
                    + "inner join pessoa pe on pe.codigo = c.funcionario                   \n"
                    + "where to_char(c.datacadastro, 'yyyy-mm-dd') \n"
                    + "between to_char(now(), 'yyyy-mm-dd') and \n"
                    + "to_char(now(), 'yyyy-mm-dd') order by cod desc";
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();

            return rs;
        } catch (SQLException e) {
            System.out.println("Erro ao ConsultarCaixaDAO " + e.getMessage());
            return rs;
        }
    }

    public ResultSet ConsultarMaiorCodigoCaixaDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select lpad(cast(count(codigo) as varchar), 8, '0') "
                    + "from caixa where to_char(datacadastro, 'yyyy-mm-dd') "
                    + "= to_char(now(), 'yyyy-mm-dd')";
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println("Erro ao ConsultarMaiorCodigoCaixaDAO " + e.getMessage());
            return rs;
        }
    }

    
    
    
}
