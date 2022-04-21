/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasCTR;

import SolucaoSistemasDAO.PessoaDAO;
import java.sql.ResultSet;

/**
 *
 * @author Vinicius
 */
public class PessoaCTR {

    PessoaDAO objpessoaDAO = new PessoaDAO();
    ResultSet rs;
   

    public boolean CadastrarPessoaCTR(String... parametros) {
        try {
            objpessoaDAO.setNome(parametros[0]);
            objpessoaDAO.setApelido(parametros[1]);
            objpessoaDAO.setCpf(parametros[2]);
            objpessoaDAO.setRg(parametros[3]);
            objpessoaDAO.setData_nasc(parametros[4]);
            objpessoaDAO.setTelefone1(parametros[5]);
            objpessoaDAO.setTelefone2(parametros[6]);
            objpessoaDAO.setCnpj(parametros[7]);
            objpessoaDAO.setFacebook(parametros[8]);
            objpessoaDAO.setInstagram(parametros[9]);
            objpessoaDAO.setEmail(parametros[10]);
            objpessoaDAO.setBloqueado(parametros[11]);
            objpessoaDAO.setLiberado(parametros[12]);
            objpessoaDAO.setObservacao(parametros[13]);
            objpessoaDAO.setTipo(parametros[14]);
            objpessoaDAO.setFoto(parametros[15]);
            objpessoaDAO.setEndereco(parametros[16]);
            objpessoaDAO.setBairro(parametros[17]);
            objpessoaDAO.setCidade(parametros[18]);
            objpessoaDAO.setEstado(parametros[19]);
            objpessoaDAO.CadastrarPessoaDAO();
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao CadastrarPessoaCTR " + e.getMessage());
            return false;
        }
    }

    public boolean AlterarPessoaCTR(String... parametros) {
        try {
            objpessoaDAO.setNome(parametros[0]);
            objpessoaDAO.setApelido(parametros[1]);
            objpessoaDAO.setCpf(parametros[2]);
            objpessoaDAO.setRg(parametros[3]);
            objpessoaDAO.setData_nasc(parametros[4]);
            objpessoaDAO.setTelefone1(parametros[5]);
            objpessoaDAO.setTelefone2(parametros[6]);
            objpessoaDAO.setCnpj(parametros[7]);
            objpessoaDAO.setFacebook(parametros[8]);
            objpessoaDAO.setInstagram(parametros[9]);
            objpessoaDAO.setEmail(parametros[10]);
            objpessoaDAO.setBloqueado(parametros[11]);
            objpessoaDAO.setLiberado(parametros[12]);
            objpessoaDAO.setObservacao(parametros[13]);
            objpessoaDAO.setTipo(parametros[14]);
            objpessoaDAO.setFoto(parametros[15]);
            objpessoaDAO.setEndereco(parametros[16]);
            objpessoaDAO.setBairro(parametros[17]);
            objpessoaDAO.setCidade(parametros[18]);
            objpessoaDAO.setEstado(parametros[19]);
            objpessoaDAO.setCodigo(parametros[20]);
            objpessoaDAO.AlterarPessoaDAO();

            return true;

        } catch (Exception e) {
            System.out.println("Erro ao AlterarPessoaCTR " + e.getMessage());
            return false;
        }
    }
    public boolean AlterarImagemPessoaCTR(String foto, String codigo){
        try {
            objpessoaDAO.setFoto(foto);
            objpessoaDAO.setCodigo(codigo);
            objpessoaDAO.AlterarImagemPessoaDAO();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao AlterarImagemPessoaCTR "+e.getMessage());
            return false;
        }
    }
    public ResultSet ConsultarPessoaCTR(String codigo, String nome, String tipo, String apelido){
        try {
            objpessoaDAO.setCodigo(codigo);
            objpessoaDAO.setNome(nome);
            objpessoaDAO.setTipo(tipo);
            objpessoaDAO.setApelido(apelido);
           
            rs = objpessoaDAO.ConsultarPessoaDAO();
            return rs;
            
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarPessoaCTR "+e.getMessage());
            return rs;
        }
    }
}
