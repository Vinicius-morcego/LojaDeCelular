/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.ConfiguracaoRelatorioDAO;
import java.sql.ResultSet;

/**
 *
 * @author Vinicius
 */
public class ConfiguracaoRelatorioCTR {
    ConfiguracaoRelatorioDAO objconfigDAO = new ConfiguracaoRelatorioDAO();
    ResultSet rs;
    public boolean CadastrarConfiguracaoCTR(String... parametros){
        try {
            objconfigDAO.setTitulo(parametros[0]);
            objconfigDAO.setTelefone1(parametros[1]);
            objconfigDAO.setTelefone2(parametros[2]);
            objconfigDAO.setImagem(parametros[3]);
            objconfigDAO.CadastrarConfiguracaoDAO();
            return true;
        } catch (Exception e) {
            
            return false;
        }
    }
    
    public boolean AlterarConfiguracaoCTR(String... parametros){
        try {
            objconfigDAO.setTitulo(parametros[0]);
            objconfigDAO.setTelefone1(parametros[1]);
            objconfigDAO.setTelefone2(parametros[2]);
            
            objconfigDAO.AlterarConfiguracaoDAO();
            return true;
        } catch (Exception e) {
            
            return false;
        }
    }
    public boolean AlterarImagemRelatorioCTR(String imagem){
        try {
            objconfigDAO.setImagem(imagem);
            objconfigDAO.AlterarImagemRelatorio();
            return true;
        } catch (Exception e) {
            
            return false;
        }
    }
    public ResultSet ConsultarLogoCTR(){
        try {
            rs = objconfigDAO.ConsultarLogoDAO();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarLogoCTR "+e.getMessage());
            return rs;
        }
    }
}
