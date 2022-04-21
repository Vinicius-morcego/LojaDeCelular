/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasVIEW;

import SolucaoSistemasCTR.ProdutoServicoCTR;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vinicius
 */
public class PesquisaProdutosVIEW extends javax.swing.JDialog {

    ProdutoServicoCTR objprodutoCTR = new ProdutoServicoCTR();
    public static String codigoProduto = "", ordena = "", filtro = "";
    NumberFormat numero = DecimalFormat.getCurrencyInstance(
            new Locale("pt", "BR"));

    /**
     * Creates new form PesquisaProdutosVIEW
     *
     * @param parent
     * @param modal
     */
    public PesquisaProdutosVIEW(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle(">>> PESQUISA DE PRODUTOS E SERVIÇOS CADASTRADOS <<<");
        tblProduto.setDefaultRenderer(Object.class, new ColorRenderer());
        UtilVIEW.Icone(this);
        ConsultarProduto("", "null", "null", "2", "0");
        tblProduto.getColumnModel().getColumn(0).setMinWidth(60);
        tblProduto.getColumnModel().getColumn(1).setMinWidth(260);
        tblProduto.getColumnModel().getColumn(2).setMinWidth(100);
        tblProduto.getColumnModel().getColumn(3).setMinWidth(150);
        tblProduto.getColumnModel().getColumn(4).setMinWidth(100);
        tblProduto.getColumnModel().getColumn(5).setMinWidth(100);
        tblProduto.getColumnModel().getColumn(6).setMinWidth(100);
        tblProduto.getColumnModel().getColumn(7).setMinWidth(100);
        tblProduto.getColumnModel().getColumn(8).setMinWidth(100);
        tblProduto.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cbxOrdena = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cbxFiltroGeral = new javax.swing.JComboBox();
        lblPesqImagem = new javax.swing.JLabel();
        lblIncluirProd = new javax.swing.JLabel();
        lblAlteraProd = new javax.swing.JLabel();
        lblImprimiProd = new javax.swing.JLabel();
        lblSairProd = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtItem = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtReferencia = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduto = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel1.setText("Ordenar a Pesquisa");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 10, 120, 14);

        cbxOrdena.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Por Codigo", "Por Nome", "Por Marca", "Por Categoria" }));
        cbxOrdena.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxOrdenaItemStateChanged(evt);
            }
        });
        getContentPane().add(cbxOrdena);
        cbxOrdena.setBounds(10, 30, 120, 20);

        jLabel2.setText("Filtro Geral");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(140, 10, 80, 14);

        cbxFiltroGeral.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "TODOS", "Nome", "Código de Barras", "Referência", " " }));
        cbxFiltroGeral.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxFiltroGeralItemStateChanged(evt);
            }
        });
        getContentPane().add(cbxFiltroGeral);
        cbxFiltroGeral.setBounds(140, 30, 120, 20);

        lblPesqImagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPesqImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/foto_32x32.png"))); // NOI18N
        lblPesqImagem.setText("Imagem");
        lblPesqImagem.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblPesqImagem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblPesqImagem.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblPesqImagem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPesqImagemMouseClicked(evt);
            }
        });
        getContentPane().add(lblPesqImagem);
        lblPesqImagem.setBounds(460, 10, 60, 50);

        lblIncluirProd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIncluirProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/7881_32x32.png"))); // NOI18N
        lblIncluirProd.setText("Incluir");
        lblIncluirProd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblIncluirProd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblIncluirProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIncluirProdMouseClicked(evt);
            }
        });
        getContentPane().add(lblIncluirProd);
        lblIncluirProd.setBounds(512, 8, 60, 50);

        lblAlteraProd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAlteraProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/8416_32x32.png"))); // NOI18N
        lblAlteraProd.setText("Alterar");
        lblAlteraProd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblAlteraProd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblAlteraProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAlteraProdMouseClicked(evt);
            }
        });
        getContentPane().add(lblAlteraProd);
        lblAlteraProd.setBounds(570, 8, 60, 50);

        lblImprimiProd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImprimiProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/Imprimir_32x32.png"))); // NOI18N
        lblImprimiProd.setText("Imprimir");
        lblImprimiProd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblImprimiProd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblImprimiProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImprimiProdMouseClicked(evt);
            }
        });
        getContentPane().add(lblImprimiProd);
        lblImprimiProd.setBounds(630, 8, 60, 50);

        lblSairProd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSairProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/casa_32x32.png"))); // NOI18N
        lblSairProd.setText("Sair");
        lblSairProd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblSairProd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblSairProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSairProdMouseClicked(evt);
            }
        });
        getContentPane().add(lblSairProd);
        lblSairProd.setBounds(690, 8, 60, 50);

        jLabel10.setText("PESQUISAR ITEM");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(10, 70, 90, 14);

        txtItem.setBackground(new java.awt.Color(255, 255, 204));
        txtItem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtItemKeyReleased(evt);
            }
        });
        getContentPane().add(txtItem);
        txtItem.setBounds(100, 68, 290, 20);

        jLabel11.setText("POR REFERÊNCIA");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(400, 70, 90, 14);

        txtReferencia.setBackground(new java.awt.Color(255, 255, 204));
        txtReferencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtReferenciaKeyReleased(evt);
            }
        });
        getContentPane().add(txtReferencia);
        txtReferencia.setBounds(500, 68, 260, 20);

        tblProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nome", "Marca", "Categoria", "Estoque Minimo", "Estoque Atual", "Custo", "Venda", "Unidade de Medida"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdutoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProduto);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 100, 750, 260);

        setBounds(0, 0, 782, 403);
    }// </editor-fold>//GEN-END:initComponents

    private void lblIncluirProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIncluirProdMouseClicked
        new ProdutosServicosVIEW(null, true).setVisible(true);
        ConsultarProduto("", "null", "null", "2", "0");
    }//GEN-LAST:event_lblIncluirProdMouseClicked

    private void txtItemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtItemKeyReleased
        if (txtItem.getText().equals("")) {
            ConsultarProduto("", "null",
                    "null", "2", "0");
        } else {
            try {
//              
                switch (cbxFiltroGeral.getSelectedIndex()) {
                    case 0:
                        ConsultarProduto(txtItem.getText().toUpperCase(), "null",
                                "null", "2", "0");

                        break;
                    case 1:
                        ConsultarProduto(txtItem.getText().toUpperCase(), "null",
                                "null", "2", "0");
                        
                        break;
                    case 2:
                        
                        ConsultarProduto(txtItem.getText().toUpperCase(), "null",
                                "null", "2", "0");
                        break;
                    case 3:
                        
                        ConsultarProduto("null",
                                "null", txtItem.getText().toUpperCase(), "2", "0");
                        break;
                    case 4:
                        
                        ConsultarProduto("null", txtReferencia.getText().toUpperCase(),
                                "null", "2", "0");
                        break;
                    default:             
                        ConsultarProduto(txtItem.getText().toUpperCase(), "null",
                                "null", "2", "0");
                        break;

                }
            } catch (Exception e) {
                System.out.println(""+e.getMessage());
            }

        }
    }//GEN-LAST:event_txtItemKeyReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        txtItem.requestFocusInWindow();
    }//GEN-LAST:event_formWindowOpened

    private void lblAlteraProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAlteraProdMouseClicked
        codigoProduto = tblProduto.getValueAt(tblProduto.getSelectedRow(), 0).toString();
        new ProdutosServicosVIEW(null, true).setVisible(true);
        ConsultarProduto("", "null", "null", "2", "0");
    }//GEN-LAST:event_lblAlteraProdMouseClicked

    private void lblSairProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSairProdMouseClicked
        int question = -1;
        question = JOptionPane.showConfirmDialog(null, "Deseja sair?", "MENSAGEM",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (question == 0) {
            this.dispose();
        } else if (question == 1) {

        }
    }//GEN-LAST:event_lblSairProdMouseClicked
    public static boolean telaProduto = false;
    private void lblPesqImagemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPesqImagemMouseClicked
        telaProduto = true;
        codigoProduto = tblProduto.getValueAt(tblProduto.getSelectedRow(), 0).toString();
        new FotoVIEW(null, true).setVisible(true);

    }//GEN-LAST:event_lblPesqImagemMouseClicked

    private void cbxOrdenaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxOrdenaItemStateChanged
        try {
            switch (cbxOrdena.getSelectedIndex()) {
                case 0:
                    ordena = "1";
                    ConsultarProduto("", "null", "null", ordena, "0");
                    ordena = "";
                    break;
                case 1:
                    ordena = "1";
                    ConsultarProduto("", "null", "null", ordena, "0");
                    ordena = "";
                    break;
                case 2:
                    ordena = "2";
                    ConsultarProduto("", "null", "null", ordena, "0");
                    ordena = "";
                    break;
                case 3:
                    ordena = "5";
                    ConsultarProduto("", "null", "null", ordena, "0");
                    ordena = "";
                    break;
                case 4:
                    ordena = "6";
                    ConsultarProduto("", "null", "null", ordena, "0");
                    ordena = "";
                    break;
                default:
                    ordena = "2";
                    ConsultarProduto("", "null", "null", ordena, "0");
                    ordena = "";
                    break;

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cbxOrdenaItemStateChanged

    private void txtReferenciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReferenciaKeyReleased
        ConsultarProduto("null", txtReferencia.getText().toUpperCase(),
                "null", "2", "0");
    }//GEN-LAST:event_txtReferenciaKeyReleased

    private void lblImprimiProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImprimiProdMouseClicked
        this.dispose();
        PrincipalVIEW.relCadProdutos = true;
        new RelatorioVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_lblImprimiProdMouseClicked

    private void tblProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutoMouseClicked
        try {
            if (evt.getClickCount() == 2) {
                if (AtendimentoVIEW.incluirProduto == true || VendaVIEW.incluirItem == true) {
                    this.dispose();
                    new CadastroItemVIEW(null, true).setVisible(true);
                } else if (AtendimentoVIEW.incluirProduto == false && VendaVIEW.incluirItem == false) {

                }

            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_tblProdutoMouseClicked

    private void cbxFiltroGeralItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxFiltroGeralItemStateChanged
        try {
            switch (cbxFiltroGeral.getSelectedIndex()) {
                case 0:
                    break;
                case 1:
                    txtItem.requestFocusInWindow();
                    break;
                case 2:
                    txtItem.requestFocusInWindow();
                    break;
                case 3:
                    txtItem.requestFocusInWindow();
                    break;
                case 4:
                    txtReferencia.requestFocusInWindow();
                    break;
                default:
                    txtItem.requestFocusInWindow();
                    break;

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cbxFiltroGeralItemStateChanged

    private void ConsultarProduto(String... pesquisas) {
        try {
            ResultSet consulta;
            consulta = objprodutoCTR.ConsultarProdutoCTR(pesquisas[0],
                    pesquisas[1], pesquisas[2],
                    pesquisas[3], pesquisas[4]);
            RestauraProduto(consulta);
            consulta = null;
        } catch (Exception e) {
            System.out.println("Erro ao Consultar o produto! " + e.getMessage());
        }
    }

    private void RestauraProduto(ResultSet rs) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) tblProduto.getModel();
            dtm.setRowCount(0);
            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getString("codigo"), rs.getString("nome"),
                    rs.getString("marca"), rs.getString("categoria"), rs.getString("estoqueminimo"),
                    rs.getString("estoqueatual"), numero.format(rs.getDouble("valorcusto")),
                    numero.format(rs.getDouble("valorvenda")), rs.getString("unidade")});
            }
            tblProduto.setModel(dtm);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Restaurar o Produto!",
                    "MENSAGEM", JOptionPane.WARNING_MESSAGE);
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
            java.util.logging.Logger.getLogger(PesquisaProdutosVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PesquisaProdutosVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PesquisaProdutosVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PesquisaProdutosVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                PesquisaProdutosVIEW dialog = new PesquisaProdutosVIEW(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox cbxFiltroGeral;
    private javax.swing.JComboBox cbxOrdena;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAlteraProd;
    private javax.swing.JLabel lblImprimiProd;
    private javax.swing.JLabel lblIncluirProd;
    private javax.swing.JLabel lblPesqImagem;
    private javax.swing.JLabel lblSairProd;
    public static javax.swing.JTable tblProduto;
    private javax.swing.JTextField txtItem;
    private javax.swing.JTextField txtReferencia;
    // End of variables declaration//GEN-END:variables
}