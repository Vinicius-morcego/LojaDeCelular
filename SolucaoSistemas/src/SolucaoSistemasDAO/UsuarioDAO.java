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
public class UsuarioDAO {

    String codigo, pessoa, dataCadastro, loguin, senha, comando;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPessoa() {
        return pessoa;
    }

    public void setPessoa(String pessoa) {
        this.pessoa = pessoa;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getLoguin() {
        return loguin;
    }

    public void setLoguin(String loguin) {
        this.loguin = loguin;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    PreparedStatement pst;
    ResultSet rs;

    public boolean CadastrarUsuarioDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "insert into usuario (pessoa, datacadastro, loguin, senha) "
                    + "values((select codigo from pessoa where tipo in('FUNCIONARIO') "
                    + "and nome = ?),now(),?,md5(?))";
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, getPessoa());
            pst.setString(2, getLoguin());
            pst.setString(3, getSenha());
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Erro ao CadastrarUsuarioDAO " + e.getMessage());
            return false;
        }
    }

    public boolean AlterarUsuarioDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "update usuario set loguin = ?, senha = md5(?) where pessoa = ?";
            pst = ConectaBanco.con.prepareStatement(comando, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, getLoguin());
            pst.setString(2, getSenha());
            pst.setInt(3, Integer.parseInt(getPessoa()));
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;

        } catch (SQLException | NumberFormatException e) {
            System.out.println("Erro ao AlterarUsuarioDAO " + e.getMessage());
            return false;
        }
    }

    public ResultSet ConsultarUsuarioDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select codigo, loguin from usuario order by codigo";
            pst = ConectaBanco.con.prepareCall(comando,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            return rs;
        } catch (Exception e) {

            return rs;
        }
    }

    public ResultSet ConsultarAcessoDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select u.loguin, u.senha, u.pessoa from usuario u inner join pessoa p on "
                    + "p.codigo = u.pessoa where u.loguin = '" + getLoguin() + "' "
                    + "and u.senha = '" + getSenha() + "' and p.bloqueado in('') ";
            pst = ConectaBanco.con.prepareCall(comando,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            return rs;
        } catch (SQLException e) {

            return rs;
        }
    }
    
}
