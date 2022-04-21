/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasVIEW;

import SolucaoSistemasCTR.AtendimentoCTR;
import SolucaoSistemasCTR.CompraCTR;
import SolucaoSistemasCTR.ConfiguracaoRelatorioCTR;
import SolucaoSistemasCTR.OrcamentoCTR;
import SolucaoSistemasCTR.OrcamentoCompraCTR;
import SolucaoSistemasCTR.OrdemServicoCTR;
import SolucaoSistemasCTR.VendaCTR;
import SolucaoSistemasDAO.ConectaBanco;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
 * @author Batman
 */
public class PesquisarPedidosVIEW extends javax.swing.JDialog {

    NumberFormat numeroPedidos = DecimalFormat.getCurrencyInstance(
            new Locale("pt", "BR"));
    ResultSet pesquisa = null;
    public static String codigoAlpha = "";

    /**
     * Creates new form PesquisarPedidosVIEW
     */
    public PesquisarPedidosVIEW(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        UtilVIEW.Icone(this);
        this.setLocationRelativeTo(null);
        lblImagem.setVisible(false);

        if (PrincipalVIEW.compra == false && PrincipalVIEW.situacaoOrdemServico.equals("")) {
            tblPedidos.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                        "", "CÓDIGO", "CLIENTE", "DATA", "TOTAL",
                        "ATENDENTE/AJUDANTE", "SITUAÇÃO"

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

        } else if (PrincipalVIEW.compra == true) {

            tblPedidos.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                        "", "CÓDIGO", "FORNECEDOR", "TOTAL", "DATA", "HORA", "ENTREGA",
                        "ATENDENTE/AJUDANTE", "SITUAÇÃO"

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

            tblPedidos.getColumnModel().getColumn(5).setMinWidth(100);
            tblPedidos.getColumnModel().getColumn(7).setMinWidth(200);
        } else if (!PrincipalVIEW.situacaoOrdemServico.equals("")) {
            tblPedidos.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                        "", "CÓDIGO", "CLIENTE", "CONTATO", "DATA", "DESCRIÇÃO", "SITUAÇÃO", "MODELO",
                        "MARCA", "COR"

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

            tblPedidos.getColumnModel().getColumn(5).setMinWidth(300);
            tblPedidos.getColumnModel().getColumn(7).setMinWidth(200);
            lblNovo.setVisible(false);
            lblImprimir.setBounds(360, 10, 40, 40);
            lblSair.setBounds(410, 10, 30, 40);
            lblImagem.setVisible(false);
            jLabel4.setVisible(false);
        }

        if (PrincipalVIEW.situacaoOrdemServico.equals("") && PrincipalVIEW.compra == false) {
            this.setTitle("PESQUISA DE PEDIDOS");
            switch (PrincipalVIEW.situacaoPedido) {
                case "Pendente":
                    ConsultarOrcamento(txtPesquisa.getText().toUpperCase(), "in('0')");
                    break;
                case "Finalizado":
                    ConsultarVenda();
                    break;
                case "Cancelado":
                    ConsultarVenda();
                    break;
                default:
                    break;

            }
            tblPedidos.getColumnModel().getColumn(5).setMinWidth(200);
        } else if (PrincipalVIEW.situacaoPedido.equals("") && PrincipalVIEW.compra == false) {
            this.setTitle("PESQUISA ORDEM DE SERVIÇO");
            ConsultarOrdemServico();
        } else if (PrincipalVIEW.situacaoOrdemServico.equals("") && PrincipalVIEW.compra == true) {
            this.setTitle("PESQUISA DE COMPRA DE PRODUTOS");
            switch (PrincipalVIEW.situacaoPedido) {
                case "Pendente":
                    ConsultarOrcamentoCompra();
                    break;
                case "Finalizado":
                    ConsultarCompra();
                    break;
                case "Cancelado":
                    ConsultarCompra();
                    break;
                default:
                    break;

            }

        }

        if(PrincipalVIEW.situacaoOrdemServico.equals("")){
            lblMensagem.setVisible(false);
        }
        tblPedidos.getColumnModel().getColumn(0).setMaxWidth(30);
        tblPedidos.getColumnModel().getColumn(1).setMinWidth(80);
        tblPedidos.getColumnModel().getColumn(2).setMinWidth(200);
        tblPedidos.getColumnModel().getColumn(3).setMinWidth(100);
        tblPedidos.getColumnModel().getColumn(4).setMinWidth(100);
        tblPedidos.getColumnModel().getColumn(6).setMinWidth(100);
        tblPedidos.setDefaultRenderer(Object.class, new ColorRenderer());
        tblPedidos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        lblNovo = new javax.swing.JLabel();
        lblImprimir = new javax.swing.JLabel();
        lblImagem = new javax.swing.JLabel();
        lblSair = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPedidos = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        lblMensagem = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel2.setText("Pesquisa por Cliente / Comprador");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 10, 310, 14);

        txtPesquisa.setBackground(new java.awt.Color(255, 255, 204));
        txtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaKeyReleased(evt);
            }
        });
        getContentPane().add(txtPesquisa);
        txtPesquisa.setBounds(10, 30, 340, 20);

        lblNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/8390_32x32.png"))); // NOI18N
        lblNovo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNovoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblNovoMouseEntered(evt);
            }
        });
        getContentPane().add(lblNovo);
        lblNovo.setBounds(360, 10, 30, 40);

        lblImprimir.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/Imprimir_32x32.png"))); // NOI18N
        lblImprimir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImprimirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblImprimirMouseEntered(evt);
            }
        });
        getContentPane().add(lblImprimir);
        lblImprimir.setBounds(390, 10, 40, 40);

        lblImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/foto_32x32.png"))); // NOI18N
        lblImagem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImagemMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblImagemMouseEntered(evt);
            }
        });
        getContentPane().add(lblImagem);
        lblImagem.setBounds(480, 10, 30, 40);

        lblSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/casa_32x32.png"))); // NOI18N
        lblSair.setText("jLabel9");
        lblSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSairMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblSairMouseEntered(evt);
            }
        });
        getContentPane().add(lblSair);
        lblSair.setBounds(440, 10, 30, 40);

        tblPedidos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPedidosMouseClicked(evt);
            }
        });
        tblPedidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblPedidosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblPedidos);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 60, 760, 230);

        jLabel4.setForeground(new java.awt.Color(0, 0, 204));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Total"));
        getContentPane().add(jLabel4);
        jLabel4.setBounds(590, 293, 180, 40);

        lblMensagem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMensagem.setText("Selecionar a ordem de serviço e pressionar enter para visualizar o histórico.");
        getContentPane().add(lblMensagem);
        lblMensagem.setBounds(10, 296, 470, 20);

        setBounds(0, 0, 794, 374);
    }// </editor-fold>//GEN-END:initComponents

    private void lblImprimirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImprimirMouseClicked
        try {
            codigoAlpha = String.valueOf(tblPedidos.getValueAt(tblPedidos.getSelectedRow(), 1));
            this.dispose();
            UtilVIEW objutilVIEW = new UtilVIEW();
            if (PrincipalVIEW.situacaoOrdemServico.equals("") && !String.valueOf(tblPedidos.getValueAt(tblPedidos.getSelectedRow(), 6)).equals("Finalizado")) {

                objutilVIEW.ImprimirOrcamento(codigoAlpha);
            } else if (PrincipalVIEW.situacaoOrdemServico.equals("") && String.valueOf(tblPedidos.getValueAt(tblPedidos.getSelectedRow(), 6)).equals("Finalizado")) {
                objutilVIEW.ImprimirFaturamento(codigoAlpha);
            } else {
                objutilVIEW.ImprimirOrdemServico(codigoAlpha, "");
            }

            codigoAlpha = "";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }//GEN-LAST:event_lblImprimirMouseClicked

    private void lblImprimirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImprimirMouseEntered
        lblImprimir.setToolTipText("Imprimir");
    }//GEN-LAST:event_lblImprimirMouseEntered

    private void lblImagemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagemMouseClicked

        new FotoVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_lblImagemMouseClicked

    private void lblImagemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagemMouseEntered
        lblImagem.setToolTipText("Imagem");
    }//GEN-LAST:event_lblImagemMouseEntered

    private void lblSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSairMouseClicked
        try {
            int sair = -1;
            sair = JOptionPane.showConfirmDialog(null, "Deseja sair?", "MENSAGEM",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (sair == 0) {
                PrincipalVIEW.situacaoOrdemServico = "";
                PrincipalVIEW.situacaoPedido = "";
                codigoAlpha = "";
                PrincipalVIEW.cancelarPedido = false;
                PrincipalVIEW.pedidoFinalizar = false;
                PrincipalVIEW.compra = false;
                VendaVIEW.finalizarPedido = false;
                this.dispose();
            } else if (sair == 1) {

            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_lblSairMouseClicked

    private void lblSairMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSairMouseEntered
        lblSair.setToolTipText("Sair");
    }//GEN-LAST:event_lblSairMouseEntered

    private void lblNovoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNovoMouseEntered
        lblNovo.setToolTipText("Novo Pedido");
    }//GEN-LAST:event_lblNovoMouseEntered

    private void tblPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPedidosMouseClicked
        try {

            if (tblPedidos.getValueAt(tblPedidos.getSelectedRow(), 0).equals(false)) {
                tblPedidos.setValueAt(true, tblPedidos.getSelectedRow(), 0);
            } else if (tblPedidos.getValueAt(tblPedidos.getSelectedRow(), 0).equals(true)) {
                tblPedidos.setValueAt(false, tblPedidos.getSelectedRow(), 0);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_tblPedidosMouseClicked

    private void txtPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyReleased
        try {
            if (txtPesquisa.getText().equals("")) {
                UtilVIEW.LimparTabela(tblPedidos);
            } else if (PrincipalVIEW.situacaoOrdemServico.equals("") && PrincipalVIEW.compra == false) {
                switch (PrincipalVIEW.situacaoPedido) {
                    case "Pendente":
                        ConsultarOrcamento(txtPesquisa.getText().toUpperCase(), "in('0')");
                        break;
                    case "Finalizado":
                        ConsultarVenda();
                        break;
                    case "Cancelado":
                        ConsultarVenda();
                        break;
                    default:
                        break;

                }
            } else if (PrincipalVIEW.situacaoPedido.equals("") && PrincipalVIEW.compra == false) {
                ConsultarOrdemServico();
            } else if (PrincipalVIEW.situacaoOrdemServico.equals("") && PrincipalVIEW.compra == true) {
                switch (PrincipalVIEW.situacaoOrdemServico) {
                    case "Pendente":
                        ConsultarCompra();
                        break;
                    case "Finalizado":
                        ConsultarCompra();
                        break;
                    case "Cancelado":
                        ConsultarCompra();
                        break;
                    default:
                        break;

                }
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtPesquisaKeyReleased

    private void lblNovoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNovoMouseClicked
        try {
            codigoAlpha = "";
            codigoAlpha = String.valueOf(tblPedidos.getValueAt(tblPedidos.getSelectedRow(), 1));
            if (PrincipalVIEW.situacaoPedido.equals("Cancelado")) {
                JOptionPane.showMessageDialog(null, "Pedido Cancelado!", "Mensagem", JOptionPane.WARNING_MESSAGE);
            } else if (PrincipalVIEW.cancelarPedido == true
                    && PrincipalVIEW.compra == false && PrincipalVIEW.situacaoPedido.equals("Pendente")) {

                CancelarOrcamento();

                System.out.println("Cancelar Orcamento");
            } else if (PrincipalVIEW.cancelarPedido == true && PrincipalVIEW.pedidoFinalizar == true
                    && PrincipalVIEW.situacaoPedido.equals("Finalizado")) {
                System.out.println("Cancelar Finalizado");
                new FinalizarAtendimentoVIEW(null, true).setVisible(true);
            } else if (PrincipalVIEW.pedidoFinalizar == true && PrincipalVIEW.situacaoPedido.equals("Pendente")
                    && VendaVIEW.finalizarPedido == true) {
                this.dispose();
                new VendaVIEW(null, true).setVisible(true);
            } else if (PrincipalVIEW.cancelarPedido == true && PrincipalVIEW.compra == true
                    && PrincipalVIEW.situacaoPedido.equals("Pendente")) {
                System.out.println("Cancelar OrcamentoCompra");
                CancelarOrcamentoCompra();

            } else if (PrincipalVIEW.situacaoPedido.equals("")) {
                new FinalizarAtendimentoVIEW(null, true).setVisible(true);
            }
            UtilVIEW.LimparTabela(tblPedidos);
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
            JOptionPane.showMessageDialog(null, "Selecionar o pedido!", "MENSAGEM", JOptionPane.WARNING_MESSAGE);
        }


    }//GEN-LAST:event_lblNovoMouseClicked

    private void tblPedidosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblPedidosKeyPressed
        try {
            if (!PrincipalVIEW.situacaoOrdemServico.equals("")) {
                this.dispose();
                new HistoricoOrdemVIEW(null, true).setVisible(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO", "MENSAGEM", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_tblPedidosKeyPressed

    private void CancelarOrcamento() {
        try {
            OrcamentoCTR objorcamentoCTR = new OrcamentoCTR();
            if (objorcamentoCTR.CancelarOrcamentoCTR("Cancelado", codigoAlpha)) {
                JOptionPane.showMessageDialog(null, "Cancelado com sucesso!", "MENSAGEM",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Selecionar o pedido!", "MENSAGEM",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void CancelarOrcamentoCompra() {
        try {
            OrcamentoCompraCTR objorcamentoCTR = new OrcamentoCompraCTR();
            if (objorcamentoCTR.CancelarOrcamentoCompraCTR("Cancelado", codigoAlpha)) {
                JOptionPane.showMessageDialog(null, "Cancelado com sucesso!", "MENSAGEM",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Selecionar o pedido!", "MENSAGEM",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void ConsultarOrcamento(String... parametro) {
        try {
            OrcamentoCTR objorcamentoCTR = new OrcamentoCTR();
            pesquisa = objorcamentoCTR.ConsultarOrcamentoCTR(parametro[0], parametro[1]);
            RestaurarPedido(pesquisa);
            pesquisa = null;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Restaurar Orcamento!",
                    "MENSAGEM", JOptionPane.WARNING_MESSAGE);
            System.out.println(String.valueOf(e.getMessage()));
        }
    }

    private void ConsultarVenda() {
        try {
            VendaCTR objvendaCTR = new VendaCTR();
            pesquisa = objvendaCTR.ConsultarVendaCTR(txtPesquisa.getText().toUpperCase(),
                    PrincipalVIEW.situacaoPedido);
            RestaurarPedido(pesquisa);
            pesquisa = null;
        } catch (Exception e) {
        }
    }

    private void ImprimirHistoricoOS(){
         try {
            int op = -1;
            op = JOptionPane.showConfirmDialog(null, "Impressora está ligada?", "PERGUNTA",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (op == 0) {

                URL arquivo = getClass().getResource("/Relatorios/historicoOS.jasper");

                //JOptionPane.showMessageDialog(this, arquivo.toString());
                if (arquivo == null) {
                    System.out.println("ARQUIVO INEXISTENTE");
                } else {

                    ConectaBanco.ConectaPostgres();
                    JasperPrint rel = null;
                    Map parametros = new HashMap();
                    parametros.put("numero", codigoAlpha);
                    JasperReport jr;
                    try {
                        jr = (JasperReport) JRLoader.loadObject(arquivo);
                        rel = JasperFillManager.fillReport(jr, parametros, ConectaBanco.con);
                        JasperViewer.viewReport(rel, false);
                    } catch (JRException ex) {
                        System.out.println(ex.getMessage());
                    }

                }
            } else if (op == 1) {

            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Mensagem UtilVIEW " + e.getMessage());
        }
    }
   

    private void ConsultarOrcamentoCompra() {
        try {
            OrcamentoCompraCTR objorcamentoCompraCTR = new OrcamentoCompraCTR();
            ResultSet orcamentoCompra = null;
            orcamentoCompra = objorcamentoCompraCTR.ConsultarOrcamentoCompraCTR(txtPesquisa.getText().toUpperCase(),
                    "in ('0')");
            RestaurarPedido(orcamentoCompra);
        } catch (Exception e) {
            System.out.println("Erro ao ConsultarOrcamentoCompra " + e.getMessage());
        }
    }

    private void ConsultarCompra() {
        try {
            CompraCTR objcompraCTR = new CompraCTR();
            pesquisa = objcompraCTR.ConsultarCompraCTR(PrincipalVIEW.situacaoPedido,
                    txtPesquisa.getText().toUpperCase());
            RestaurarPedido(pesquisa);
        } catch (Exception e) {
        }
    }

    private void RestaurarAtendimento(ResultSet rs) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) tblPedidos.getModel();
            dtm.setRowCount(0);
            while (rs.next()) {
                dtm.addRow(new Object[]{false, rs.getString("codigo"),
                    rs.getString("nome"), rs.getString("data"),
                    numeroPedidos.format(rs.getDouble("total")),
                    rs.getString("atendente"), rs.getString("situacao")});
            }
            tblPedidos.setModel(dtm);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Restaurar Pedido!",
                    "MENSAGEM", JOptionPane.WARNING_MESSAGE);
            System.out.println(String.valueOf(e.getMessage()));
        }
    }

    private void ConsultarOrdemServico() {
        try {
            OrdemServicoCTR objordemCTR = new OrdemServicoCTR();
            ResultSet ordem = null;
            ordem = objordemCTR.ConsultarOrdemServicoCTR(PrincipalVIEW.situacaoOrdemServico,
                    txtPesquisa.getText().toUpperCase());
            RestaurarOrdemServico(ordem);
            ordem = null;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao ConsultarOrdemServico", "MENSAGEM",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void RestaurarOrdemServico(ResultSet rs) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) tblPedidos.getModel();
            dtm.setRowCount(0);
            while (rs.next()) {
                dtm.addRow(new Object[]{false, rs.getString("codigo"), rs.getString("nome"),
                    rs.getString("telefone1"), rs.getString("dat"), rs.getString("descricao"), rs.getString("situacao"),
                    rs.getString("modelo"), rs.getString("marca"), rs.getString("cor")});
            }
            tblPedidos.setModel(dtm);
        } catch (Exception e) {
        }
    }

    private void RestaurarPedido(ResultSet rs) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) tblPedidos.getModel();
            dtm.setRowCount(0);
            if (PrincipalVIEW.compra == false) {
                while (rs.next()) {
                    dtm.addRow(new Object[]{false, rs.getString("codigo"),
                        rs.getString("nome"), rs.getString("data"),
                        numeroPedidos.format(rs.getDouble("total")),
                        rs.getString("atendente"), rs.getString("situacao")});
                }
            } else if (PrincipalVIEW.compra == true) {
                while (rs.next()) {
                    dtm.addRow(new Object[]{false, rs.getString("codigo"),
                        rs.getString("nome"), numeroPedidos.format(rs.getDouble("total")),
                        rs.getString("dat"), rs.getString("hora"),
                        rs.getString("entrega"), rs.getString("funcionario"), rs.getString("situacao")});
                }
            }

            tblPedidos.setModel(dtm);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Restaurar Pedido!",
                    "MENSAGEM", JOptionPane.WARNING_MESSAGE);
            System.out.println(String.valueOf(e.getMessage()));
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
            java.util.logging.Logger.getLogger(PesquisarPedidosVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PesquisarPedidosVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PesquisarPedidosVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PesquisarPedidosVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                PesquisarPedidosVIEW dialog = new PesquisarPedidosVIEW(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImagem;
    private javax.swing.JLabel lblImprimir;
    private javax.swing.JLabel lblMensagem;
    private javax.swing.JLabel lblNovo;
    private javax.swing.JLabel lblSair;
    public static javax.swing.JTable tblPedidos;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}
