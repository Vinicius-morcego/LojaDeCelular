/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasVIEW;

import SolucaoSistemasCTR.ConfiguracaoRelatorioCTR;
import SolucaoSistemasDAO.ConectaBanco;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Batman
 */
public class RelatorioVIEW extends javax.swing.JDialog {

    /**
     * Creates new form RelatorioVIEW
     */
    public RelatorioVIEW(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("RELATÓRIO");
        this.setLocationRelativeTo(null);
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

        opcaoRelatorios = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        rbProduto = new javax.swing.JCheckBox();
        rbServico = new javax.swing.JCheckBox();
        btnImprimir = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Selecione"));
        jPanel1.setLayout(null);

        opcaoRelatorios.add(rbProduto);
        rbProduto.setText("Produto");
        rbProduto.setOpaque(false);
        jPanel1.add(rbProduto);
        rbProduto.setBounds(4, 14, 63, 23);

        opcaoRelatorios.add(rbServico);
        rbServico.setText("Serviço");
        jPanel1.add(rbServico);
        rbServico.setBounds(70, 14, 60, 23);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(6, 2, 140, 40);

        btnImprimir.setText("Visualizar");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        getContentPane().add(btnImprimir);
        btnImprimir.setBounds(4, 50, 80, 23);

        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        getContentPane().add(btnSair);
        btnSair.setBounds(88, 50, 60, 23);

        setBounds(0, 0, 168, 119);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        try {
            int sair = -1;
            sair = JOptionPane.showConfirmDialog(null, "Deseja sair?", "PERGUNTA",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (sair == 0) {
                this.dispose();
            } else if (sair == 1) {

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        
        Imprimir();
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        rbProduto.setSelected(true);

    }//GEN-LAST:event_formWindowOpened

    private void Imprimir() {
        try {
            int op = -1;
            op = JOptionPane.showConfirmDialog(null, "Impressora esta ligada?", "PERGUNTA",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (op == 0) {
                this.dispose();
                
                URL arquivo = null;
                if (PrincipalVIEW.relCadProdutos == true) {
                    arquivo = getClass().getResource("/Relatorios/TodasCategorias.jasper");

                } else if (PrincipalVIEW.relPrecoProdutos == true) {
                    arquivo = getClass().getResource("/Relatorios/ListaPreco.jasper");

                }

                String tipo = null, titulo = null;
                //JOptionPane.showMessageDialog(this, arquivo.toString());
                if (arquivo == null) {
                    System.out.println("ARQUIVO INEXISTENTE");
                } else {

                    ConectaBanco.ConectaPostgres();
                    if (rbProduto.isSelected() == true) {
                        tipo = "PRODUTO";
                        if (PrincipalVIEW.relCadProdutos == true) {
                            titulo = "RELATÓRIO DE PRODUTOS";
                            PrincipalVIEW.relCadProdutos = false;
                        } else if (PrincipalVIEW.relPrecoProdutos == true) {
                            titulo = "TABELA DE PREÇO DE PRODUTOS";
                            PrincipalVIEW.relPrecoProdutos = false;
                        }

                    } else if (rbServico.isSelected() == true) {
                        tipo = "SERVICO";
                        if (PrincipalVIEW.relCadProdutos == true) {
                            titulo = "RELATÓRIO DE SERVIÇOS";
                            PrincipalVIEW.relCadProdutos = false;
                        } else if (PrincipalVIEW.relPrecoProdutos == true) {
                            titulo = "TABELA DE PREÇO DE SERVIÇOS";
                            PrincipalVIEW.relPrecoProdutos = false;
                        }
                    }
                    JasperPrint rel = null;
                    Map parametros = new HashMap();

                    parametros.put("tipo", tipo);
                    parametros.put("titulo", titulo);
                    // parametros.put("RGCRMV", documento);

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
            java.util.logging.Logger.getLogger(RelatorioVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RelatorioVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RelatorioVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RelatorioVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                RelatorioVIEW dialog = new RelatorioVIEW(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnSair;
    private javax.swing.JPanel jPanel1;
    private javax.swing.ButtonGroup opcaoRelatorios;
    private javax.swing.JCheckBox rbProduto;
    private javax.swing.JCheckBox rbServico;
    // End of variables declaration//GEN-END:variables
}