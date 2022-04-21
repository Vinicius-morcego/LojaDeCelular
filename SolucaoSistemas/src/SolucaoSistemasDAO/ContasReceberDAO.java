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
public class ContasReceberDAO {
    String codigo, cliente, faturamento, historico, valorParcela, vencimento, 
            situacao, numeroParcela, porExtenso, formaPagamento, dataInicial, 
            dataFinal, comando;

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

    public String getFaturamento() {
        return faturamento;
    }

    public void setFaturamento(String faturamento) {
        this.faturamento = faturamento;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public String getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(String valorParcela) {
        this.valorParcela = valorParcela;
    }

    public String getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(String numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public String getPorExtenso() {
        return porExtenso;
    }

    public void setPorExtenso(String porExtenso) {
        this.porExtenso = porExtenso;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    
    PreparedStatement pst;
    ResultSet rs;
    
    public boolean CadastrarContasReceberDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "insert into contasreceber (cliente, faturamento, historico, "
                    + "valorparcela, vencimento, situacao, numeroparcela, porextenso, "
                    + "formapgto) values(?,"+getFaturamento()+",?,?,?,?,?,?,"
                    + "(select codigo from planocontas where "
                    + "tipo in('3. RECEITA') and descricao = ?))";
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setInt(1, Integer.parseInt(getCliente()));
        
            pst.setString(2, getHistorico());
            pst.setDouble(3, Double.parseDouble(getValorParcela().replace("R$", "").replace(".", "").
                    replace(",", ".")));            
            pst.setDate(4, Date.valueOf(getVencimento()));
            pst.setString(5, getSituacao());
            pst.setString(6, getNumeroParcela());
            pst.setString(7, getPorExtenso());
            pst.setString(8, getFormaPagamento());
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
            
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Erro ao CadastrarContasReceberDAO "+e.getMessage());
            return false;
        }
    }
    public boolean CancelarContasReceberDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "update contasreceber set situacao = 'Cancelado' where codigo = "+getCodigo();
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CancelarContasReceberDAO "+e.getMessage());
            return false;
        }
    }
    public ResultSet ConsultarContasReceberDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select c.codigo, p.nome, c.faturamento, c.valorparcela, "
                    + "to_char(c.vencimento, 'dd/mm/yyyy') as venc, c.numeroparcela, "
                    + "c.historico, a.descricao, c.situacao, (c.valorparcela - coalesce(sum(h.valorpago), 0)) "
                    + "as restante "
                    + "from contasreceber c "
                    + "inner join pessoa p on p.codigo = c.cliente "
                    + "inner join planocontas a on a.codigo = c.formapgto "
                    + "left join historico_receber h on h.parcela = c.codigo "
                    + "where c.situacao not in('PAGO') and c.situacao not in('Cancelado') "
                    + "and p.tipo in('CLIENTE') and "
                    + "c.vencimento between '"+getDataInicial()+"%' and '"+getDataFinal()+"' group by c.codigo, p.nome, "
                    + "c.faturamento, c.valorparcela, c.numeroparcela, "
                    + "c.historico, a.descricao, c.situacao order by c.vencimento "
                    + "limit 100";
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println("Erro ao ConsultarContasReceberDAO "+e.getMessage());
            return rs;
        }
    }
}
