/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasVIEW;

import SolucaoSistemasCTR.Historico_OrdemServicoCTR;
import static SolucaoSistemasVIEW.PesquisarPedidosVIEW.tblPedidos;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vinicius
 */
public class HistoricoOrdemVIEW extends javax.swing.JDialog {

    Historico_OrdemServicoCTR objHistoricoCTR = new Historico_OrdemServicoCTR();
    ResultSet rs;

    /**
     * Creates new form HistoricoOrdemVIEW
     */
    public HistoricoOrdemVIEW(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Histórico Ordem de Serviço");
        UtilVIEW.Icone(this);
        cbxSituacaoOS.requestFocusInWindow();
        txtNumeroOS.setText(String.valueOf(tblPedidos.getValueAt(tblPedidos.getSelectedRow(), 1)));
        ConsultarHistoricoVIEW();
        tblHistoricoOS.getColumnModel().getColumn(0).setCellRenderer(new SituacaoOrdemServicoVIEW());
        tblHistoricoOS.getColumnModel().getColumn(0).setMaxWidth(70);
        tblHistoricoOS.getColumnModel().getColumn(1).setMinWidth(190);
        tblHistoricoOS.getColumnModel().getColumn(2).setMaxWidth(80);
        tblHistoricoOS.setRowHeight(60);
        tblHistoricoOS.setShowGrid(false);
        //  tblHistoricoOS.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

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
        txtNumeroOS = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHistoricoOS = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservacaoOS = new javax.swing.JTextArea();
        lblSituacao = new javax.swing.JLabel();
        cbxSituacaoOS = new javax.swing.JComboBox<>();
        btnSalvar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setLayout(null);

        jLabel1.setText(" Nº Ordem de Serviço:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 10, 110, 14);

        txtNumeroOS.setEditable(false);
        jPanel1.add(txtNumeroOS);
        txtNumeroOS.setBounds(120, 10, 130, 20);

        tblHistoricoOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblHistoricoOS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "", "Observação:", "Data:"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHistoricoOS.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tblHistoricoOS);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 80, 820, 200);

        jLabel2.setText("Observação:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(420, 10, 120, 14);

        txtObservacaoOS.setColumns(20);
        txtObservacaoOS.setLineWrap(true);
        txtObservacaoOS.setRows(5);
        jScrollPane2.setViewportView(txtObservacaoOS);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(490, 10, 340, 60);

        lblSituacao.setText("Situação:");
        jPanel1.add(lblSituacao);
        lblSituacao.setBounds(260, 10, 70, 14);

        cbxSituacaoOS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Entrada", "Em análise", "Em manutenção", "Entregue", "Finalizado" }));
        cbxSituacaoOS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxSituacaoOSKeyPressed(evt);
            }
        });
        jPanel1.add(cbxSituacaoOS);
        cbxSituacaoOS.setBounds(310, 10, 100, 20);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/disquete_16x16.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalvar);
        btnSalvar.setBounds(10, 40, 83, 25);

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/Fechar_16x16.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        jPanel1.add(btnSair);
        btnSair.setBounds(104, 40, 71, 25);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 840, 300);

        setBounds(0, 0, 855, 343);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            if (String.valueOf(cbxSituacaoOS.getSelectedItem()).equals("") || 
                    cbxSituacaoOS.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Selecionar a situação!", "MENSAGEM", JOptionPane.WARNING_MESSAGE);
            } else if(txtObservacaoOS.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Preencher a observação!", "MENSAGEM", JOptionPane.WARNING_MESSAGE);
            }else{
                CadastrarHistoricoVIEW();
                ConsultarHistoricoVIEW();
                txtObservacaoOS.setText("");
            }

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!", "MENSAGEM", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        try {
            int op = -1;
            op = JOptionPane.showConfirmDialog(null, "Deseja sair?", "MENSAGEM",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (op == 0) {
                PrincipalVIEW.situacaoOrdemServico = "";
                this.dispose();
            } else if (op == 1) {

            }
        } catch (HeadlessException e) {
        }

    }//GEN-LAST:event_btnSairActionPerformed

    private void cbxSituacaoOSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxSituacaoOSKeyPressed
        try {
            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                txtObservacaoOS.requestFocusInWindow();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_cbxSituacaoOSKeyPressed

    private void CadastrarHistoricoVIEW() {
        try {
            if (objHistoricoCTR.CadastrarHistoricoOrdemCTR(txtNumeroOS.getText(),
                    txtObservacaoOS.getText(), String.valueOf(cbxSituacaoOS.getSelectedItem()))) {
                JOptionPane.showMessageDialog(null, "Salvo com sucesso!", "MENSAGEM", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!", "MENSAGEM", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void ConsultarHistoricoVIEW() {
        try {
            rs = objHistoricoCTR.ConsultarHistoricoOrdemCTR(txtNumeroOS.getText());
            RestaurarHistoricoVIEW(rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao ConsultarHistoricoVIEW", "MENSAGEM", JOptionPane.WARNING_MESSAGE);
            System.out.println(e.getMessage());
        }
    }

    private void RestaurarHistoricoVIEW(ResultSet ted) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) tblHistoricoOS.getModel();
            String caminho = "";
            dtm.setRowCount(0);
            while (ted.next()) {
                switch (ted.getString("situacao")) {
                    case "Entrada":
                        caminho = "/SolucaoSistemas/src/Imagem/info-48.png";
                        break;
                    case "Em análise":
                        caminho = "/SolucaoSistemas/src/Imagem/analisando.png";
                        break;
                    case "Em manutenção":
                        caminho = "/SolucaoSistemas/src/Imagem/suporte.png";
                        break;
                    case "Entregue":
                        caminho = "/SolucaoSistemas/src/Imagem/pronto.png";
                        break;
                    case "Finalizado":
                        caminho = "/SolucaoSistemas/src/Imagem/pessoa-40.png";
                        break;
                    default:
                        break;
                }
                dtm.addRow(new Object[]{new ImageIcon(caminho),
                    ted.getString("observacao"), ted.getString("dia")});
                caminho = "";
            }

            tblHistoricoOS.setModel(dtm);

        } catch (SQLException e) {
            System.out.println("Erro ao RestaurarHistoricoVIEW " + e.getMessage());
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
            java.util.logging.Logger.getLogger(HistoricoOrdemVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HistoricoOrdemVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HistoricoOrdemVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HistoricoOrdemVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                HistoricoOrdemVIEW dialog = new HistoricoOrdemVIEW(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbxSituacaoOS;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblSituacao;
    public static javax.swing.JTable tblHistoricoOS;
    private javax.swing.JTextField txtNumeroOS;
    private javax.swing.JTextArea txtObservacaoOS;
    // End of variables declaration//GEN-END:variables
}
