/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasVIEW;

import SolucaoSistemasCTR.ComissaoCTR;
import SolucaoSistemasCTR.ComissaoOrcamentoCTR;
import SolucaoSistemasCTR.CompraCTR;
import SolucaoSistemasCTR.ItensCompraCTR;
import SolucaoSistemasCTR.ItensOrcamentoCTR;
import SolucaoSistemasCTR.ItensOrcamentoCompraCTR;
import SolucaoSistemasCTR.ItensVendaCTR;
import SolucaoSistemasCTR.OrcamentoCTR;
import SolucaoSistemasCTR.OrcamentoCompraCTR;
import SolucaoSistemasCTR.PessoaCTR;
import SolucaoSistemasCTR.VendaCTR;
import static SolucaoSistemasVIEW.PrincipalVIEW.cancelarPedido;
import static SolucaoSistemasVIEW.PrincipalVIEW.compra;
import static SolucaoSistemasVIEW.PrincipalVIEW.pedidoFinalizar;
import static SolucaoSistemasVIEW.PrincipalVIEW.situacaoPedido;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vinicius
 */
public class VendaVIEW extends javax.swing.JDialog {

    OrcamentoCTR objorcamentoCTR = new OrcamentoCTR();
    public static String pedidoCliente = "", codigoPedido = "";
    PessoaCTR objpessoaCTR = new PessoaCTR();
    ResultSet atendentePedido = null;
    Date dataPedido = null;
    UtilVIEW objutil = new UtilVIEW();
    int confirmaSair = -1, comissaoPercorre = 0;
    int cont = 0;
    public static Double totalPedido = 0.0, quantidade = 0.0, subtotal = 0.0;
    Double desconto = 0.0, frete = 0.0, resultado = 0.0;
    NumberFormat moedaPedido = DecimalFormat.getCurrencyInstance(
            new Locale("pt", "BR"));
    String mensagem = "";

    /**
     * Creates new form VendaVIEW
     */
    public VendaVIEW(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        UtilVIEW.Icone(this);

        if (PrincipalVIEW.compra == true) {
            ConsultarMaiorCodigoPedido();
            this.setTitle(">>> TELA PEDIDO DE COMPRA / ORÇAMENTO <<<");
            lblComprador.setText("Descrição do Fornecedor");
            mensagem = "Fornecedor!";
            lblVendedor.setText("Funcionário");
            rbPedido.setText("PEDIDO DE COMPRA");
            if (PesquisarPedidosVIEW.codigoAlpha.equals("")) {
                txtQuantidade.setText("0");
                UtilVIEW.FormatarCampo(txtSubtotal, txtTotal);
            } else {
                FinalizarOrcamentoCompra();
                btnSalvarPedido.setEnabled(false);
                incluirItem = true;

            }
        } else if (PrincipalVIEW.compra == false) {
            this.setTitle(">>> TELA PEDIDO DE VENDAS / ORÇAMENTO <<<");
            mensagem = "Cliente!";
            ConsultarMaiorCodigoPedido();
            if (PesquisarPedidosVIEW.codigoAlpha.equals("")) {
                txtQuantidade.setText("0");
                UtilVIEW.FormatarCampo(txtSubtotal, txtTotal);
            } else {
                FinalizarOrcamento();
                btnSalvarPedido.setEnabled(false);
                incluirItem = true;

            }
        }
        UtilVIEW.FormatarCampo(txtDesconto, txtFrete);

        btnAutomatico.setMnemonic(KeyEvent.VK_F2);
        btnIncluirProduto.setMnemonic(KeyEvent.VK_F3);
        btnAlterarProduto.setMnemonic(KeyEvent.VK_F4);
        btnExcluirProduto.setMnemonic(KeyEvent.VK_F5);
        btnImprimirPedido.setMnemonic(KeyEvent.VK_F6);
        btnSalvarPedido.setMnemonic(KeyEvent.VK_F7);
        btnFinalizarPedido.setMnemonic(KeyEvent.VK_F8);
        btnSairPedido.setMnemonic(KeyEvent.VK_F9);
        Atalhos();

        tblItensPedido.getColumnModel().getColumn(0).setMaxWidth(60);
        tblItensPedido.getColumnModel().getColumn(2).setMinWidth(200);
        tblItensPedido.getColumnModel().getColumn(6).setMinWidth(200);
        tblItensPedido.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblItensPedido.setDefaultRenderer(Object.class, new ColorRenderer());
        txtHoraEntrega.setText(new SimpleDateFormat("HH:mm").format(new Date()));
        txtHoraPedido.setText(new SimpleDateFormat("HH:mm").format(new Date()));
        UtilVIEW.DataAtual(txtDataEntrega);
        UtilVIEW.DataAtual(txtDataPedido);

    }

    private void Atalhos() {
        JPanel painel = (JPanel) getContentPane();
        //Criamos as ações
        ActionMap mapear = painel.getActionMap();
        mapear.put("Sair", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSairPedido.doClick();
                totalPedido = 0.0;
                finalizarPedido = false;
                clientePedido = false;
                incluirItem = false;
                subtotal = 0.0;
                quantidade = 0.0;
                PrincipalVIEW.compra = false;
                PesquisarPedidosVIEW.codigoAlpha = "";
                dispose();
            }
        });
        mapear.put("Automatico", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAutomatico.doClick();
            }
        });
        mapear.put("Incluir", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnIncluirProduto.doClick();
            }
        });

        mapear.put("Alterar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAlterarProduto.doClick();
            }
        });

        mapear.put("Excluir", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnExcluirProduto.doClick();

            }
        });
        mapear.put("Imprimir", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnImprimirPedido.doClick();

            }
        });
        mapear.put("Salvar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSalvarPedido.doClick();

            }
        });

        mapear.put("Finalizar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnFinalizarPedido.doClick();

            }
        });

        //Registramos os componentes que queremos para as ações
        InputMap imap = painel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);

        imap.put(KeyStroke.getKeyStroke("F2"), "Automatico");
        imap.put(KeyStroke.getKeyStroke("F3"), "Incluir");
        imap.put(KeyStroke.getKeyStroke("F4"), "Alterar");
        imap.put(KeyStroke.getKeyStroke("F5"), "Excluir");
        imap.put(KeyStroke.getKeyStroke("F6"), "Imprimir");
        imap.put(KeyStroke.getKeyStroke("F7"), "Salvar");
        imap.put(KeyStroke.getKeyStroke("F8"), "Finalizar");
        imap.put(KeyStroke.getKeyStroke("F9"), "Sair");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        opcaoVendas = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPedido = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        rbPedido = new javax.swing.JCheckBox();
        rbOrcamento = new javax.swing.JCheckBox();
        txtDataPedido = new javax.swing.JFormattedTextField();
        txtHoraPedido = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        lblComprador = new javax.swing.JLabel();
        txtComprador = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtContato = new javax.swing.JTextField();
        lblVendedor = new javax.swing.JLabel();
        cbxVendedor = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblItensPedido = new javax.swing.JTable();
        btnFormaPagamento = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        cbxSituacaoPedido = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnAutomatico = new javax.swing.JButton();
        btnIncluirProduto = new javax.swing.JButton();
        btnAlterarProduto = new javax.swing.JButton();
        btnExcluirProduto = new javax.swing.JButton();
        btnImprimirPedido = new javax.swing.JButton();
        btnSalvarPedido = new javax.swing.JButton();
        btnFinalizarPedido = new javax.swing.JButton();
        btnSairPedido = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtDesconto = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtFrete = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservacao = new javax.swing.JTextArea();
        txtDataEntrega = new javax.swing.JFormattedTextField();
        txtHoraEntrega = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setLayout(null);

        jLabel1.setText("Nº DO PEDIDO");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(4, 4, 71, 14);

        txtPedido.setEditable(false);
        txtPedido.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtPedido.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel1.add(txtPedido);
        txtPedido.setBounds(4, 20, 100, 20);

        jLabel2.setText("DATA PEDIDO");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(120, 4, 80, 14);

        jLabel3.setText("HORA");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(220, 4, 60, 14);

        opcaoVendas.add(rbPedido);
        rbPedido.setText("PEDIDO DE VENDA");
        rbPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPedidoActionPerformed(evt);
            }
        });
        jPanel1.add(rbPedido);
        rbPedido.setBounds(4, 44, 140, 23);

        opcaoVendas.add(rbOrcamento);
        rbOrcamento.setText("ORÇAMENTO");
        rbOrcamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbOrcamentoActionPerformed(evt);
            }
        });
        jPanel1.add(rbOrcamento);
        rbOrcamento.setBounds(4, 66, 110, 23);

        try {
            txtDataPedido.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataPedido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDataPedidoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDataPedidoFocusLost(evt);
            }
        });
        txtDataPedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDataPedidoKeyPressed(evt);
            }
        });
        jPanel1.add(txtDataPedido);
        txtDataPedido.setBounds(120, 20, 80, 20);

        try {
            txtHoraPedido.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtHoraPedido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtHoraPedidoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtHoraPedidoFocusLost(evt);
            }
        });
        jPanel1.add(txtHoraPedido);
        txtHoraPedido.setBounds(220, 20, 60, 20);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(4, 4, 290, 90);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setLayout(null);

        lblComprador.setText("Descrição do Comprador");
        jPanel2.add(lblComprador);
        lblComprador.setBounds(4, 4, 250, 14);

        txtComprador.setEditable(false);
        txtComprador.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel2.add(txtComprador);
        txtComprador.setBounds(4, 20, 376, 20);

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/pessoas_32x32.png"))); // NOI18N
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });
        jPanel2.add(btnPesquisar);
        btnPesquisar.setBounds(386, 4, 150, 40);

        jLabel5.setText("Contato / Outras Informações");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(4, 44, 270, 14);

        txtContato.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtContatoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtContatoFocusLost(evt);
            }
        });
        txtContato.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContatoKeyPressed(evt);
            }
        });
        jPanel2.add(txtContato);
        txtContato.setBounds(4, 60, 338, 20);

        lblVendedor.setText("Atendente / Vendedor");
        jPanel2.add(lblVendedor);
        lblVendedor.setBounds(348, 44, 150, 14);

        cbxVendedor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cbxVendedor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cbxVendedorFocusGained(evt);
            }
        });
        jPanel2.add(cbxVendedor);
        cbxVendedor.setBounds(348, 60, 186, 20);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(300, 4, 540, 90);

        tblItensPedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Código", "Produto", "Valor", "Quantidade", "Subtotal", "Funcionário", "Comissão"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblItensPedido);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 100, 830, 220);

        btnFormaPagamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/6939_32x32.png"))); // NOI18N
        btnFormaPagamento.setText("Forma e Condições de Pagamento");
        btnFormaPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFormaPagamentoActionPerformed(evt);
            }
        });
        getContentPane().add(btnFormaPagamento);
        btnFormaPagamento.setBounds(10, 340, 270, 41);

        jLabel7.setText("Situação Atual");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(10, 390, 80, 14);

        cbxSituacaoPedido.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pendente", "Finalizado", "Cancelado" }));
        cbxSituacaoPedido.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSituacaoPedidoItemStateChanged(evt);
            }
        });
        getContentPane().add(cbxSituacaoPedido);
        cbxSituacaoPedido.setBounds(90, 390, 190, 20);

        jLabel8.setText("Data entrega:");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(10, 420, 70, 14);

        jLabel9.setText("Hora:");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(190, 420, 30, 14);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAutomatico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/codigobarras.png"))); // NOI18N
        btnAutomatico.setText("Automático [F2]");
        btnAutomatico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAutomaticoActionPerformed(evt);
            }
        });
        jPanel3.add(btnAutomatico, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 190, 40));

        btnIncluirProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/8390_32x32.png"))); // NOI18N
        btnIncluirProduto.setText("Incluir Produto [F3]");
        btnIncluirProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirProdutoActionPerformed(evt);
            }
        });
        jPanel3.add(btnIncluirProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 52, 190, 40));

        btnAlterarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/Alterar_32x32.png"))); // NOI18N
        btnAlterarProduto.setText("Alterar Produto [F4]");
        btnAlterarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarProdutoActionPerformed(evt);
            }
        });
        jPanel3.add(btnAlterarProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 94, 190, 40));

        btnExcluirProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/sair_32x32.png"))); // NOI18N
        btnExcluirProduto.setText("Excluir Produto [F5]");
        btnExcluirProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirProdutoActionPerformed(evt);
            }
        });
        jPanel3.add(btnExcluirProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 136, 190, 40));

        btnImprimirPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/Imprimir_32x32.png"))); // NOI18N
        btnImprimirPedido.setText("Imprimir Jato / Laser [F6]");
        btnImprimirPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirPedidoActionPerformed(evt);
            }
        });
        jPanel3.add(btnImprimirPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(206, 10, 190, -1));

        btnSalvarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/7881_32x32.png"))); // NOI18N
        btnSalvarPedido.setText("Salvar Pedido [F7]");
        btnSalvarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarPedidoActionPerformed(evt);
            }
        });
        jPanel3.add(btnSalvarPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(206, 52, 190, 40));

        btnFinalizarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/8416_32x32.png"))); // NOI18N
        btnFinalizarPedido.setText("Finalizar Pedido [F8]");
        btnFinalizarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarPedidoActionPerformed(evt);
            }
        });
        jPanel3.add(btnFinalizarPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(206, 94, 190, 40));

        btnSairPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/casa_32x32.png"))); // NOI18N
        btnSairPedido.setText("Sair [F9]");
        btnSairPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairPedidoActionPerformed(evt);
            }
        });
        jPanel3.add(btnSairPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(206, 136, 190, 40));

        jLabel11.setText("Quantidade");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 60, 20));

        txtQuantidade.setEditable(false);
        txtQuantidade.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtQuantidade.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtQuantidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtQuantidadeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtQuantidadeFocusLost(evt);
            }
        });
        jPanel3.add(txtQuantidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 70, -1));

        jLabel12.setText("Subtotal");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, 50, 20));

        txtSubtotal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtSubtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSubtotal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSubtotalFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSubtotalFocusLost(evt);
            }
        });
        jPanel3.add(txtSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, 90, -1));

        jLabel13.setText("Desconto");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, 50, 20));

        txtDesconto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtDesconto.setForeground(new java.awt.Color(255, 0, 0));
        txtDesconto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDesconto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDescontoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDescontoFocusLost(evt);
            }
        });
        txtDesconto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescontoKeyPressed(evt);
            }
        });
        jPanel3.add(txtDesconto, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 90, -1));

        jLabel14.setText("Frete");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, 40, 20));

        txtFrete.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtFrete.setForeground(new java.awt.Color(0, 0, 255));
        txtFrete.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtFrete.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFreteFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFreteFocusLost(evt);
            }
        });
        txtFrete.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFreteKeyPressed(evt);
            }
        });
        jPanel3.add(txtFrete, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, 90, -1));

        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N
        txtTotal.setOpaque(false);
        jPanel3.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, 140, 50));

        getContentPane().add(jPanel3);
        jPanel3.setBounds(290, 330, 550, 190);

        jLabel10.setText("Observações / Ocorrências");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(10, 450, 170, 14);

        txtObservacao.setColumns(20);
        txtObservacao.setLineWrap(true);
        txtObservacao.setRows(5);
        txtObservacao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtObservacaoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtObservacaoFocusLost(evt);
            }
        });
        jScrollPane2.setViewportView(txtObservacao);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 470, 270, 60);

        try {
            txtDataEntrega.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataEntrega.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDataEntregaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDataEntregaFocusLost(evt);
            }
        });
        getContentPane().add(txtDataEntrega);
        txtDataEntrega.setBounds(90, 420, 90, 20);

        try {
            txtHoraEntrega.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtHoraEntrega.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtHoraEntregaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtHoraEntregaFocusLost(evt);
            }
        });
        getContentPane().add(txtHoraEntrega);
        txtHoraEntrega.setBounds(220, 420, 60, 20);

        setBounds(0, 0, 863, 575);
    }// </editor-fold>//GEN-END:initComponents

    private void txtDataPedidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDataPedidoKeyPressed
        try {
            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                txtHoraPedido.requestFocusInWindow();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtDataPedidoKeyPressed

    private void txtDataPedidoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDataPedidoFocusGained
        try {
            txtDataPedido.setBackground(new Color(255, 255, 204));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtDataPedidoFocusGained

    private void txtHoraPedidoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHoraPedidoFocusLost
        try {
            txtHoraPedido.setBackground(Color.WHITE);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtHoraPedidoFocusLost

    private void txtDataPedidoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDataPedidoFocusLost
        try {
            txtDataPedido.setBackground(Color.WHITE);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtDataPedidoFocusLost

    private void txtHoraPedidoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHoraPedidoFocusGained
        try {
            txtHoraPedido.setBackground(new Color(255, 255, 204));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtHoraPedidoFocusGained

    private void txtContatoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtContatoFocusGained
        try {
            txtContato.setBackground(new Color(255, 255, 204));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtContatoFocusGained

    private void txtContatoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtContatoFocusLost
        try {
            txtContato.setBackground(Color.WHITE);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtContatoFocusLost

    private void txtDataEntregaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDataEntregaFocusGained
        try {
            txtDataEntrega.setBackground(new Color(255, 255, 204));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtDataEntregaFocusGained

    private void txtDataEntregaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDataEntregaFocusLost
        try {
            txtDataEntrega.setBackground(Color.WHITE);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtDataEntregaFocusLost

    private void txtHoraEntregaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHoraEntregaFocusGained
        try {
            txtHoraEntrega.setBackground(new Color(255, 255, 204));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtHoraEntregaFocusGained

    private void txtHoraEntregaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHoraEntregaFocusLost
        try {
            txtDataPedido.setBackground(Color.WHITE);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtHoraEntregaFocusLost

    private void txtObservacaoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtObservacaoFocusGained
        try {
            txtObservacao.setBackground(new Color(255, 255, 204));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtObservacaoFocusGained

    private void txtObservacaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtObservacaoFocusLost
        try {
            txtObservacao.setBackground(Color.WHITE);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtObservacaoFocusLost

    private void txtQuantidadeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtQuantidadeFocusGained
        try {
            txtQuantidade.setBackground(new Color(255, 255, 204));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtQuantidadeFocusGained

    private void txtQuantidadeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtQuantidadeFocusLost
        try {
            txtQuantidade.setBackground(Color.WHITE);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtQuantidadeFocusLost

    private void txtSubtotalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSubtotalFocusGained
        try {
            txtSubtotal.setBackground(new Color(255, 255, 204));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtSubtotalFocusGained

    private void txtSubtotalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSubtotalFocusLost
        try {
            txtSubtotal.setBackground(Color.WHITE);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtSubtotalFocusLost

    private void txtDescontoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescontoFocusGained
        try {
            txtDesconto.setBackground(new Color(255, 255, 204));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtDescontoFocusGained

    private void txtDescontoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescontoFocusLost
        try {
            txtDesconto.setBackground(Color.WHITE);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtDescontoFocusLost

    private void txtFreteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFreteFocusGained
        try {
            txtFrete.setBackground(new Color(255, 255, 204));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtFreteFocusGained

    private void txtFreteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFreteFocusLost
        try {
            txtFrete.setBackground(Color.WHITE);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtFreteFocusLost
    public static boolean clientePedido = false;
    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        clientePedido = true;
        new PesquisaDiversosVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void cbxVendedorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbxVendedorFocusGained
        try {

            atendentePedido = objpessoaCTR.ConsultarPessoaCTR("0", "", "FUNCIONARIO", "FUNCIONARIO");
            UtilVIEW.PreencheCombo(atendentePedido, cbxVendedor, 2);
            atendentePedido = null;
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cbxVendedorFocusGained

    private void txtContatoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContatoKeyPressed
        try {
            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                cbxVendedor.requestFocusInWindow();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtContatoKeyPressed

    private void cbxSituacaoPedidoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSituacaoPedidoItemStateChanged
        txtDataEntrega.requestFocusInWindow();
    }//GEN-LAST:event_cbxSituacaoPedidoItemStateChanged

    private void btnSairPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairPedidoActionPerformed
        try {
            //  int confirmaSair = -1;
            confirmaSair = JOptionPane.showConfirmDialog(null, "Deseja sair?",
                    "MENSAGEM", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirmaSair == 0) {
                totalPedido = 0.0;
                finalizarPedido = false;
                clientePedido = false;
                incluirItem = false;
                subtotal = 0.0;
                quantidade = 0.0;
                PrincipalVIEW.compra = false;
                PesquisarPedidosVIEW.codigoAlpha = "";
                this.dispose();
            } else if (confirmaSair == 1) {

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnSairPedidoActionPerformed
    public static boolean incluirItem = false;
    private void btnIncluirProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirProdutoActionPerformed
        try {
            if (PesquisarPedidosVIEW.codigoAlpha.equals("")) {

            } else {
                quantidade = 0.0;
                cont = 0;
                while (cont < tblItensPedido.getRowCount()) {

                    quantidade += Double.parseDouble(String.valueOf(tblItensPedido.getValueAt(
                            cont, 4)).replace(",", "."));
                    cont++;
                }
                subtotal = Double.parseDouble(txtTotal.getText().replace("R$", "").replace(".", "").replace(",", "."));
            }
            incluirItem = true;
            new PesquisaProdutosVIEW(null, true).setVisible(true);
        } catch (NumberFormatException e) {
        }


    }//GEN-LAST:event_btnIncluirProdutoActionPerformed

    private void btnAlterarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarProdutoActionPerformed

        try {
            incluirItem = true;
            RemoverItem();
            subtotal = 0.0;
            quantidade = 0.0;
            cont = 0;
            while (cont < tblItensPedido.getRowCount()) {
                subtotal += Double.parseDouble(String.valueOf(tblItensPedido.getValueAt(
                        cont, 5)).replace("R$", "").replace(".", "").replace(",", "."));
                quantidade += Double.parseDouble(String.valueOf(tblItensPedido.getValueAt(
                        cont, 4)).replace(",", "."));
                cont++;
            }

            txtSubtotal.setText(moedaPedido.format(subtotal));
            txtQuantidade.setText(String.valueOf(quantidade));
            txtTotal.setText(txtSubtotal.getText());
            incluirItem = true;
            new PesquisaProdutosVIEW(null, true).setVisible(true);

        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Selecionar o item!", "MENSAGEM",
                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAlterarProdutoActionPerformed

    private void btnSalvarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarPedidoActionPerformed
        try {
            if (String.valueOf(cbxVendedor.getSelectedItem()).equals("")) {
                JOptionPane.showMessageDialog(null, "Selecionar o atendente!",
                        "MENSAGEM", JOptionPane.WARNING_MESSAGE);
            } else if (rbPedido.isSelected() == true) {
                JOptionPane.showMessageDialog(null, "Selecionar o orçamento!",
                        "MENSAGEM", JOptionPane.WARNING_MESSAGE);
            } else if (incluirItem == false) {
                JOptionPane.showMessageDialog(null, "Incluir os produtos/serviços!",
                        "MENSAGEM", JOptionPane.WARNING_MESSAGE);
            } else if (rbOrcamento.isSelected() == true) {
                dataPedido = new SimpleDateFormat("dd/MM/yyyy").parse(txtDataPedido.getText());
                if (PrincipalVIEW.compra == true) {
                    CadastrarOrcamentoCompraVIEW();
                    UtilVIEW.LimparCampos(jPanel1);
                    UtilVIEW.LimparCampos(jPanel2);
                    UtilVIEW.LimparTabela(tblItensPedido);
                    txtDataEntrega.setText("");
                    txtHoraEntrega.setText("");
                    txtObservacao.setText("");
                    UtilVIEW.FormatarCampo(txtSubtotal, txtDesconto, txtFrete, txtTotal);
                    txtQuantidade.setText("0");
                    ConsultarMaiorCodigoPedido();
                    subtotal = 0.0;
                    quantidade = 0.0;
                } else if (PrincipalVIEW.compra == false) {

                    CadastrarOrcamentoVIEW();
                    CadastrarComissaoOrcamento();
                    subtotal = 0.0;
                    quantidade = 0.0;
                    totalPedido = 0.0;
                    finalizarPedido = false;
                    clientePedido = false;
                    PrincipalVIEW.compra = false;
                    PesquisarPedidosVIEW.codigoAlpha = "";
                    this.dispose();
                    ImprimirOrcamento();
                }

            }

        } catch (HeadlessException | ParseException e) {
        }
    }//GEN-LAST:event_btnSalvarPedidoActionPerformed
    public static boolean finalizarPedido = false;
    private void btnFinalizarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarPedidoActionPerformed
        try {
            if (txtComprador.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Pesquisar o "+mensagem, "MENSAGEM", JOptionPane.WARNING_MESSAGE);
                mensagem = "";
            } else {
                if (incluirItem == false) {
                    JOptionPane.showMessageDialog(null, "Incluir os produtos/serviços!",
                            "MENSAGEM", JOptionPane.WARNING_MESSAGE);
                } else if (rbPedido.isSelected() == true) {
                    finalizarPedido = true;
                    dataPedido = new SimpleDateFormat("dd/MM/yyyy").parse(txtDataPedido.getText());
                    if (PrincipalVIEW.compra == false) {
                        CadastrarVendaVIEW();
                        CadastrarComissao();
                        if (PesquisarPedidosVIEW.codigoAlpha.equals("")) {

                        } else {
                            AtualizarOrcamento();

                        }
                    } else if (PrincipalVIEW.compra == true) {
                        CadastrarCompraVIEW();
                        if (PesquisarPedidosVIEW.codigoAlpha.equals("")) {

                        } else {
                            AtualizarOrcamentoCompra();

                        }
                    }

                    totalPedido = 0.0;
                    totalPedido
                            = Double.parseDouble(txtTotal.getText().replace("R$", "").
                                    replace(".", "").replace(",", "."));
                    subtotal = 0.0;
                    quantidade = 0.0;
                    PesquisarPedidosVIEW.codigoAlpha = "";
                    AutomaticoVIEW.codigoBarras = "";
                    PesquisarPedidosVIEW.codigoAlpha = txtPedido.getText();
                    this.dispose();
                    new FinalizarAtendimentoVIEW(null, true).setVisible(true);
                } else if (rbPedido.isSelected() == false) {
                    JOptionPane.showMessageDialog(null, "Selecionar o pedido de venda!",
                            "MENSAGEM", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (NumberFormatException | HeadlessException | ParseException e) {
        }
    }//GEN-LAST:event_btnFinalizarPedidoActionPerformed

    private void btnFormaPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFormaPagamentoActionPerformed
        try {
            if (incluirItem == false) {
                JOptionPane.showMessageDialog(null, "Incluir os produtos/serviços!",
                        "MENSAGEM", JOptionPane.WARNING_MESSAGE);
            } else if (rbPedido.isSelected() == true) {
                finalizarPedido = true;
                dataPedido = new SimpleDateFormat("dd/MM/yyyy").parse(txtDataPedido.getText());
                if (PrincipalVIEW.compra == false) {
                    CadastrarVendaVIEW();
                    CadastrarComissao();
                    if (PesquisarPedidosVIEW.codigoAlpha.equals("")) {

                    } else {
                        AtualizarOrcamento();
                        PesquisarPedidosVIEW.codigoAlpha = "";
                    }
                } else if (PrincipalVIEW.compra == true) {
                    CadastrarCompraVIEW();
                }

                totalPedido = 0.0;
                totalPedido
                        = Double.parseDouble(txtTotal.getText().replace("R$", "").
                                replace(".", "").replace(",", "."));
                subtotal = 0.0;
                quantidade = 0.0;
                PesquisarPedidosVIEW.codigoAlpha = "";
                PesquisarPedidosVIEW.codigoAlpha = txtPedido.getText();
                this.dispose();
                new FinalizarAtendimentoVIEW(null, true).setVisible(true);
            } else if (rbPedido.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "Selecionar o pedido de venda!",
                        "MENSAGEM", JOptionPane.WARNING_MESSAGE);
            }

        } catch (NumberFormatException | HeadlessException | ParseException e) {
        }
    }//GEN-LAST:event_btnFormaPagamentoActionPerformed

    private void txtDescontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescontoKeyPressed
        try {
            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                desconto = Double.parseDouble(txtDesconto.getText().replace("R$", "").replace(",", "."));
                totalPedido = Double.parseDouble(txtTotal.getText().replace("R$", "").
                        replace(".", "").replace(",", "."));
                resultado = (totalPedido - desconto);
                txtTotal.setText(moedaPedido.format(resultado));
                desconto = 0.0;
                totalPedido = 0.0;
                resultado = 0.0;
                txtFrete.requestFocusInWindow();
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtDescontoKeyPressed

    private void txtFreteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFreteKeyPressed
        try {
            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                frete = 0.0;
                totalPedido = 0.0;
                resultado = 0.0;
                frete = Double.parseDouble(txtFrete.getText().replace("R$", "").replace(",", "."));
                totalPedido = Double.parseDouble(txtTotal.getText().replace("R$", "").
                        replace(".", "").replace(",", "."));
                resultado = (totalPedido + frete);
                txtTotal.setText(moedaPedido.format(resultado));
                frete = 0.0;
                totalPedido = 0.0;
                resultado = 0.0;
                //txtTotal.requestFocusInWindow();
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtFreteKeyPressed

    private void btnExcluirProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirProdutoActionPerformed
        try {
            RemoverItem();
            subtotal = 0.0;
            quantidade = 0.0;
            cont = 0;
            while (cont < tblItensPedido.getRowCount()) {
                subtotal += Double.parseDouble(String.valueOf(tblItensPedido.getValueAt(
                        cont, 5)).replace("R$", "").replace(".", "").replace(",", "."));
                quantidade += Double.parseDouble(String.valueOf(tblItensPedido.getValueAt(
                        cont, 4)).replace(",", "."));
                cont++;
            }

            txtSubtotal.setText(moedaPedido.format(subtotal));
            txtQuantidade.setText(String.valueOf(quantidade));
            txtTotal.setText(txtSubtotal.getText());

        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Selecionar o item!", "MENSAGEM",
                    JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btnExcluirProdutoActionPerformed

    private void rbPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPedidoActionPerformed
        txtDataPedido.requestFocusInWindow();
    }//GEN-LAST:event_rbPedidoActionPerformed

    private void rbOrcamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbOrcamentoActionPerformed
        txtDataPedido.requestFocusInWindow();
    }//GEN-LAST:event_rbOrcamentoActionPerformed

    private void btnImprimirPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirPedidoActionPerformed
        try {

            if (PrincipalVIEW.venda == true) {
                if (rbOrcamento.isSelected() == false && rbPedido.isSelected() == false) {
                    JOptionPane.showMessageDialog(null, "Orçamento ou Pedido?", "MENSAGEM", JOptionPane.WARNING_MESSAGE);
                } else {
                    this.dispose();
                    if (rbPedido.isSelected() == true) {
                        VendaVIEW.finalizarPedido = true;
                        cancelarPedido = true;
                        pedidoFinalizar = true;
                        compra = false;
                        situacaoPedido = "Finalizado";
                    } else if (rbOrcamento.isSelected() == true) {
                        situacaoPedido = "Pendente";
                        cancelarPedido = false;
                        compra = false;
                        pedidoFinalizar = true;
                        VendaVIEW.finalizarPedido = true;
                    }

                    new PesquisarPedidosVIEW(null, true).setVisible(true);
                }

            } else if (PrincipalVIEW.venda == false) {

            }

        } catch (HeadlessException e) {
        }
    }//GEN-LAST:event_btnImprimirPedidoActionPerformed

    private void btnAutomaticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAutomaticoActionPerformed
        AutomaticoVIEW.codigoBarras = "";
        incluirItem = true;
        new AutomaticoVIEW(null, true).setVisible(true);
    }//GEN-LAST:event_btnAutomaticoActionPerformed

    private void CadastrarVendaVIEW() {
        try {
            VendaCTR objvendaCTR = new VendaCTR();
            ItensVendaCTR objitensCTR = new ItensVendaCTR();
            if (objvendaCTR.CadastrarVendaCTR(pedidoCliente, "0", txtDesconto.getText(),
                    txtTotal.getText(), String.valueOf(cbxVendedor.getSelectedItem()),
                    "", String.valueOf(cbxSituacaoPedido.getSelectedItem()),
                    "0", txtDataEntrega.getText(), txtHoraEntrega.getText(),
                    new SimpleDateFormat("yyyy-MM-dd").format(dataPedido),
                    txtHoraPedido.getText(), txtObservacao.getText())) {
                int linha = 0;
                while (linha < tblItensPedido.getRowCount()) {
                    objitensCTR.CadastrarItensVendaCTR(
                            String.valueOf(tblItensPedido.getValueAt(linha, 1)),
                            String.valueOf(tblItensPedido.getValueAt(linha, 4)),
                            String.valueOf(tblItensPedido.getValueAt(linha, 3)),
                            String.valueOf(tblItensPedido.getValueAt(linha, 5)));
                    linha++;
                }

                JOptionPane.showMessageDialog(null, "Salvo com sucesso!", "MENSAGEM",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Pedido!",
                    "MENSAGEM", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void AtualizarOrcamento() {
        try {
            objorcamentoCTR.CancelarOrcamentoCTR("Finalizado", PesquisarPedidosVIEW.codigoAlpha);
        } catch (Exception e) {
        }
    }

    private void AtualizarOrcamentoCompra() {
        try {
            OrcamentoCompraCTR objorcamentoCompraCTR = new OrcamentoCompraCTR();
            objorcamentoCompraCTR.CancelarOrcamentoCompraCTR("Finalizado", PesquisarPedidosVIEW.codigoAlpha);
        } catch (Exception e) {
        }
    }

    private void CadastrarComissao() {
        try {
            ComissaoCTR objcomissao = new ComissaoCTR();
            while (comissaoPercorre < tblItensPedido.getRowCount()) {
                objcomissao.CadastrarComissaoCTR(String.valueOf(
                        tblItensPedido.getValueAt(comissaoPercorre, 7)),
                        String.valueOf(tblItensPedido.getValueAt(comissaoPercorre, 1)),
                        String.valueOf(tblItensPedido.getValueAt(comissaoPercorre, 4)),
                        String.valueOf(tblItensPedido.getValueAt(comissaoPercorre, 6)));
                comissaoPercorre++;
            }
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarComissao " + e.getMessage());
        }
    }

    private void CadastrarCompraVIEW() {
        try {
            CompraCTR objcompraCTR = new CompraCTR();
            ItensCompraCTR objitenscompraCTR = new ItensCompraCTR();
            if (objcompraCTR.CadastrarCompraCTR(
                    txtDesconto.getText(), txtTotal.getText(),
                    String.valueOf(cbxVendedor.getSelectedItem()),
                    pedidoCliente,
                    String.valueOf(cbxSituacaoPedido.getSelectedItem()),
                    txtDataEntrega.getText(), txtHoraEntrega.getText(),
                    new SimpleDateFormat("yyyy-MM-dd").format(dataPedido),
                    txtHoraPedido.getText(), txtObservacao.getText())) {
                int itens = 0;
                while (itens < tblItensPedido.getRowCount()) {
                    objitenscompraCTR.CadastrarItensCompraCTR(
                            String.valueOf(tblItensPedido.getValueAt(itens, 1)),
                            String.valueOf(tblItensPedido.getValueAt(itens, 4)),
                            String.valueOf(tblItensPedido.getValueAt(itens, 3)),
                            String.valueOf(tblItensPedido.getValueAt(itens, 5)));
                    itens++;
                }
                JOptionPane.showMessageDialog(null, "Salvo com sucesso!", "MENSAGEM",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Compra!",
                    "MENSAGEM", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void ImprimirOrcamento() {
        try {

            ResultSet imprimi = null;
            imprimi = objorcamentoCTR.ConsultarMaiorCodigoOrcamentoCTR();
            if (imprimi.next()) {
                objutil.ImprimirOrcamento(imprimi.getString(1));
            }
        } catch (Exception e) {
        }
    }

    private void CadastrarOrcamentoVIEW() {
        try {

            ItensOrcamentoCTR objitensCTR = new ItensOrcamentoCTR();
            if (objorcamentoCTR.CadastrarOrcamentoCTR(pedidoCliente, "0",
                    txtDesconto.getText(), txtTotal.getText(),
                    String.valueOf(cbxVendedor.getSelectedItem()), "",
                    String.valueOf(cbxSituacaoPedido.getSelectedItem()),
                    "0", txtDataEntrega.getText(), txtHoraEntrega.getText(),
                    txtObservacao.getText(),
                    new SimpleDateFormat("yyyy-MM-dd").format(dataPedido),
                    txtHoraPedido.getText())) {
                int row = 0;
                while (row < tblItensPedido.getRowCount()) {
                    objitensCTR.CadastrarItensOrcamentoCTR(
                            String.valueOf(tblItensPedido.getValueAt(row, 1)),
                            String.valueOf(tblItensPedido.getValueAt(row, 4)),
                            String.valueOf(tblItensPedido.getValueAt(row, 3)),
                            String.valueOf(tblItensPedido.getValueAt(row, 5)));
                    row++;
                }
                JOptionPane.showMessageDialog(null, "Salvo com sucesso!", "MENSAGEM",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Orcamento!",
                    "MENSAGEM", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void CadastrarOrcamentoCompraVIEW() {
        try {
            OrcamentoCompraCTR objorcamentoCompraCTR = new OrcamentoCompraCTR();
            ItensOrcamentoCompraCTR objitensCTR = new ItensOrcamentoCompraCTR();
            if (objorcamentoCompraCTR.CadastrarOrcamentoCompraCTR(pedidoCliente, "0",
                    txtDesconto.getText(), txtTotal.getText(),
                    String.valueOf(cbxVendedor.getSelectedItem()), "",
                    String.valueOf(cbxSituacaoPedido.getSelectedItem()),
                    "0", txtDataEntrega.getText(), txtHoraEntrega.getText(),
                    txtObservacao.getText(),
                    new SimpleDateFormat("yyyy-MM-dd").format(dataPedido),
                    txtHoraPedido.getText())) {
                int row = 0;
                while (row < tblItensPedido.getRowCount()) {
                    objitensCTR.CadastrarItensOrcamentoCompraCTR(
                            String.valueOf(tblItensPedido.getValueAt(row, 1)),
                            String.valueOf(tblItensPedido.getValueAt(row, 4)),
                            String.valueOf(tblItensPedido.getValueAt(row, 3)),
                            String.valueOf(tblItensPedido.getValueAt(row, 5)));
                    row++;
                }
                JOptionPane.showMessageDialog(null, "Salvo com sucesso!", "MENSAGEM",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Orcamento Compra!",
                    "MENSAGEM", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void CadastrarComissaoOrcamento() {
        try {
            ComissaoOrcamentoCTR objcomissaoOrcamento = new ComissaoOrcamentoCTR();
            while (comissaoPercorre < tblItensPedido.getRowCount()) {
                objcomissaoOrcamento.CadastrarComissaoOrcamentoCTR(String.valueOf(
                        tblItensPedido.getValueAt(comissaoPercorre, 7)),
                        String.valueOf(tblItensPedido.getValueAt(comissaoPercorre, 1)),
                        String.valueOf(tblItensPedido.getValueAt(comissaoPercorre, 4)),
                        String.valueOf(tblItensPedido.getValueAt(comissaoPercorre, 6)));
                comissaoPercorre++;
            }
        } catch (Exception e) {
            System.out.println("Erro ao CadastrarComissaoOrcamento " + e.getMessage());
        }
    }

    private void ConsultarItensOrcamento() {
        try {
            ItensOrcamentoCTR objitensOrcamentoCTR = new ItensOrcamentoCTR();
            ComissaoOrcamentoCTR objcomissaoCTR = new ComissaoOrcamentoCTR();
            ResultSet itens = null, comissaoItens = null;
            itens = objitensOrcamentoCTR.ConsultarItensOrcamentoCTR(PesquisarPedidosVIEW.codigoAlpha);
            comissaoItens = objcomissaoCTR.ConsultarComissaoOrcamentoCTR(PesquisarPedidosVIEW.codigoAlpha);
            RestaurarItensOrcamento(itens, comissaoItens);
        } catch (Exception e) {
            System.out.println("Consultar ItensOrcamento " + e.getMessage());
        }
    }

    private void ConsultarItensOrcamentoCompra() {
        try {
            ItensOrcamentoCompraCTR objitensOrcamentoCTR = new ItensOrcamentoCompraCTR();

            ResultSet itens = null;
            itens = objitensOrcamentoCTR.ConsultarItensOrcamentoCompraCTR(
                    PesquisarPedidosVIEW.codigoAlpha);

            RestaurarItensOrcamentoCompra(itens);
        } catch (Exception e) {
            System.out.println("Consultar ItensOrcamentoCompra" + e.getMessage());
        }
    }

    private void RestaurarItensOrcamento(ResultSet rs, ResultSet comissao) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) tblItensPedido.getModel();
            dtm.setRowCount(0);
            while (rs.next() && comissao.next()) {
                dtm.addRow(new Object[]{false, rs.getString("produto"), rs.getString("nome"),
                    moedaPedido.format(rs.getDouble("unitario")), rs.getString("quantidade"),
                    moedaPedido.format(rs.getDouble("subtotal")), rs.getString("func"),
                    moedaPedido.format(comissao.getDouble("valor"))});
            }
            tblItensPedido.setModel(dtm);
        } catch (Exception e) {
        }
    }

    private void RestaurarItensOrcamentoCompra(ResultSet rs) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) tblItensPedido.getModel();
            dtm.setRowCount(0);
            while (rs.next()) {
                dtm.addRow(new Object[]{false, rs.getString("produto"), rs.getString("nome"),
                    moedaPedido.format(rs.getDouble("unitario")), rs.getString("quantidade"),
                    moedaPedido.format(rs.getDouble("subtotal")), rs.getString("func"), moedaPedido.format(0.0)});
            }
            tblItensPedido.setModel(dtm);
        } catch (Exception e) {
            System.out.println("RestaurarItensOrcamentoCompra " + e.getMessage());
        }
    }

    private void FinalizarOrcamento() {
        try {
            // OrcamentoCTR objorcamentoCTR = new OrcamentoCTR();
            ResultSet consultarOrcamento = null;
            consultarOrcamento = objorcamentoCTR.FecharOrcamentoCTR(PesquisarPedidosVIEW.codigoAlpha);
            if (consultarOrcamento.next()) {
                rbPedido.setSelected(true);
                pedidoCliente = consultarOrcamento.getString("cliente");
                txtComprador.setText(consultarOrcamento.getString("nome"));
                cbxVendedor.removeAllItems();
                cbxVendedor.addItem(consultarOrcamento.getString("funcionario"));
                txtSubtotal.setText(moedaPedido.format(consultarOrcamento.getDouble("total")));
                txtTotal.setText(moedaPedido.format(consultarOrcamento.getDouble("total")));

            }
            ConsultarItensOrcamento();
        } catch (Exception e) {
            System.out.println("Consultar Finalizar " + e.getMessage());
        }
    }

    private void FinalizarOrcamentoCompra() {
        try {
            OrcamentoCompraCTR objorcamentoCompraCTR = new OrcamentoCompraCTR();
            ResultSet consultarOrcamento = null;
            consultarOrcamento = objorcamentoCompraCTR.FecharOrcamentoCompraCTR(
                    PesquisarPedidosVIEW.codigoAlpha);
            if (consultarOrcamento.next()) {
                rbPedido.setSelected(true);
                pedidoCliente = consultarOrcamento.getString("fornecedor");
                txtComprador.setText(consultarOrcamento.getString("nome"));
                cbxVendedor.removeAllItems();
                cbxVendedor.addItem(consultarOrcamento.getString("funcionario"));
                txtSubtotal.setText(moedaPedido.format(consultarOrcamento.getDouble("total")));
                txtTotal.setText(moedaPedido.format(consultarOrcamento.getDouble("total")));

            }
            ConsultarItensOrcamentoCompra();
        } catch (Exception e) {
            System.out.println("Consultar Finalizar " + e.getMessage());
        }
    }

    private void ConsultarMaiorCodigoPedido() {
        try {
            ResultSet maiorCodigo = null;
            if (PrincipalVIEW.compra == false) {
                VendaCTR objvendaCTR = new VendaCTR();
                maiorCodigo = objvendaCTR.ConsultarMaiorCodigoVendaCTR();
            } else if (PrincipalVIEW.compra == true) {
                CompraCTR objcompraCTR = new CompraCTR();
                maiorCodigo = objcompraCTR.ConsultarMaiorCodigoCompraCTR();
            }

            if (maiorCodigo.next()) {
                txtPedido.setText(maiorCodigo.getString(1));
                codigoPedido = maiorCodigo.getString(1);
            } else {
                txtPedido.setText("000000001");
                codigoPedido = txtPedido.getText();
            }
            maiorCodigo = null;
        } catch (Exception e) {
            txtPedido.setText("000000001");
            codigoPedido = txtPedido.getText();
        }
    }

    private void RemoverItem() {
        try {
            DefaultTableModel dtm = (DefaultTableModel) tblItensPedido.getModel();
            dtm.removeRow(tblItensPedido.getSelectedRow());
            tblItensPedido.setModel(dtm);

        } catch (Exception e) {

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
            java.util.logging.Logger.getLogger(VendaVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VendaVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VendaVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VendaVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                VendaVIEW dialog = new VendaVIEW(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAlterarProduto;
    private javax.swing.JButton btnAutomatico;
    private javax.swing.JButton btnExcluirProduto;
    private javax.swing.JButton btnFinalizarPedido;
    private javax.swing.JButton btnFormaPagamento;
    private javax.swing.JButton btnImprimirPedido;
    private javax.swing.JButton btnIncluirProduto;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSairPedido;
    private javax.swing.JButton btnSalvarPedido;
    private javax.swing.JComboBox cbxSituacaoPedido;
    private javax.swing.JComboBox cbxVendedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblComprador;
    private javax.swing.JLabel lblVendedor;
    private javax.swing.ButtonGroup opcaoVendas;
    private javax.swing.JCheckBox rbOrcamento;
    private javax.swing.JCheckBox rbPedido;
    public static javax.swing.JTable tblItensPedido;
    public static javax.swing.JTextField txtComprador;
    public static javax.swing.JTextField txtContato;
    private javax.swing.JFormattedTextField txtDataEntrega;
    private javax.swing.JFormattedTextField txtDataPedido;
    private javax.swing.JTextField txtDesconto;
    private javax.swing.JTextField txtFrete;
    private javax.swing.JFormattedTextField txtHoraEntrega;
    private javax.swing.JFormattedTextField txtHoraPedido;
    private javax.swing.JTextArea txtObservacao;
    private javax.swing.JTextField txtPedido;
    public static javax.swing.JTextField txtQuantidade;
    public static javax.swing.JTextField txtSubtotal;
    public static javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
