/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasVIEW;

import SolucaoSistemasCTR.AgendamentoCTR;
import SolucaoSistemasCTR.PessoaCTR;
import SolucaoSistemasDAO.ConectaBanco;
import java.awt.HeadlessException;
import java.net.URL;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
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
public class PesquisaAgendamentoVIEW extends javax.swing.JDialog {

    public static String codigoAgenda = "";
    AgendamentoCTR objagendaCTR = new AgendamentoCTR();
    ResultSet personagem = null;
    PessoaCTR objpessoaCTR = new PessoaCTR();
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat formatBR = new SimpleDateFormat("dd/MM/yyyy");
    String vet = "";

    /**
     * Creates new form PesquisaAgendamentoVIEW
     */
    public PesquisaAgendamentoVIEW(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle(">>> PESQUISA E CONTROLE DE AGENDAMENTOS <<<");
        UtilVIEW.Icone(this);
        tblAgendamento.getColumnModel().getColumn(4).setMinWidth(200);
        tblAgendamento.getColumnModel().getColumn(5).setMinWidth(200);
        tblAgendamento.getColumnModel().getColumn(6).setMinWidth(200);
        tblAgendamento.getColumnModel().getColumn(7).setMinWidth(100);
        tblAgendamento.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        // tblAgendamento.setDefaultRenderer(Object.class, new CellRenderer());

        rbGeral.setSelected(true);
        ConsultarAgenda("", "", "", "", "", "", "");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoOpcaoPesquisa = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rbGeral = new javax.swing.JCheckBox();
        rbDisponivel = new javax.swing.JCheckBox();
        rbAConfirmar = new javax.swing.JCheckBox();
        rbConfirmado = new javax.swing.JCheckBox();
        rbCancelados = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        txtPesquisaDono = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPesquisaAnimal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbxFiltroAtendente = new javax.swing.JComboBox();
        cbxFiltroVeterinario = new javax.swing.JComboBox();
        btnNovoHorario = new javax.swing.JButton();
        btnExcluirHorario = new javax.swing.JButton();
        btnAgendarHorario = new javax.swing.JButton();
        btnRelatorio = new javax.swing.JButton();
        btnAtendimento = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAgendamento = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtDataInicial = new com.toedter.calendar.JDateChooser();
        txtDataFinal = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setText("Data Inicial:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 10, 100, 14);

        jLabel2.setText("Data Final:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(110, 10, 110, 14);

        grupoOpcaoPesquisa.add(rbGeral);
        rbGeral.setText("Geral");
        rbGeral.setOpaque(false);
        rbGeral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbGeralActionPerformed(evt);
            }
        });
        getContentPane().add(rbGeral);
        rbGeral.setBounds(10, 60, 51, 23);

        grupoOpcaoPesquisa.add(rbDisponivel);
        rbDisponivel.setText("Dispon??vel");
        rbDisponivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDisponivelActionPerformed(evt);
            }
        });
        getContentPane().add(rbDisponivel);
        rbDisponivel.setBounds(70, 60, 73, 23);

        grupoOpcaoPesquisa.add(rbAConfirmar);
        rbAConfirmar.setText("A Confirmar");
        rbAConfirmar.setOpaque(false);
        rbAConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAConfirmarActionPerformed(evt);
            }
        });
        getContentPane().add(rbAConfirmar);
        rbAConfirmar.setBounds(150, 60, 110, 23);

        grupoOpcaoPesquisa.add(rbConfirmado);
        rbConfirmado.setText("Confirmado");
        rbConfirmado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbConfirmadoActionPerformed(evt);
            }
        });
        getContentPane().add(rbConfirmado);
        rbConfirmado.setBounds(70, 80, 81, 23);

        grupoOpcaoPesquisa.add(rbCancelados);
        rbCancelados.setText("Cancelados");
        rbCancelados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCanceladosActionPerformed(evt);
            }
        });
        getContentPane().add(rbCancelados);
        rbCancelados.setBounds(150, 80, 81, 23);

        jLabel3.setText("Pesquisar pelo nome do Dono:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(250, 10, 160, 14);

        txtPesquisaDono.setBackground(new java.awt.Color(255, 255, 204));
        txtPesquisaDono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaDonoKeyReleased(evt);
            }
        });
        getContentPane().add(txtPesquisaDono);
        txtPesquisaDono.setBounds(250, 30, 160, 20);

        jLabel4.setText("Pesquisar pelo nome do Animal:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(420, 10, 170, 14);

        txtPesquisaAnimal.setBackground(new java.awt.Color(255, 255, 204));
        txtPesquisaAnimal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaAnimalKeyReleased(evt);
            }
        });
        getContentPane().add(txtPesquisaAnimal);
        txtPesquisaAnimal.setBounds(420, 30, 160, 20);

        jLabel5.setText("Filtrar por Atentende:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(250, 60, 160, 14);

        jLabel6.setText("Filtrar por Veterin??rio:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(420, 60, 160, 14);

        cbxFiltroAtendente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cbxFiltroAtendente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxFiltroAtendenteItemStateChanged(evt);
            }
        });
        cbxFiltroAtendente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cbxFiltroAtendenteFocusGained(evt);
            }
        });
        getContentPane().add(cbxFiltroAtendente);
        cbxFiltroAtendente.setBounds(250, 80, 160, 20);

        cbxFiltroVeterinario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cbxFiltroVeterinario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxFiltroVeterinarioItemStateChanged(evt);
            }
        });
        cbxFiltroVeterinario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cbxFiltroVeterinarioFocusGained(evt);
            }
        });
        getContentPane().add(cbxFiltroVeterinario);
        cbxFiltroVeterinario.setBounds(420, 80, 160, 20);

        btnNovoHorario.setText("Novo Hor??rio");
        btnNovoHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoHorarioActionPerformed(evt);
            }
        });
        getContentPane().add(btnNovoHorario);
        btnNovoHorario.setBounds(590, 10, 126, 23);

        btnExcluirHorario.setText("Excluir Hor??rio");
        btnExcluirHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirHorarioActionPerformed(evt);
            }
        });
        getContentPane().add(btnExcluirHorario);
        btnExcluirHorario.setBounds(730, 10, 110, 23);

        btnAgendarHorario.setText("Agendar Hor??rio");
        btnAgendarHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendarHorarioActionPerformed(evt);
            }
        });
        getContentPane().add(btnAgendarHorario);
        btnAgendarHorario.setBounds(590, 40, 126, 23);

        btnRelatorio.setText("Relat??rio");
        btnRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelatorioActionPerformed(evt);
            }
        });
        getContentPane().add(btnRelatorio);
        btnRelatorio.setBounds(730, 40, 110, 23);

        btnAtendimento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/atendimentoPequeno.JPG"))); // NOI18N
        btnAtendimento.setText("Atendimento");
        getContentPane().add(btnAtendimento);
        btnAtendimento.setBounds(590, 70, 126, 40);

        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        getContentPane().add(btnSair);
        btnSair.setBounds(730, 70, 110, 23);

        tblAgendamento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblAgendamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Agendado", "Hora", "Dia", "Atendente", "Cliente / Dono", "Animal", "Telefone 1", "N?? Atend", "Realizado", "Hora"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAgendamento.setShowHorizontalLines(false);
        tblAgendamento.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tblAgendamento);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 120, 830, 220);

        jLabel7.setText("Para marcar um hor??rio dispon??vel, selecione e clique em Agendar Hor??rio!");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(10, 350, 370, 14);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/Lupa_32x32.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel8);
        jLabel8.setBounds(210, 14, 40, 40);
        getContentPane().add(txtDataInicial);
        txtDataInicial.setBounds(10, 30, 90, 20);
        getContentPane().add(txtDataFinal);
        txtDataFinal.setBounds(110, 30, 90, 20);

        setBounds(0, 0, 865, 409);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoHorarioActionPerformed
        this.dispose();
        new AgendamentoVIEW(null, true).setVisible(true);
       // ConsultarAgenda("00/00/0000", "00/00/0000", "null", "null", "null", "null", "");
    }//GEN-LAST:event_btnNovoHorarioActionPerformed

    private void rbGeralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbGeralActionPerformed
        ConsultarAgenda("00/00/0000", "00/00/0000", "", "", "", "", "");
    }//GEN-LAST:event_rbGeralActionPerformed

    private void rbDisponivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDisponivelActionPerformed
        ConsultarAgenda("00/00/0000", "00/00/0000", "null", "null", "null", "null", "DISPONIVEL");
    }//GEN-LAST:event_rbDisponivelActionPerformed

    private void rbConfirmadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbConfirmadoActionPerformed
        ConsultarAgenda("00/00/0000", "00/00/0000", "null", "null", "null", "null", "CONFIRMADO");
    }//GEN-LAST:event_rbConfirmadoActionPerformed

    private void rbAConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAConfirmarActionPerformed
        ConsultarAgenda("00/00/0000", "00/00/0000", "null", "null", "null", "null", "CONFIRMAR");
    }//GEN-LAST:event_rbAConfirmarActionPerformed

    private void rbCanceladosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCanceladosActionPerformed
        ConsultarAgenda("00/00/0000", "00/00/0000", "null", "null", "null", "null", "CANCELADO");
    }//GEN-LAST:event_rbCanceladosActionPerformed

    private void btnExcluirHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirHorarioActionPerformed
        try {
            int confirma = -1;
            confirma = JOptionPane.showConfirmDialog(null, "Deseja excluir hor??rio?",
                    "MENSAGEM", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirma == 0) {
                objagendaCTR.ExcluirAgendaCTR(String.valueOf(tblAgendamento.getValueAt(
                        tblAgendamento.getSelectedRow(), 0)));
                JOptionPane.showMessageDialog(null, "Exclu??do com sucesso!",
                        "MENSAGEM", JOptionPane.INFORMATION_MESSAGE);
                ConsultarAgenda("00/00/0000", "00/00/0000", "", "", "", "", "");
            } else if (confirma == 1) {

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Selecionar um hor??rio!", "MENSAGEM",
                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnExcluirHorarioActionPerformed

    private void txtPesquisaDonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaDonoKeyReleased
        try {
            if (txtPesquisaDono.getText().equals("")) {
                ConsultarAgenda("00/00/0000", "00/00/0000", "", "", "", "", "");
            } else {
                ConsultarAgenda("00/00/0000", "00/00/0000", txtPesquisaDono.getText().toUpperCase(),
                        "null", "null", "null", "null");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtPesquisaDonoKeyReleased

    private void txtPesquisaAnimalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaAnimalKeyReleased
        try {
            if (txtPesquisaAnimal.getText().equals("")) {
                ConsultarAgenda("00/00/0000", "00/00/0000", "", "", "", "", "");
            } else {
                ConsultarAgenda("00/00/0000", "00/00/0000", "null",
                        txtPesquisaAnimal.getText().toUpperCase(), "null", "null", "null");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtPesquisaAnimalKeyReleased

    private void cbxFiltroAtendenteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxFiltroAtendenteItemStateChanged
        try {

            ConsultarAgenda("00/00/0000", "00/00/0000", "null",
                    "null", String.valueOf(cbxFiltroAtendente.getSelectedItem()), "null", "null");

        } catch (Exception e) {
        }
    }//GEN-LAST:event_cbxFiltroAtendenteItemStateChanged

    private void cbxFiltroVeterinarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxFiltroVeterinarioItemStateChanged
        ConsultarAgenda("00/00/0000", "00/00/0000", "null",
                "null", "null", String.valueOf(cbxFiltroVeterinario.getSelectedItem()), "null");
        vet = String.valueOf(cbxFiltroVeterinario.getSelectedItem());

    }//GEN-LAST:event_cbxFiltroVeterinarioItemStateChanged

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked

        ConsultarAgenda(format.format(txtDataInicial.getDate()), format.format(txtDataFinal.getDate()),
                "null", "null", "null", "null", "null");
    }//GEN-LAST:event_jLabel8MouseClicked

    private void cbxFiltroAtendenteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbxFiltroAtendenteFocusGained
        try {

            personagem = objpessoaCTR.ConsultarPessoaCTR("0", "", "FUNCIONARIO", "FUNCIONARIO");
            UtilVIEW.PreencheCombo(personagem, cbxFiltroAtendente, 2);
            personagem = null;
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cbxFiltroAtendenteFocusGained

    private void cbxFiltroVeterinarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbxFiltroVeterinarioFocusGained
        try {

            personagem = objpessoaCTR.ConsultarPessoaCTR("0", "", "VETERINARIO", "VETERINARIO");
            UtilVIEW.PreencheCombo(personagem, cbxFiltroVeterinario, 2);
            personagem = null;
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cbxFiltroVeterinarioFocusGained

    private void btnAgendarHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgendarHorarioActionPerformed
        try {
            codigoAgenda = String.valueOf(tblAgendamento.getValueAt(tblAgendamento.getSelectedRow(), 0));
            new AgendamentoVIEW(null, true).setVisible(true);
            ConsultarAgenda("null", "null", "null", "null", "null", "null", "");
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Selecionar o hor??rio!", "MENSAGEM",
                    JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btnAgendarHorarioActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelatorioActionPerformed
        if (txtDataInicial.getDate() == null || txtDataFinal.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Informar as datas!", "MENSAGEM", JOptionPane.WARNING_MESSAGE);
        } else {
            Imprimir();
        }

    }//GEN-LAST:event_btnRelatorioActionPerformed

    private void Imprimir() {
        try {
            int op = -1;
            op = JOptionPane.showConfirmDialog(null, "Impressora esta ligada?", "PERGUNTA",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (op == 0) {
                this.dispose();

                URL arquivo = null;
                ConectaBanco.ConectaPostgres();
                JasperPrint rel = null;
                Map parametros = new HashMap();
                arquivo = getClass().getResource("/Relatorios/Agenda.jasper");
                if (arquivo == null) {
                    System.out.println("ARQUIVO INEXISTENTE");
                } else {                   

                    parametros.put("dataInicial", format.format(txtDataInicial.getDate()));
                    parametros.put("dataFinal", format.format(txtDataFinal.getDate()));
                    parametros.put("dataBR1", formatBR.format(txtDataInicial.getDate()));
                    parametros.put("dataBR2", formatBR.format(txtDataFinal.getDate()));
                    parametros.put("veterinario", vet);

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
            System.out.println("Imprimi " + e.getMessage());
        }
    }

    private void ConsultarAgenda(String... parametros) {
        try {

            ResultSet consulta = null;
            consulta = objagendaCTR.ConsultarAgendaCTR(parametros);
            RestaurarAgenda(consulta);
            consulta = null;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao ConsultarAgenda", "MENSAGEM", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void RestaurarAgenda(ResultSet rs) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) tblAgendamento.getModel();
            dtm.setRowCount(0);
            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getString("codigo"), rs.getString("datas"),
                    rs.getString("hora"), rs.getString("dia"), rs.getString("atendente"),
                    rs.getString("cliente"), rs.getString("nome"), rs.getString("telefone1"),
                    "", "", ""});
            }
            tblAgendamento.setModel(dtm);
        } catch (Exception e) {
            System.out.println("Erro ao Restaurar Agenda " + e.getMessage());
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
            java.util.logging.Logger.getLogger(PesquisaAgendamentoVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PesquisaAgendamentoVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PesquisaAgendamentoVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PesquisaAgendamentoVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                PesquisaAgendamentoVIEW dialog = new PesquisaAgendamentoVIEW(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAgendarHorario;
    private javax.swing.JButton btnAtendimento;
    private javax.swing.JButton btnExcluirHorario;
    private javax.swing.JButton btnNovoHorario;
    private javax.swing.JButton btnRelatorio;
    private javax.swing.JButton btnSair;
    private javax.swing.JComboBox cbxFiltroAtendente;
    private javax.swing.JComboBox cbxFiltroVeterinario;
    private javax.swing.ButtonGroup grupoOpcaoPesquisa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox rbAConfirmar;
    private javax.swing.JCheckBox rbCancelados;
    private javax.swing.JCheckBox rbConfirmado;
    private javax.swing.JCheckBox rbDisponivel;
    private javax.swing.JCheckBox rbGeral;
    private javax.swing.JTable tblAgendamento;
    private com.toedter.calendar.JDateChooser txtDataFinal;
    private com.toedter.calendar.JDateChooser txtDataInicial;
    private javax.swing.JTextField txtPesquisaAnimal;
    private javax.swing.JTextField txtPesquisaDono;
    // End of variables declaration//GEN-END:variables
}
