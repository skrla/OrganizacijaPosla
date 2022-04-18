/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package skrla.view;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import skrla.controller.ObradaDjelatnik;
import skrla.controller.ObradaPoslovnaJedinica;
import skrla.controller.ObradaTim;
import skrla.model.Djelatnik;
import skrla.model.PoslovnaJedinica;
import skrla.util.OrganizacijaException;
import skrla.util.ComparatorOrganizacija;
import skrla.util.ViewUtil;

/**
 *
 * @author skrla
 */
public class PoslovnaJedinicaFrame extends javax.swing.JFrame {

    private ObradaPoslovnaJedinica obradaPoslovnaJedinica;
    private ObradaDjelatnik obradaDjelatnik;

    /**
     * Creates new form PoslovnaJedinicaFrame
     */
    public PoslovnaJedinicaFrame() {
        initComponents();
        ucitajPodatke();
    }

    private void ucitajPodatke() {
        setTitle(ViewUtil.getNaslov("Poslovne jedinice"));
        obradaPoslovnaJedinica = new ObradaPoslovnaJedinica();
        obradaDjelatnik = new ObradaDjelatnik();
        ucitajPoslovneJedinice();
        ucitajPoslovneJedinice1();
        lstDjelatnik.setCellRenderer(new PrikazDjelatnik());
        ucitajDjelatnikeNaPoslovnojJedinici((PoslovnaJedinica) jComboPoslovnaJedinica.getSelectedItem());
        ucitajDjelatnikeNaPoslovnojJedinici1((PoslovnaJedinica) jComboPoslovnaJedinica1.getSelectedItem());
    }

    private void ucitajDjelatnikeNaPoslovnojJedinici(PoslovnaJedinica odabrana) {
        DefaultListModel<Djelatnik> m = new DefaultListModel<>();
        List<Djelatnik> djelatniciNaPoslovnojJednici;
        djelatniciNaPoslovnojJednici = obradaDjelatnik.read();

        Collections.sort(djelatniciNaPoslovnojJednici, new ComparatorOrganizacija());

        if (jComboPoslovnaJedinica.getSelectedIndex() != 0) {
            for (Djelatnik djelatnik : djelatniciNaPoslovnojJednici) {
                if (djelatnik.getPoslovnaJedinica() == odabrana) {
                    m.addElement(djelatnik);
                }
            }
        } else {
            for (Djelatnik djelatnik : djelatniciNaPoslovnojJednici) {
                if (djelatnik.getPoslovnaJedinica() == null) {
                    m.addElement(djelatnik);
                }
            }
        }
        lstDjelatnik.setModel(m);
    }

    private void ucitajDjelatnikeNaPoslovnojJedinici1(PoslovnaJedinica odabrana) {
        DefaultListModel<Djelatnik> m = new DefaultListModel<>();
        List<Djelatnik> djelatniciNaPoslovnojJednici;
        djelatniciNaPoslovnojJednici = obradaDjelatnik.read();

        Collections.sort(djelatniciNaPoslovnojJednici, new ComparatorOrganizacija());

        if (jComboPoslovnaJedinica1.getSelectedIndex() != 0) {
            for (Djelatnik djelatnik : djelatniciNaPoslovnojJednici) {
                if (djelatnik.getPoslovnaJedinica() == odabrana) {
                    m.addElement(djelatnik);
                }
            }
        } else {
            for (Djelatnik djelatnik : djelatniciNaPoslovnojJednici) {
                if (djelatnik.getPoslovnaJedinica() == null) {
                    m.addElement(djelatnik);
                }
            }
        }
        lstDjelatnik1.setModel(m);
    }

    private void ucitajPoslovneJedinice() {
        DefaultComboBoxModel<PoslovnaJedinica> ms = new DefaultComboBoxModel<>();
        PoslovnaJedinica poslovnaJedinica = new PoslovnaJedinica();
        poslovnaJedinica.setSifraPoslovneJedinice(Integer.valueOf(0));
        poslovnaJedinica.setNazivPoslovneJedinice("Djelatnik nema poslovnu jedinicu");
        ms.addElement(poslovnaJedinica);
        if (jComboPoslovnaJedinica1 != null || jComboPoslovnaJedinica1.getSelectedIndex() != 0) {
            new ObradaPoslovnaJedinica().read().forEach(s -> {
                if (!s.equals(jComboPoslovnaJedinica1.getSelectedItem())) {
                    ms.addElement(s);
                }
            });
        } else {
            new ObradaPoslovnaJedinica().read().forEach(s -> {
                ms.addElement(s);
            });
        }

        jComboPoslovnaJedinica.setModel(ms);
    }

    private void ucitajPoslovneJedinice1() {
        DefaultComboBoxModel<PoslovnaJedinica> ms = new DefaultComboBoxModel<>();
        PoslovnaJedinica poslovnaJedinica = new PoslovnaJedinica();
        poslovnaJedinica.setSifraPoslovneJedinice(Integer.valueOf(0));
        poslovnaJedinica.setNazivPoslovneJedinice("Djelatnik nema poslovnu jedinicu");
        ms.addElement(poslovnaJedinica);
        if (jComboPoslovnaJedinica != null || jComboPoslovnaJedinica.getSelectedIndex() != 0) {
            new ObradaPoslovnaJedinica().read().forEach(s -> {
                if (!s.equals(jComboPoslovnaJedinica.getSelectedItem())) {
                    ms.addElement(s);
                }
            });
        } else {
            new ObradaPoslovnaJedinica().read().forEach(s -> {
                ms.addElement(s);
            });
        }

        jComboPoslovnaJedinica1.setModel(ms);
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
        jComboPoslovnaJedinica = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jListDjelatnik = new javax.swing.JScrollPane();
        lstDjelatnik = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        jComboPoslovnaJedinica1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jListDjelatnik1 = new javax.swing.JScrollPane();
        lstDjelatnik1 = new javax.swing.JList<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        btnDodajPoslovnuJedinicu = new javax.swing.JButton();
        txtAdresa = new javax.swing.JTextField();
        btnPromjenaPodatakaPoslovneJedinice = new javax.swing.JButton();
        btnPrebaci = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtNaziv = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnDodaj = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jComboPoslovnaJedinica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboPoslovnaJedinicaActionPerformed(evt);
            }
        });

        jLabel1.setText("Poslovna jedinica:");

        jListDjelatnik.setViewportView(lstDjelatnik);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jListDjelatnik)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jComboPoslovnaJedinica, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboPoslovnaJedinica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jListDjelatnik, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addContainerGap())
        );

        jComboPoslovnaJedinica1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboPoslovnaJedinica1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Poslovna jedinica:");

        jListDjelatnik1.setViewportView(lstDjelatnik1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jListDjelatnik1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jComboPoslovnaJedinica1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboPoslovnaJedinica1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jListDjelatnik1))
        );

        jLabel6.setText("Adresa poslovne jedinice:");

        btnDodajPoslovnuJedinicu.setText("Dodaj poslovnu jedinicu");
        btnDodajPoslovnuJedinicu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDodajPoslovnuJedinicuActionPerformed(evt);
            }
        });

        txtAdresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdresaActionPerformed(evt);
            }
        });

        btnPromjenaPodatakaPoslovneJedinice.setText("Promjeni podatke o poslovnoj jedinici");
        btnPromjenaPodatakaPoslovneJedinice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPromjenaPodatakaPoslovneJediniceActionPerformed(evt);
            }
        });

        btnPrebaci.setText(">>");
        btnPrebaci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrebaciActionPerformed(evt);
            }
        });

        jLabel3.setText("Dodaj djelatnike:");

        txtNaziv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNazivActionPerformed(evt);
            }
        });

        jLabel5.setText("Naziv poslovne jedinice:");

        jLabel4.setText("Prebaci djelatnike:");

        btnDodaj.setText("<<");
        btnDodaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDodajActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDodaj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPrebaci, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel5)
                        .addComponent(txtNaziv)
                        .addComponent(jLabel6)
                        .addComponent(txtAdresa)
                        .addComponent(btnDodajPoslovnuJedinicu, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnPromjenaPodatakaPoslovneJedinice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(txtNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(txtAdresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnPromjenaPodatakaPoslovneJedinice)
                .addGap(18, 18, 18)
                .addComponent(btnDodajPoslovnuJedinicu)
                .addGap(35, 35, 35)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrebaci)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDodaj)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboPoslovnaJedinicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboPoslovnaJedinicaActionPerformed
        ucitajDjelatnikeNaPoslovnojJedinici((PoslovnaJedinica) jComboPoslovnaJedinica.getSelectedItem());
        obradaPoslovnaJedinica.setEntitet((PoslovnaJedinica) jComboPoslovnaJedinica.getSelectedItem());
        txtNaziv.setText(obradaPoslovnaJedinica.getEntitet().getNazivPoslovneJedinice());
        txtAdresa.setText(obradaPoslovnaJedinica.getEntitet().getAdresa());
    }//GEN-LAST:event_jComboPoslovnaJedinicaActionPerformed

    private void jComboPoslovnaJedinica1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboPoslovnaJedinica1ActionPerformed
        ucitajDjelatnikeNaPoslovnojJedinici1((PoslovnaJedinica) jComboPoslovnaJedinica1.getSelectedItem());
    }//GEN-LAST:event_jComboPoslovnaJedinica1ActionPerformed

    private void btnPrebaciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrebaciActionPerformed
        DefaultListModel<Djelatnik> m = (DefaultListModel<Djelatnik>) lstDjelatnik.getModel();
        if (jComboPoslovnaJedinica1.getSelectedIndex() != 0) {
            obradaPoslovnaJedinica.setEntitet((PoslovnaJedinica) jComboPoslovnaJedinica1.getSelectedItem());
            var e = obradaPoslovnaJedinica.getEntitet();
            for (Djelatnik d : lstDjelatnik.getSelectedValuesList()) {
                m.removeElement(d);
                d.setPoslovnaJedinica(e);
                obradaDjelatnik.setEntitet(d);
                spremi();

            }
        } else {
            for (Djelatnik d : lstDjelatnik.getSelectedValuesList()) {
                m.removeElement(d);
                d.setPoslovnaJedinica(null);
                obradaDjelatnik.setEntitet(d);
                spremi();
            }
        }
        ucitajDjelatnikeNaPoslovnojJedinici((PoslovnaJedinica) jComboPoslovnaJedinica.getSelectedItem());
        ucitajDjelatnikeNaPoslovnojJedinici1((PoslovnaJedinica) jComboPoslovnaJedinica1.getSelectedItem());
    }//GEN-LAST:event_btnPrebaciActionPerformed

    private void btnDodajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDodajActionPerformed
        DefaultListModel<Djelatnik> m = (DefaultListModel<Djelatnik>) lstDjelatnik1.getModel();
        if (jComboPoslovnaJedinica.getSelectedIndex() != 0) {
            obradaPoslovnaJedinica.setEntitet((PoslovnaJedinica) jComboPoslovnaJedinica.getSelectedItem());
            var e = obradaPoslovnaJedinica.getEntitet();
            for (Djelatnik d : lstDjelatnik1.getSelectedValuesList()) {
                m.removeElement(d);
                d.setPoslovnaJedinica(e);
                obradaDjelatnik.setEntitet(d);
                spremi();
            }
        } else {
            for (Djelatnik d : lstDjelatnik1.getSelectedValuesList()) {
                m.removeElement(d);
                d.setPoslovnaJedinica(null);
                obradaDjelatnik.setEntitet(d);
                spremi();
            }
        }
        ucitajDjelatnikeNaPoslovnojJedinici((PoslovnaJedinica) jComboPoslovnaJedinica.getSelectedItem());
        ucitajDjelatnikeNaPoslovnojJedinici1((PoslovnaJedinica) jComboPoslovnaJedinica1.getSelectedItem());

    }//GEN-LAST:event_btnDodajActionPerformed

    private void btnDodajPoslovnuJedinicuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDodajPoslovnuJedinicuActionPerformed
        obradaPoslovnaJedinica.setEntitet(new PoslovnaJedinica());
        obradaPoslovnaJedinica.getEntitet().setNazivPoslovneJedinice(txtNaziv.getText());
        obradaPoslovnaJedinica.getEntitet().setAdresa(txtAdresa.getText());
        try {
            obradaPoslovnaJedinica.create();
        } catch (OrganizacijaException ex) {
            JOptionPane.showMessageDialog(getRootPane(), ex.getPoruka());
        }
        ucitajPoslovneJedinice();
        ucitajPoslovneJedinice1();
    }//GEN-LAST:event_btnDodajPoslovnuJedinicuActionPerformed

    private void btnPromjenaPodatakaPoslovneJediniceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPromjenaPodatakaPoslovneJediniceActionPerformed
        if (jComboPoslovnaJedinica.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(getRootPane(), "Prvo odaberite stavku");
            return;
        }

        obradaPoslovnaJedinica.setEntitet((PoslovnaJedinica) jComboPoslovnaJedinica.getSelectedItem());
        obradaPoslovnaJedinica.getEntitet().setNazivPoslovneJedinice(txtNaziv.getText().trim());
        obradaPoslovnaJedinica.getEntitet().setAdresa(txtAdresa.getText());
        try {
            obradaPoslovnaJedinica.update();
        } catch (OrganizacijaException ex) {
            JOptionPane.showMessageDialog(getRootPane(), ex.getPoruka());
        }
        ucitajPoslovneJedinice();
        ucitajPoslovneJedinice1();
    }//GEN-LAST:event_btnPromjenaPodatakaPoslovneJediniceActionPerformed

    private void txtNazivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNazivActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNazivActionPerformed

    private void txtAdresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdresaActionPerformed

    private void spremi() {
        try {
            obradaDjelatnik.update();

        } catch (OrganizacijaException ex) {
            JOptionPane.showMessageDialog(getRootPane(), ex.getPoruka());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDodaj;
    private javax.swing.JButton btnDodajPoslovnuJedinicu;
    private javax.swing.JButton btnPrebaci;
    private javax.swing.JButton btnPromjenaPodatakaPoslovneJedinice;
    private javax.swing.JComboBox<PoslovnaJedinica> jComboPoslovnaJedinica;
    private javax.swing.JComboBox<PoslovnaJedinica> jComboPoslovnaJedinica1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jListDjelatnik;
    private javax.swing.JScrollPane jListDjelatnik1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JList<Djelatnik> lstDjelatnik;
    private javax.swing.JList<Djelatnik> lstDjelatnik1;
    private javax.swing.JTextField txtAdresa;
    private javax.swing.JTextField txtNaziv;
    // End of variables declaration//GEN-END:variables

}
