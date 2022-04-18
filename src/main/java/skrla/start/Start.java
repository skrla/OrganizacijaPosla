/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skrla.start;

import org.hibernate.Session;
import skrla.model.PoslovnaJedinica;
import skrla.util.HibernateUtil;
import skrla.view.SplashScreen;

/**
 *
 * @author skrla
 */
public class Start {
    
    private Session session;
    
    public Start(){
        this.session = HibernateUtil.getSession();
    }
    
    public static void main(String[] args) {
       new Start();
       new SplashScreen().setVisible(true);
    }
}