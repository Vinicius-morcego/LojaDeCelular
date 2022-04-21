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
public class PessoaDAO {

    String codigo, nome, apelido, cpf, rg, telefone1, telefone2, cnpj,
            facebook, instagram, email, bloqueado, liberado, observacao, tipo,
            datacadastro, foto, data_nasc, comando, endereco, estado, cidade, bairro;

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(String data_nasc) {
        this.data_nasc = data_nasc;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(String bloqueado) {
        this.bloqueado = bloqueado;
    }

    public String getLiberado() {
        return liberado;
    }

    public void setLiberado(String liberado) {
        this.liberado = liberado;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDatacadastro() {
        return datacadastro;
    }

    public void setDatacadastro(String datacadastro) {
        this.datacadastro = datacadastro;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    PreparedStatement pst;
    ResultSet rs;
    File imagemFile;
    FileInputStream fls;

    public boolean CadastrarPessoaDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "insert into pessoa (nome, apelido_nomefantasia, cpf, rg, "
                    + "data_nasc, telefone1, telefone2, cnpj, facebook, instagram, "
                    + "email, bloqueado, liberado, observacoes, tipo, datacadastro, foto,"
                    + "endereco, bairro, cidade, estado) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),?,?,?,?,?)";
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setString(1, getNome());
            pst.setString(2, getApelido());
            pst.setString(3, getCpf());
            pst.setString(4, getRg());
            pst.setString(5, getData_nasc());
            pst.setString(6, getTelefone1());
            pst.setString(7, getTelefone2());
            pst.setString(8, getCnpj());
            pst.setString(9, getFacebook());
            pst.setString(10, getInstagram());
            pst.setString(11, getEmail());
            pst.setString(12, getBloqueado());
            pst.setString(13, getLiberado());
            pst.setString(14, getObservacao());
            pst.setString(15, getTipo());
            imagemFile = new File(getFoto());
            fls = new FileInputStream(imagemFile);
            pst.setBinaryStream(16, fls, (int) imagemFile.length());
            pst.setString(17, getEndereco());
            pst.setString(18, getBairro());
            pst.setString(19, getCidade());
            pst.setString(20, getEstado());
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
        } catch (SQLException | FileNotFoundException e) {
            System.out.println("Erro ao CadastrarPessoaDAO " + e.getMessage());
            return false;
        }
    }

    public boolean AlterarPessoaDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "update pessoa set nome = ?, apelido_nomefantasia = ?, cpf = ?, rg = ?, "
                    + "data_nasc = ?, telefone1 = ?, telefone2 = ?, cnpj = ?, facebook = ?, instagram = ?, "
                    + "email = ?, bloqueado = ?, liberado = ?, observacoes = ?, "
                    + "tipo = ?, endereco = ?, bairro = ?, cidade = ?, "
                    + "estado = ? where codigo = ?";

            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setString(1, getNome());
            pst.setString(2, getApelido());
            pst.setString(3, getCpf());
            pst.setString(4, getRg());
            pst.setString(5, getData_nasc());
            pst.setString(6, getTelefone1());
            pst.setString(7, getTelefone2());
            pst.setString(8, getCnpj());
            pst.setString(9, getFacebook());
            pst.setString(10, getInstagram());
            pst.setString(11, getEmail());
            pst.setString(12, getBloqueado());
            pst.setString(13, getLiberado());
            pst.setString(14, getObservacao());
            pst.setString(15, getTipo());

            pst.setString(16, getEndereco());
            pst.setString(17, getBairro());
            pst.setString(18, getCidade());
            pst.setString(19, getEstado());
            pst.setInt(20, Integer.parseInt(getCodigo()));
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Erro ao AlterarPessoaDAO " + e.getMessage());
            return false;
        }
    }

    public boolean AlterarImagemPessoaDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "update pessoa set foto = ? where codigo = ?";
            pst = ConectaBanco.con.prepareStatement(comando);
            imagemFile = new File(getFoto());
            fls = new FileInputStream(imagemFile);
            pst.setBinaryStream(1, fls, (int) imagemFile.length());
            pst.setInt(2, Integer.parseInt(getCodigo()));
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
        } catch (SQLException | FileNotFoundException | NumberFormatException e) {
            System.out.println("Erro ao AlterarImagemPessoaDAO " + e.getMessage());
            return false;
        }
    }

    public ResultSet ConsultarPessoaDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select distinct codigo, nome, apelido_nomefantasia, cpf, rg, telefone1, "
                    + "telefone2, cnpj, facebook, instagram, email, bloqueado, liberado, "
                    + "observacoes, data_nasc, foto, endereco, bairro, cidade, "
                    + "estado from pessoa where tipo in ('" + getTipo() + "') and "
                    + "codigo = " + getCodigo() + " and bloqueado in('') or tipo in ('" + getTipo() + "') and "
                    + "nome like '" + getNome() + "%' and bloqueado in('') or "
                    + "tipo in('" + getTipo() + "') and apelido_nomefantasia like '" + 
                    getApelido() + "%' and bloqueado in('') order by nome "
                    + "limit 100";
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarPessoaDAO " + e.getMessage());
            return rs;
        }
    }
}
