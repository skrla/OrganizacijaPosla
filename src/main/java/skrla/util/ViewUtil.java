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
public class ViewUtil {
    
    public static final String NAZIV ="Organizacija posla";
    
    public static String getNaslov(String naslov){

        return ViewUtil.NAZIV + " - " + naslov;
    }

}
