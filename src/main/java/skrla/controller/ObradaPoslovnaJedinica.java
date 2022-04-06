/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skrla.controller;

import java.util.List;
import skrla.model.Djelatnik;
import skrla.model.PoslovnaJedinica;
import skrla.util.OrganizacijaException;

/**
 *
 * @author skrla
 */
public class ObradaPoslovnaJedinica extends Obrada<PoslovnaJedinica>{

    @Override
    public List<PoslovnaJedinica> read() {    
        return session.createQuery("from PoslovnaJedinica").list();
    }


    @Override
    protected void kontrolaCreate() throws OrganizacijaException {
        kontrolaNaziv();
        kontrolaAdresa();
    }

    @Override
    protected void kontrolaUpdate() throws OrganizacijaException {
        kontrolaCreate();
    }

    @Override
    protected void kontrolaDelete() throws OrganizacijaException {
        if(entitet.getDjelatnici() != null || entitet.getDjelatnici().size() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("/n");
            for(Djelatnik d: entitet.getDjelatnici()) {
                sb.append(d.getIme() + " " + d.getPrezime());
                sb.append("/n");
            }
            throw new OrganizacijaException("Ne možete obrisati poslovnu jedinicu dok joj pripadaju djelatnici:" + sb.toString());
        }
    }
    
    private void kontrolaNaziv() throws OrganizacijaException {
        if(entitet.getNazivPoslovneJedinice() != null || entitet.getNazivPoslovneJedinice().trim().isEmpty()) {
            throw new OrganizacijaException("Morate unjeti naziv poslovne jedinice");
        }
    }
    
    private void kontrolaAdresa() throws OrganizacijaException {
        if(entitet.getAdresa() != null || entitet.getAdresa().trim().isEmpty()) {
            throw new OrganizacijaException("Morate unjeti adresu poslovne jedinice");
        }
    }
    
}
