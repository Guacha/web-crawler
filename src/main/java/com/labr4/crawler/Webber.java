/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labr4.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Guacha
 */
public class Webber {

    private static final String USER_AGENT //Tipo de explorador que se usa
            = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    private List<URL> links = new LinkedList<>();
    private int failCont = 0;
    private final int maxLinks = 10;
    
    /**
     *
     * @param url 
     * La URL que se utiliza para hacer el Query HTML
     */
    public void tricot(URL url) {
        try {
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", USER_AGENT);
            connection.connect();
            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));        
            String line;
            String link = "";
            int cont = 0;
            while((line = bf.readLine()) != null && cont < maxLinks) {
                line = line.toLowerCase();
                if (line.contains("href=\"") && line.contains("a ")) {
                    link = line.substring(line.indexOf("href=\"")).substring(6);
                    String possible[] = link.split("href=\"");
                    for (String string : possible) {
                        link = string.substring(0, string.indexOf("\""));
                        if (!link.contains("http") && !link.contains("javascript")) {
                            if (!link.contains("//")) {
                                String prefix = "https://".concat(url.getHost());   
                                if (prefix.endsWith("/") && !link.startsWith("/")) {
                                    link = prefix.concat(link);
                                } else {
                                    if (!link.startsWith("/")) {
                                        
                                        link = prefix.concat("/").concat(link);
                                    } else {
                                        link = prefix.concat(link);
                                    }                                    
                                }
                            } else {
                                link = "https://".concat(link.substring(2));
                            }
                            if (link.endsWith("#")) {
                                link = link.substring(0, link.length()-1);
                            }
                            if (link.endsWith("/")) {
                                link = link.substring(0,link.length()-1);
                            }
                        } else {
                            link = link.replace("http://", "https://");
                        }
                        
                        
                        URL trouve = new URL(link);
                        if (cont < maxLinks) {
                            if (!links.contains(trouve) && !trouve.equals(url)) {
                                links.add(trouve);
                                cont++;
                            }
                            
                        }
                    }
                    
                }   
            }
            bf.close();
        } catch (IOException ex) {
            failCont++;
        }     
    }
                        
                      
    /**
     * Obtiene la lista de elementos encontrados luego de un query HTML
     *
     * @return La lista de elementos de enlace encontrados
     */
    public List<URL> getLinks() { 
        return this.links;
    }
        
    
    public int getFails() {
        return failCont;
    }
}
