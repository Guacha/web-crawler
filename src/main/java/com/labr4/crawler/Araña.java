/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labr4.crawler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author Guacha
 */
public class Araña {
    private static final int Limite = 20;
    
    
    private Set<URL> dejaRegarde;
    
    private Queue<URL> pourVisiter;
    
    public Araña() {
        dejaRegarde = new HashSet<>();
        pourVisiter = new LinkedList<>();
    }
    
    /**
     *Función que se encarga de obtener la siguiente dirección web válida que 
     * aún no se ha visitado
     * 
     * @return La siguiente Dirección web (String) válida que no se haya 
     * explorado
     */
    public URL prochainWeb() {
        URL url;
        
        do {
            url = this.pourVisiter.remove();
        } while (this.dejaRegarde.contains(url) && !this.pourVisiter.isEmpty());
        this.dejaRegarde.add(url);
        return url;
    }
        
    /**
     *creerWeb se encarga de organizar las peticiones HTML de la clase Webber, y
     * llevar cuenta de todas las páginas visitas y por visitar, así como el 
     * límite de paginas que se pueden visitar, adaptado para que funcione sin 
     * librerías externas
     * @param url
     * URL inicial del crawl, a partir de la cual se seguirá explorando
     */
    public void creerWebURL(URL url) {
        pourVisiter.add(url);
        int cont = 0;
        URL urlActuelle;
        while(this.dejaRegarde.size() < Limite && !this.pourVisiter.isEmpty()) {
            Webber w = new Webber();
            urlActuelle = analyserURL(prochainWeb());
            w.tricot(urlActuelle);
            cont =+ w.getFails();
            
            if (!w.getLinks().isEmpty()) {
                w.getLinks().stream().filter((urls) -> (!dejaRegarde.contains(urls) && !pourVisiter.contains(urls))).map((urls) -> {
                    //System.out.println("\t" + urls);
                    return urls;
                }).forEachOrdered((urls) -> {
                    pourVisiter.add(urls);
                });
            }
        }
        System.out.println("Limite del Crawl alcanzado, total de paginas encontradas: " + (pourVisiter.size() + dejaRegarde.size()));
        System.out.println("Hubo un total de " + cont + " errores de query HTML");
    }
    
    private URL analyserURL(URL url) {
        String urlTxt = url.toString();
        
        if (!urlTxt.contains("https")) {
            urlTxt = urlTxt.replace("http://", "https://");
        }
        
        try {
            return new URL(urlTxt);
        } catch (MalformedURLException ex) {
            return null;
        }
    }
}
