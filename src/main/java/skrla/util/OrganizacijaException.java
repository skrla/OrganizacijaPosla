/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skrla.util;

import lombok.Getter;

/**
 *
 * @author skrla
 */
@Getter
public class OrganizacijaException extends Exception{
    private String poruka;

    public OrganizacijaException(String poruka) {
        super();
        this.poruka = poruka;
    }
    
    
}
