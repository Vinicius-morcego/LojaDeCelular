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
 * @author Batman
 */
public class AgendamentoDAO {

    String codigo, hora, dia, situacao, cliente, animal, telefone1, telefone2,
            atendente, veterinario, observacao, comando, dataInicial, dataFinal;

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }
    Date dataAgenda;

    public Date getDataAgenda() {
        return dataAgenda;
    }

    public void setDataAgenda(Date dataAgenda) {
        this.dataAgenda = dataAgenda;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
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

    public String getAtendente() {
        return atendente;
    }

    public void setAtendente(String atendente) {
        this.atendente = atendente;
    }

    public String getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(String veterinario) {
        this.veterinario = veterinario;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    PreparedStatement pst;
    ResultSet rs;
    
    public boolean CadastrarAgendaDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "insert into agendamento (data_agenda, hora, dia, situacao, "
                    + "cliente, animal, telefone1, telefone2, atendente, veterinario, "
                    + "observacao, cadastro) values(?,?,?,?,?,?,?,?,?,?,?,now())";
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setDate(1, getDataAgenda());
            pst.setString(2, getHora());
            pst.setString(3, getDia());
            pst.setString(4, getSituacao());
            pst.setString(5, getCliente());
            pst.setInt(6, Integer.parseInt(getAnimal()));
            pst.setString(7, getTelefone1());
            pst.setString(8, getTelefone2());
            pst.setString(9, getAtendente());
            pst.setString(10, getVeterinario());
            pst.setString(11, getObservacao());
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Erro ao CadastrarAgendaDAO " + e.getMessage());
            return false;

        }
    }

    public boolean AlterarAgendaDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "update agendamento set data_agenda = ?, hora = ?, dia = ?, situacao = ?, "
                    + "cliente = ?, animal = ?, telefone1 = ?, telefone2 = ?, "
                    + "atendente = ?, veterinario = ?, "
                    + "observacao = ?, cadastro = now() where codigo = ?";
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setDate(1, getDataAgenda());
            pst.setString(2, getHora());
            pst.setString(3, getDia());
            pst.setString(4, getSituacao());
            pst.setString(5, getCliente());
            pst.setInt(6, Integer.parseInt(getAnimal()));
            pst.setString(7, getTelefone1());
            pst.setString(8, getTelefone2());
            pst.setString(9, getAtendente());
            pst.setString(10, getVeterinario());
            pst.setString(11, getObservacao());
            pst.setInt(12, Integer.parseInt(getCodigo()));
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Erro ao AlterarAgendaDAO " + e.getMessage());
            return false;

        }
    }

    public boolean ExcluirAgendaDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "delete from agendamento where codigo = "+getCodigo();
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
        } catch (Exception e) {
            
            return false;
        }
    }
    public ResultSet ConsultarAgendamentoDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select a.codigo, to_char(a.data_agenda, 'dd/mm/yyyy') as datas, a.hora, "
                    + "a.dia, a.atendente, a.cliente, n.nome, "
                    + "a.telefone1, a.telefone2, a.atendimento from agendamento a "                   
                    + "left join animais n on n.codigo = a.animal "
                    + "where to_char(a.data_agenda, 'yyyy-mm-dd') >= to_char(now(), 'yyyy-mm-dd') "
                    + "and to_char(a.data_agenda, 'yyyy-mm-dd') between '" + getDataInicial() + "' and "
                    + "'" + getDataFinal() + "' or to_char(a.data_agenda, 'yyyy-mm-dd') >= to_char(now(), 'yyyy-mm-dd') and "
                    + "a.cliente like '"+getCliente()+"%' "
                    + "or to_char(a.data_agenda, 'yyyy-mm-dd') >= to_char(now(), 'yyyy-mm-dd') "
                    + "and n.nome like '"+getAnimal()+"%' or to_char(a.data_agenda, 'yyyy-mm-dd') >= to_char(now(), 'yyyy-mm-dd') "
                    + "and a.atendente like '"+getAtendente()+"%' "
                    + "or to_char(a.data_agenda, 'yyyy-mm-dd') >= to_char(now(), 'yyyy-mm-dd')and "
                    + "a.veterinario like '"+getVeterinario()+"%' "
                    + "or to_char(a.data_agenda, 'yyyy-mm-dd') >= to_char(now(), 'yyyy-mm-dd') "
                    + "and a.situacao like '"+getSituacao()+"%' order by a.codigo";
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            return rs;
            
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarAgendamentoDAO "+e.getMessage());
            return rs;
        }
    }
    
    public ResultSet ConsultarAgendaDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select a.codigo,a.data_agenda as datas, a.hora, "
                    + "a.dia, a.atendente, a.cliente, n.nome, a.animal, "
                    + "a.telefone1, a.telefone2, a.veterinario, a.observacao,"
                    + "a.situacao from agendamento a "                   
                    + "left join animais n on n.codigo = a.animal "
                    + "where a.codigo = "+getCodigo();
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            return rs;
            
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarAgendaDAO "+e.getMessage());
            return rs;
        }
    }
}
