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
public class VendaDAO {
    String codigo, cliente, animal, desconto, total, situacao, funcionario, veterinario, 
            atendimento, dataInicial, dataFinal, comando, dataEntrega, horaEntrega,
            dataPedido, horaPedido, observacao;

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getHoraPedido() {
        return horaPedido;
    }

    public void setHoraPedido(String horaPedido) {
        this.horaPedido = horaPedido;
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
    
    public boolean CadastrarVendaDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "insert into venda (cliente, animal, desconto, total, "
                    + "funcionario, veterinario, situacao, datacadastro, atendimento,"
                    + "data_entrega, hora_entrega, data_pedido, hora_pedido, observacao) "
                    + "values(?,?,?,?,?,?,?,now(),?,?,?,?,?,?)";
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setInt(1, Integer.parseInt(getCliente()));
            pst.setInt(2, Integer.parseInt(getAnimal()));
            pst.setDouble(3, Double.parseDouble(getDesconto().replace("R$", "").replace(",", ".")));
            pst.setDouble(4, Double.parseDouble(getTotal().replace("R$", "").replace(".", "").replace(",", ".")));
            pst.setString(5, getFuncionario());
            pst.setString(6, getVeterinario());
            pst.setString(7, getSituacao());
            pst.setInt(8, Integer.parseInt(getAtendimento()));
            pst.setString(9, getDataEntrega());
            pst.setString(10, getHoraEntrega());
            pst.setDate(11, Date.valueOf(getDataPedido()));
            pst.setString(12, getHoraPedido());
            pst.setString(13, getObservacao());
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
                    
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Erro ao CadastrarVendaDAO "+e.getMessage());
            return false;
        }
    }
    
    public ResultSet ConsultarMaiorCodigoVendaDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select lpad(cast((max(codigo)+1) as varchar), 9, '0') from venda";
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarMaiorCodigoVendaDAO "+e.getMessage());
            return rs;
        }
    }
    
     public ResultSet ConsultarVendaDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select v.codigo, p.nome, a.nome as animal, to_char(v.datacadastro, 'dd/MM/yyyy') "
                    + "as data, v.total, v.veterinario, v.funcionario as atendente, v.situacao "
                    + "from venda v inner join pessoa p on p.codigo = v.cliente "
                    + "left join animais a on a.codigo = v.animal "                               
                    + "where v.situacao in('"+getSituacao()+"') "
                    + "and p.nome like '"+getCliente()+"%' order by v.codigo desc "
                    + "limit 100";
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarVendaDAO "+e.getMessage());
            return rs;
        }
    }
     
   
}
