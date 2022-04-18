/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skrla.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import skrla.model.Posao;

/**
 *
 * @author skrla
 */
public class TablicaPosaoModel extends AbstractTableModel {

    private List<Posao> poslovi;

    public TablicaPosaoModel(List<Posao> poslovi) {
        this.poslovi = poslovi;
    }

    @Override
    public int getRowCount() {
        return poslovi == null ? 0 : poslovi.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Posao posao = poslovi.get(row);
        Date date = posao.getPocetakPosla();
        LocalDate ld = LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
        Object value = "??";
        switch (column) {
            case 0:
                value = posao.getRadniNalog();
                break;
            case 1:
                value = ld;
                break;
            case 2:
                value = posao.getZavrsen() ? "Završen" : "Nezavršen";
                break;
        }
        return value;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Radni nalog";
            case 1:
                return "Početak posla";
            case 2:
                return "Završen";
        }
        return "";
    }

    public Posao getPosaoAt(int row) {
        return poslovi.get(row);
    }

}
