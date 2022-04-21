/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasVIEW;

import SolucaoSistemasCTR.PermissaoCadastroCTR;
import SolucaoSistemasCTR.PermissaoFinanceiroCTR;
import SolucaoSistemasCTR.PermissaoRelatorioCTR;
import SolucaoSistemasCTR.UsuarioCTR;
import SolucaoSistemasDAO.ConectaBanco;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Vinicius
 */
public class PrincipalVIEW extends javax.swing.JFrame {

    UtilVIEW objutil = new UtilVIEW();
    String permissaoFuncionario = "";

    /**
     * Creates new form PrincipalVIEW
     */
    public PrincipalVIEW() {
        try {
            this.setContentPane(new MarcaDagua("/SolucaoSistemas/src/Imagem/legal.jpg"));
            // this.setContentPane(new MarcaDagua("/SolucaoSistemas/src/Imagem/cartaojuliana.png"));
        } catch (IOException ex) {
            System.out.println("Imagem não encontrada!");
        }
        initComponents();
        this.setLocationRelativeTo(null);

        //mnuPlanoContas.setVisible(false);
        mnuAnimais.setVisible(false);
        mnuAgendamento.setVisible(false);
        //mnuOrdemServico.setVisible(false);
        mnuInformacoes.setVisible(false);
        jMenu15.setVisible(false);

        //this.setTitle("Programa Pet Shop - Agendamento - Vendas - Financeiro - Solução Sistemas");
        this.setTitle("Programa Comercial - Vendas - Financeiro - Solução Sistemas");
        this.setExtendedState(PrincipalVIEW.MAXIMIZED_BOTH);
        this.setIconImage(
                Toolkit.getDefaultToolkit().
                        getImage("/SolucaoSistemas/src/Imagem/cifrao_32x32.png"));
        mnuCadastro.setMnemonic(KeyEvent.VK_F2);
        mnuArquivo.setMnemonic(KeyEvent.VK_F1);
        mnuVenda.setMnemonic(KeyEvent.VK_F5);
        mnuRelatorio.setMnemonic(KeyEvent.VK_F8);
        mnuAjuda.setMnemonic(KeyEvent.VK_F9);
        mnuProduto.setMnemonic(KeyEvent.VK_F3);
        mnuCompra.setMnemonic(KeyEvent.VK_F6);
        mnuFinanceiro.setMnemonic(KeyEvent.VK_F7);
        RegistarAtalhos();
        ConsultarFuncionario();
        ConsultarPermissaoCadastro();
        ConsultarPermissaoFinanceiro();
        ConsultarPermissaoRelatorio();

    }

    private void RegistarAtalhos() {
        JPanel painel = (JPanel) getContentPane();
        //Criamos as ações
        ActionMap mapear = painel.getActionMap();
        mapear.put("Financeiro", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mnuFinanceiro.doClick();
            }
        });
        mapear.put("Cadastro", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mnuCadastro.doClick();
            }
        });
        mapear.put("Arquivo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mnuArquivo.doClick();
            }
        });

        mapear.put("Relatorio", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mnuRelatorio.doClick();
            }
        });

        mapear.put("Ajuda", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mnuAjuda.doClick();

            }
        });

        mapear.put("Produto", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mnuProduto.doClick();

            }
        });

        mapear.put("Venda", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mnuVenda.doClick();

            }
        });

        mapear.put("Compra", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mnuCompra.doClick();

            }
        });

        mapear.put("Ordem", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mnuOrdemServico.doClick();

            }
        });

        //Registramos os componentes que queremos para as ações
        InputMap imap = painel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        imap.put(KeyStroke.getKeyStroke("F1"), "Arquivo");
        imap.put(KeyStroke.getKeyStroke("F2"), "Cadastro");
        imap.put(KeyStroke.getKeyStroke("F3"), "Produto");
        imap.put(KeyStroke.getKeyStroke("F4"), "Ordem");
        imap.put(KeyStroke.getKeyStroke("F5"), "Venda");
        imap.put(KeyStroke.getKeyStroke("F6"), "Compra");
        imap.put(KeyStroke.getKeyStroke("F7"), "Financeiro");
        imap.put(KeyStroke.getKeyStroke("F8"), "Relatorio");
        imap.put(KeyStroke.getKeyStroke("F9"), "Ajuda");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        mnuArquivo = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        mnuConfigurarRelatorio = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        mnuCadastro = new javax.swing.JMenu();
        jMenu12 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenu13 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenu14 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenu15 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenu16 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        mnuPlanoContas = new javax.swing.JMenuItem();
        mnuUsuario = new javax.swing.JMenuItem();
        mnuPermissoes = new javax.swing.JMenuItem();
        mnuAnimais = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        mnuAgendamento = new javax.swing.JMenu();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenuItem28 = new javax.swing.JMenuItem();
        mnuProduto = new javax.swing.JMenu();
        jMenuItem29 = new javax.swing.JMenuItem();
        jMenuItem30 = new javax.swing.JMenuItem();
        jMenuItem31 = new javax.swing.JMenuItem();
        mnuCodigoBarras = new javax.swing.JMenuItem();
        mnuOrdemServico = new javax.swing.JMenu();
        jMenuItem32 = new javax.swing.JMenuItem();
        jMenuItem33 = new javax.swing.JMenuItem();
        mnuVenda = new javax.swing.JMenu();
        jMenuItem39 = new javax.swing.JMenuItem();
        jMenuItem40 = new javax.swing.JMenuItem();
        jMenuItem41 = new javax.swing.JMenuItem();
        jMenuItem42 = new javax.swing.JMenuItem();
        jMenuItem43 = new javax.swing.JMenuItem();
        jMenuItem44 = new javax.swing.JMenuItem();
        jMenuItem45 = new javax.swing.JMenuItem();
        mnuCompra = new javax.swing.JMenu();
        jMenuItem46 = new javax.swing.JMenuItem();
        jMenuItem47 = new javax.swing.JMenuItem();
        jMenuItem48 = new javax.swing.JMenuItem();
        jMenuItem49 = new javax.swing.JMenuItem();
        jMenuItem50 = new javax.swing.JMenuItem();
        jMenuItem51 = new javax.swing.JMenuItem();
        jMenuItem52 = new javax.swing.JMenuItem();
        mnuFinanceiro = new javax.swing.JMenu();
        mnuCaixa = new javax.swing.JMenuItem();
        mnuContasReceber = new javax.swing.JMenuItem();
        mnuContasPagar = new javax.swing.JMenuItem();
        mnuRelatorio = new javax.swing.JMenu();
        mnuEstoque = new javax.swing.JMenu();
        jMenuItem57 = new javax.swing.JMenuItem();
        jMenuItem58 = new javax.swing.JMenuItem();
        mnuRelFinanceiro = new javax.swing.JMenu();
        jMenuItem56 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem60 = new javax.swing.JMenuItem();
        jMenuItem62 = new javax.swing.JMenuItem();
        jMenuItem61 = new javax.swing.JMenuItem();
        mnuRelPlanoContas = new javax.swing.JMenuItem();
        mnuAuditoria = new javax.swing.JMenuItem();
        mnuAjuda = new javax.swing.JMenu();
        jMenuItem64 = new javax.swing.JMenuItem();
        mnuInformacoes = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(null);

        mnuArquivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/arquivo_32x32.png"))); // NOI18N
        mnuArquivo.setText("F1-ARQUIVO");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, 0));
        jMenuItem1.setText("Backup");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        mnuArquivo.add(jMenuItem1);

        mnuConfigurarRelatorio.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, 0));
        mnuConfigurarRelatorio.setText("Configurar Relatório");
        mnuConfigurarRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuConfigurarRelatorioActionPerformed(evt);
            }
        });
        mnuArquivo.add(mnuConfigurarRelatorio);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, 0));
        jMenuItem3.setText("Sair");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        mnuArquivo.add(jMenuItem3);

        jMenuBar1.add(mnuArquivo);

        mnuCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/7881_32x32.png"))); // NOI18N
        mnuCadastro.setText("F2-CADASTRO");

        jMenu12.setText("Cliente");

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, 0));
        jMenuItem4.setText("Cadastro de Clientes");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem4);

        jMenuItem17.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, 0));
        jMenuItem17.setText("Relatório de Cadastro de Clientes");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem17);

        jMenuItem18.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, 0));
        jMenuItem18.setText("Relatório de Clientes Aniversariando");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem18);

        mnuCadastro.add(jMenu12);

        jMenu13.setText("Fornecedor");

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, 0));
        jMenuItem5.setText("Cadastro de Fornecedores");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem5);

        jMenuItem19.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, 0));
        jMenuItem19.setText("Relatório de Cadastro de Fornecedores");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem19);

        mnuCadastro.add(jMenu13);

        jMenu14.setText("Funcionário");

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, 0));
        jMenuItem7.setText("Cadastro de Funcionários");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu14.add(jMenuItem7);

        jMenuItem20.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, 0));
        jMenuItem20.setText("Relatório de Cadastro de Funcionários");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu14.add(jMenuItem20);

        jMenuItem21.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, 0));
        jMenuItem21.setText("Relatório de Funcionários Aniversariando");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu14.add(jMenuItem21);

        mnuCadastro.add(jMenu14);

        jMenu15.setText("Veterinário");

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, 0));
        jMenuItem6.setText("Cadastro de Veterinário");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu15.add(jMenuItem6);

        jMenuItem22.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_J, 0));
        jMenuItem22.setText("Relatório de Cadastro de Veterinários");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu15.add(jMenuItem22);

        jMenuItem23.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, 0));
        jMenuItem23.setText("Relatório de Veterinários Aniversariando");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu15.add(jMenuItem23);

        mnuCadastro.add(jMenu15);

        jMenu16.setText("Empresa");

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, 0));
        jMenuItem8.setText("Cadastro de Empresas");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu16.add(jMenuItem8);

        jMenuItem24.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, 0));
        jMenuItem24.setText("Relatório de Cadastro de Empresas");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu16.add(jMenuItem24);

        mnuCadastro.add(jMenu16);

        mnuPlanoContas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, 0));
        mnuPlanoContas.setText("Plano de Contas");
        mnuPlanoContas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuPlanoContasActionPerformed(evt);
            }
        });
        mnuCadastro.add(mnuPlanoContas);

        mnuUsuario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, 0));
        mnuUsuario.setText("Usuário");
        mnuUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuUsuarioActionPerformed(evt);
            }
        });
        mnuCadastro.add(mnuUsuario);

        mnuPermissoes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, 0));
        mnuPermissoes.setText("Permissões");
        mnuPermissoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuPermissoesActionPerformed(evt);
            }
        });
        mnuCadastro.add(mnuPermissoes);

        jMenuBar1.add(mnuCadastro);

        mnuAnimais.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/animal_32x32.png"))); // NOI18N
        mnuAnimais.setText("F3-ANIMAIS");

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, 0));
        jMenuItem11.setText("Cadastro de Animais");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        mnuAnimais.add(jMenuItem11);

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, 0));
        jMenuItem12.setText("Cadastro das Especies");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        mnuAnimais.add(jMenuItem12);

        jMenuItem13.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, 0));
        jMenuItem13.setText("Cadastro das Raças");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        mnuAnimais.add(jMenuItem13);

        jMenuItem14.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, 0));
        jMenuItem14.setText("Tabela das Vacinas");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        mnuAnimais.add(jMenuItem14);

        jMenuItem15.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_5, 0));
        jMenuItem15.setText("Relatório Cadastro dos Animais");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        mnuAnimais.add(jMenuItem15);

        jMenuItem16.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_6, 0));
        jMenuItem16.setText("Relatório dos Animais Aniversariantes");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        mnuAnimais.add(jMenuItem16);

        jMenuBar1.add(mnuAnimais);

        mnuAgendamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/8392_32x32.png"))); // NOI18N
        mnuAgendamento.setText("F4-AGENDAMENTO");

        jMenuItem25.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, 0));
        jMenuItem25.setText("Controlar Agendamento");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        mnuAgendamento.add(jMenuItem25);

        jMenuItem26.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, 0));
        jMenuItem26.setText("Montar Horários da Agenda");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        mnuAgendamento.add(jMenuItem26);

        jMenuItem27.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, 0));
        jMenuItem27.setText("Relatório do Agendamento");
        mnuAgendamento.add(jMenuItem27);

        jMenuItem28.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, 0));
        jMenuItem28.setText("Mensagem Padrão para Impressão do Agendamento");
        mnuAgendamento.add(jMenuItem28);

        jMenuBar1.add(mnuAgendamento);

        mnuProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/Produtos_32x32.png"))); // NOI18N
        mnuProduto.setText("F3-PRODUTOS/SERVIÇOS");

        jMenuItem29.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, 0));
        jMenuItem29.setText("Cadastro de Produtos / Serviços");
        jMenuItem29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem29ActionPerformed(evt);
            }
        });
        mnuProduto.add(jMenuItem29);

        jMenuItem30.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, 0));
        jMenuItem30.setText("Relatório de Cadastro de Produtos / Serviços");
        jMenuItem30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem30ActionPerformed(evt);
            }
        });
        mnuProduto.add(jMenuItem30);

        jMenuItem31.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, 0));
        jMenuItem31.setText("Tabela de Preços de Produtos / Serviços");
        jMenuItem31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem31ActionPerformed(evt);
            }
        });
        mnuProduto.add(jMenuItem31);

        mnuCodigoBarras.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, 0));
        mnuCodigoBarras.setText("Gerar Código de Barras");
        mnuCodigoBarras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCodigoBarrasActionPerformed(evt);
            }
        });
        mnuProduto.add(mnuCodigoBarras);

        jMenuBar1.add(mnuProduto);

        mnuOrdemServico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/OS_32x32.png"))); // NOI18N
        mnuOrdemServico.setText("F4-ORDEM DE SERVIÇO");

        jMenuItem32.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, 0));
        jMenuItem32.setText("Nova Ordem de Serviço");
        jMenuItem32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem32ActionPerformed(evt);
            }
        });
        mnuOrdemServico.add(jMenuItem32);

        jMenuItem33.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, 0));
        jMenuItem33.setText("Pesquisar Ordem de Serviço");
        jMenuItem33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem33ActionPerformed(evt);
            }
        });
        mnuOrdemServico.add(jMenuItem33);

        jMenuBar1.add(mnuOrdemServico);

        mnuVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/cartao_32x32.png"))); // NOI18N
        mnuVenda.setText("F5-VENDAS");

        jMenuItem39.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, 0));
        jMenuItem39.setText("Novo Pedido");
        jMenuItem39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem39ActionPerformed(evt);
            }
        });
        mnuVenda.add(jMenuItem39);

        jMenuItem40.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, 0));
        jMenuItem40.setText("Finalizar Pedido");
        jMenuItem40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem40ActionPerformed(evt);
            }
        });
        mnuVenda.add(jMenuItem40);

        jMenuItem41.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, 0));
        jMenuItem41.setText("Cancelar Pedido");
        jMenuItem41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem41ActionPerformed(evt);
            }
        });
        mnuVenda.add(jMenuItem41);

        jMenuItem42.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, 0));
        jMenuItem42.setText("Cancelar Finalizados");
        jMenuItem42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem42ActionPerformed(evt);
            }
        });
        mnuVenda.add(jMenuItem42);

        jMenuItem43.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_5, 0));
        jMenuItem43.setText("Pesquisar Pendentes");
        jMenuItem43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem43ActionPerformed(evt);
            }
        });
        mnuVenda.add(jMenuItem43);

        jMenuItem44.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_6, 0));
        jMenuItem44.setText("Pesquisar Cancelados");
        jMenuItem44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem44ActionPerformed(evt);
            }
        });
        mnuVenda.add(jMenuItem44);

        jMenuItem45.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_7, 0));
        jMenuItem45.setText("Pesquisar Finalizados");
        jMenuItem45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem45ActionPerformed(evt);
            }
        });
        mnuVenda.add(jMenuItem45);

        jMenuBar1.add(mnuVenda);

        mnuCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/caminhao_32x32.png"))); // NOI18N
        mnuCompra.setText("F6-COMPRAS");

        jMenuItem46.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, 0));
        jMenuItem46.setText("Novo Pedido");
        jMenuItem46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem46ActionPerformed(evt);
            }
        });
        mnuCompra.add(jMenuItem46);

        jMenuItem47.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, 0));
        jMenuItem47.setText("Finalizar Pedido");
        jMenuItem47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem47ActionPerformed(evt);
            }
        });
        mnuCompra.add(jMenuItem47);

        jMenuItem48.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, 0));
        jMenuItem48.setText("Cancelar Pedido");
        jMenuItem48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem48ActionPerformed(evt);
            }
        });
        mnuCompra.add(jMenuItem48);

        jMenuItem49.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, 0));
        jMenuItem49.setText("Cancelar Finalizados");
        jMenuItem49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem49ActionPerformed(evt);
            }
        });
        mnuCompra.add(jMenuItem49);

        jMenuItem50.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_5, 0));
        jMenuItem50.setText("Pesquisar Pendentes");
        jMenuItem50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem50ActionPerformed(evt);
            }
        });
        mnuCompra.add(jMenuItem50);

        jMenuItem51.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_6, 0));
        jMenuItem51.setText("Pesquisar Cancelados");
        jMenuItem51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem51ActionPerformed(evt);
            }
        });
        mnuCompra.add(jMenuItem51);

        jMenuItem52.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_7, 0));
        jMenuItem52.setText("Pesquisar Finalizados");
        jMenuItem52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem52ActionPerformed(evt);
            }
        });
        mnuCompra.add(jMenuItem52);

        jMenuBar1.add(mnuCompra);

        mnuFinanceiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/6939_32x32.png"))); // NOI18N
        mnuFinanceiro.setText("F7-FINANCEIRO");

        mnuCaixa.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, 0));
        mnuCaixa.setText("Caixa");
        mnuCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCaixaActionPerformed(evt);
            }
        });
        mnuFinanceiro.add(mnuCaixa);

        mnuContasReceber.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, 0));
        mnuContasReceber.setText("Contas a Receber");
        mnuContasReceber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuContasReceberActionPerformed(evt);
            }
        });
        mnuFinanceiro.add(mnuContasReceber);

        mnuContasPagar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, 0));
        mnuContasPagar.setText("Contas à Pagar");
        mnuContasPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuContasPagarActionPerformed(evt);
            }
        });
        mnuFinanceiro.add(mnuContasPagar);

        jMenuBar1.add(mnuFinanceiro);

        mnuRelatorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/relatorio_32x32.png"))); // NOI18N
        mnuRelatorio.setText("F8-RELATÓRIOS");

        mnuEstoque.setText("Estoque");

        jMenuItem57.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, 0));
        jMenuItem57.setText("Estoque Baixo");
        jMenuItem57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem57ActionPerformed(evt);
            }
        });
        mnuEstoque.add(jMenuItem57);

        jMenuItem58.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, 0));
        jMenuItem58.setText("Estoque Normal");
        jMenuItem58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem58ActionPerformed(evt);
            }
        });
        mnuEstoque.add(jMenuItem58);

        mnuRelatorio.add(mnuEstoque);

        mnuRelFinanceiro.setText("Financeiro");

        jMenuItem56.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, 0));
        jMenuItem56.setText("Movimento Caixa");
        jMenuItem56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem56ActionPerformed(evt);
            }
        });
        mnuRelFinanceiro.add(jMenuItem56);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, 0));
        jMenuItem2.setText("Vendas");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        mnuRelFinanceiro.add(jMenuItem2);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_5, 0));
        jMenuItem9.setText("Produtos mais Vendidos");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        mnuRelFinanceiro.add(jMenuItem9);

        jMenuItem60.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_6, 0));
        jMenuItem60.setText("Balanço Financeiro");
        jMenuItem60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem60ActionPerformed(evt);
            }
        });
        mnuRelFinanceiro.add(jMenuItem60);

        jMenuItem62.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_7, 0));
        jMenuItem62.setText("Despesas");
        jMenuItem62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem62ActionPerformed(evt);
            }
        });
        mnuRelFinanceiro.add(jMenuItem62);

        jMenuItem61.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_8, 0));
        jMenuItem61.setText("Receitas");
        jMenuItem61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem61ActionPerformed(evt);
            }
        });
        mnuRelFinanceiro.add(jMenuItem61);

        mnuRelatorio.add(mnuRelFinanceiro);

        mnuRelPlanoContas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_9, 0));
        mnuRelPlanoContas.setText("Plano de Contas");
        mnuRelPlanoContas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRelPlanoContasActionPerformed(evt);
            }
        });
        mnuRelatorio.add(mnuRelPlanoContas);

        mnuAuditoria.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, 0));
        mnuAuditoria.setText("Auditoria");
        mnuAuditoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAuditoriaActionPerformed(evt);
            }
        });
        mnuRelatorio.add(mnuAuditoria);

        jMenuBar1.add(mnuRelatorio);

        mnuAjuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/ajuda_32x32.png"))); // NOI18N
        mnuAjuda.setText("F9-AJUDA");

        jMenuItem64.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, 0));
        jMenuItem64.setText("Suporte");
        jMenuItem64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem64ActionPerformed(evt);
            }
        });
        mnuAjuda.add(jMenuItem64);

        mnuInformacoes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, 0));
        mnuInformacoes.setText("Informações sobre o Programa");
        mnuAjuda.add(mnuInformacoes);

        jMenuBar1.add(mnuAjuda);

        setJMenuBar(jMenuBar1);

        setBounds(0, 0, 1008, 330);
    }// </editor-fold>//GEN-END:initComponents

    private void mnuConfigurarRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuConfigurarRelatorioActionPerformed
        new ConfiguracaoVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_mnuConfigurarRelatorioActionPerformed
    public static boolean cliente = false;
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        cliente = true;
        new PessoaVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed
    public static boolean fornecedor = false;
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        fornecedor = true;
        new PessoaVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed
    public static boolean funcionario = false;
    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        funcionario = true;
        new PessoaVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed
    public static boolean veterinario = false;
    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        veterinario = true;
        new PessoaVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed
    public static boolean empresa = false;
    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        empresa = true;
        new PessoaVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed

        new PesquisaAnimaisVIEW(null, true).setVisible(true);


    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
        new PesquisaAgendamentoVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
//        new AgendamentoVIEW(null, true).setVisible(true);
//        new MontarAgendaVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenuItem29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem29ActionPerformed
        VendaVIEW.clientePedido = false;
        new PesquisaProdutosVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem29ActionPerformed
    public static boolean venda = false;
    private void jMenuItem39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem39ActionPerformed
        venda = true;
        new VendaVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem39ActionPerformed

    private void mnuUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuUsuarioActionPerformed
        new UsuarioVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_mnuUsuarioActionPerformed
    public static boolean especie = false;
    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        especie = true;
        new PesquisaDiversosVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed
    public static boolean raca = false;
    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        raca = true;
        new PesquisaDiversosVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            UtilVIEW.realizaBackup();
            // UtilVIEW.EmailAnexo();
//            if(UtilVIEW.VerificaConexao() == true){
//               
//            }else if(UtilVIEW.VerificaConexao() == false){
//                JOptionPane.showMessageDialog(null, "Sem conexão com a internet!");
//            }
        } catch (IOException | InterruptedException ex) {
            //Logger.getLogger(PrincipalVIEW.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Logger.getLogger(PrincipalVIEW.class.getName()).log(Level.SEVERE, null, ex);
        //  Logger.getLogger(PrincipalVIEW.class.getName()).log(Level.SEVERE, null, ex);

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        try {
            int perguntaPrincipal = -1;
            perguntaPrincipal = JOptionPane.showConfirmDialog(null, "Deseja sair?",
                    "MENSAGEM", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (perguntaPrincipal == 0) {
                UtilVIEW.realizaBackup();
                System.exit(0);
            } else if (perguntaPrincipal == 1) {

            }
            permissaoFuncionario = "";
        } catch (HeadlessException | IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        try {
            UtilVIEW objutilVIEW = new UtilVIEW();
            objutilVIEW.ImprimirPessoas("CLIENTE", "RELATÓRIO DE CLIENTES", "RG:");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        try {
            UtilVIEW objutilVIEW = new UtilVIEW();
            objutilVIEW.ImprimirPessoas("FORNECEDOR", "RELATÓRIO DE FORNECEDORES", "RG:");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        try {
            UtilVIEW objutilVIEW = new UtilVIEW();
            objutilVIEW.ImprimirPessoas("FUNCIONARIO", "RELATÓRIO DE FUNCIONARIOS", "RG:");
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        try {
            UtilVIEW objutilVIEW = new UtilVIEW();
            objutilVIEW.ImprimirPessoas("VETERINARIO", "RELATÓRIO DE VETERINARIOS", "RG/CRMV:");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mensagem " + e.getMessage(), "MENSAGEM", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        try {
            UtilVIEW objutilVIEW = new UtilVIEW();
            objutilVIEW.ImprimirPessoas("EMPRESA", "RELATÓRIO DE EMPRESA", "RG:");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        try {
            UtilVIEW objutilVIEW = new UtilVIEW();
            objutilVIEW.ImprimirAniversarios("CLIENTE", "ANIVERSARIO DE CLIENTES");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        try {
            UtilVIEW objutilVIEW = new UtilVIEW();
            objutilVIEW.ImprimirAniversarios("FUNCIONARIO", "ANIVERSARIO DE FUNCIONARIOS");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        try {
            UtilVIEW objutilVIEW = new UtilVIEW();
            objutilVIEW.ImprimirAniversarios("VETERINARIO", "ANIVERSARIO DE VETERINARIOS");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem64ActionPerformed
        new SuporteVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem64ActionPerformed
    public static boolean relPrecoProdutos = false;
    private void jMenuItem31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem31ActionPerformed
        relPrecoProdutos = true;
        new RelatorioVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem31ActionPerformed

    public static boolean pedidoFinalizar = false;
    private void jMenuItem40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem40ActionPerformed
        situacaoPedido = "Pendente";
        pedidoFinalizar = true;
        VendaVIEW.finalizarPedido = true;
        cancelarPedido = false;
        compra = false;
        new PesquisarPedidosVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem40ActionPerformed
    public static boolean tabelaVacinas = false;
    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        tabelaVacinas = true;
        new PeriodoVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        objutil.ImprimirAnimais();
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        objutil.ImprimirAniversarioAnimal();
    }//GEN-LAST:event_jMenuItem16ActionPerformed
    public static boolean relCadProdutos = false;
    private void jMenuItem30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed
        relCadProdutos = true;
        new RelatorioVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem30ActionPerformed

    private void mnuCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCaixaActionPerformed
        try {
            new PedeSenhaVIEW(null, true).setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Acesso negado! " + e.getMessage(), "MENSAGEM", JOptionPane.WARNING_MESSAGE);
        }


    }//GEN-LAST:event_mnuCaixaActionPerformed
    public static String situacaoOrdemServico = "";
    public static boolean compra = false;
    private void jMenuItem46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem46ActionPerformed
        compra = true;
        new VendaVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem46ActionPerformed
    public static boolean contasreceber = false;
    private void mnuContasReceberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuContasReceberActionPerformed
        contasreceber = true;
        new PesquisarFinanceiroVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_mnuContasReceberActionPerformed
    public static boolean contaspagar = false;
    private void mnuContasPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuContasPagarActionPerformed
        contaspagar = true;
        new PesquisarFinanceiroVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_mnuContasPagarActionPerformed
    public static String situacaoPedido = "";
    private void jMenuItem43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem43ActionPerformed
        situacaoPedido = "Pendente";
        cancelarPedido = false;
        compra = false;
        pedidoFinalizar = true;
        VendaVIEW.finalizarPedido = true;
        new PesquisarPedidosVIEW(null, true).setVisible(true);

    }//GEN-LAST:event_jMenuItem43ActionPerformed
    public static boolean cancelarPedido = false;
    private void jMenuItem41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem41ActionPerformed
        cancelarPedido = true;
        pedidoFinalizar = false;
        compra = false;
        situacaoPedido = "Pendente";
        new PesquisarPedidosVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem41ActionPerformed

    private void jMenuItem42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem42ActionPerformed
        VendaVIEW.finalizarPedido = true;
        cancelarPedido = true;
        pedidoFinalizar = true;
        compra = false;
        situacaoPedido = "Finalizado";
        new PesquisarPedidosVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem42ActionPerformed

    private void jMenuItem49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem49ActionPerformed
        VendaVIEW.finalizarPedido = true;
        cancelarPedido = true;
        pedidoFinalizar = true;
        compra = true;
        situacaoPedido = "Finalizado";
        new PesquisarPedidosVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem49ActionPerformed

    private void jMenuItem50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem50ActionPerformed
        VendaVIEW.finalizarPedido = true;
        pedidoFinalizar = true;
        cancelarPedido = false;
        compra = true;
        situacaoPedido = "Pendente";
        new PesquisarPedidosVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem50ActionPerformed

    private void jMenuItem44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem44ActionPerformed
        compra = false;
        situacaoPedido = "Cancelado";
        new PesquisarPedidosVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem44ActionPerformed

    private void jMenuItem45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem45ActionPerformed
        VendaVIEW.finalizarPedido = true;
        cancelarPedido = true;
        pedidoFinalizar = true;
        compra = false;
        situacaoPedido = "Finalizado";
        new PesquisarPedidosVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem45ActionPerformed
    public static boolean estoqueBaixo = false;
    private void jMenuItem57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem57ActionPerformed
        estoqueBaixo = true;
        ImprimirEstoque();

    }//GEN-LAST:event_jMenuItem57ActionPerformed
    public static boolean estoqueNormal = false;
    private void jMenuItem58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem58ActionPerformed
        estoqueNormal = true;
        ImprimirEstoque();
    }//GEN-LAST:event_jMenuItem58ActionPerformed
    public static boolean relCaixa = false;
    private void jMenuItem56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem56ActionPerformed
        relCaixa = true;
        new PeriodoVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem56ActionPerformed
    public static boolean relBalanco = false;
    private void jMenuItem60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem60ActionPerformed

        new BalancoFinanceiro(null, true).setVisible(true);

    }//GEN-LAST:event_jMenuItem60ActionPerformed
    public static boolean relDespesa = false;
    private void jMenuItem62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem62ActionPerformed
        relDespesa = true;
        new PeriodoVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem62ActionPerformed
    public static boolean relReceita = false;
    private void jMenuItem61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem61ActionPerformed
        relReceita = true;
        new PeriodoVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem61ActionPerformed

    private void jMenuItem47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem47ActionPerformed
        VendaVIEW.finalizarPedido = true;
        cancelarPedido = false;
        pedidoFinalizar = true;
        compra = true;
        situacaoPedido = "Pendente";
        new PesquisarPedidosVIEW(null, true).setVisible(true);


    }//GEN-LAST:event_jMenuItem47ActionPerformed

    private void jMenuItem48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem48ActionPerformed
        VendaVIEW.finalizarPedido = false;
        pedidoFinalizar = false;
        cancelarPedido = true;
        compra = true;
        situacaoPedido = "Pendente";
        new PesquisarPedidosVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem48ActionPerformed

    private void jMenuItem51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem51ActionPerformed
        compra = true;
        situacaoPedido = "Cancelado";
        new PesquisarPedidosVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem51ActionPerformed

    private void jMenuItem52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem52ActionPerformed
        VendaVIEW.finalizarPedido = true;
        cancelarPedido = true;
        pedidoFinalizar = true;
        compra = true;
        situacaoPedido = "Finalizado";
        new PesquisarPedidosVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem52ActionPerformed

    private void mnuRelPlanoContasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRelPlanoContasActionPerformed
        try {
            ImprimirPlanoContas();
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }//GEN-LAST:event_mnuRelPlanoContasActionPerformed

    private void mnuPermissoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuPermissoesActionPerformed
        new PermissaoVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_mnuPermissoesActionPerformed
    public static boolean vendas = false;
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        vendas = true;
        new PeriodoVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed
    public static boolean produtosMais = false;
    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        produtosMais = true;
        new PeriodoVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void mnuAuditoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAuditoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnuAuditoriaActionPerformed

    private void mnuCodigoBarrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCodigoBarrasActionPerformed
        new EtiquetaVIEW(null, true).setVisible(true);
        //new CodigoBarrasVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_mnuCodigoBarrasActionPerformed

    private void jMenuItem32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem32ActionPerformed
        ProdutosServicosVIEW.marca = false;
        ProdutosServicosVIEW.categoria = false;
        VendaVIEW.clientePedido = false;
        PermissaoVIEW.permissaoFuncionario = false;
        new OrdemServicoVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem32ActionPerformed

    private void jMenuItem33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem33ActionPerformed
        situacaoOrdemServico = "Pendente";
        situacaoPedido = "";
        pedidoFinalizar = true;
        VendaVIEW.finalizarPedido = false;
        new PesquisarPedidosVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem33ActionPerformed

    private void mnuPlanoContasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuPlanoContasActionPerformed
        new PlanoContasVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_mnuPlanoContasActionPerformed

    private void ImprimirEstoque() {
        try {
            int op = -1;
            op = JOptionPane.showConfirmDialog(null, "Impressora esta ligada?", "PERGUNTA",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (op == 0) {
                // this.dispose();
                URL arquivo = null;
                if (estoqueBaixo == true) {
                    arquivo = getClass().getResource("/Relatorios/estoqueCategoria.jasper");
                } else if (estoqueNormal == true) {
                    arquivo = getClass().getResource("/Relatorios/estoqueNormal.jasper");
                }

                //JOptionPane.showMessageDialog(this, arquivo.toString());
                if (arquivo == null) {
                    System.out.println("ARQUIVO INEXISTENTE");
                } else {

                    ConectaBanco.ConectaPostgres();

                    JasperPrint rel = null;
                    Map parametros = new HashMap();
                    parametros.put("tipo", "PRODUTO");

                    JasperReport jr;
                    try {
                        jr = (JasperReport) JRLoader.loadObject(arquivo);
                        rel = JasperFillManager.fillReport(jr, parametros, ConectaBanco.con);
                        JasperViewer.viewReport(rel, false);
                    } catch (JRException ex) {
                        System.out.println("" + ex.getMessage());
                    }

                }
                estoqueNormal = false;
                estoqueBaixo = false;
            } else if (op == 1) {

            }
        } catch (HeadlessException e) {
        }
    }

    private void ConsultarFuncionario() {
        try {
            UsuarioCTR objusuarioCTR = new UsuarioCTR();
            char[] pegaSenha;
            String senha = "", usuario = "";
            pegaSenha = LogarVIEW.txtKey.getPassword();
            senha = String.valueOf(pegaSenha);
            usuario = LogarVIEW.txtUser.getText();
            ResultSet user = null;
            user = objusuarioCTR.ConsultarAcessoCTR(usuario, UtilVIEW.Criptografar(senha));
            if (user.next()) {
                permissaoFuncionario = user.getString("pessoa");

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar Funcionario",
                    "MENSAGEM", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void ConsultarPermissaoCadastro() {
        try {
            PermissaoCadastroCTR objpermissaoCadastro = new PermissaoCadastroCTR();
            ResultSet permissaoCadastro = null;
            permissaoCadastro = objpermissaoCadastro.ConsultarPermissaoCadastroCTR(permissaoFuncionario);
            if (permissaoCadastro.next()) {
                switch (permissaoCadastro.getString("usuario")) {
                    case "SIM":
                        mnuUsuario.setVisible(true);
                        break;
                    case "NAO":
                        mnuUsuario.setVisible(false);
                        break;
                    default:
                        mnuUsuario.setVisible(true);
                        break;
                }
                switch (permissaoCadastro.getString("planocontas")) {
                    case "SIM":
                        mnuPlanoContas.setVisible(true);
                        break;
                    case "NAO":
                        mnuPlanoContas.setVisible(false);
                        break;
                    default:
                        mnuPlanoContas.setVisible(true);
                        break;
                }
                switch (permissaoCadastro.getString("permissoes")) {
                    case "SIM":
                        mnuPermissoes.setVisible(true);
                        break;
                    case "NAO":
                        mnuPermissoes.setVisible(false);
                        break;
                    default:
                        mnuPermissoes.setVisible(true);
                        break;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ConsultarPermissoesCadastro",
                    "MENSAGEM", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void ConsultarPermissaoFinanceiro() {
        try {
            PermissaoFinanceiroCTR objpermissaoFinanceiro = new PermissaoFinanceiroCTR();
            ResultSet permissaoFinanceiro = null;
            permissaoFinanceiro = objpermissaoFinanceiro.ConsultarPermissaoFinanceiroCTR(permissaoFuncionario);
            if (permissaoFinanceiro.next()) {
                switch (permissaoFinanceiro.getString("caixa")) {
                    case "SIM":
                        mnuCaixa.setVisible(true);
                        break;
                    case "NAO":
                        mnuCaixa.setVisible(false);
                        break;
                    default:
                        mnuCaixa.setVisible(true);
                        break;
                }
                switch (permissaoFinanceiro.getString("contasreceber")) {
                    case "SIM":
                        mnuContasReceber.setVisible(true);
                        break;
                    case "NAO":
                        mnuContasReceber.setVisible(false);
                        break;
                    default:
                        mnuContasReceber.setVisible(true);
                        break;
                }
                switch (permissaoFinanceiro.getString("contaspagar")) {
                    case "SIM":
                        mnuContasPagar.setVisible(true);
                        break;
                    case "NAO":
                        mnuContasPagar.setVisible(false);
                        break;
                    default:
                        mnuContasPagar.setVisible(true);
                        break;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ConsultarPermissoesFinanceiro",
                    "MENSAGEM", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void ConsultarPermissaoRelatorio() {
        try {
            PermissaoRelatorioCTR objpermissaoRelatorio = new PermissaoRelatorioCTR();
            ResultSet permissaoRelatorio = null;
            permissaoRelatorio = objpermissaoRelatorio.ConsultarPermissaoRelatorioCTR(permissaoFuncionario);
            if (permissaoRelatorio.next()) {
                switch (permissaoRelatorio.getString("estoque")) {
                    case "SIM":
                        mnuEstoque.setVisible(true);
                        break;
                    case "NAO":
                        mnuEstoque.setVisible(false);
                        break;
                    default:
                        mnuEstoque.setVisible(true);
                        break;
                }
                switch (permissaoRelatorio.getString("financeiro")) {
                    case "SIM":
                        mnuRelFinanceiro.setVisible(true);
                        break;
                    case "NAO":
                        mnuRelFinanceiro.setVisible(false);
                        break;
                    default:
                        mnuRelFinanceiro.setVisible(true);
                        break;
                }
                switch (permissaoRelatorio.getString("planocontas")) {
                    case "SIM":
                        mnuRelPlanoContas.setVisible(true);
                        break;
                    case "NAO":
                        mnuRelPlanoContas.setVisible(false);
                        break;
                    default:
                        mnuRelPlanoContas.setVisible(true);
                        break;
                }
                switch (permissaoRelatorio.getString("auditoria")) {
                    case "SIM":
                        mnuAuditoria.setVisible(true);
                        break;
                    case "NAO":
                        mnuAuditoria.setVisible(false);
                        break;
                    default:
                        mnuAuditoria.setVisible(true);
                        break;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ConsultarPermissoesRelatorio",
                    "MENSAGEM", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void ImprimirPlanoContas() {
        try {
            int op = -1;
            op = JOptionPane.showConfirmDialog(null, "Impressora esta ligada?", "PERGUNTA",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (op == 0) {
                // this.dispose();
                URL arquivo = getClass().getResource("/Relatorios/PlanoContaContabil.jasper");

                //JOptionPane.showMessageDialog(this, arquivo.toString());
                if (arquivo == null) {
                    System.out.println("ARQUIVO INEXISTENTE");
                } else {

                    ConectaBanco.ConectaPostgres();
                    JasperPrint rel = null;
                    Map parametros = new HashMap();

                    JasperReport jr;
                    try {
                        jr = (JasperReport) JRLoader.loadObject(arquivo);
                        rel = JasperFillManager.fillReport(jr, parametros, ConectaBanco.con);
                        JasperViewer.viewReport(rel, false);
                    } catch (JRException ex) {
                        System.out.println("" + ex.getMessage());
                    }

                }
            } else if (op == 1) {

            }
        } catch (HeadlessException e) {
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PrincipalVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PrincipalVIEW().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu14;
    private javax.swing.JMenu jMenu15;
    private javax.swing.JMenu jMenu16;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem31;
    private javax.swing.JMenuItem jMenuItem32;
    private javax.swing.JMenuItem jMenuItem33;
    private javax.swing.JMenuItem jMenuItem39;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem40;
    private javax.swing.JMenuItem jMenuItem41;
    private javax.swing.JMenuItem jMenuItem42;
    private javax.swing.JMenuItem jMenuItem43;
    private javax.swing.JMenuItem jMenuItem44;
    private javax.swing.JMenuItem jMenuItem45;
    private javax.swing.JMenuItem jMenuItem46;
    private javax.swing.JMenuItem jMenuItem47;
    private javax.swing.JMenuItem jMenuItem48;
    private javax.swing.JMenuItem jMenuItem49;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem50;
    private javax.swing.JMenuItem jMenuItem51;
    private javax.swing.JMenuItem jMenuItem52;
    private javax.swing.JMenuItem jMenuItem56;
    private javax.swing.JMenuItem jMenuItem57;
    private javax.swing.JMenuItem jMenuItem58;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem60;
    private javax.swing.JMenuItem jMenuItem61;
    private javax.swing.JMenuItem jMenuItem62;
    private javax.swing.JMenuItem jMenuItem64;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenu mnuAgendamento;
    private javax.swing.JMenu mnuAjuda;
    private javax.swing.JMenu mnuAnimais;
    private javax.swing.JMenu mnuArquivo;
    private javax.swing.JMenuItem mnuAuditoria;
    private javax.swing.JMenu mnuCadastro;
    private javax.swing.JMenuItem mnuCaixa;
    private javax.swing.JMenuItem mnuCodigoBarras;
    private javax.swing.JMenu mnuCompra;
    private javax.swing.JMenuItem mnuConfigurarRelatorio;
    private javax.swing.JMenuItem mnuContasPagar;
    private javax.swing.JMenuItem mnuContasReceber;
    private javax.swing.JMenu mnuEstoque;
    private javax.swing.JMenu mnuFinanceiro;
    private javax.swing.JMenuItem mnuInformacoes;
    private javax.swing.JMenu mnuOrdemServico;
    private javax.swing.JMenuItem mnuPermissoes;
    private javax.swing.JMenuItem mnuPlanoContas;
    private javax.swing.JMenu mnuProduto;
    private javax.swing.JMenu mnuRelFinanceiro;
    private javax.swing.JMenuItem mnuRelPlanoContas;
    private javax.swing.JMenu mnuRelatorio;
    private javax.swing.JMenuItem mnuUsuario;
    private javax.swing.JMenu mnuVenda;
    // End of variables declaration//GEN-END:variables
}
