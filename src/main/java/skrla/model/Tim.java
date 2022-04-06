/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skrla.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author skrla
 */
@Getter
@Setter
@Entity
public class Tim {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sifraTima;
    private String nazivTima;
    private String napomena;
    @OneToMany(mappedBy = "tim")
    private List<Djelatnik> djelatnik;
    @OneToMany(mappedBy = "tim")
    private List<Posao> posao;

}
