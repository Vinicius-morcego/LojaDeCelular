/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasDAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Vinicius
 */
public class ProdutoServicoDAO {

    String codigo, nome, codigobarras, unidade, referencia, localizacao, marca,
            categoria, controlaestoque, atualizarCusto, venderZerado, estoqueMinimo,
            valorCusto, margem, valorVenda, observacao, tipo, imagem, dataCadastro, 
            dataValidade, comando;

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
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

    public String getCodigobarras() {
        return codigobarras;
    }

    public void setCodigobarras(String codigobarras) {
        this.codigobarras = codigobarras;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getControlaestoque() {
        return controlaestoque;
    }

    public void setControlaestoque(String controlaestoque) {
        this.controlaestoque = controlaestoque;
    }

    public String getAtualizarCusto() {
        return atualizarCusto;
    }

    public void setAtualizarCusto(String atualizarCusto) {
        this.atualizarCusto = atualizarCusto;
    }

    public String getVenderZerado() {
        return venderZerado;
    }

    public void setVenderZerado(String venderZerado) {
        this.venderZerado = venderZerado;
    }

    public String getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(String estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public String getValorCusto() {
        return valorCusto;
    }

    public void setValorCusto(String valorCusto) {
        this.valorCusto = valorCusto;
    }

    public String getMargem() {
        return margem;
    }

    public void setMargem(String margem) {
        this.margem = margem;
    }

    public String getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(String valorVenda) {
        this.valorVenda = valorVenda;
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    PreparedStatement pst;
    ResultSet rs;
    File imagemFile;
    FileInputStream fls;
    public boolean CadastrarProdutoDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "insert into produto_servico (nome, codigobarras, unidade, referencia,"
                    + "localizacao, marca, categoria, controlaestoque, atualizarcusto,"
                    + "venderzerado, estoqueminimo, valorcusto, margem, valorvenda, "
                    + "observacao, tipo, imagem, datacadastro, data_validade)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),?)";

            pst = ConectaBanco.con.prepareStatement(comando);

            pst.setString(1, getNome());
            pst.setString(2, getCodigobarras());
            pst.setString(3, getUnidade());
            pst.setString(4, getReferencia());
            pst.setString(5, getLocalizacao());
            pst.setString(6, getMarca());
            pst.setString(7, getCategoria());
            pst.setString(8, getControlaestoque());
            pst.setString(9, getAtualizarCusto());
            pst.setString(10, getVenderZerado());
            pst.setDouble(11, Double.parseDouble(getEstoqueMinimo().
                    replace(",", ".")));
            pst.setDouble(12, Double.parseDouble(getValorCusto().replace("R$", "").
                    replace(".", "").replace(",", ".")));
            pst.setString(13, getMargem());
            pst.setDouble(14, Double.parseDouble(getValorVenda().replace("R$", "").
                    replace(".", "").replace(",", ".")));
            pst.setString(15, getObservacao());
            pst.setString(16, getTipo());
            imagemFile = new File(getImagem());
            fls = new FileInputStream(imagemFile);
            pst.setBinaryStream(17, fls, (int) imagemFile.length());
            pst.setString(18, getDataValidade());
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;

        } catch (SQLException | NumberFormatException | FileNotFoundException e) {
            System.out.println("Erro ao CadastrarProdutoDAO " + e.getMessage());
            return false;
        }
    }

    public boolean AlterarProdutoDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "update produto_servico set nome = ?, "
                    + "unidade = ?, referencia = ?, "
                    + "localizacao = ?, marca = ?, categoria = ?, controlaestoque = ?, "
                    + "atualizarcusto = ?, datacadastro = now(), "
                    + "venderzerado = ?, estoqueminimo = ?, valorcusto = ?, margem = ?, "
                    + "valorvenda = ?, "
                    + "observacao = ?, tipo = ?, data_validade = ? where codigo = ?";

            pst = ConectaBanco.con.prepareStatement(comando);

            pst.setString(1, getNome());          
            pst.setString(2, getUnidade());
            pst.setString(3, getReferencia());
            pst.setString(4, getLocalizacao());
            pst.setString(5, getMarca());
            pst.setString(6, getCategoria());
            pst.setString(7, getControlaestoque());
            pst.setString(8, getAtualizarCusto());
            pst.setString(9, getVenderZerado());
            pst.setDouble(10, Double.parseDouble(getEstoqueMinimo().
                    replace(".", "").replace(",", ".")));
            pst.setDouble(11, Double.parseDouble(getValorCusto().replace("R$", "").replace(".", "").
                    replace(",", ".")));
            pst.setString(12, getMargem());
            pst.setDouble(13, Double.parseDouble(getValorVenda().replace("R$", "").replace(".", "").
                    replace(",", ".")));
            pst.setString(14, getObservacao());
            pst.setString(15, getTipo());
            pst.setString(16, getDataValidade());
            pst.setInt(17, Integer.parseInt(getCodigo()));
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;

        } catch (SQLException | NumberFormatException e) {
            System.out.println("Erro ao AlterarProdutoDAO " + e.getMessage());
            return false;
        }
    }
    
    public boolean AlterarImagemProdutoDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "update produto_servico set imagem = ? where codigo = ?";
            pst = ConectaBanco.con.prepareStatement(comando);
            imagemFile = new File(getImagem());
            fls = new FileInputStream(imagemFile);
            pst.setBinaryStream(1, fls, (int) imagemFile.length());
            pst.setInt(2, Integer.parseInt(getCodigo()));
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
        } catch (SQLException | FileNotFoundException | NumberFormatException e) {
            System.out.println("Erro ao AlterarImagemProdutoDAO "+e.getMessage());
            return false;
        }
    }
    
    public ResultSet ConsultarProdutoDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select distinct p.codigo, p.nome, substring(p.codigobarras from 1 for 12) as codigobarras, p.referencia, "
                    + "p.marca, p.categoria, p.data_validade, "
                    + "p.estoqueminimo, e.estoqueatual, p.valorcusto, p.valorvenda, "
                    + "p.imagem, p.unidade, p.localizacao, p.observacao, p.tipo,"
                    + "p.atualizarcusto, p.controlaestoque, p.venderzerado "
                    + "from produto_servico p inner join estoque e on e.produto = p.codigo "
                    + "where p.codigo = ? or p.nome like '"+getNome()+"%' or p.referencia "
                    + "like '"+getReferencia()+"%' or p.codigobarras like '%"+getCodigobarras()+"' "
                    + "order by "+Integer.parseInt(getCodigo())+" limit 100";
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
            pst.setInt(1, Integer.parseInt(getMargem()));
            rs = pst.executeQuery();
                    
            return rs;
        } catch (NumberFormatException | SQLException e) {
            System.out.println("Erro ao ConsularProdutoDAO "+e.getMessage());
            return rs;
        }
    }
    
    public ResultSet ConsultarMaiorCodigoProdutoDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select lpad(cast((coalesce(max(codigo), '0')+1) as varchar), 12, '0') as chocolate from "
                    + "produto_servico;";
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();               
            return rs;            
        } catch (SQLException e) {
            System.out.println("Erro ConsultarMaiorCodigoProdutoDAO "+e.getMessage());
            return rs;
        }
    }
    public ResultSet GerarCodigoBarrasDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select distinct nome from produto_servico where codigobarras not in('') and nome like '"+getNome()+"%'";
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();               
            return rs;            
        } catch (SQLException e) {
            System.out.println("Erro GerarCodigoBarrasDAO "+e.getMessage());
            return rs;
        }
    }
}
