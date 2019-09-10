/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labr4.crawler;

import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;

/**
 *
 * @author Guacha
 */
public class CrawlerTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Araña a = new Araña();
        URL url;
        String s = JOptionPane.showInputDialog(null, "Ingrese el enlace a revisar", "WebCrawler", -1).concat("/"); //Pedir la URL
        try {
            url = new URL(s); //Convertir la string de pagina a URL
        } catch (MalformedURLException ex) {
            url = null;
        }
        System.out.println("Paginas encontradas por metodo manual");
        a.creerWebURL(url);
//        System.out.println("");
//        a = new Araña();
//        a.creerWeb(s);
    }
    
}
