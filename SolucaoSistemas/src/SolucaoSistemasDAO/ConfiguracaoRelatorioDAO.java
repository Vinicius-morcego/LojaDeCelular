/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasDAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Vinicius
 */
public class ConfiguracaoRelatorioDAO {

    String codigo, titulo, telefone1, telefone2, imagem, comando;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    PreparedStatement pst;
    ResultSet rs;
    File imagemFile;
    FileInputStream fls;

    public boolean CadastrarConfiguracaoDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "insert into relatorio_config (titulo, telefone1, telefone2, logotipo) "
                    + "values(?,?,?,?)";
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setString(1, getTitulo());
            pst.setString(2, getTelefone1());
            pst.setString(3, getTelefone2());
            imagemFile = new File(getImagem());
            fls = new FileInputStream(imagemFile);
            pst.setBinaryStream(4, fls, (int) imagemFile.length());
            pst.execute();
            pst.close();
            return true;
        } catch (SQLException | FileNotFoundException e) {

            return false;
        }
    }

    public boolean AlterarConfiguracaoDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "update relatorio_config set titulo = ?, telefone1 = ?, "
                    + "telefone2 = ? where codigo = 1 ";

            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setString(1, getTitulo());
            pst.setString(2, getTelefone1());
            pst.setString(3, getTelefone2());

            pst.execute();
            pst.close();
            return true;
        } catch (SQLException e) {

            return false;
        }
    }

    public boolean AlterarImagemRelatorio() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "update relatorio_config set logotipo = ? where codigo = 1";
            pst = ConectaBanco.con.prepareStatement(comando);
            imagemFile = new File(getImagem());
            fls = new FileInputStream(imagemFile);
            pst.setBinaryStream(1, fls, (int) imagemFile.length());
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;

        } catch (SQLException | FileNotFoundException e) {

            return false;
        }
    }

    public ResultSet ConsultarLogoDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select logotipo, titulo, telefone1, telefone2 "
                    + "from relatorio_config where codigo = 1";
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarLogoDAO " + e.getMessage());
            return rs;
        }
    }
}
