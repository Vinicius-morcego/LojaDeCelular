/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasVIEW;

import javax.swing.JOptionPane;

/**
 *
 * @author Batman
 */
public class OpcaoFechamentoVIEW extends javax.swing.JDialog {

    int opcao = -1;

    /**
     * Creates new form OpcaoFechamentoVIEW
     */
    public OpcaoFechamentoVIEW(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("FINANCEIRO");
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

        btnAvista = new javax.swing.JButton();
        btnAprazo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(null);

        btnAvista.setText("À VISTA");
        btnAvista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvistaActionPerformed(evt);
            }
        });
        getContentPane().add(btnAvista);
        btnAvista.setBounds(10, 23, 100, 30);

        btnAprazo.setText("A PRAZO");
        btnAprazo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAprazoActionPerformed(evt);
            }
        });
        getContentPane().add(btnAprazo);
        btnAprazo.setBounds(120, 23, 100, 30);

        setBounds(0, 0, 258, 107);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAprazoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAprazoActionPerformed
        try {
            opcao = JOptionPane.showConfirmDialog(null, "Deseja confirmar a prazo?",
                    "MENSAGEM", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opcao == 0) {
                this.dispose();
                new ParcelamentoVIEW(null, true).setVisible(true);
            } else if (opcao == 1) {

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnAprazoActionPerformed
    public static boolean vista = false;
    private void btnAvistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvistaActionPerformed
        try {
            opcao = JOptionPane.showConfirmDialog(null, "Deseja confirmar a vista?",
                    "MENSAGEM", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opcao == 0) {
                vista = true;
                this.dispose();
                new CaixaVIEW(null, true).setVisible(true);
            } else if (opcao == 1) {

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnAvistaActionPerformed

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
            java.util.logging.Logger.getLogger(OpcaoFechamentoVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OpcaoFechamentoVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OpcaoFechamentoVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OpcaoFechamentoVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                OpcaoFechamentoVIEW dialog = new OpcaoFechamentoVIEW(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAprazo;
    private javax.swing.JButton btnAvista;
    // End of variables declaration//GEN-END:variables
}
