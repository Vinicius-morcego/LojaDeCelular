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
public class PermissaoCadastroDAO {
    String codigo, usuario, planocontas, permissoes, funcionario, comando;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPlanocontas() {
        return planocontas;
    }

    public void setPlanocontas(String planocontas) {
        this.planocontas = planocontas;
    }

    public String getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(String permissoes) {
        this.permissoes = permissoes;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }
    
    PreparedStatement pst;
    ResultSet rs;
    public boolean CadastrarPermissaoCadastroDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "insert into permissao_cadastro (usuario, planocontas, permissoes, "
                    + "funcionario) values(?,?,?,?)";
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setString(1, getUsuario());
            pst.setString(2, getPlanocontas());
            pst.setString(3, getPermissoes());
            pst.setInt(4, Integer.parseInt(getFuncionario()));
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarPermissaoCadastroDAO "+e.getMessage());
            return false;
        }
    }
    
    public boolean AlterarPermissaoCadastroDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "update permissao_cadastro set usuario = ?, planocontas = ?, "
                    + "permissoes = ? where funcionario = ? ";             
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setString(1, getUsuario());
            pst.setString(2, getPlanocontas());
            pst.setString(3, getPermissoes());
            pst.setInt(4, Integer.parseInt(getFuncionario()));
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao AlterarPermissaoCadastroDAO "+e.getMessage());
            return false;
        }
    }
    
    public ResultSet ConsultarPermissaoCadastroDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select usuario, planocontas, permissoes from permissao_cadastro "
                    + "where funcionario = "+getFuncionario();
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao COnsultarPermissaoCadastroDAO "+e.getMessage());
            return rs;
        }
    }
}
