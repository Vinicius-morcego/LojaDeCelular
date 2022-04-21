/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.VacinaAnimalDAO;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Vinicius
 */
public class VacinaAnimalCTR {

    VacinaAnimalDAO objvacinaDAO = new VacinaAnimalDAO();
    ResultSet rs;
    

    public boolean CadastrarVacinaAnimalCTR(String... parametros) {
        try {
            objvacinaDAO.setVacina(parametros[0]);
            objvacinaDAO.setDataAplicacao(parametros[1]);
            objvacinaDAO.setDose(parametros[2]);
            objvacinaDAO.setAnimal(parametros[3]);
            objvacinaDAO.CadastrarVacinaAnimalDAO();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
            return false;
        }
    }
    
     public boolean AlterarVacinaAnimalCTR(String... parametros) {
        try {
            objvacinaDAO.setVacina(parametros[0]);
            objvacinaDAO.setDataAplicacao(parametros[1]);
            objvacinaDAO.setDose(parametros[2]);
            objvacinaDAO.setCodigo(parametros[3]);
            objvacinaDAO.AlterarVacinaAnimalDAO();
            return true;
        } catch (Exception e) {
            System.out.println("AlterarVacinaAnimalCTR " + e.getMessage());
            return false;
        }
    }

     public ResultSet ConsultarVacinaCTR(){
         try {
             
             rs = objvacinaDAO.ConsultarVacinaDAO();
             return rs;
         } catch (Exception e) {
             System.out.println("Erro ao ConsultarVacinaCTR "+e.getMessage());
             return rs;
         }
     }
    public boolean ExcluirVacinaCTR(String codigo) {
        try {
            objvacinaDAO.setCodigo(codigo);
            objvacinaDAO.ExcluirVacinaDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao ExcluirVacinaCTR " + e.getMessage());
            return false;
        }
    }
}
