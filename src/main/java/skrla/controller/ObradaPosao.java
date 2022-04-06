/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skrla.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import skrla.model.Posao;
import skrla.util.OrganizacijaException;

/**
 *
 * @author skrla
 */
public class ObradaPosao extends Obrada<Posao> {

    @Override
    public List<Posao> read() {
        return session.createQuery("from Posao").list();
    }

    @Override
    protected void kontrolaCreate() throws OrganizacijaException {
        kontrolaCijena();
        kontrolaOpis();
        kontrolaTim();
        kontrolaKrajPosla();
    }

    @Override
    protected void kontrolaUpdate() throws OrganizacijaException {
        kontrolaCreate();
    }

    @Override
    protected void kontrolaDelete() throws OrganizacijaException {
        if(entitet.getZavrsen()) {
            throw new OrganizacijaException("Ne možete obrisati posao koji je završen!");
        }
    }

    private void kontrolaCijena() throws OrganizacijaException {
        if (entitet.getCijenaPosla() == null || entitet.getCijenaPosla().equals(new BigDecimal(BigInteger.ZERO))) {
            throw new OrganizacijaException("Morate unjeti cijenu posla!");
        }

    }

    private void kontrolaOpis() throws OrganizacijaException {
        if (entitet.getOpisPosla() == null || entitet.getOpisPosla().trim().isEmpty()) {
            throw new OrganizacijaException("Morate unjeti poslove koje obavljate za klijenta!");
        }
    }

    private void kontrolaTim() throws OrganizacijaException {
        if (entitet.getTim() == null) {
            throw new OrganizacijaException("Morate odabrati tim koji će odrađivati posao!");
        }
    }

    private void kontrolaKrajPosla() throws OrganizacijaException {
        if (entitet.getPocetakPosla() == null && entitet.getKrajPosla() != null) {
            throw new OrganizacijaException("Morate prvo postaviti početak posla!");
        } else if (entitet.getKrajPosla() != null) {
            if (!(entitet.getPocetakPosla().compareTo(entitet.getKrajPosla()) <= 0)) {
                throw new OrganizacijaException("Datum kraja posla ne može biti prije datuma početak posla!");
            }
        }
    }

}
