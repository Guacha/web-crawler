/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labr4.crawler;

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
        
        a.creerWeb(JOptionPane.showInputDialog(null, "Ingrese el enlace a revisar", "WebCrawler", -1));
    }
    
}
