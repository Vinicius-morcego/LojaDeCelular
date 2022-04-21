/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.AnimaisDAO;
import java.sql.ResultSet;

/**
 *
 * @author Vinicius
 */
public class AnimalCTR {
    AnimaisDAO objAnimalDAO = new AnimaisDAO();
    ResultSet rs;
    
    public boolean CadastrarAnimalCTR(String... parametros){
        try {
            objAnimalDAO.setNome(parametros[0]);
            objAnimalDAO.setDono(parametros[1]);
            objAnimalDAO.setSexo(parametros[2]);
            objAnimalDAO.setNascimento(parametros[3]);
            objAnimalDAO.setPeso(parametros[4]);
            objAnimalDAO.setEspecie(parametros[5]);
            objAnimalDAO.setRaca(parametros[6]);
            objAnimalDAO.setIdentificacao(parametros[7]);
            objAnimalDAO.setFoto(parametros[8]);
            objAnimalDAO.setAlimentacao(parametros[9]);
            objAnimalDAO.setFobia(parametros[10]);
            objAnimalDAO.setDisponivel(parametros[11]);
            objAnimalDAO.setPossuiPedigree(parametros[12]);
            objAnimalDAO.setRestricao(parametros[13]);
            objAnimalDAO.setFalecido(parametros[14]);
            objAnimalDAO.setAgressivo(parametros[15]);
            objAnimalDAO.setHiperativo(parametros[16]);
            objAnimalDAO.setAntiSocial(parametros[17]);
            objAnimalDAO.setObsessivo(parametros[18]);
            objAnimalDAO.setHistorico(parametros[19]);
            objAnimalDAO.setComportamento(parametros[20]);
            objAnimalDAO.setRotina(parametros[21]);
            objAnimalDAO.setObservacao(parametros[22]);
            objAnimalDAO.CadastrarAnimalDAO();
            return true;
            
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarAnimalCTR "+e.getMessage());
            return false;
        }
    }
    
    public boolean AlterarAnimalCTR(String... parametros){
        try {
            objAnimalDAO.setNome(parametros[0]);
           // objAnimalDAO.setDono(parametros[1]);
            objAnimalDAO.setSexo(parametros[1]);
            objAnimalDAO.setNascimento(parametros[2]);
            objAnimalDAO.setPeso(parametros[3]);
            objAnimalDAO.setEspecie(parametros[4]);
            objAnimalDAO.setRaca(parametros[5]);
            objAnimalDAO.setIdentificacao(parametros[6]);           
            objAnimalDAO.setAlimentacao(parametros[7]);
            objAnimalDAO.setFobia(parametros[8]);
            objAnimalDAO.setDisponivel(parametros[9]);
            objAnimalDAO.setPossuiPedigree(parametros[10]);
            objAnimalDAO.setRestricao(parametros[11]);
            objAnimalDAO.setFalecido(parametros[12]);
            objAnimalDAO.setAgressivo(parametros[13]);
            objAnimalDAO.setHiperativo(parametros[14]);
            objAnimalDAO.setAntiSocial(parametros[15]);
            objAnimalDAO.setObsessivo(parametros[16]);
            objAnimalDAO.setHistorico(parametros[17]);
            objAnimalDAO.setComportamento(parametros[18]);
            objAnimalDAO.setRotina(parametros[19]);
            objAnimalDAO.setObservacao(parametros[20]);
            objAnimalDAO.setCodigo(parametros[21]);
            objAnimalDAO.AlterarAnimalDAO();
            return true;
            
        } catch (Exception e) {
            System.out.println("Erro ao AlterarAnimalCTR "+e.getMessage());
            return false;
        }
    }
    
    public boolean AlterarImagemAnimalCTR(String foto, String codigo){
        try {
            objAnimalDAO.setFoto(foto);
            objAnimalDAO.setCodigo(codigo);
            objAnimalDAO.AlterarImagemAnimalDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao AlterarImagemAnimalCTR "+e.getMessage());
            return false;
        }
    }
    public ResultSet ConsultarAnimalCTR(String... parametros){
        try {
            objAnimalDAO.setNome(parametros[0]);
            objAnimalDAO.setDono(parametros[1]);
            objAnimalDAO.setCodigo(parametros[2]);
            objAnimalDAO.setIdentificacao(parametros[3]);
            rs = objAnimalDAO.ConsultarAnimalDAO();
            return rs;
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarAnimalCTR "+e.getMessage());
            return rs;
        }
    }
    
   
 
}
