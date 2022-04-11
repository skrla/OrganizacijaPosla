/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skrla.view;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import skrla.model.Djelatnik;

/**
 *
 * @author skrla
 */
public class PrikazDjelatnik extends JLabel implements ListCellRenderer<Djelatnik> {

     public PrikazDjelatnik() {
        setOpaque(true);
    }
     
    @Override
    public Component getListCellRendererComponent(JList<? extends Djelatnik> list,
            Djelatnik value, int index, boolean isSelected, boolean cellHasFocus) {

        if (isSelected) {
            setBackground(Color.BLUE);
            setForeground(Color.WHITE);
        } else {
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);
        }
        
        setText((value.getIme()==null ? "Nepoznato" : value.getIme().equals("") ? "Nepoznato" :  value.getIme())
                + " " + (value.getPrezime()==null ? "Nepoznato" : value.getPrezime()));

        return this;
    }

}
