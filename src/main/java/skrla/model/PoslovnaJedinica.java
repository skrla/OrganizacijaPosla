
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
public class PoslovnaJedinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sifraPoslovneJedinice;
    private String nazivPoslovneJedinice;
    private String adresa;
    @OneToMany(mappedBy = "poslovnaJedinica")
    private List<Djelatnik> djelatnici;
    
    @Override
    public String toString() {
        return nazivPoslovneJedinice;
    }
}
