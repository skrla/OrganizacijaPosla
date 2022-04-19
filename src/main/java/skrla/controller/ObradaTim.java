/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skrla.controller;

import java.util.Date;
import java.util.List;
import skrla.model.Djelatnik;
import skrla.model.Posao;
import skrla.model.Tim;
import skrla.util.OrganizacijaException;

/**
 *
 * @author skrla
 */
public class ObradaTim extends Obrada<Tim>{

    @Override
    public List<Tim> read() {
        return session.createQuery("from Tim t order by t.nazivTima").list();
    }
    
    public List<Tim> read(String naziv) {
        return session.createQuery("from Tim t where"
                + " t.nazivTima like :naziv"
                + " order by t.nazivTima")
                .setParameter("naziv", naziv + "%")
                .list();
    }

    @Override
    protected void kontrolaCreate() throws OrganizacijaException {
        kontrolaNaziv();
    }

    @Override
    protected void kontrolaUpdate() throws OrganizacijaException {
        kontrolaCreate();
    }

    @Override
    protected void kontrolaDelete() throws OrganizacijaException {
        if(entitet.getDjelatnik() != null && entitet.getDjelatnik().size() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("\n ");
            for(Djelatnik d: entitet.getDjelatnik()) {
                sb.append(d.getIme() + " " + d.getPrezime());
                sb.append(" \n");
            }
            throw new OrganizacijaException("Ne možete obrisati tim dok joj pripadaju djelatnici:" + sb.toString());
        }
        if(entitet.getPosao() != null && entitet.getPosao().size() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("/n");
            for(Posao p: entitet.getPosao()) {
                if(p.getZavrsen()) {
                    sb.append(p.getOpisPosla());
                    sb.append("/n");
                }
            }
            if(sb.length() > 0) {
                throw new OrganizacijaException("Ne možete obrisati tim dok ne završi poslove:" + sb.toString());
            }
        }
    }
    
    private void kontrolaNaziv() throws OrganizacijaException {
        if(entitet.getNazivTima() == null || entitet.getNazivTima().trim().isEmpty()) {
            throw new OrganizacijaException("Morate unjeti naziv tima");
        }
    }
    
}
