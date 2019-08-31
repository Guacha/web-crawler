/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labr4.crawler;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



/**
 *
 * @author Guacha
 */
public class Webber {
    
    private static final String USER_AGENT = 
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    private List<String> links = new LinkedList<>();
    private Document htmlDoc;
    
    /**
     *Se encarga de revisar el documento HTML de una pagina especifica, leerlo y
     * hacerle un parse legible para java. 
     * 
     * De esto, se obtienen los elementos enlace de la pagina y se agregan a la
     * lista links
     * 
     * @param url
     * Url para realizar el Query HTML
     * 
     * @exception IOException
     * Excepción que ocurre si el URL ingresado es inválido
     */
    public void tricot(String url) {
        try {
            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            Document htmlObtenue = connection.get();
            
            htmlDoc = htmlObtenue;
            
            if (connection.response().statusCode() == 200) {
                System.out.println("Visitando pagina " + url);
                
                Elements linksDansPage = htmlDoc.select("a[href]");
                    System.out.println("Se encontraron " + linksDansPage.size() + " enlaces web en " +
                        url + ":");
                for (Element element : linksDansPage) {
                    System.out.println("\t" + element.absUrl("href"));
                    links.add(element.absUrl("href"));
                }
            }
        }catch(IOException ex) {
            System.out.println(ex);
        }
    }
    
    /**
     *Obtiene la lista de elementos encontrados luego de un query HTML
     * @return
     * La lista de elementos de enlace encontrados
     */
    public List<String> getLinks() {
        return this.links;
    }
    
    
}
