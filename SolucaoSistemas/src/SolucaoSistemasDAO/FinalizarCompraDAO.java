/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasDAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Batman
 */
public class FinalizarCompraDAO {
    
      String codigo, hora, situacao, dataFechamento, compra, comando;

    public String getCompra() {
        return compra;
    }

    public void setCompra(String compra) {
        this.compra = compra;
    }
   

    public String getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(String dataFechamento) {
        this.dataFechamento = dataFechamento;
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

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    
    PreparedStatement pst;
    ResultSet rs;
    
    public boolean CadastrarFinalizarCompraDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "insert into finalizar_compra (data_fechamento, hora, situacao, "
                    + "datacadastro, compra) values(?,?,?,now(),?)";
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setDate(1, Date.valueOf(getDataFechamento()));
            pst.setString(2, getHora());
            pst.setString(3, getSituacao());
            pst.setInt(4, Integer.parseInt(getCompra()));
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarFinalizarCompraDAO "+e.getMessage());
            return false;
        }
    }
}
