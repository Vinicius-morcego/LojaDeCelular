/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.AgendamentoDAO;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Batman
 */
public class AgendamentoCTR {
    AgendamentoDAO objagendaDAO = new AgendamentoDAO();
    ResultSet rs;
    
    public boolean CadastrarAgendaCTR(String... parametros){
        try {
            objagendaDAO.setDataAgenda(java.sql.Date.valueOf(parametros[0]));
            objagendaDAO.setHora(parametros[1]);
            objagendaDAO.setDia(parametros[2]);
            objagendaDAO.setSituacao(parametros[3]);
            objagendaDAO.setCliente(parametros[4]);
            objagendaDAO.setAnimal(parametros[5]);
            objagendaDAO.setTelefone1(parametros[6]);
            objagendaDAO.setTelefone2(parametros[7]);
            objagendaDAO.setAtendente(parametros[8]);
            objagendaDAO.setVeterinario(parametros[9]);
            objagendaDAO.setObservacao(parametros[10]);
            objagendaDAO.CadastrarAgendaDAO();
            return true;
            
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarAgendaCTR "+e.getMessage());
            return false;
        }
    }
    
    public boolean AlterarAgendaCTR(String... parametros){
        try {
            objagendaDAO.setDataAgenda(java.sql.Date.valueOf(parametros[0]));
            objagendaDAO.setHora(parametros[1]);
            objagendaDAO.setDia(parametros[2]);
            objagendaDAO.setSituacao(parametros[3]);
            objagendaDAO.setCliente(parametros[4]);
            objagendaDAO.setAnimal(parametros[5]);
            objagendaDAO.setTelefone1(parametros[6]);
            objagendaDAO.setTelefone2(parametros[7]);
            objagendaDAO.setAtendente(parametros[8]);
            objagendaDAO.setVeterinario(parametros[9]);
            objagendaDAO.setObservacao(parametros[10]);
            objagendaDAO.setCodigo(parametros[11]);
            objagendaDAO.AlterarAgendaDAO();
            return true;
            
        } catch (Exception e) {
            System.out.println("Erro ao AlterarAgendaCTR "+e.getMessage());
            return false;
        }
    }
    
    public boolean ExcluirAgendaCTR(String codigo){
        try {
            objagendaDAO.setCodigo(codigo);
            objagendaDAO.ExcluirAgendaDAO();
            return true;
        } catch (Exception e) {
            
            return false;
        }
    }
    
    public ResultSet ConsultarAgendaCTR(String... parametros){
        try {
            objagendaDAO.setDataInicial(parametros[0]);
            objagendaDAO.setDataFinal(parametros[1]);
            objagendaDAO.setCliente(parametros[2]);
            objagendaDAO.setAnimal(parametros[3]);
            objagendaDAO.setAtendente(parametros[4]);
            objagendaDAO.setVeterinario(parametros[5]);
            objagendaDAO.setSituacao(parametros[6]);
            rs = objagendaDAO.ConsultarAgendamentoDAO();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarAgendaCTR "+e.getMessage());
            return rs;
        }
    }
    
    public ResultSet ConsultarAgendaCTR(String codigo){
        try {
            objagendaDAO.setCodigo(codigo);
            rs = objagendaDAO.ConsultarAgendaDAO();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarAgendaCTR "+e.getMessage());
            return rs;
        }
    }
}
