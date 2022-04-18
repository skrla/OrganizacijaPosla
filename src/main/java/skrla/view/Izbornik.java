/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package skrla.view;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author skrla
 */
public class Izbornik extends javax.swing.JFrame {

    /**
     * Creates new form Izbornik
     */
    
    private SimpleDateFormat df;
    
    public Izbornik() {
        initComponents();
        postaviIzgled();
    }
    
    private void postaviIzgled() {
        df = new SimpleDateFormat("dd. MM. yyy. HH:mm:ss");
        
        Vrijeme v = new Vrijeme();
        v.start();
    }
    
        private class Vrijeme extends Thread{

        @Override
        public void run() {
            jVrijeme.setText(df.format(new Date()));
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
            run();
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

        jVrijeme = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuPoslovnaJedinica = new javax.swing.JMenuItem();
        jMenuDjelatnik = new javax.swing.JMenuItem();
        jMenuTim = new javax.swing.JMenuItem();
        jMenuPosao = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jVrijeme.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jMenu1.setText("Organizacija Posla");

        miExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        miExit.setText("Izlaz");
        miExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miExitActionPerformed(evt);
            }
        });
        jMenu1.add(miExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Programi");

        jMenuPoslovnaJedinica.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_J, 0));
        jMenuPoslovnaJedinica.setText("Poslovna Jedinica");
        jMenuPoslovnaJedinica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuPoslovnaJedinicaActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuPoslovnaJedinica);

        jMenuDjelatnik.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, 0));
        jMenuDjelatnik.setText("Djelatnik");
        jMenuDjelatnik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuDjelatnikActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuDjelatnik);

        jMenuTim.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, 0));
        jMenuTim.setText("Tim");
        jMenuTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuTimActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuTim);

        jMenuPosao.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, 0));
        jMenuPosao.setText("Posao");
        jMenuPosao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuPosaoActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuPosao);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jVrijeme, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 255, Short.MAX_VALUE)
                .addComponent(jVrijeme, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void miExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miExitActionPerformed
        dispose();
    }//GEN-LAST:event_miExitActionPerformed

    private void jMenuDjelatnikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuDjelatnikActionPerformed
        new DjelatnikFrame().setVisible(true);
    }//GEN-LAST:event_jMenuDjelatnikActionPerformed

    private void jMenuPoslovnaJedinicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPoslovnaJedinicaActionPerformed
        new PoslovnaJedinicaFrame().setVisible(true);
    }//GEN-LAST:event_jMenuPoslovnaJedinicaActionPerformed

    private void jMenuTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuTimActionPerformed
        new TimFrame().setVisible(true);
    }//GEN-LAST:event_jMenuTimActionPerformed

    private void jMenuPosaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPosaoActionPerformed
        new PosaoFrame().setVisible(true);
    }//GEN-LAST:event_jMenuPosaoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuDjelatnik;
    private javax.swing.JMenuItem jMenuPosao;
    private javax.swing.JMenuItem jMenuPoslovnaJedinica;
    private javax.swing.JMenuItem jMenuTim;
    private javax.swing.JLabel jVrijeme;
    private javax.swing.JMenuItem miExit;
    // End of variables declaration//GEN-END:variables


}
