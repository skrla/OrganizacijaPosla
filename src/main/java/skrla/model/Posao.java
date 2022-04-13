/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skrla.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author skrla
 */
@Getter
@Setter
@Entity
public class Posao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sifraPosla;
    private String opisPosla;
    private BigDecimal cijenaPosla;
    private String lokacijaPosla;
    private Date pocetakPosla;
    private Date krajPosla;
    private String napomena;
    @ManyToOne
    private Tim tim;
    private Boolean zavrsen;
    
    @Override
    public String toString() {
        return lokacijaPosla;
    }

}
