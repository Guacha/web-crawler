/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labr4.crawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author Guacha
 */
public class Webber {

    private static final String USER_AGENT //Tipo de explorador que se usa
            = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    private List<String> links = new LinkedList<>();
    private Document htmlDoc;

    /**
     * Se encarga de revisar el documento HTML de una pagina especifica, leerlo
     * y hacerle un parse legible para java.
     *
     * De esto, se obtienen los elementos enlace de la pagina y se agregan a la
     * lista links
     *
     * @param url Url para realizar el Query HTML
     *
     * @exception IOException Excepción que ocurre si el URL ingresado es
     * inválido
     */
    public void tricot(String url) {

        try {
            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            Document htmlObtenue = connection.get();
            htmlDoc = htmlObtenue;
            if (connection.response().statusCode() == 200) {
                System.out.println("Visitando pagina " + url);
                Elements linksDansPage = htmlDoc.select("a[href]");
                System.out.println("    Se encontraron " + linksDansPage.size() + " enlaces web en "
                        + url + ":");
                linksDansPage.forEach((element) -> {
                    System.out.println(element.absUrl("href"));
                    links.add(element.absUrl("href"));
                });
            }
        } catch (IOException ex) {
            System.out.println("La dirección: " + url + " no es válida");
        }

    }
    
    /**
     *
     * @param url 
     * La URL que se utiliza para hacer el Query HTML
     */
    public void tricot(URL url) {
        try {
            int cont = 0;
            BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream()));            
            String line;
            String link = "";
            while((line = bf.readLine()) != null) {
                if (line.contains("href=\"") && line.contains("a ")) {
                    link = line.substring(line.indexOf("href=\"")).substring(6);
                    
                    String possible[] = link.split("href=\"");
                    
                    for (String string : possible) {
                        link = string.substring(0, string.indexOf("\""));
                        cont++;
                        if (!link.contains("http")) {

                            if (!link.contains("//")) {
                                link = "http://".concat(url.getHost()).concat(link);
                            } else {
                                link = "http://".concat(link.substring(2));
                            }
                        }
                        links.add(link);
                    }   
                }   
            }
            bf.close();
            System.out.println("Se encontraron " + cont + " enlaces válidos");
        } catch (IOException ex) {
            Logger.getLogger(Webber.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    /**
     * Obtiene la lista de elementos encontrados luego de un query HTML
     *
     * @return La lista de elementos de enlace encontrados
     */
    public List<String> getLinks() {
        return this.links;
    }

}
