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
public class AtendimentoDAO {
    String codigo, hora_entrada, hora_saida, cliente, telefone, veterinario, atendente,
            animal, identificacao, acessorios, vacinas, alimentacao, fobia, procedimento,
            prescricao, exame, temperatura, frequencia_cardiaca, frequencia_respiral,
            tpc, linfonodos, mucosa, hidratacao, ectoparasitas, exames_solicitados,
            situacao, problema_atendimento, desconto, total, datacadastro, comando, 
            datainicial, datafinal;

    public String getDatainicial() {
        return datainicial;
    }

    public void setDatainicial(String datainicial) {
        this.datainicial = datainicial;
    }

    public String getDatafinal() {
        return datafinal;
    }

    public void setDatafinal(String datafinal) {
        this.datafinal = datafinal;
    }
    Date data_entrada, data_saida;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(String hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    public String getHora_saida() {
        return hora_saida;
    }

    public void setHora_saida(String hora_saida) {
        this.hora_saida = hora_saida;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(String veterinario) {
        this.veterinario = veterinario;
    }

    public String getAtendente() {
        return atendente;
    }

    public void setAtendente(String atendente) {
        this.atendente = atendente;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(String acessorios) {
        this.acessorios = acessorios;
    }

    public String getVacinas() {
        return vacinas;
    }

    public void setVacinas(String vacinas) {
        this.vacinas = vacinas;
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

    public String getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(String procedimento) {
        this.procedimento = procedimento;
    }

    public String getPrescricao() {
        return prescricao;
    }

    public void setPrescricao(String prescricao) {
        this.prescricao = prescricao;
    }

    public String getExame() {
        return exame;
    }

    public void setExame(String exame) {
        this.exame = exame;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getFrequencia_cardiaca() {
        return frequencia_cardiaca;
    }

    public void setFrequencia_cardiaca(String frequencia_cardiaca) {
        this.frequencia_cardiaca = frequencia_cardiaca;
    }

    public String getFrequencia_respiral() {
        return frequencia_respiral;
    }

    public void setFrequencia_respiral(String frequencia_respiral) {
        this.frequencia_respiral = frequencia_respiral;
    }

    public String getTpc() {
        return tpc;
    }

    public void setTpc(String tpc) {
        this.tpc = tpc;
    }

    public String getLinfonodos() {
        return linfonodos;
    }

    public void setLinfonodos(String linfonodos) {
        this.linfonodos = linfonodos;
    }

    public String getMucosa() {
        return mucosa;
    }

    public void setMucosa(String mucosa) {
        this.mucosa = mucosa;
    }

    public String getHidratacao() {
        return hidratacao;
    }

    public void setHidratacao(String hidratacao) {
        this.hidratacao = hidratacao;
    }

    public String getEctoparasitas() {
        return ectoparasitas;
    }

    public void setEctoparasitas(String ectoparasitas) {
        this.ectoparasitas = ectoparasitas;
    }

    public String getExames_solicitados() {
        return exames_solicitados;
    }

    public void setExames_solicitados(String exames_solicitados) {
        this.exames_solicitados = exames_solicitados;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getProblema_atendimento() {
        return problema_atendimento;
    }

    public void setProblema_atendimento(String problema_atendimento) {
        this.problema_atendimento = problema_atendimento;
    }

    public String getDesconto() {
        return desconto;
    }

    public void setDesconto(String desconto) {
        this.desconto = desconto;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDatacadastro() {
        return datacadastro;
    }

    public void setDatacadastro(String datacadastro) {
        this.datacadastro = datacadastro;
    }

    public Date getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(Date data_entrada) {
        this.data_entrada = data_entrada;
    }

    public Date getData_saida() {
        return data_saida;
    }

    public void setData_saida(Date data_saida) {
        this.data_saida = data_saida;
    }
    
    PreparedStatement pst;
    ResultSet rs;
    
    public boolean CadastrarAtendimentoDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "insert into atendimento (data_entrada, hora_entrada, data_saida, "
                    + "hora_saida, cliente, telefone, veterinario, atendente, animal, "
                    + "identificacao, acessorios, vacinas, alimentacao, fobia, "
                    + "procedimento, prescricao, exame, temperatura, frequencia_cardiaca, "
                    + "frequencia_respiral, tpc, linfonodos, mucosa, hidratacao, "
                    + "ectoparasitas, exames_solicitados, situacao, problema_atendimento, "
                    + "desconto, total, datacadastro) values(?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now())";
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setDate(1, getData_entrada());
            pst.setString(2, getHora_entrada());
            pst.setDate(3, getData_saida());
            pst.setString(4, getHora_saida());
            pst.setInt(5, Integer.parseInt(getCliente()));
            pst.setString(6, getTelefone());
            pst.setString(7, getVeterinario());
            pst.setString(8, getAtendente());
            pst.setInt(9, Integer.parseInt(getAnimal()));
            pst.setString(10, getIdentificacao());
            pst.setString(11, getAcessorios());
            pst.setString(12, getVacinas());
            pst.setString(13, getAlimentacao());
            pst.setString(14, getFobia());
            pst.setString(15, getProcedimento());
            pst.setString(16, getPrescricao());
            pst.setString(17, getExame());
            pst.setString(18, getTemperatura());
            pst.setString(19, getFrequencia_cardiaca());
            pst.setString(20, getFrequencia_respiral());
            pst.setString(21, getTpc());
            pst.setString(22, getLinfonodos());
            pst.setString(23, getMucosa());
            pst.setString(24, getHidratacao());
            pst.setString(25, getEctoparasitas());
            pst.setString(26, getExames_solicitados());
            pst.setString(27, getSituacao());
            pst.setString(28, getProblema_atendimento());
            pst.setDouble(29, Double.parseDouble(getDesconto().replace("R$", "").replace(",", ".")));
            pst.setDouble(30, Double.parseDouble(getTotal().replace("R$", "").replace(".", "").replace(",", ".")));
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
            
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Erro ao CadastrarAtendimentoDAO "+e.getMessage());
            return false;
        }
    }
    
    public ResultSet ConsultarMaiorCodigoAtendimentoDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select lpad(cast((max(codigo)+1) as varchar), 9, '0') from atendimento";
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarMaiorCodigoAtendimentoDAO "+e.getMessage());
            return rs;
        }
    }
    
    public ResultSet ConsultarAtendimentoDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select a.codigo, p.nome, n.nome as animal, "
                    + "to_char(a.datacadastro, 'dd/mm/yyyy HH24:mi:ss') as data,"
                    + " a.total, a.veterinario, a.atendente, a.situacao from atendimento a "
                    + "inner join pessoa p on p.codigo = a.cliente "
                    + "inner join animais n on n.codigo = a.animal "
                    + "where a.situacao in('"+getSituacao()+"') and "
                    + "p.nome like '"+getCliente()+"%' or a.situacao in('"+getSituacao()+"') and "
                    + "to_char(a.datacadastro, 'yyyy-mm-dd') between "
                    + "'"+getDatainicial()+"' and "
                    + "'"+getDatafinal()+"' order by a.codigo desc "
                    + "limit 100";   
            
            
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarAtendimentoDAO "+e.getMessage());
            return rs;
        }
    }
            
}
