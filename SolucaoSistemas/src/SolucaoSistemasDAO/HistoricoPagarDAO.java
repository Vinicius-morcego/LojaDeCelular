/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Batman
 */
public class HistoricoPagarDAO {
    String codigo, parcela, valorPago, formaPgto, quitar, comando;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    public String getValorPago() {
        return valorPago;
    }

    public void setValorPago(String valorPago) {
        this.valorPago = valorPago;
    }

    public String getFormaPgto() {
        return formaPgto;
    }

    public void setFormaPgto(String formaPgto) {
        this.formaPgto = formaPgto;
    }

    public String getQuitar() {
        return quitar;
    }

    public void setQuitar(String quitar) {
        this.quitar = quitar;
    }
    
    ResultSet rs;
    PreparedStatement pst;
    public boolean CadastrarHistoricoPagarDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "insert into historico_pagar (datacadastro, parcela, formapgto, "
                    + "valorpago, quitar) values(now(),?,(select codigo from planocontas where "
                    + "descricao = '"+getFormaPgto()+"'),?,?)";
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setInt(1, Integer.parseInt(getParcela()));
            pst.setDouble(2, Double.parseDouble(getValorPago().replace("R$", "").replace(".", "").replace(",", ".")));
            pst.setString(3, getQuitar());
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
            
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Erro ao CadastrarHistoricoPagarDAO "+e.getMessage());
            return false;
        }
    }
}
