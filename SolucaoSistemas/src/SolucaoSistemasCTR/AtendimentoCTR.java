/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.AtendimentoDAO;
import java.sql.ResultSet;

/**
 *
 * @author Batman
 */
public class AtendimentoCTR {
    AtendimentoDAO objAtendimentoDAO = new AtendimentoDAO();
    ResultSet rs;
    
    public boolean CadastrarAtendimentoCTR(String... parametros){
        try {
            objAtendimentoDAO.setData_entrada(java.sql.Date.valueOf(parametros[0]));
            objAtendimentoDAO.setHora_entrada(parametros[1]);
            objAtendimentoDAO.setData_saida(java.sql.Date.valueOf(parametros[2]));
            objAtendimentoDAO.setHora_saida(parametros[3]);
            objAtendimentoDAO.setCliente(parametros[4]);
            objAtendimentoDAO.setTelefone(parametros[5]);
            objAtendimentoDAO.setVeterinario(parametros[6]);
            objAtendimentoDAO.setAtendente(parametros[7]);
            objAtendimentoDAO.setAnimal(parametros[8]);
            objAtendimentoDAO.setIdentificacao(parametros[9]);
            objAtendimentoDAO.setAcessorios(parametros[10]);
            objAtendimentoDAO.setVacinas(parametros[11]);
            objAtendimentoDAO.setAlimentacao(parametros[12]);
            objAtendimentoDAO.setFobia(parametros[13]);
            objAtendimentoDAO.setProcedimento(parametros[14]);
            objAtendimentoDAO.setPrescricao(parametros[15]);
            objAtendimentoDAO.setExame(parametros[16]);
            objAtendimentoDAO.setTemperatura(parametros[17]);
            objAtendimentoDAO.setFrequencia_cardiaca(parametros[18]);
            objAtendimentoDAO.setFrequencia_respiral(parametros[19]);
            objAtendimentoDAO.setTpc(parametros[20]);
            objAtendimentoDAO.setLinfonodos(parametros[21]);
            objAtendimentoDAO.setMucosa(parametros[22]);
            objAtendimentoDAO.setHidratacao(parametros[23]);
            objAtendimentoDAO.setEctoparasitas(parametros[24]);
            objAtendimentoDAO.setExames_solicitados(parametros[25]);
            objAtendimentoDAO.setSituacao(parametros[26]);
            objAtendimentoDAO.setProblema_atendimento(parametros[27]);
            objAtendimentoDAO.setDesconto(parametros[28]);
            objAtendimentoDAO.setTotal(parametros[29]);
            objAtendimentoDAO.CadastrarAtendimentoDAO();
            return true;         
            
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarAtendimentoCTR "+e.getMessage());
            return false;
        }
    }
    
    public ResultSet ConsultarMaiorCodigoAtendimentoCTR(){
        try {
            rs = objAtendimentoDAO.ConsultarMaiorCodigoAtendimentoDAO();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarMaiorCodigoAtendimentoCTR "+e.getMessage());
            return rs;
        }
    }
    
    public ResultSet ConsultarAtendimentoCTR(String... parametros){
        try {
            objAtendimentoDAO.setSituacao(parametros[0]);
            objAtendimentoDAO.setCliente(parametros[1]);
            objAtendimentoDAO.setDatainicial(parametros[2]);
            objAtendimentoDAO.setDatafinal(parametros[3]);            
            rs = objAtendimentoDAO.ConsultarAtendimentoDAO();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarAtendimentoCTR "+e.getMessage());
            return rs;
        }
    }
}
