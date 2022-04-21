/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasVIEW;

import SolucaoSistemasCTR.CaixaCTR;
import SolucaoSistemasCTR.ContasPagarCTR;
import SolucaoSistemasCTR.ContasReceberCTR;
import SolucaoSistemasDAO.ConectaBanco;
import java.awt.HeadlessException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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
public class PesquisarFinanceiroVIEW extends javax.swing.JDialog {

    public static String codigoLancamento = "", codigoParcela = "";
    NumberFormat numeroPesquisa = DecimalFormat.getCurrencyInstance(
            new Locale("pt", "BR"));
    Double entradas = 0.0, saidas = 0.0, resultado = 0.0, saldoAtual = 0.0;
    Integer sinal = 0;

    /**
     * Creates new form PesquisarFinanceiroVIEW
     *
     * @param parent
     * @param modal
     */
    public PesquisarFinanceiroVIEW(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        UtilVIEW.FormatarLabel(lblEntradas, lblSaidas, lblSaldoAtual);
        this.setLocationRelativeTo(null);
        if (PrincipalVIEW.contasreceber == true || PrincipalVIEW.contaspagar == true) {
            btnAlterar.setText("Cancelar");
            btnRelatorio.setText("Pesquisar");
            btnRecibo.setVisible(false);
            if (PrincipalVIEW.contaspagar == true) {
                btnRecibo.setText("Novo");
                btnRecibo.setVisible(true);

            }else if(PrincipalVIEW.contasreceber == true){
                btnRelatorio.setBounds(252, 370, 119, 40);
            }
            txtPesquisar.setVisible(false);
            lblSaldoAnterior.setVisible(false);
            lblEntradas.setVisible(false);
            lblSaidas.setVisible(false);
            lblSaldoAtual.setVisible(false);
            if (PrincipalVIEW.contasreceber == true) {
                this.setTitle("CONTAS A RECEBER");
            } else if (PrincipalVIEW.contaspagar == true) {
                this.setTitle("CONTAS À PAGAR");
            }
            tblFinanceiro.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                        "", "CÓDIGO", "NOME", "FATURAMENTO", "VALOR", "RESTANTE", "VENCIMENTO",
                        "DUPLICATA", "HISTORICO/DOCUMENTO", "FORMA PAGAMENTO",
                        "SITUAÇÃO"
                    }) {
                private static final long serialVersionUID = -2093013531712007495L;

                @Override
                public Class getColumnClass(int columnIndex) {
                    if (columnIndex == 0) {
                        return Boolean.class;
                    } else {
                        return Object.class;
                    }
                }

                @Override
                public boolean isCellEditable(int rowIndex, int mColIndex) {
                    return false;
                }
            });
            tblFinanceiro.getColumnModel().getColumn(2).setMinWidth(200);
            tblFinanceiro.getColumnModel().getColumn(5).setMinWidth(100);
            tblFinanceiro.getColumnModel().getColumn(6).setMinWidth(100);
            tblFinanceiro.getColumnModel().getColumn(7).setMinWidth(100);
            tblFinanceiro.getColumnModel().getColumn(8).setMinWidth(200);
            tblFinanceiro.getColumnModel().getColumn(9).setMinWidth(200);
        } else if (PrincipalVIEW.contaspagar == false && PrincipalVIEW.contasreceber == false) {
            this.setTitle(">>> PESQUISA DE MOVIMENTO DO CAIXA <<<");
            ConsultarCaixa();
            ConsultarSaldoAnterior();
            ConsultarMovimentoCaixa();
            txtPesquisar.setVisible(false);

            tblFinanceiro.getColumnModel().getColumn(5).setMinWidth(200);
            tblFinanceiro.getColumnModel().getColumn(6).setMinWidth(200);
            tblFinanceiro.getColumnModel().getColumn(2).setMinWidth(100);
            tblFinanceiro.getColumnModel().getColumn(7).setMinWidth(200);
        }

        tblFinanceiro.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        UtilVIEW.Icone(this);
        tblFinanceiro.getColumnModel().getColumn(0).setMaxWidth(30);
        tblFinanceiro.getColumnModel().getColumn(1).setMinWidth(100);

        tblFinanceiro.getColumnModel().getColumn(3).setMinWidth(100);
        tblFinanceiro.getColumnModel().getColumn(4).setMinWidth(100);

        tblFinanceiro.getColumnModel().getColumn(8).setMinWidth(100);
        tblFinanceiro.setDefaultRenderer(Object.class, new ColorRenderer());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDataInicial = new javax.swing.JLabel();
        txtDataInicial = new com.toedter.calendar.JDateChooser();
        lblDataFinal = new javax.swing.JLabel();
        txtDataFinal = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFinanceiro = new javax.swing.JTable();
        btnIncluir = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnRecibo = new javax.swing.JButton();
        btnRelatorio = new javax.swing.JButton();
        lblSaldoAnterior = new javax.swing.JLabel();
        lblEntradas = new javax.swing.JLabel();
        lblSaidas = new javax.swing.JLabel();
        lblSaldoAtual = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        txtPesquisar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        lblDataInicial.setText("Data Inicial:");
        getContentPane().add(lblDataInicial);
        lblDataInicial.setBounds(560, 10, 70, 14);
        getContentPane().add(txtDataInicial);
        txtDataInicial.setBounds(560, 30, 110, 20);

        lblDataFinal.setText("Data Final:");
        getContentPane().add(lblDataFinal);
        lblDataFinal.setBounds(680, 10, 90, 14);
        getContentPane().add(txtDataFinal);
        txtDataFinal.setBounds(680, 30, 110, 20);

        tblFinanceiro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "", "Lançamento", "Data ", "Horário", "Valor", "Plano de Contas", "Histórico", "Funcionário", "Tipo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblFinanceiro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFinanceiroMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblFinanceiro);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 60, 900, 300);

        btnIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/8390_32x32.png"))); // NOI18N
        btnIncluir.setText("Incluir");
        btnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirActionPerformed(evt);
            }
        });
        getContentPane().add(btnIncluir);
        btnIncluir.setBounds(10, 370, 118, 40);

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/Alterar_32x32.png"))); // NOI18N
        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAlterar);
        btnAlterar.setBounds(134, 370, 110, 40);

        btnRecibo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/8416_32x32.png"))); // NOI18N
        btnRecibo.setText("Recibo");
        btnRecibo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReciboActionPerformed(evt);
            }
        });
        getContentPane().add(btnRecibo);
        btnRecibo.setBounds(252, 370, 110, 40);

        btnRelatorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/Imprimir_32x32.png"))); // NOI18N
        btnRelatorio.setText("Relatório");
        btnRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelatorioActionPerformed(evt);
            }
        });
        getContentPane().add(btnRelatorio);
        btnRelatorio.setBounds(370, 370, 119, 40);

        lblSaldoAnterior.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSaldoAnterior.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Saldo Anterior", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N
        getContentPane().add(lblSaldoAnterior);
        lblSaldoAnterior.setBounds(492, 370, 100, 40);

        lblEntradas.setForeground(new java.awt.Color(0, 0, 255));
        lblEntradas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEntradas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total Entradas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N
        getContentPane().add(lblEntradas);
        lblEntradas.setBounds(596, 370, 100, 40);

        lblSaidas.setForeground(new java.awt.Color(255, 0, 51));
        lblSaidas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSaidas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total Saídas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N
        getContentPane().add(lblSaidas);
        lblSaidas.setBounds(700, 370, 100, 40);

        lblSaldoAtual.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSaldoAtual.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Saldo Atual", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N
        getContentPane().add(lblSaldoAtual);
        lblSaldoAtual.setBounds(806, 370, 100, 40);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/casa_32x32.png"))); // NOI18N
        jButton6.setText("Sair");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6);
        jButton6.setBounds(810, 10, 100, 40);

        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyReleased(evt);
            }
        });
        getContentPane().add(txtPesquisar);
        txtPesquisar.setBounds(10, 30, 530, 20);

        setBounds(0, 0, 936, 456);
    }// </editor-fold>//GEN-END:initComponents

    private void btnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirActionPerformed
        try {
            codigoLancamento = "";
            if (PrincipalVIEW.contaspagar == true || PrincipalVIEW.contasreceber == true) {
                codigoParcela = String.valueOf(tblFinanceiro.getValueAt(tblFinanceiro.getSelectedRow(), 1));
                this.dispose();
                new CaixaVIEW(null, true).setVisible(true);
            } else if (PrincipalVIEW.contaspagar == false && PrincipalVIEW.contasreceber == false) {

                new CaixaVIEW(null, true).setVisible(true);
                ConsultarSaldoAnterior();
                ConsultarCaixa();
                ConsultarMovimentoCaixa();

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Selecionar a parcela!", "MENSAGEM", JOptionPane.WARNING_MESSAGE);
        }


    }//GEN-LAST:event_btnIncluirActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed

        try {

            codigoLancamento = String.valueOf(tblFinanceiro.getValueAt(tblFinanceiro.getSelectedRow(), 1));
            if (PrincipalVIEW.contaspagar == false && PrincipalVIEW.contasreceber == false) {
                new CaixaVIEW(null, true).setVisible(true);
                ConsultarSaldoAnterior();
                ConsultarCaixa();
                ConsultarMovimentoCaixa();

            } else if (PrincipalVIEW.contaspagar == true || PrincipalVIEW.contasreceber == true) {
                int cancela = -1;
                cancela = JOptionPane.showConfirmDialog(null, "Deseja cancelar?",
                        "MENSAGEM", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (cancela == 0) {
                    for (int i = 0; i < tblFinanceiro.getRowCount(); i++) {

                        if (tblFinanceiro.getValueAt(i, 0).equals(true)) {
                            if (PrincipalVIEW.contasreceber == true) {
                                CancelarContasReceber(String.valueOf(tblFinanceiro.getValueAt(i, 1)));
                            } else if (PrincipalVIEW.contaspagar == true) {
                                CancelarContasPagar(String.valueOf(tblFinanceiro.getValueAt(i, 1)));

                            }
                        } else if (tblFinanceiro.getValueAt(i, 0).equals(false)) {

                        }

                    }
                    JOptionPane.showMessageDialog(null, "Cancelado com sucesso!",
                            "MENSAGEM", JOptionPane.INFORMATION_MESSAGE);
                    UtilVIEW.LimparTabela(tblFinanceiro);
                    txtPesquisar.requestFocusInWindow();
                } else if (cancela == 1) {

                }
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Selecionar o lançamento!", "MENSAGEM",
                    JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelatorioActionPerformed
        try {
            if (txtDataInicial.getDate() == null || txtDataFinal.getDate() == null) {
                JOptionPane.showMessageDialog(null, "Informar as datas!", "ATENÇÃO",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                if (PrincipalVIEW.contaspagar == false && PrincipalVIEW.contasreceber == false) {
                    ImprimirCaixa();
                } else if (PrincipalVIEW.contaspagar == true) {
                    ConsultarContasPagar();
                } else if (PrincipalVIEW.contasreceber == true) {
                    ConsultarContasReceber();
                }
            }

        } catch (HeadlessException e) {
            System.out.println("Erro");
        }
    }//GEN-LAST:event_btnRelatorioActionPerformed

    private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyReleased
        try {
            if (txtPesquisar.getText().equals("")) {
                UtilVIEW.LimparTabela(tblFinanceiro);
            } 
        } catch (HeadlessException e) {
        }
    }//GEN-LAST:event_txtPesquisarKeyReleased

    private void tblFinanceiroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFinanceiroMouseClicked
        try {
            if (PrincipalVIEW.contaspagar == true || PrincipalVIEW.contasreceber == true) {
                if (tblFinanceiro.getValueAt(tblFinanceiro.getSelectedRow(), 0).equals(false)) {
                    tblFinanceiro.setValueAt(true, tblFinanceiro.getSelectedRow(), 0);
                } else if (tblFinanceiro.getValueAt(tblFinanceiro.getSelectedRow(), 0).equals(true)) {
                    tblFinanceiro.setValueAt(false, tblFinanceiro.getSelectedRow(), 0);
                }
            } else if (PrincipalVIEW.contaspagar == false && PrincipalVIEW.contasreceber == false) {

            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_tblFinanceiroMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            PrincipalVIEW.contaspagar = false;
            PrincipalVIEW.contasreceber = false;
            codigoLancamento = "";
            codigoParcela = "";
            this.dispose();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            if (PrincipalVIEW.contaspagar == true || PrincipalVIEW.contasreceber == true) {
                txtPesquisar.requestFocusInWindow();
            } else if (PrincipalVIEW.contaspagar == false && PrincipalVIEW.contasreceber == false) {

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_formWindowOpened

    private void btnReciboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReciboActionPerformed
        new ContasVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_btnReciboActionPerformed

    private void CancelarContasReceber(String codigo) {
        try {
            ContasReceberCTR objcontasCTR = new ContasReceberCTR();
            objcontasCTR.CancelarContasReceberCTR(codigo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Cancelar!", "MENSAGEM", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void CancelarContasPagar(String codigo) {
        try {
            ContasPagarCTR objcontasCTR = new ContasPagarCTR();
            objcontasCTR.CancelarContasPagarCTR(codigo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Cancelar!", "MENSAGEM", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void ConsultarMovimentoCaixa() {
        try {
            ResultSet movimentoCaixa = null;
            CaixaCTR objcaixaCTR = new CaixaCTR();
            movimentoCaixa = objcaixaCTR.ConsultarMovimentoCaixaCTR();
            if (movimentoCaixa.next()) {
                lblEntradas.setText(numeroPesquisa.format(movimentoCaixa.getDouble("entradas")));
                lblSaidas.setText(numeroPesquisa.format(movimentoCaixa.getDouble("saidas")));
                entradas = movimentoCaixa.getDouble("entradas");
                saidas = movimentoCaixa.getDouble("saidas");
                resultado = ((Double.parseDouble(
                        lblSaldoAnterior.getText().replace("-R$", "").replace("R$", "").replace(".", "").replace(",", "."))* sinal)
                        + entradas);
                saldoAtual = (resultado - saidas);
                lblSaldoAtual.setText(numeroPesquisa.format(saldoAtual));

            }
            entradas = 0.0;
            saidas = 0.0;
            resultado = 0.0;
            saldoAtual = 0.0;
            sinal = 0;
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Erro ao ConsultarMovimentoCaixa " + e.getMessage());
        }
    }

    private void ConsultarSaldoAnterior() {
        try {
            CaixaCTR ojcaixaCTR = new CaixaCTR();
            ResultSet saldoAnterior = null;
            saldoAnterior = ojcaixaCTR.ConsultarSaldoAnteriorCTR();
            if (saldoAnterior.next()) {
                lblSaldoAnterior.setText(numeroPesquisa.format(saldoAnterior.getDouble("saldoanterior")));  
                if(saldoAnterior.getDouble("saldoanterior") < 0.0){
                    sinal = -1;
                }else if(saldoAnterior.getDouble("saldoanterior") >= 0.0){
                    sinal = 1;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ConsultarSaldoAnterior",
                    "MENSAGEM", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void ConsultarCaixa() {
        try {
            ResultSet rs;
            CaixaCTR objcaixaCTR = new CaixaCTR();
            rs = objcaixaCTR.ConsultarCaixaCTR();
            RestauraCaixa(rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao Consultar o Caixa!", "MENSAGEM", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void RestauraCaixa(ResultSet modelo) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) tblFinanceiro.getModel();
            dtm.setRowCount(0);
            while (modelo.next()) {
                dtm.addRow(new Object[]{false, modelo.getString("cod"), modelo.getString("dat"),
                    modelo.getString("hora"), numeroPesquisa.format(modelo.getDouble("valor")),
                    modelo.getString("plano"), modelo.getString("observacao"),
                    modelo.getString("funcionario"), modelo.getString("tipo")});
            }
        } catch (Exception e) {
            System.out.println("Erro ao RestaurarCaixa " + e.getMessage());
        }
    }

    private void ConsultarContasPagar() {
        try {
            ContasPagarCTR objcontasCTR = new ContasPagarCTR();
            ResultSet pagar = null;
            pagar = objcontasCTR.ConsultarContasPagarCTR(new SimpleDateFormat("yyyy-MM-dd").format(txtDataInicial.getDate()),
                    new SimpleDateFormat("yyyy-MM-dd").format(txtDataFinal.getDate()));
            RestaurarContas(pagar);
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarContasPagar " + e.getMessage());
        }
    }

    private void ConsultarContasReceber() {
        try {
            ContasReceberCTR objcontasCTR = new ContasReceberCTR();
            ResultSet receber = null;
            receber = objcontasCTR.ConsultarContasReceberCTR(new SimpleDateFormat("yyyy-MM-dd").format(txtDataInicial.getDate()),
                    new SimpleDateFormat("yyyy-MM-dd").format(txtDataFinal.getDate()));
            RestaurarContas(receber);
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarContasReceber " + e.getMessage());
        }
    }

    private void RestaurarContas(ResultSet rs) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) tblFinanceiro.getModel();
            dtm.setRowCount(0);
            while (rs.next()) {
                dtm.addRow(new Object[]{false, rs.getString("codigo"), rs.getString("nome"),
                    rs.getString("faturamento"), numeroPesquisa.format(rs.getDouble("valorparcela")),
                    numeroPesquisa.format(rs.getDouble("restante")), rs.getString("venc"), rs.getString("numeroparcela"),
                    rs.getString("historico"), rs.getString("descricao"), rs.getString("situacao")});
            }
        } catch (Exception e) {
            System.out.println("Erro ao RestaurarCaixa " + e.getMessage());
        }
    }

    private void ImprimirCaixa() {
        try {

            int op = -1;
            op = JOptionPane.showConfirmDialog(null, "Impressora esta ligada?", "PERGUNTA",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (op == 0) {
                this.dispose();
                URL arquivo = null;
                arquivo = getClass().getResource("/Relatorios/MovimentacaoCaixa.jasper");

                //JOptionPane.showMessageDialog(this, arquivo.toString());
                if (arquivo == null) {
                    System.out.println("ARQUIVO INEXISTENTE");
                } else {

                    ConectaBanco.ConectaPostgres();
                    JasperPrint rel = null;
                    Map parametros = new HashMap();
                    parametros.put("dataInicial", new SimpleDateFormat("yyyy-MM-dd").format(txtDataInicial.getDate()));
                    parametros.put("dataFinal", new SimpleDateFormat("yyyy-MM-dd").format(txtDataFinal.getDate()));
                    parametros.put("SUBREPORT_DIR", "\\SolucaoSistemas\\src\\Relatorios\\");
                    parametros.put("dataBR1", new SimpleDateFormat("dd/MM/yyyy").format(txtDataInicial.getDate()));
                    parametros.put("dataBR2", new SimpleDateFormat("dd/MM/yyyy").format(txtDataFinal.getDate()));

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

        } catch (HeadlessException | NumberFormatException e) {
            System.out.println("" + e.getMessage());
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
            java.util.logging.Logger.getLogger(PesquisarFinanceiroVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PesquisarFinanceiroVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PesquisarFinanceiroVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PesquisarFinanceiroVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                PesquisarFinanceiroVIEW dialog = new PesquisarFinanceiroVIEW(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnRecibo;
    private javax.swing.JButton btnRelatorio;
    private javax.swing.JButton jButton6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDataFinal;
    private javax.swing.JLabel lblDataInicial;
    private javax.swing.JLabel lblEntradas;
    private javax.swing.JLabel lblSaidas;
    private javax.swing.JLabel lblSaldoAnterior;
    private javax.swing.JLabel lblSaldoAtual;
    public static javax.swing.JTable tblFinanceiro;
    private com.toedter.calendar.JDateChooser txtDataFinal;
    private com.toedter.calendar.JDateChooser txtDataInicial;
    public static javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables
}
