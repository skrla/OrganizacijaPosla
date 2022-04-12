/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skrla.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import skrla.util.OrganizacijaException;

/**
 *
 * @author skrla
 */
@Getter
@Setter
@Entity
public class Djelatnik {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sifraDjelatnika;
    private String oib;
    private String ime;
    private String prezime;
    private String adresaStanovanja;
    private String IBAN;
    @ManyToOne
    private PoslovnaJedinica poslovnaJedinica;
    private Date datumRodenja;
    private String email;
    @ManyToOne
    private Tim tim;
    private boolean aktivan;
    
    
    @Override
    public String toString() {
        return ime + " " + prezime;
    }

}
