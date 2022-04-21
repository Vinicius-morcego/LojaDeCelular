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
public class AnimaisDAO {

    String codigo, nome, dono, sexo, nascimento, peso, especie, raca, identificacao,
            foto, alimentacao, fobia, disponivel, possuiPedigree, restricao, falecido,
            agressivo, hiperativo, antiSocial, obsessivo, historico, comportamento,
            rotina, dataCadastro, observacao, comando;

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
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

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getAlimentacao() {
        return alimentacao;
    }

    public void setAlimentacao(String alimentacao) {
        this.alimentacao = alimentacao;
    }

    public String getFobia() {
        return fobia;
    }

    public void setFobia(String fobia) {
        this.fobia = fobia;
    }

    public String getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(String disponivel) {
        this.disponivel = disponivel;
    }

    public String getPossuiPedigree() {
        return possuiPedigree;
    }

    public void setPossuiPedigree(String possuiPedigree) {
        this.possuiPedigree = possuiPedigree;
    }

    public String getRestricao() {
        return restricao;
    }

    public void setRestricao(String restricao) {
        this.restricao = restricao;
    }

    public String getFalecido() {
        return falecido;
    }

    public void setFalecido(String falecido) {
        this.falecido = falecido;
    }

    public String getAgressivo() {
        return agressivo;
    }

    public void setAgressivo(String agressivo) {
        this.agressivo = agressivo;
    }

    public String getHiperativo() {
        return hiperativo;
    }

    public void setHiperativo(String hiperativo) {
        this.hiperativo = hiperativo;
    }

    public String getAntiSocial() {
        return antiSocial;
    }

    public void setAntiSocial(String antiSocial) {
        this.antiSocial = antiSocial;
    }

    public String getObsessivo() {
        return obsessivo;
    }

    public void setObsessivo(String obsessivo) {
        this.obsessivo = obsessivo;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public String getComportamento() {
        return comportamento;
    }

    public void setComportamento(String comportamento) {
        this.comportamento = comportamento;
    }

    public String getRotina() {
        return rotina;
    }

    public void setRotina(String rotina) {
        this.rotina = rotina;
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

    public boolean CadastrarAnimalDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "insert into animais (nome, dono, sexo, nascimento, peso, especie, "
                    + "raca, identificacao, foto, alimentacao, fobia, disponivel_tosa, "
                    + "possui_pedigree, restricao, falecido, agressivo, hiperativo, "
                    + "anti_social, obsessivo, historico, comportamento, rotina, "
                    + "datacadastro, observacao) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),?)";
            pst = ConectaBanco.con.prepareStatement(comando);

            pst.setString(1, getNome());
            pst.setInt(2, Integer.parseInt(getDono()));
            pst.setString(3, getSexo());
            pst.setString(4, getNascimento());
            pst.setDouble(5, Double.parseDouble(getPeso().replace(",", ".")));
            pst.setString(6, getEspecie());
            pst.setString(7, getRaca());
            pst.setString(8, getIdentificacao());
            imagemFile = new File(getFoto());
            fls = new FileInputStream(imagemFile);
            pst.setBinaryStream(9, fls, (int) imagemFile.length());
            pst.setString(10, getAlimentacao());
            pst.setString(11, getFobia());
            pst.setString(12, getDisponivel());
            pst.setString(13, getPossuiPedigree());
            pst.setString(14, getRestricao());
            pst.setString(15, getFalecido());
            pst.setString(16, getAgressivo());
            pst.setString(17, getHiperativo());
            pst.setString(18, getAntiSocial());
            pst.setString(19, getObsessivo());
            pst.setString(20, getHistorico());
            pst.setString(21, getComportamento());
            pst.setString(22, getRotina());
            pst.setString(23, getObservacao());
            pst.execute();
            pst.close();
            ConectaBanco.con.close();

            return true;
        } catch (SQLException | NumberFormatException | FileNotFoundException e) {
            System.out.println("Erro ao CadastrarAnimalDAO " + e.getMessage());
            return false;
        }
    }

    public boolean AlterarAnimalDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "update animais set nome = ?, sexo = ?, "
                    + "nascimento = ?, peso = ?, especie = ?, "
                    + "raca = ?, identificacao = ?, alimentacao = ?, "
                    + "fobia = ?, disponivel_tosa = ?, "
                    + "possui_pedigree = ?, restricao = ?, falecido = ?, agressivo = ?, "
                    + "hiperativo = ?, "
                    + "anti_social = ?, obsessivo = ?, historico = ?, comportamento = ?, "
                    + "rotina = ?, observacao = ? where codigo = ?";
           pst = ConectaBanco.con.prepareStatement(comando);

            pst.setString(1, getNome());
            // pst.setInt(2, Integer.parseInt(getDono()));
            pst.setString(2, getSexo());
            pst.setString(3, getNascimento());
            pst.setDouble(4, Double.parseDouble(getPeso().replace(".", "").replace(",", ".")));
            pst.setString(5, getEspecie());
            pst.setString(6, getRaca());
            pst.setString(7, getIdentificacao());
            pst.setString(8, getAlimentacao());
            pst.setString(9, getFobia());
            pst.setString(10, getDisponivel());
            pst.setString(11, getPossuiPedigree());
            pst.setString(12, getRestricao());
            pst.setString(13, getFalecido());
            pst.setString(14, getAgressivo());
            pst.setString(15, getHiperativo());
            pst.setString(16, getAntiSocial());
            pst.setString(17, getObsessivo());
            pst.setString(18, getHistorico());
            pst.setString(19, getComportamento());
            pst.setString(20, getRotina());
            pst.setString(21, getObservacao());
            pst.setInt(22, Integer.parseInt(getCodigo()));
            pst.execute();
            pst.close();
            ConectaBanco.con.close();

            return true;
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Erro ao AlterarAnimalDAO " + e.getMessage());
            return false;
        }
    }

    public boolean AlterarImagemAnimalDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "update animais set foto = ? where codigo = ?";
            pst = ConectaBanco.con.prepareStatement(comando);
            imagemFile = new File(getFoto());
            fls = new FileInputStream(imagemFile);
            pst.setBinaryStream(1, fls, (int) imagemFile.length());
            pst.setInt(2, Integer.parseInt(getCodigo()));
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
        } catch (SQLException | FileNotFoundException e) {
            System.out.println("Erro ao AlterarImagemAnimalDAO " + e.getMessage());
            return false;
        }
    }

    public ResultSet ConsultarAnimalDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select a.codigo, a.nome as nomeanimal, a.dono, p.nome, a.sexo, a.peso, "
                    + "a.especie, a.raca, a.identificacao, a.foto, a.alimentacao, a.fobia, "
                    + "a.nascimento, a.disponivel_tosa, a.possui_pedigree, "
                    + "a.historico, a.comportamento, a.rotina, a.obsessivo, "
                    + "a.hiperativo, a.falecido, "
                    + "a.anti_social, a.obsessivo, a.observacao, a.restricao, a.agressivo, "
                    + "a.nascimento from animais a inner join pessoa p on p.codigo = a.dono "
                    + "where a.codigo = " + getIdentificacao() + " or "
                    + "a.nome like '" + getNome() + "%' or p.nome like '" + getDono() + "%' "
                    + "order by " + getCodigo() + " limit 100";
            pst = ConectaBanco.con.prepareStatement(comando);
            rs = pst.executeQuery();
            return rs;

        } catch (NumberFormatException | SQLException e) {
            System.out.println("Erro ao ConsultarAnimalDAO " + e.getMessage());
            return rs;
        }
    }
    


}
