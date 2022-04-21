/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolucaoSistemasVIEW;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Vinicius
 */
public class SituacaoOrdemServicoVIEW extends DefaultTableCellRenderer {

    @Override
    protected void setValue(Object valor) {
        if (valor instanceof ImageIcon) {
            if (valor != null) {
                ImageIcon d = (ImageIcon) valor;
                setIcon(d);
            } else {
                setText("");
                setIcon(null);
            }
        } else {
            super.setValue(valor);
        }
    }

}
