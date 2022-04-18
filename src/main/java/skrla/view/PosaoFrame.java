/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package skrla.view;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import skrla.controller.ObradaPosao;
import skrla.controller.ObradaTim;
import skrla.model.Posao;
import skrla.model.Tim;
import skrla.util.TablicaPosaoModel;
import skrla.util.ViewUtil;
import skrla.model.Djelatnik;
import skrla.util.OrganizacijaException;

/**
 *
 * @author skrla
 */
public class PosaoFrame extends javax.swing.JFrame {

    private ObradaPosao obradaPosao;
    private ObradaTim obradaTim;
    TablicaPosaoModel m;
    private DecimalFormat nf;
    List<Posao> poslovi;

    /**
     * Creates new form PosaoFrame
     */
    public PosaoFrame() {
        initComponents();
        ucitajPodatke();
    }

    private void ucitajPodatke() {
        obradaPosao = new ObradaPosao();
        obradaTim = new ObradaTim();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("hr", "HR"));
        nf = new DecimalFormat("###,###.00", symbols);
        DatePickerSettings dps = new DatePickerSettings(new Locale("hr", "HR"));
        dps.setFormatForDatesCommonEra("dd.MM.yyyy");
        dps.setTranslationClear("Očisti");
        dps.setTranslationToday("Danas");
        DatePickerSettings dps1 = new DatePickerSettings(new Locale("hr", "HR"));
        dps1.setFormatForDatesCommonEra("dd.MM.yyyy");
        dps1.setTranslationClear("Očisti");
        dps1.setTranslationToday("Danas");
        dpDatumPocetka.setSettings(dps);
        dpDatumZavrsetka.setSettings(dps1);
        ucitajPosao();
        ucitajTim();
    }

    private void ucitajPosao() {
        poslovi = obradaPosao.read();
        m = new TablicaPosaoModel(poslovi);
        lstPosao.setModel(m);

    }

    private void postaviPosao(Posao posao) {
        obradaPosao.setEntitet(posao);
        dpDatumPocetka.setDate(dateToLocal(posao.getPocetakPosla()));
        dpDatumZavrsetka.setDate(dateToLocal(posao.getKrajPosla()));
        txtCijenaPosla.setText(nf.format(posao.getCijenaPosla()));
        txtOpisPosla.setText(posao.getOpisPosla());
        txtNapomena.setText(posao.getNapomena());
        cbTim.setSelectedItem(posao.getTim());
    }

    private void ucitajTim() {
        DefaultComboBoxModel<Tim> tm = new DefaultComboBoxModel<>();
        Tim tim = new Tim();
        tim.setSifraTima(0);
        tim.setNazivTima("Nije odabrano");
        tm.addElement(tim);
        new ObradaTim().read().forEach(s -> {
            tm.addElement(s);
        });
        cbTim.setModel(tm);

    }

    private void promjenaPosla(Posao posao) {
        obradaPosao.setEntitet(posao);
        obradiPodatke();
        try {
            obradaPosao.update();
        } catch (OrganizacijaException ex) {
            JOptionPane.showMessageDialog(getRootPane(), ex.getPoruka());
        }
    }

    private void stvaranjePosla() {
        obradaPosao.setEntitet(new Posao());
        obradiPodatke();
        obradaPosao.getEntitet().setRadniNalog(generirajRadniNalog(obradaPosao.getEntitet().getPocetakPosla()));
        String radni = obradaPosao.getEntitet().getRadniNalog();
        try {
            obradaPosao.create();
        } catch (OrganizacijaException ex) {
            JOptionPane.showMessageDialog(getRootPane(), ex.getPoruka());
        }
    }

    private String generirajRadniNalog(Date datum) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = datum;
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String year = df.format(d);
        return poslovi.get(0).getSifraPosla() + 1 + "/" + year;
    }

    private void obradiPodatke() {
        obradaPosao.getEntitet().setPocetakPosla(datePickerToDate(dpDatumPocetka));
        obradaPosao.getEntitet().setKrajPosla(datePickerToDate(dpDatumZavrsetka));
        try {
            obradaPosao.getEntitet().setCijenaPosla(new BigDecimal(nf.parse(txtCijenaPosla.getText()).toString()));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(getRootPane(), "Niste unjeli pravovaljanu cijenu!");
        }
        obradaPosao.getEntitet().setOpisPosla(txtOpisPosla.getText());
        obradaPosao.getEntitet().setNapomena(txtNapomena.getText());
        obradaPosao.getEntitet().setTim((Tim) cbTim.getSelectedItem());
        Date date = new Date(System.currentTimeMillis());
        if (obradaPosao.getEntitet().getKrajPosla() != null) {
            if (obradaPosao.getEntitet().getKrajPosla().compareTo(new Date(System.currentTimeMillis())) <= 0) {
                obradaPosao.getEntitet().setZavrsen(true);
            } else {
                obradaPosao.getEntitet().setZavrsen(false);
            }
        } else {
            obradaPosao.getEntitet().setZavrsen(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstPosao = new javax.swing.JTable();
        btnKreiraj = new javax.swing.JButton();
        btnObrisi = new javax.swing.JButton();
        btnPromejna = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtNapomena = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtOpisPosla = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dpDatumZavrsetka = new com.github.lgooddatepicker.components.DatePicker();
        txtCijenaPosla = new javax.swing.JTextField();
        dpDatumPocetka = new com.github.lgooddatepicker.components.DatePicker();
        jLabel2 = new javax.swing.JLabel();
        cbTim = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        lstDjelatnik = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Poslovi:");

        lstPosao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Radni nalog", "Početak posla", "Završen"
            }
        ));
        lstPosao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstPosaoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lstPosao);

        btnKreiraj.setText("Kreiraj posao");
        btnKreiraj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKreirajActionPerformed(evt);
            }
        });

        btnObrisi.setText("Obriši posao");
        btnObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiActionPerformed(evt);
            }
        });

        btnPromejna.setText("Promjeni podatke o poslu");
        btnPromejna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPromejnaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnKreiraj, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnObrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnPromejna, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnKreiraj)
                    .addComponent(btnObrisi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPromejna)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setText("Opis posla:");

        txtNapomena.setColumns(20);
        txtNapomena.setRows(5);
        jScrollPane3.setViewportView(txtNapomena);

        txtOpisPosla.setColumns(20);
        txtOpisPosla.setLineWrap(true);
        txtOpisPosla.setRows(5);
        txtOpisPosla.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtOpisPosla);

        jLabel6.setText("Napomena za posao:");

        jLabel4.setText("Cijena posla:");

        jLabel3.setText("Datum završetka posla:");

        txtCijenaPosla.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel2.setText("Datum početka posla:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)
                        .addComponent(jScrollPane1)
                        .addComponent(txtCijenaPosla)
                        .addComponent(dpDatumZavrsetka, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dpDatumPocetka, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dpDatumPocetka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dpDatumZavrsetka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCijenaPosla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        cbTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTimActionPerformed(evt);
            }
        });

        jLabel7.setText("Tim na poslu:");

        lstDjelatnik.setToolTipText("");
        jScrollPane4.setViewportView(lstDjelatnik);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTim, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTimActionPerformed
        DefaultListModel<Djelatnik> m = new DefaultListModel<>();
        Tim tim = (Tim) cbTim.getSelectedItem();

        if (cbTim.getSelectedIndex() != 0) {
            for (Djelatnik d : tim.getDjelatnik()) {
                m.addElement(d);
            }
        }
        lstDjelatnik.setModel(m);
    }//GEN-LAST:event_cbTimActionPerformed

    private void lstPosaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstPosaoMouseClicked
        if (lstPosao.getSelectedRow() == -1) {
            return;
        }
        postaviPosao(m.getPosaoAt(lstPosao.getSelectedRow()));
    }//GEN-LAST:event_lstPosaoMouseClicked

    private void btnObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiActionPerformed
        if (obradaPosao.getEntitet() == null) {
            JOptionPane.showMessageDialog(getRootPane(), "Prvo odaberite stavku");
            return;
        }
        if (JOptionPane.showConfirmDialog(
                getRootPane(),
                "Sigurno obrisati: " + obradaPosao.getEntitet().getRadniNalog() + "?",
                "Brisanje",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION) {
            return;
        }

        try {
            obradaPosao.delete();
            ucitajPosao();
        } catch (OrganizacijaException ex) {
            JOptionPane.showMessageDialog(getRootPane(), ex.getPoruka());
        }

    }//GEN-LAST:event_btnObrisiActionPerformed

    private void btnPromejnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPromejnaActionPerformed
        if (obradaPosao.getEntitet() == null) {
            JOptionPane.showMessageDialog(getRootPane(), "Prvo odaberite stavku");
            return;
        }
        promjenaPosla(m.getPosaoAt(lstPosao.getSelectedRow()));
        ucitajPosao();
    }//GEN-LAST:event_btnPromejnaActionPerformed

    private void btnKreirajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKreirajActionPerformed
        stvaranjePosla();
        ucitajPosao();
    }//GEN-LAST:event_btnKreirajActionPerformed

    private LocalDate dateToLocal(Date date) {
        if (date != null) {
            return date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        } else {
            return null;
        }
    }

    private Date datePickerToDate(DatePicker datePicker) {
        if (datePicker.getDate() != null) {
            return Date.from(
                    datePicker.getDate().atStartOfDay().atZone(
                            ZoneId.systemDefault()
                    ).toInstant()
            );
        }
        return null;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKreiraj;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JButton btnPromejna;
    private javax.swing.JComboBox<Tim> cbTim;
    private com.github.lgooddatepicker.components.DatePicker dpDatumPocetka;
    private com.github.lgooddatepicker.components.DatePicker dpDatumZavrsetka;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList<Djelatnik> lstDjelatnik;
    private javax.swing.JTable lstPosao;
    private javax.swing.JTextField txtCijenaPosla;
    private javax.swing.JTextArea txtNapomena;
    private javax.swing.JTextArea txtOpisPosla;
    // End of variables declaration//GEN-END:variables

}
