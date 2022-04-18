/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skrla.util;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;
import skrla.model.Djelatnik;

/**
 *
 * @author skrla
 */
public class ComparatorOrganizacija implements Comparator<Djelatnik> {

    private Collator hr;

    public ComparatorOrganizacija() {
        hr = Collator.getInstance(new Locale("hr", "HR")); //Your locale here
        hr.setStrength(Collator.PRIMARY);
    }

    @Override
    public int compare(Djelatnik o1, Djelatnik o2) {
        return hr.compare(o1.getPrezime(), o2.getPrezime());
    }
    
}
