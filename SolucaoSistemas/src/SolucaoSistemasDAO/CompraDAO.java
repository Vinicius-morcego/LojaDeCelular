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
public class CompraDAO {
    
     String codigo, comprador, desconto, total, situacao, funcionario, fornecedor, 
            dataInicial, dataFinal, comando, dataEntrega, horaEntrega,
            dataPedido, horaPedido, observacao;

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

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
    
    public boolean CadastrarCompraDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "insert into compra (desconto, total, "
                    + "funcionario, fornecedor, situacao, datacadastro, "
                    + "data_entrega, hora_entrega, data_pedido, hora_pedido, observacao) "
                    + "values(?,?,?,?,?,now(),?,?,?,?,?)";
            pst = ConectaBanco.con.prepareStatement(comando);                 
            pst.setDouble(1, Double.parseDouble(getDesconto().replace("R$", "").replace(",", ".")));
            pst.setDouble(2, Double.parseDouble(getTotal().replace("R$", "").replace(".", "").replace(",", ".")));
            pst.setString(3, getFuncionario());
            pst.setInt(4, Integer.parseInt(getFornecedor()));
            pst.setString(5, getSituacao());            
            pst.setString(6, getDataEntrega());
            pst.setString(7, getHoraEntrega());
            pst.setDate(8, Date.valueOf(getDataPedido()));
            pst.setString(9, getHoraPedido());
            pst.setString(10, getObservacao());
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
                    
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Erro ao CadastrarCompraDAO "+e.getMessage());
            return false;
        }
    }
    
    public ResultSet ConsultarMaiorCodigoCompraDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select lpad(cast((max(codigo)+1) as varchar), 9, '0') from compra";
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarMaiorCodigoCompraDAO "+e.getMessage());
            return rs;
        }
    }
    
    public ResultSet ConsultarCompraDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select c.codigo, p.nome, c.total, to_char(c.data_pedido, 'dd/mm/yyyy') as dat, "
                    + "to_char(c.datacadastro, 'HH24:mi:ss') as hora, "
                    + "c.data_entrega as entrega, c.funcionario, "
                    + "c.situacao from compra c "
                    + "inner join pessoa p on p.codigo = c.fornecedor "
                    + "where p.tipo in('FORNECEDOR') and "
                    + "c.situacao in('"+getSituacao()+"') and p.nome like '"+getFornecedor()+"%' "
                    + "order by c.codigo desc limit 100";
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarCompraDAO "+e.getMessage());
            return rs;
        }
    }
}
