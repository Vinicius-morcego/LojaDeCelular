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
 * @author Vinicius
 */
public class VacinaAnimalDAO {

    String codigo, vacina, dataAplicacao, dose, animal, comando;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getVacina() {
        return vacina;
    }

    public void setVacina(String vacina) {
        this.vacina = vacina;
    }

    public String getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(String dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    PreparedStatement pst;
    ResultSet rs;

    public boolean CadastrarVacinaAnimalDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "insert into vacina_animal (vacina, data_aplic, dose, animal) "
                    + "values(?,?,?,?)";
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setString(1, getVacina());
            pst.setDate(2, java.sql.Date.valueOf(getDataAplicacao()));
            pst.setString(3, getDose());
            pst.setInt(4, Integer.parseInt(getAnimal()));
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;

        } catch (SQLException | NumberFormatException e) {
            System.out.println("" + e.getMessage());
            return false;
        }
    }
    
    public boolean AlterarVacinaAnimalDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "update vacina_animal set vacina = ?, data_aplic = ?, dose = ? "
                    + "where codigo = ?";                    
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.setString(1, getVacina());
            pst.setDate(2, java.sql.Date.valueOf(getDataAplicacao()));
            pst.setString(3, getDose());
           // pst.setInt(4, Integer.parseInt(getAnimal()));
            pst.setInt(4, Integer.parseInt(getCodigo()));
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;

        } catch (SQLException | NumberFormatException e) {
            System.out.println("AlterarVacinaAnimalCTR " + e.getMessage());
            return false;
        }
    }

    public boolean ExcluirVacinaDAO() {
        try {
            ConectaBanco.ConectaPostgres();
            comando = "delete from vacina_animal where codigo = " + getCodigo();
            pst = ConectaBanco.con.prepareStatement(comando);
            pst.execute();
            pst.close();
            ConectaBanco.con.close();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao Excluir VacinaDAO " + e.getMessage());
            return false;
        }
    }
    
    public ResultSet ConsultarVacinaDAO(){
        try {
            ConectaBanco.ConectaPostgres();
            comando = "select to_char(data_aplic, 'yyyy-mm-dd') as aplica, "
                    + "to_char(data_aplic, 'dd/MM/yyyy') "
                    + "from vacina_animal where "
                    + "to_char(data_aplic, 'yyyy-mm-dd') = to_char(now(), 'yyyy-mm-dd')";
            pst = ConectaBanco.con.prepareCall(comando, ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarVacinaDAO "+e.getMessage());
            return rs;
        }
    }
}
