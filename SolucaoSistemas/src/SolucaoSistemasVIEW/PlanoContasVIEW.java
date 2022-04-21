/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasVIEW;

import SolucaoSistemasCTR.PlanocontasCTR;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vinicius
 */
public class PlanoContasVIEW extends javax.swing.JDialog {

    String controle = "", escolha = "";
    ResultSet consulta = null;
    PlanocontasCTR objPlanoCTR = new PlanocontasCTR();

    /**
     * Creates new form PlanoContasVIEW
     *
     * @param parent
     * @param modal
     */
    public PlanoContasVIEW(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        tblPlanoContas.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "CÓDIGO", "DESCRIÇÃO"
                }) {
            private static final long serialVersionUID = -2093013531712007495L;

            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        });
        tblPlanoContas.setDefaultRenderer(Object.class, new ColorRenderer());
        tblPlanoContas.getColumnModel().getColumn(0).setMinWidth(40);
        tblPlanoContas.getColumnModel().getColumn(1).setMinWidth(300);
        this.setLocationRelativeTo(null);
        setTitle("PLANO DE CONTAS");
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

        bgOpcao = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        rbSaida = new javax.swing.JRadioButton();
        rbEntrada = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPlanoContas = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setLayout(null);

        jLabel1.setText("Descrição");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(110, 10, 80, 14);

        txtDescricao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDescricaoFocusGained(evt);
            }
        });
        jPanel1.add(txtDescricao);
        txtDescricao.setBounds(110, 30, 420, 20);

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalvar);
        btnSalvar.setBounds(110, 60, 63, 23);

        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        jPanel1.add(btnSair);
        btnSair.setBounds(180, 60, 51, 23);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Selecione:"));

        bgOpcao.add(rbSaida);
        rbSaida.setText("Saida");
        rbSaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSaidaActionPerformed(evt);
            }
        });

        bgOpcao.add(rbEntrada);
        rbEntrada.setText("Entrada");
        rbEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbEntradaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbEntrada)
                    .addComponent(rbSaida))
                .addGap(31, 31, 31))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbEntrada)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbSaida)
                .addContainerGap())
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 10, 80, 80);

        tblPlanoContas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblPlanoContas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblPlanoContas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPlanoContasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPlanoContas);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 100, 520, 190);

        jLabel2.setText("Selecione para atualizar o plano de contas!");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(240, 64, 290, 20);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 540, 300);

        setBounds(0, 0, 553, 346);
    }// </editor-fold>//GEN-END:initComponents

    private void rbEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbEntradaActionPerformed
        escolha = "";
        escolha = "3. RECEITA";
        ConsultarPlano();
    }//GEN-LAST:event_rbEntradaActionPerformed

    private void rbSaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSaidaActionPerformed
        escolha = "";
        escolha = "4. DESPESA";
        ConsultarPlano();
    }//GEN-LAST:event_rbSaidaActionPerformed

    private void tblPlanoContasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPlanoContasMouseClicked
        controle = String.valueOf(tblPlanoContas.getValueAt(tblPlanoContas.getSelectedRow(), 0));
        txtDescricao.setText(String.valueOf(tblPlanoContas.getValueAt(tblPlanoContas.getSelectedRow(), 1)));
    }//GEN-LAST:event_tblPlanoContasMouseClicked

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            if (rbEntrada.isSelected() == false && rbSaida.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "Escolha uma opção!", "MENSAGEM", JOptionPane.WARNING_MESSAGE);
            } else if (txtDescricao.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Preencha o campo descrição!", "MENSAGEM", JOptionPane.WARNING_MESSAGE);
            } else {
                if (controle.equals("")) {
                    CadastrarPlano();
                } else {
                    AtualizarPlano();
                }
                controle = "";
                txtDescricao.setText("");
                tblPlanoContas.clearSelection();
                ConsultarPlano();

            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Valor nulo foi passado!", "MENSAGEM", JOptionPane.WARNING_MESSAGE);
        }


    }//GEN-LAST:event_btnSalvarActionPerformed

    private void txtDescricaoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescricaoFocusGained
        txtDescricao.setText("");
    }//GEN-LAST:event_txtDescricaoFocusGained

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        try {
            int op = -1;
            op = JOptionPane.showConfirmDialog(null, "Deseja sair?", "MENSAGEM", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(op == 0){
                controle = "";
                escolha = "";
                this.dispose();
            }else if(op == 1){
                
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnSairActionPerformed

    /**
     * @param args the command line arguments
     */
    private void CadastrarPlano() {
        try {

            if (objPlanoCTR.CadastrarPlanoContasCTR(txtDescricao.getText().toUpperCase(), escolha)) {
                JOptionPane.showMessageDialog(null, "Salvo com sucesso!", "MENSAGEM", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar!", "MENSAGEM", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void AtualizarPlano() {
        try {
            if (objPlanoCTR.AtualizarPlanoContasCTR(
                    controle, txtDescricao.getText().toUpperCase())) {
                JOptionPane.showMessageDialog(null, "Atualizado com sucesso!", "MENSAGEM", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!", "MENSAGEM", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void ConsultarPlano() {
        try {
            if (rbEntrada.isSelected() == true) {
                consulta = objPlanoCTR.ConsultarPlanoContasCTR("3. RECEITA", "3. RECEITA");
            } else if (rbSaida.isSelected() == true) {
                consulta = objPlanoCTR.ConsultarPlanoContasCTR("4. DESPESA", "5. TRANSFERÊNCIA");
            }
            RestaurarPlano(consulta);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar Plano", "MENSAGEM", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void RestaurarPlano(ResultSet rs) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) tblPlanoContas.getModel();
            dtm.setRowCount(0);
            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getString("codigo"), rs.getString("descricao")});
            }
            tblPlanoContas.setModel(dtm);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao RestaurarPlano", "MENSAGEM", JOptionPane.WARNING_MESSAGE);
        }

    }

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
            java.util.logging.Logger.getLogger(PlanoContasVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlanoContasVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlanoContasVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlanoContasVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                PlanoContasVIEW dialog = new PlanoContasVIEW(new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup bgOpcao;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbEntrada;
    private javax.swing.JRadioButton rbSaida;
    private javax.swing.JTable tblPlanoContas;
    private javax.swing.JTextField txtDescricao;
    // End of variables declaration//GEN-END:variables
}
