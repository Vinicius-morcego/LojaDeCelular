/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasVIEW;

import SolucaoSistemasCTR.FinalizarAtendimentoCTR;
import SolucaoSistemasCTR.FinalizarCompraCTR;
import SolucaoSistemasCTR.FinalizarPedidoCTR;
import SolucaoSistemasCTR.ItensCompraCTR;
import SolucaoSistemasCTR.ItensCompraCanceladaCTR;
import SolucaoSistemasCTR.ItensVendaCTR;
import SolucaoSistemasCTR.ItensVendaCanceladaCTR;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author Vinicius
 */
public class FinalizarAtendimentoVIEW extends javax.swing.JDialog {

    Date pegaData = null;
    int cancela = -1;

    /**
     * Creates new form FinalizarAtendimentoVIEW
     */
    public FinalizarAtendimentoVIEW(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        if (VendaVIEW.finalizarPedido == false) {
            this.setTitle("Situação Ordem de Serviço");
        } else if (VendaVIEW.finalizarPedido == true) {
            this.setTitle("Situação Pedido");
        }

        UtilVIEW.Icone(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbxSituacao = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnConfirmar = new javax.swing.JButton();
        txtFechamento = new javax.swing.JFormattedTextField();
        txtHoraFechamento = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setLayout(null);

        jLabel1.setText("SITUAÇÃO FECHAMENTO");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(12, 13, 150, 14);

        cbxSituacao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Finalizado", "Cancelado" }));
        cbxSituacao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSituacaoItemStateChanged(evt);
            }
        });
        jPanel1.add(cbxSituacao);
        cbxSituacao.setBounds(12, 33, 180, 20);

        jLabel2.setText("DT FECHAMENTO");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 70, 90, 14);

        jLabel3.setText("HORA");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(140, 70, 70, 14);

        btnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/7881_32x32.png"))); // NOI18N
        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });
        jPanel1.add(btnConfirmar);
        btnConfirmar.setBounds(10, 120, 190, 40);

        try {
            txtFechamento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFechamento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFechamentoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFechamentoFocusLost(evt);
            }
        });
        txtFechamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFechamentoKeyPressed(evt);
            }
        });
        jPanel1.add(txtFechamento);
        txtFechamento.setBounds(10, 90, 90, 20);

        try {
            txtHoraFechamento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtHoraFechamento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtHoraFechamentoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtHoraFechamentoFocusLost(evt);
            }
        });
        jPanel1.add(txtHoraFechamento);
        txtHoraFechamento.setBounds(140, 90, 60, 20);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(4, 4, 210, 170);

        setBounds(0, 0, 232, 219);
    }// </editor-fold>//GEN-END:initComponents

    private void txtFechamentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechamentoKeyPressed
        try {
            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                txtHoraFechamento.requestFocusInWindow();
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtFechamentoKeyPressed

    private void txtFechamentoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFechamentoFocusGained
        try {
            txtFechamento.setBackground(new Color(255, 255, 204));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtFechamentoFocusGained

    private void txtHoraFechamentoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHoraFechamentoFocusGained
        try {
            txtHoraFechamento.setBackground(new Color(255, 255, 204));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtHoraFechamentoFocusGained

    private void txtFechamentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFechamentoFocusLost
        try {
            txtFechamento.setBackground(Color.WHITE);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtFechamentoFocusLost

    private void txtHoraFechamentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHoraFechamentoFocusLost
        try {
            txtHoraFechamento.setBackground(Color.WHITE);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtHoraFechamentoFocusLost

    private void cbxSituacaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSituacaoItemStateChanged
        try {
            txtFechamento.requestFocusInWindow();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cbxSituacaoItemStateChanged

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        try {
            int confirmar = -1;
            confirmar = JOptionPane.showConfirmDialog(null, "Deseja confirmar?",
                    "MENSAGEM", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirmar == 0) {
                if (txtFechamento.getText().equals("  /  /    ") || txtHoraFechamento.getText().equals("  :  ")) {
                    JOptionPane.showMessageDialog(null, "Preencher os campos!", "MENSAGEM",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    pegaData = new SimpleDateFormat("dd/MM/yyyy").parse(txtFechamento.getText());
                    if (VendaVIEW.finalizarPedido == false) {
                        CadastrarFinalizarVIEW();
                        
                    } else if (VendaVIEW.finalizarPedido == true
                            && PrincipalVIEW.compra == false) {
                        CadastrarFinalizarPedido();
                        if (String.valueOf(cbxSituacao.getSelectedItem()).equals("Cancelado")) {

                            cancela = JOptionPane.showConfirmDialog(null, "Atualizar estoque?",
                                    "MENSAGEM", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (cancela == 0) {
                                CadastrarItensVendaCancelada();
                                VendaVIEW.finalizarPedido = false;
                                PrincipalVIEW.pedidoFinalizar = false;
                                PrincipalVIEW.cancelarPedido = false;
                                PesquisarPedidosVIEW.codigoAlpha = "";
                            } else if (cancela == 1) {

                            }
                        }
                    } else if (VendaVIEW.finalizarPedido == true
                            && PrincipalVIEW.compra == true) {
                        CadastrarFinalizarCompra();

                        if (String.valueOf(cbxSituacao.getSelectedItem()).equals("Cancelado")) {

                            cancela = JOptionPane.showConfirmDialog(null, "Atualizar estoque?",
                                    "MENSAGEM", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (cancela == 0) {
                                CadastrarItensCompraCancelada();
                                VendaVIEW.finalizarPedido = false;
                                PrincipalVIEW.compra = false;
                                PrincipalVIEW.cancelarPedido = false;
                                PrincipalVIEW.pedidoFinalizar = false;
                                PesquisarPedidosVIEW.codigoAlpha = "";
                            } else if (cancela == 1) {

                            }
                        }
                    }

                    this.dispose();
                    if (String.valueOf(cbxSituacao.getSelectedItem()).equals("Finalizado") 
                            && PrincipalVIEW.situacaoOrdemServico.equals("")) {
                        new OpcaoFechamentoVIEW(null, true).setVisible(true);
                    }

                }

            } else if (confirmar == 1) {

            }
        } catch (HeadlessException | ParseException e) {
            JOptionPane.showMessageDialog(null, "Erro "+e.getMessage(), 
                    "MENSAGEM", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btnConfirmarActionPerformed

    public void CadastrarItensVendaCancelada() {
        try {
            ItensVendaCTR objitensCTR = new ItensVendaCTR();
            ItensVendaCanceladaCTR objitensVenda = new ItensVendaCanceladaCTR();
            ResultSet itens = null;
            String condicao = "";
            if (VendaVIEW.finalizarPedido == false) {
                condicao = "(select codigo from venda where atendimento = "
                        + PesquisarPedidosVIEW.codigoAlpha + ")";
            } else if (VendaVIEW.finalizarPedido == true
                    && PrincipalVIEW.compra == false) {
                condicao = PesquisarPedidosVIEW.codigoAlpha;
            } else if (VendaVIEW.finalizarPedido == true
                    && PrincipalVIEW.compra == true) {
                condicao = "";
            }
            itens = objitensCTR.ConsultarItensVendaCTR(condicao);
            while (itens.next()) {
                objitensVenda.CadastrarItensVendaCanceladaCTR(itens.getString("produto"),
                        itens.getString("quantidade"), itens.getString("unitario"),
                        itens.getString("subtotal"), itens.getString("venda"));

            }
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarItensVendaCancelada " + e.getMessage());
        }
    }

    public void CadastrarItensCompraCancelada() {
        try {
            ItensCompraCTR objitensCTR = new ItensCompraCTR();
            ItensCompraCanceladaCTR objitensCompraCTR = new ItensCompraCanceladaCTR();
            ResultSet itens = null;

            itens = objitensCTR.ConsultarItensCompraCTR(PesquisarPedidosVIEW.codigoAlpha);
            while (itens.next()) {
                objitensCompraCTR.CadastrarItensCompraCanceladaCTR(itens.getString("produto"),
                        itens.getString("quantidade"), itens.getString("unitario"),
                        itens.getString("subtotal"), itens.getString("compra"));

            }
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarItensCompraCancelada " + e.getMessage());
        }
    }

    public void CadastrarFinalizarVIEW() {
        try {

            FinalizarAtendimentoCTR objfinalizarCTR = new FinalizarAtendimentoCTR();

            if (objfinalizarCTR.CadastrarFinalizarCTR(new SimpleDateFormat("yyyy-MM-dd").format(pegaData),
                    txtHoraFechamento.getText(), String.valueOf(cbxSituacao.getSelectedItem()),
                    PesquisarPedidosVIEW.codigoAlpha)) {
                JOptionPane.showMessageDialog(null, "Salvo com sucesso!", "MENSAGEM",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!", "MENSAGEM",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void CadastrarFinalizarPedido() {
        try {
            
            FinalizarPedidoCTR objfinalizarCTR = new FinalizarPedidoCTR();
            if (objfinalizarCTR.CadastrarFinalizarPedidoCTR(new SimpleDateFormat("yyyy-MM-dd").format(pegaData),
                    txtHoraFechamento.getText(), String.valueOf(cbxSituacao.getSelectedItem()),
                    PesquisarPedidosVIEW.codigoAlpha)) {
                JOptionPane.showMessageDialog(null, "Salvo com sucesso!", "MENSAGEM",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!", "MENSAGEM",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void CadastrarFinalizarCompra() {
        try {
            FinalizarCompraCTR objfinalizarCTR = new FinalizarCompraCTR();
            
            if (objfinalizarCTR.CadastrarFinalizarCompraCTR(new SimpleDateFormat("yyyy-MM-dd").format(pegaData),
                    txtHoraFechamento.getText(), String.valueOf(cbxSituacao.getSelectedItem()),
                    PesquisarPedidosVIEW.codigoAlpha)) {
                JOptionPane.showMessageDialog(null, "Salvo com sucesso!", "MENSAGEM",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!", "MENSAGEM",
                    JOptionPane.WARNING_MESSAGE);
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
            java.util.logging.Logger.getLogger(FinalizarAtendimentoVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FinalizarAtendimentoVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FinalizarAtendimentoVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FinalizarAtendimentoVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FinalizarAtendimentoVIEW dialog = new FinalizarAtendimentoVIEW(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JComboBox cbxSituacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JFormattedTextField txtFechamento;
    private javax.swing.JFormattedTextField txtHoraFechamento;
    // End of variables declaration//GEN-END:variables
}