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
public class OrcamentoCompraDAO {
    String codigo, fornecedor, animal, desconto, total, situacao, funcionario, veterinario, 
            atendimento, dataInicial, dataFinal, comando, dataEntrega, horaEntrega, observacao,
            dataOrcamento, horaOrcamento;

    public String getDataOrcamento() {
        return dataOrcamento;
    }

    public void setDataOrcamento(String dataOrcamento) {
        this.dataOrcamento = dataOrcamento;
    }

    public String getHoraOrcamento() {
        return horaOrcamento;
    }

    public void setHoraOrcamento(String horaOrcamento) {
        this.horaOrcamento = horaOrcamento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
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

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    public String getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(String veterinario) {
        this.veterinario = veterinario;
    }

    public String getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(String atendimento) {
        this.atendimento = atendimento;
    }

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
    
    PreparedStatement pst;
    ResultSet rs;
    
    public boolean CadastrarOrcamentoCompraDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "insert into orcamento_compra (fornecedor, animal, desconto, total, "
                    + "funcionario, veterinario, situacao, datacadastro, atendimento, "
                    + "data_entrega, hora_entrega, observacao, data_orcamento, "
                    + "hora_orcamento) "
                    + "values(?,?,?,?,?,?,?,now(),?,?,?,?,?,?)";
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setInt(1, Integer.parseInt(getFornecedor()));
            pst.setInt(2, Integer.parseInt(getAnimal()));
            pst.setDouble(3, Double.parseDouble(getDesconto().replace("R$", "").replace(",", ".")));
            pst.setDouble(4, Double.parseDouble(getTotal().replace("R$", "").replace(".", "").replace(",", ".")));
            pst.setString(5, getFuncionario());
            pst.setString(6, getVeterinario());
            pst.setString(7, getSituacao());
            pst.setInt(8, Integer.parseInt(getAtendimento()));
            pst.setString(9, getDataEntrega());
            pst.setString(10, getHoraEntrega());
            pst.setString(11, getObservacao());
            pst.setDate(12, Date.valueOf(getDataOrcamento()));
            pst.setString(13, getHoraOrcamento());
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
                    
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Erro ao CadastrarOrcamentoCompraDAO "+e.getMessage());
            return false;
        }
    }
    
    public boolean CancelarOrcamentoCompraDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "update orcamento_compra set situacao = ? where codigo = ?";
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setString(1, getSituacao());
            pst.setInt(2, Integer.parseInt(getCodigo()));
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Erro ao CancelarOrcamentoCompraDAO "+e.getMessage());
            return false;
        }
    }
    public ResultSet ConsultarOrcamentoCompraDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select o.codigo, p.nome, o.fornecedor, a.nome as animal, "
                    + "to_char(o.datacadastro, 'dd/MM/yyyy') "
                    + "as dat, to_char(o.datacadastro, 'HH24:mi:ss') as hora, "
                    + "o.data_entrega as entrega, "
                    + "o.total, o.veterinario, o.funcionario, o.situacao "
                    + "from orcamento_compra o inner join pessoa p on p.codigo = o.fornecedor "
                    + "left join animais a on a.codigo = o.animal "
                    + "where o.situacao in('Pendente') and o.atendimento "+getAtendimento()+" "
                    + "and p.nome like '"+getFornecedor()+"%' order by o.codigo desc "
                    + "limit 100";
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarOrcamentoCompraDAO "+e.getMessage());
            return rs;
        }
    }
    
    public ResultSet FecharOrcamentoCompraDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select o.codigo, p.nome, o.fornecedor, o.animal, a.telefone, "
                    + "o.veterinario, o.funcionario, "
                    + "n.nome as nomeanimal, o.total, o.atendimento, o.observacao, "
                    + "a.identificacao, a.acessorios, a.vacinas, a.alimentacao, "
                    + "a.fobia, a.procedimento, a.prescricao, a.exame, a.temperatura, "
                    + "a.frequencia_cardiaca, a.frequencia_respiral, a.tpc, a.linfonodos, "
                    + "a.mucosa, a.hidratacao, a.ectoparasitas, a.exames_solicitados, "
                    + "a.problema_atendimento, n.foto, n.sexo, coalesce(n.peso, 0) as pesoanimal, "
                    + "n.especie, n.raca "
                    + "from orcamento_compra o "
                    + "left join animais n on n.codigo = o.animal "
                    + "inner join pessoa p on p.codigo = o.fornecedor "
                    + "left join atendimento a on a.codigo = o.atendimento "
                    + "where o.codigo = "+getCodigo();
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarOrcamentoCompraDAO "+e.getMessage());
            return rs;
        }
    }
    
    public ResultSet ConsultarMaiorCodigoOrcamentoCompraDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select max(codigo) from orcamento_compra";
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarMaiorCodigoOrcamentoCompraDAO "+e.getMessage());
            return rs;
        }
    }
}


