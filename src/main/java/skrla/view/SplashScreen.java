/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package skrla.view;

import javax.swing.JOptionPane;
import org.hibernate.Session;
import skrla.controller.ObradaDjelatnik;
import skrla.util.HibernateUtil;
import skrla.util.UnosPodataka;

/**
 *
 * @author skrla
 */
public class SplashScreen extends javax.swing.JFrame {

    /**
     * Creates new form SplashScreen
     */
    private int i = 0;
    private boolean hibernateGotov;
    
    public SplashScreen() {
        initComponents();
        unosi();
    }
    
     private void unosi(){
        i = 0;
        hibernateGotov = false;
        Ucitanje ucitanje = new Ucitanje();
        ucitanje.start();

        TijekUcitanja tijekUcitanja = new TijekUcitanja();
        tijekUcitanja.start();
    }

    private class TijekUcitanja extends Thread {

        @Override
        public void run() {
            if (hibernateGotov) {
                return;
            }
            try {
                pbUcitanje.setValue(++i);
                Thread.sleep(1000);
                run();
            } catch (InterruptedException ex) {
                
            }
        }

    }

   private class Ucitanje extends Thread {

        @Override
        public void run() {
            Session s = HibernateUtil.getSession();
            if (s.getMetamodel().getEntities().size() > 0) {
                if(new ObradaDjelatnik().read().isEmpty()){
                    UnosPodataka.noviPodaci();
                }
                hibernateGotov = true;
                for (int t = i; t < 100; t++) {
                    try {
                        pbUcitanje.setValue(++i);
                        Thread.sleep(3);
                    } catch (InterruptedException ex) {
                       
                    }
                }

                new Izbornik().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(getRootPane(), "Problem s povezivanje na bazu");
            }
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

        jLabel1 = new javax.swing.JLabel();
        pbUcitanje = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SplashScreen.png"))); // NOI18N
        jLabel1.setLabelFor(jLabel1);
        jLabel1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jLabel1.setFocusable(false);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pbUcitanje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pbUcitanje, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar pbUcitanje;
    // End of variables declaration//GEN-END:variables
}
