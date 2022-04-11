/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skrla.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import nl.garvelink.iban.IBAN;
import skrla.model.Djelatnik;
import skrla.util.OibValidation;
import skrla.util.OrganizacijaException;

/**
 *
 * @author skrla
 */
public class ObradaDjelatnik extends Obrada<Djelatnik>{

    @Override
    public List<Djelatnik> read() {
        return session.createQuery("from Djelatnik").list();
    }
    
    public List<Djelatnik> read(String uvjet) {
        return session.createQuery("from Djelatnik d "
                + " where concat(d.ime,' ',d.prezime,' ',ifnull(d.oib,'')) "
                + " like :uvjet order by d.prezime, d.ime")
                .setParameter("uvjet","%" + uvjet + "%")
                .setMaxResults(50)
                .list();
    }

    @Override
    protected void kontrolaCreate() throws OrganizacijaException {
        kontrolaOib();
        kontrolaIme();
        kontrolaPrezime();
        kontrolaIBAN();
        kontrolaAdresa();
        kontrolaDatumRodenja();
        kontrolaEmail();
    }

    @Override
    protected void kontrolaUpdate() throws OrganizacijaException {
        kontrolaCreate();
    }

    @Override
    protected void kontrolaDelete() throws OrganizacijaException {
    
    }

    private void kontrolaOib() throws OrganizacijaException {
        if(!OibValidation.checkOIB(entitet.getOib())) {
            throw new OrganizacijaException("OIB nije ispravan!");
        }
    }

    private void kontrolaIme() throws OrganizacijaException {
        if(!entitet.getIme().matches("\\p{L}+")) {
            throw new OrganizacijaException("Ime mora sadržavati samo slova!");
        }
    }

    private void kontrolaPrezime() throws OrganizacijaException {
        if (!entitet.getPrezime().matches("\\p{L}+")) {
            throw new OrganizacijaException("Prezime smije samo sadržavati slova i znak: -!");
        }
    }

    private void kontrolaIBAN() throws OrganizacijaException {
        if (entitet.getIBAN() == null) {
            throw new OrganizacijaException("Niste unjeli IBAN!");
        }
        try {
            IBAN iban = IBAN.valueOf(entitet.getIBAN());
            if (!iban.isSEPA()) {
                throw new OrganizacijaException("IBAN nije ispravan");
            }
        } catch (Exception e) {
            throw new OrganizacijaException("IBAN nije ispravan");
        }
    }

    private void kontrolaAdresa() throws OrganizacijaException {
        if(entitet.getAdresaStanovanja() == null || entitet.getAdresaStanovanja().trim().isEmpty()) {
            throw new OrganizacijaException("Niste unjeli adresu stanovanja!");
        }
    }

    private void kontrolaDatumRodenja() throws OrganizacijaException {
        if (entitet.getDatumRodenja() == null) {
            throw new OrganizacijaException("Morate unjeti datum rođenja!");
        }
 /*       LocalDate current = LocalDate.now();
        Period p = Period.between(entitet.getDatumRodenja(), current);
 */       
    }

    private void kontrolaEmail() throws OrganizacijaException {
        if (entitet.getEmail() == null || entitet.getEmail().trim().isEmpty()) {
            throw new OrganizacijaException("Morate unjeti email!");
        }
        try {
            InternetAddress emailAddr = new InternetAddress(entitet.getEmail());
            emailAddr.validate();
        } catch (AddressException ex) {
            throw new OrganizacijaException("Email nije formalno ispravan");
        }
    }
    
}
