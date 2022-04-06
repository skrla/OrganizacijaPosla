/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skrla.controller;

import java.util.List;
import org.hibernate.Session;
import skrla.util.HibernateUtil;
import skrla.util.OrganizacijaException;

/**
 *
 * @author skrla
 */
public abstract class Obrada<T> {
    
    protected Session session;
    protected T entitet;
    
    public abstract List<T> read();
    protected abstract void kontrolaCreate() throws OrganizacijaException;
    protected abstract void kontrolaUpdate() throws OrganizacijaException;
    protected abstract void kontrolaDelete() throws OrganizacijaException;
    
    public Obrada(){
        session = HibernateUtil.getSession();
    }
    
    public T create() throws OrganizacijaException{
        kontrolaCreate();
        save();
        return entitet;
    }
    
    public T update() throws OrganizacijaException{
        kontrolaUpdate();
        save();
        return entitet;
    }
    
    public void delete() throws OrganizacijaException{
        kontrolaDelete();
        session.beginTransaction();
        session.delete(entitet);
        session.getTransaction().commit();
    }
    
    private void save(){
        session.beginTransaction();
        session.save(entitet);
        session.getTransaction().commit();
    }

    public T getEntitet() {
        return entitet;
    }

    public void setEntitet(T entitet) {
        this.entitet = entitet;
    }
}
