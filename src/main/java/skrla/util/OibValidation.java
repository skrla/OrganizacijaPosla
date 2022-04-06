/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skrla.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import us.codecraft.xsoup.Xsoup;

/**
 *
 * @author skrla
 */
public class OibValidation {
	
	public static final int NO_ERROR = 0;
	public static final int ERR_INVALID_LENGHT = 1;
	public static final int ERR_INVALID_FORMAT = 2;
	public static final int ERR_INVALID_CONTROL = 3;
	
	private static final int asciiDigitsOffset = '0';	

        /**
         * Provjera OIB
         * @param oib oib koji provjeravam
         * @return true ako je ispravan, false ako nije
         */
	public static boolean checkOIB(String oib) {
		return checkOIBState(oib) == NO_ERROR;
	}
	
	public static int checkOIBState(String oib) {
            if(oib==null){
                return ERR_INVALID_FORMAT;
            }
        if (oib.length() != 11) {
            return ERR_INVALID_LENGHT;
        }

        char[] chars = oib.toCharArray();
        
        int a = 10;
        for (int i = 0; i < 10; i++) {
        	char c = chars[i];
        	if (c < '0' || c > '9') {
        		return ERR_INVALID_FORMAT;
        	}
            a = a + (c - asciiDigitsOffset);
            a = a % 10;
            if (a == 0) {
                a = 10;
            }
            a *= 2;
            a = a % 11;
        }
        int kontrolni = 11 - a;
        kontrolni = kontrolni % 10;

        if (kontrolni != (chars[10] - asciiDigitsOffset)) {
        	return ERR_INVALID_CONTROL;
        }
    	return NO_ERROR;
    }
        
    public static String generirajOib() {
        // DZ
        // Umjesto spajanja na web stranicu napraviti generiranje u java programskom jeziku
        // https://regos.hr/app/uploads/2018/07/KONTROLA-OIB-a.pdf
        try {
            URL url = new URL("http://oib.itcentrala.com/oib-generator/");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            url.openStream()));
            String inputLine;
            StringBuilder sb = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine);
            }
            in.close();
            Document d = Jsoup.parse(sb.toString());
            return Xsoup.compile("/html/body/div[1]/div[1]/text()").evaluate(d).get();
        } catch (Exception e) {
        }
        return "";
    }

}

