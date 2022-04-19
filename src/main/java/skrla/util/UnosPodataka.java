/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skrla.util;

import com.github.javafaker.Faker;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.hibernate.Session;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import skrla.model.Djelatnik;
import skrla.model.Posao;
import skrla.model.PoslovnaJedinica;
import skrla.model.Tim;

/**
 *
 * @author skrla
 */
public class UnosPodataka {

    public static void noviPodaci() {
        UnosPodataka.unesi();
    }

    public static void unesi() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Faker faker = new Faker();

        List<PoslovnaJedinica> poslovneJedinice = generirajPoslovneJedinice(faker, session);
        List<Tim> timovi = generirajTimove(faker, session);
        List<Djelatnik> djelatnici = generirajDjelatnike(faker, session, true, poslovneJedinice, timovi);
        List<Posao> poslovi = generirajPoslove(faker, session, timovi);
        session.getTransaction().commit();

    }

    private static List<Djelatnik> generirajDjelatnike(Faker faker, Session session, boolean b, List<PoslovnaJedinica> pj, List<Tim> t) {
        List<Djelatnik> djelatnici = new ArrayList();
        Djelatnik d;
        for (int i = 0; i < 124; i++) {
            int m = (int) (Math.random() * (10 - 0) + 10) % 10;
            d = new Djelatnik();
            d.setIme(faker.name().firstName());
            d.setPrezime(faker.name().lastName());
            d.setEmail(faker.name().firstName().substring(0, 1).toLowerCase()
                    + faker.name().lastName().toLowerCase().replace(" ", "")
                    + "@orka.hr");
            if (b) {
                d.setOib(OibValidation.generirajOib());
            }
            d.setDatumRodenja(datumRodenja());
            d.setAdresaStanovanja(faker.pokemon().location());
            d.setPoslovnaJedinica(pj.get((int) (Math.random() * (5 - 0) + 5) % 5));
            d.setTim(t.get(m));
            d.setIBAN(Iban.random(CountryCode.HR).toString());
            d.setAktivan(true);
            session.save(d);
            djelatnici.add(d);
            System.out.println("Krierao djelatnika: " + d.getIme() + " " + d.getPrezime());
        }
        return djelatnici;
    }

    private static List<Tim> generirajTimove(Faker faker, Session session) {
        List<Tim> tim = new ArrayList();
        Tim t;

        for (int i = 0; i < 10; i++) {
            t = new Tim();
            t.setNazivTima(faker.dog().breed());
            session.save(t);
            tim.add(t);
            System.out.println("Kreiran tim: " + t.getNazivTima());
        }
        return tim;
    }

    private static List<PoslovnaJedinica> generirajPoslovneJedinice(Faker faker, Session session) {
        List<PoslovnaJedinica> poslovnaJedinica = new ArrayList();
        PoslovnaJedinica pj;
        for (int i = 0; i < 5; i++) {
            pj = new PoslovnaJedinica();
            pj.setNazivPoslovneJedinice(faker.company().name());
            pj.setAdresa(faker.address().streetAddress());
            session.save(pj);
            poslovnaJedinica.add(pj);
            System.out.println("Kreirana poslovna jedinica: " + pj.getNazivPoslovneJedinice() + " " + pj.getAdresa());
        }
        return poslovnaJedinica;
    }

    private static List<Posao> generirajPoslove(Faker faker, Session session, List<Tim> t) {
        List<Posao> poslovi = new ArrayList();
        Posao p;
        for (int i = 0; i < 300; i++) {
            p = new Posao();
            int m = (int) (Math.random() * (10 - 0) + 10) % 10;
            p.setOpisPosla(faker.chuckNorris().fact());
            p.setCijenaPosla(new BigDecimal(Math.random() * (100000 - 5000) + 5000));
            p.setTim(t.get(m));
            p.setZavrsen(false);
            p.setPocetakPosla(pocetakPosla());
            p.setRadniNalog((i+1) + "/" + godina(p.getPocetakPosla()));
            p.setKrajPosla(krajPosla(p.getPocetakPosla()));
            if (p.getKrajPosla() == null) {
                p.setZavrsen(false);
            } else {
                p.setZavrsen(true);
            }
            session.save(p);
            poslovi.add(p);
            System.out.println("Kreiran posao: " + p.getOpisPosla());
        }
        return poslovi;
    }

    private static Date pocetakPosla() {
        Random random = new Random();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        int minDay = (int) LocalDate.of(2000, 1, 1).toEpochDay();
        var maxDay = (int) LocalDate.of(2022, 1, 31).toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);

        LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);
        Date date = Date.from(randomBirthDate.atStartOfDay(defaultZoneId).toInstant());
        return date;
    }

    private static Date krajPosla(Date pocetak) {
        Random random = new Random();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        int minDay = (int) LocalDate.of(2010, 1, 1).toEpochDay();
        var maxDay = (int) LocalDate.of(2022, 1, 31).toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);

        LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);
        Date date = Date.from(randomBirthDate.atStartOfDay(defaultZoneId).toInstant());
        return (date.compareTo(pocetak) > 0) ? date : null;
    }

    private static Date datumRodenja() {
        Random random = new Random();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        int minDay = (int) LocalDate.of(1958, 1, 1).toEpochDay();
        var maxDay = (int) LocalDate.of(2004, 1, 1).toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);

        LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);
        Date date = Date.from(randomBirthDate.atStartOfDay(defaultZoneId).toInstant());
        return date;
    }

    private static String godina(Date datum) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = datum;
        
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String year = df.format(d);
        return year;
    }
}
