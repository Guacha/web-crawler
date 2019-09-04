/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labr4.crawler;

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
    
    
    private Set<String> dejaRegarde;
    
    private Queue<String> pourVisiter;
    
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
    public String prochainWeb() {
        String url;
        
        do {
            url = this.pourVisiter.remove();
        } while (this.dejaRegarde.contains(url));
        this.dejaRegarde.add(url);
        return url;
    }
    
    /**
     *creerWeb se encarga de organizar las peticiones HTML de la clase Webber, y
     * llevar cuenta de todas las páginas visitas y por visitar, así como el 
     * límite de paginas que se pueden visitar
     * 
     * @param url
     * Url inicial del Crawl, a partir de esta se seguirá explorando
     */
    public void creerWeb(String url) {
        
        //while(this.dejaRegarde.size() < Limite){
            Webber w = new Webber();
            String urlActuelle;
            if (pourVisiter.isEmpty()) {
                urlActuelle = url;
            } else {
                urlActuelle = prochainWeb();
            }
            w.tricot(urlActuelle);
            pourVisiter.addAll(w.getLinks());
        //}
        
        System.out.println("Limite del Crawl alcanzado, total de paginas encontradas: " + (pourVisiter.size() + dejaRegarde.size()));
        
        
    }
    
    public void creerWebURL(URL url) {
        Webber w = new Webber();
        w.tricot(url);
    }
    
    
}
