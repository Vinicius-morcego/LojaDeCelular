/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasVIEW;

import SolucaoSistemasCTR.CaixaCTR;
import SolucaoSistemasCTR.ContasPagarCTR;
import SolucaoSistemasCTR.ContasReceberCTR;
import SolucaoSistemasCTR.EntradaCTR;
import SolucaoSistemasCTR.PlanocontasCTR;
import SolucaoSistemasCTR.SaidaCTR;
import SolucaoSistemasCTR.UsuarioCTR;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vinicius
 */
public class ParcelamentoVIEW extends javax.swing.JDialog {

    NumberFormat numero = DecimalFormat.getCurrencyInstance(
            new Locale("pt", "BR"));
    PorExtenso objextenso = new PorExtenso();
    Double valorParcela = 0.0, valorParcelar = 0.0, resultado = 0.0, vlrEntrada = 0.0;
    GregorianCalendar cal = new GregorianCalendar();
    String funcParcelamento = "";
    Date dataEntrada = null, vencimento = null;
    String pessoa = "", fatura = "";

    /**
     * Creates new form ParcelamentoVIEW
     */
    public ParcelamentoVIEW(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle(">>>>>> Formas e Condições de Pagamento / Parcelamento <<<<<<");
        UtilVIEW.FormatarCampo(txtValorEntrada);
        UtilVIEW.DataAtual(txtDataParcelado);
        UtilVIEW.Icone(this);
        txtNumeroCheque.setDocument(new SomenteNumeros(100));
        if (VendaVIEW.finalizarPedido == false) {
            txtValorTotal.setText(numero.format(AtendimentoVIEW.finalizarAtendimento));
        } else if (VendaVIEW.finalizarPedido == true) {
            txtValorTotal.setText(numero.format(VendaVIEW.totalPedido));
        }

        txtValorParcelar.setText(txtValorTotal.getText());
        txtTotal.setText(txtValorTotal.getText());
        tblParcelamento.getColumnModel().getColumn(0).setMinWidth(100);
        tblParcelamento.getColumnModel().getColumn(1).setMinWidth(80);
        tblParcelamento.getColumnModel().getColumn(2).setMinWidth(100);
        tblParcelamento.getColumnModel().getColumn(3).setMinWidth(100);
        tblParcelamento.getColumnModel().getColumn(4).setMinWidth(200);
        tblParcelamento.getColumnModel().getColumn(5).setMinWidth(100);
        tblParcelamento.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblParcelamento.setDefaultRenderer(Object.class, new ColorRenderer());
        ConsultarFuncionario();
        ConsultarPlanoContas();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        opcoesParcelamento = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtValorTotal = new javax.swing.JTextField();
        rbInformar = new javax.swing.JCheckBox();
        txtValorEntrada = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cbxFormaParcelar = new javax.swing.JComboBox();
        btnCalcular = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtPrimeiroVencimento = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        txtNumeroParcelas = new javax.swing.JTextField();
        rbIntervaloDias = new javax.swing.JRadioButton();
        rbFixar = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        txtQuantidadeDias = new javax.swing.JTextField();
        txtNumeroCheque = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtValorParcelar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblParcelamento = new javax.swing.JTable();
        btnImprimir = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        txtDataParcelado = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setLayout(null);

        jLabel1.setText("VALOR TOTAL");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(100, 20, 80, 14);

        txtValorTotal.setEditable(false);
        txtValorTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtValorTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel1.add(txtValorTotal);
        txtValorTotal.setBounds(184, 14, 150, 30);

        rbInformar.setText("INFORMAR VALOR DE ENTRADA");
        rbInformar.setOpaque(false);
        rbInformar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbInformarActionPerformed(evt);
            }
        });
        jPanel1.add(rbInformar);
        rbInformar.setBounds(0, 60, 181, 23);

        txtValorEntrada.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtValorEntrada.setForeground(new java.awt.Color(0, 0, 255));
        txtValorEntrada.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtValorEntrada.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtValorEntradaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtValorEntradaFocusLost(evt);
            }
        });
        txtValorEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtValorEntradaKeyPressed(evt);
            }
        });
        jPanel1.add(txtValorEntrada);
        txtValorEntrada.setBounds(184, 60, 150, 30);

        jLabel2.setText("DATA:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(340, 64, 40, 14);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Criar Parcelas Automaticamente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N
        jPanel2.setLayout(null);

        jLabel3.setText("Forma:");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(10, 30, 40, 14);

        cbxFormaParcelar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxFormaParcelarItemStateChanged(evt);
            }
        });
        jPanel2.add(cbxFormaParcelar);
        cbxFormaParcelar.setBounds(50, 30, 170, 20);

        btnCalcular.setText("CALCULAR PARCELAMENTO");
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });
        jPanel2.add(btnCalcular);
        btnCalcular.setBounds(370, 30, 170, 23);

        jLabel4.setText("1º Vencimento:");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(10, 64, 80, 14);

        txtPrimeiroVencimento.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel2.add(txtPrimeiroVencimento);
        txtPrimeiroVencimento.setBounds(86, 60, 106, 20);

        jLabel5.setText("Nº de Parcelas:");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(200, 64, 80, 14);

        txtNumeroParcelas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNumeroParcelasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNumeroParcelasFocusLost(evt);
            }
        });
        jPanel2.add(txtNumeroParcelas);
        txtNumeroParcelas.setBounds(280, 60, 30, 20);

        opcoesParcelamento.add(rbIntervaloDias);
        rbIntervaloDias.setText("Intervalo de Dias");
        rbIntervaloDias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbIntervaloDiasActionPerformed(evt);
            }
        });
        jPanel2.add(rbIntervaloDias);
        rbIntervaloDias.setBounds(314, 60, 110, 23);

        opcoesParcelamento.add(rbFixar);
        rbFixar.setText("Fixar Dia por Parcela");
        jPanel2.add(rbFixar);
        rbFixar.setBounds(314, 80, 130, 23);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Intervalo:");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(450, 76, 50, 14);

        txtQuantidadeDias.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtQuantidadeDias.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtQuantidadeDiasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtQuantidadeDiasFocusLost(evt);
            }
        });
        jPanel2.add(txtQuantidadeDias);
        txtQuantidadeDias.setBounds(500, 73, 30, 20);

        txtNumeroCheque.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNumeroChequeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNumeroChequeFocusLost(evt);
            }
        });
        jPanel2.add(txtNumeroCheque);
        txtNumeroCheque.setBounds(230, 30, 130, 20);

        jLabel9.setText("Nº do cheque:");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(230, 12, 110, 14);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 150, 550, 110);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("VALOR A PARCELAR");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(60, 112, 110, 14);

        txtValorParcelar.setEditable(false);
        txtValorParcelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtValorParcelar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel1.add(txtValorParcelar);
        txtValorParcelar.setBounds(184, 106, 150, 30);

        tblParcelamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº Cheque ", "Duplicata", "Valor", "Vencimento", "Forma pgto", "Situacao"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblParcelamento);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 270, 550, 190);

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/Imprimir_32x32.png"))); // NOI18N
        btnImprimir.setText("Imprimir Parcelas");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        jPanel1.add(btnImprimir);
        btnImprimir.setBounds(10, 470, 160, 40);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/8416_32x32.png"))); // NOI18N
        btnSalvar.setText("Salvar e voltar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalvar);
        btnSalvar.setBounds(180, 470, 160, 40);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("TOTAL:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(410, 480, 50, 20);

        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(0, 0, 153));
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel1.add(txtTotal);
        txtTotal.setBounds(460, 480, 100, 21);

        try {
            txtDataParcelado.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataParcelado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDataParcelado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDataParceladoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDataParceladoFocusLost(evt);
            }
        });
        txtDataParcelado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDataParceladoKeyPressed(evt);
            }
        });
        jPanel1.add(txtDataParcelado);
        txtDataParcelado.setBounds(380, 60, 100, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 570, 520);

        setBounds(0, 0, 580, 561);
    }// </editor-fold>//GEN-END:initComponents

    private void rbIntervaloDiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbIntervaloDiasActionPerformed
        txtPrimeiroVencimento.setDate(new Date());
        txtQuantidadeDias.setEnabled(true);
        txtQuantidadeDias.requestFocusInWindow();
    }//GEN-LAST:event_rbIntervaloDiasActionPerformed

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
        if (String.valueOf(cbxFormaParcelar.getSelectedItem()).equals("")
                || txtPrimeiroVencimento.getDate() == null || txtNumeroParcelas.getText().equals("")
                || rbIntervaloDias.isSelected() == false && rbFixar.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Informar os campos!", "MENSAGEM", JOptionPane.WARNING_MESSAGE);
        } else {
            if (rbInformar.isSelected() == true) {
                InserirEntrada();
            }
            Inserir();
        }

    }//GEN-LAST:event_btnCalcularActionPerformed

    private void txtValorEntradaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtValorEntradaFocusGained
        txtValorEntrada.setBackground(new Color(255, 255, 204));
        txtValorEntrada.setText("");
    }//GEN-LAST:event_txtValorEntradaFocusGained

    private void txtValorEntradaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtValorEntradaFocusLost
        txtValorEntrada.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtValorEntradaFocusLost

    private void txtDataParceladoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDataParceladoFocusGained
        txtDataParcelado.setBackground(new Color(255, 255, 204));
    }//GEN-LAST:event_txtDataParceladoFocusGained

    private void txtDataParceladoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDataParceladoFocusLost
        txtDataParcelado.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtDataParceladoFocusLost

    private void txtNumeroChequeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumeroChequeFocusGained
        txtNumeroCheque.setBackground(new Color(255, 255, 204));
    }//GEN-LAST:event_txtNumeroChequeFocusGained

    private void txtNumeroParcelasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumeroParcelasFocusGained
        txtNumeroParcelas.setBackground(new Color(255, 255, 204));
    }//GEN-LAST:event_txtNumeroParcelasFocusGained

    private void txtQuantidadeDiasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtQuantidadeDiasFocusGained
        txtQuantidadeDias.setBackground(new Color(255, 255, 204));
    }//GEN-LAST:event_txtQuantidadeDiasFocusGained

    private void txtNumeroChequeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumeroChequeFocusLost
        txtNumeroCheque.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtNumeroChequeFocusLost

    private void txtNumeroParcelasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumeroParcelasFocusLost
        txtNumeroParcelas.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtNumeroParcelasFocusLost

    private void txtQuantidadeDiasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtQuantidadeDiasFocusLost
        txtQuantidadeDias.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtQuantidadeDiasFocusLost

    private void txtValorEntradaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorEntradaKeyPressed
        try {
            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {

                vlrEntrada = Double.parseDouble(
                        txtValorEntrada.getText().replace("R$", "").replace(".", "").replace(",", "."));
                valorParcelar = Double.parseDouble(
                        txtValorTotal.getText().replace("R$", "").replace(".", "").replace(",", "."));
                resultado = (valorParcelar - vlrEntrada);
                txtValorParcelar.setText(numero.format(resultado));
                txtValorEntrada.setText(numero.format(vlrEntrada));
                vlrEntrada = 0.0;
                valorParcelar = 0.0;
                resultado = 0.0;
                txtDataParcelado.requestFocusInWindow();

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtValorEntradaKeyPressed

    private void rbInformarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbInformarActionPerformed
        txtValorEntrada.requestFocusInWindow();
    }//GEN-LAST:event_rbInformarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            dataEntrada = new SimpleDateFormat("dd/MM/yyyy").parse(txtDataParcelado.getText());
            if (rbInformar.isSelected() == true) {
                CadastrarEntrada();
            }
            if (PrincipalVIEW.compra == false) {
                CadastrarContasReceber();
            } else if (PrincipalVIEW.compra == true) {
                CadastrarContasPagar();
            }
            PesquisarPedidosVIEW.codigoAlpha = "";
            this.dispose();
            if (VendaVIEW.finalizarPedido == false) {
                new AtendimentoVIEW(null, true).setVisible(true);
            } else if (VendaVIEW.finalizarPedido == true) {
                new VendaVIEW(null, true).setVisible(true);
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void txtDataParceladoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDataParceladoKeyPressed
        try {
            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                cbxFormaParcelar.requestFocusInWindow();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtDataParceladoKeyPressed

    private void cbxFormaParcelarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxFormaParcelarItemStateChanged
        txtNumeroCheque.requestFocusInWindow();
    }//GEN-LAST:event_cbxFormaParcelarItemStateChanged

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void InserirEntrada() {
        try {
            String tipoEntrada = "";
            if(PrincipalVIEW.venda == true){
                    tipoEntrada = "DINHEIRO/CHEQUE À VISTA";
            }else if(PrincipalVIEW.venda == false){
                    tipoEntrada = "FORNECEDOR PRODUTO";
            }
            DefaultTableModel dtm = (DefaultTableModel) tblParcelamento.getModel();
            
            dtm.setRowCount(0);
            
            dtm.addRow(new Object[]{"000000", String.valueOf("0/" + txtNumeroParcelas.getText()),
                txtValorEntrada.getText(), txtDataParcelado.getText(), tipoEntrada, "PAGO"});
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Inserir Entrada!", "MENSAGEM",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void Inserir() {
        try {

            DefaultTableModel tabela = (DefaultTableModel) tblParcelamento.getModel();

            String primeiroVencimento = "", diaAtual = "";
            Long cheque = 0L;
            diaAtual = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            primeiroVencimento = new SimpleDateFormat("yyyy-MM-dd").format(
                    txtPrimeiroVencimento.getDate());
            Integer numPar = 0, adicionaDias = 0;
            // int dias = 0;
            Date data = null;
            numPar = Integer.parseInt(txtNumeroParcelas.getText());
            valorParcela = (Double.parseDouble(txtValorParcelar.getText().
                    replace("R$", "").replace(".", "").replace(",", "."))
                    / numPar);
            if (new SimpleDateFormat("yyyy-MM-dd").parse(primeiroVencimento).before(
                    new SimpleDateFormat("yyyy-MM-dd").parse(diaAtual))) {
                JOptionPane.showMessageDialog(null, "Data anterior a data atual!",
                        "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            } else {
                if (rbInformar.isSelected() == false) {
                    tabela.setRowCount(0);
                }
                for (int e = 0; e < numPar; e++) {
                    if (txtNumeroCheque.getText().equals("")) {
                        cheque = 0L;
                    } else {
                        cheque = (Long.parseLong(txtNumeroCheque.getText()) + e);
                    }

                    if (rbFixar.isSelected() == true) {
                        cal.setTime(txtPrimeiroVencimento.getDate());
                        cal.add(GregorianCalendar.MONTH, e);
                    } else if (rbIntervaloDias.isSelected() == true) {
                        cal.setTime(new Date());
                        adicionaDias += Integer.parseInt(txtQuantidadeDias.getText());
                        cal.add(GregorianCalendar.DAY_OF_MONTH, adicionaDias);
                    }

                    data = cal.getTime();
                    tabela.addRow(new Object[]{String.valueOf(cheque), (e + 1) + "/" + txtNumeroParcelas.getText(),
                        numero.format(valorParcela), new SimpleDateFormat("dd/MM/yyyy").format(data),
                        String.valueOf(cbxFormaParcelar.getSelectedItem()), "EM ABERTO"});
                }
                tblParcelamento.setModel(tabela);

            }

        } catch (NumberFormatException | HeadlessException | ParseException e) {
            System.out.println("" + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao Inserir a Prazo!", "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void CadastrarEntrada() {
        try {
            CaixaCTR objcaixaCTR = new CaixaCTR();

            if (objcaixaCTR.CadastrarCaixaCTR(funcParcelamento,
                    new SimpleDateFormat("yyyy-MM-dd").format(dataEntrada))) {
                if (PrincipalVIEW.compra == false) {
                    EntradaCTR objentradaCTR = new EntradaCTR();
                    objentradaCTR.CadastrarEntradaCTR("CAIXA GERAL",
                            txtValorEntrada.getText(), "ENTRADA", "DINHEIRO/CHEQUE À VISTA",
                            "ENTRADA PARCELAMENTO");
                } else if (PrincipalVIEW.compra == true) {
                    SaidaCTR objsaidaCTR = new SaidaCTR();
                    objsaidaCTR.CadastrarSaidaCTR("CAIXA GERAL", txtValorEntrada.getText(),
                            "SAIDA", "FORNECEDOR PRODUTO", "ENTRADA COMPRA PRODUTOS");
                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao lançar a entrada!",
                    "MENSAGEM", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void ConsultarPlanoContas() {
        try {
            ResultSet rs = null;
            PlanocontasCTR objplanoCTR = new PlanocontasCTR();
            if (PrincipalVIEW.compra == false) {
                rs = objplanoCTR.ConsultarPlanoContasCTR("3. RECEITA", "3. RECEITA");
            } else if (PrincipalVIEW.compra == true) {
                rs = objplanoCTR.ConsultarPlanoContasCTR("2. PASSIVO", "2. PASSIVO");
            }

            UtilVIEW.PreencheCombo(rs, cbxFormaParcelar, 2);
        } catch (Exception e) {
        }
    }

    private void CadastrarContasPagar() {
        try {
            ContasPagarCTR objPagar = new ContasPagarCTR();
            int percorrePagar = 0;
            while (percorrePagar < tblParcelamento.getRowCount()) {
                vencimento = null;
                vencimento = new SimpleDateFormat("dd/MM/yyyy").parse(
                        String.valueOf(tblParcelamento.getValueAt(percorrePagar, 3)));
                if (objPagar.CadastrarContasPagarCTR(VendaVIEW.pedidoCliente,
                        "(select max(codigo) from compra)",
                        String.valueOf(tblParcelamento.getValueAt(percorrePagar, 0)),
                        String.valueOf(tblParcelamento.getValueAt(percorrePagar, 2)),
                        new SimpleDateFormat("yyyy-MM-dd").format(vencimento),
                        String.valueOf(tblParcelamento.getValueAt(percorrePagar, 5)),
                        String.valueOf(tblParcelamento.getValueAt(percorrePagar, 1)),
                        objextenso.write(new BigDecimal(
                                Double.parseDouble(
                                        tblParcelamento.getValueAt(percorrePagar, 2).toString().
                                        replace("R$", "").replace(".", "").replace(",", ".")))),
                        String.valueOf(tblParcelamento.getValueAt(percorrePagar, 4)))) {
                    percorrePagar++;
                }
            }

        } catch (ParseException | NumberFormatException e) {
        }
    }

    private void CadastrarContasReceber() {
        try {
            ContasReceberCTR objcontasCTR = new ContasReceberCTR();
            pessoa = VendaVIEW.pedidoCliente;
            int percorra = 0;
            while (percorra < tblParcelamento.getRowCount()) {
                vencimento = null;
                vencimento = new SimpleDateFormat("dd/MM/yyyy").parse(
                        String.valueOf(tblParcelamento.getValueAt(percorra, 3)));
                if (objcontasCTR.CadastrarContasReceberCTR(pessoa, "(select max(codigo) from venda)",
                        String.valueOf(tblParcelamento.getValueAt(percorra, 0)),
                        String.valueOf(tblParcelamento.getValueAt(percorra, 2)),
                        new SimpleDateFormat("yyyy-MM-dd").format(vencimento),
                        String.valueOf(tblParcelamento.getValueAt(percorra, 5)),
                        String.valueOf(tblParcelamento.getValueAt(percorra, 1)),
                        objextenso.write(new BigDecimal(
                                Double.parseDouble(
                                        tblParcelamento.getValueAt(percorra, 2).toString().
                                        replace("R$", "").replace(".", "").replace(",", ".")))),
                        String.valueOf(tblParcelamento.getValueAt(percorra, 4))));
                percorra++;
            }

        } catch (ParseException | NumberFormatException e) {
            System.out.println("" + e.getMessage());
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
                funcParcelamento = user.getString("pessoa");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar Funcionario",
                    "MENSAGEM", JOptionPane.WARNING_MESSAGE);
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
            java.util.logging.Logger.getLogger(ParcelamentoVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ParcelamentoVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ParcelamentoVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ParcelamentoVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ParcelamentoVIEW dialog = new ParcelamentoVIEW(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCalcular;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox cbxFormaParcelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.ButtonGroup opcoesParcelamento;
    private javax.swing.JRadioButton rbFixar;
    private javax.swing.JCheckBox rbInformar;
    private javax.swing.JRadioButton rbIntervaloDias;
    private javax.swing.JTable tblParcelamento;
    private javax.swing.JFormattedTextField txtDataParcelado;
    private javax.swing.JTextField txtNumeroCheque;
    private javax.swing.JTextField txtNumeroParcelas;
    private com.toedter.calendar.JDateChooser txtPrimeiroVencimento;
    private javax.swing.JTextField txtQuantidadeDias;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtValorEntrada;
    private javax.swing.JTextField txtValorParcelar;
    private javax.swing.JTextField txtValorTotal;
    // End of variables declaration//GEN-END:variables
}
