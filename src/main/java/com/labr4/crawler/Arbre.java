/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labr4.crawler;

import java.net.URL;

/**
 *
 * @author Guacha
 */
public class Arbre {
    Node root;

    public Arbre(URL url) {
        root = new Node(url);
    }
    
    
    
    public void throwNodo(URL pere, URL add) {
        
    }
    
    public Node trouverNode(URL url, Node n) {
        if (n ==  null) {
            return null;
        } else {
            if (url.equals(n.getUrl())) {
                return n;
            }
            Node trouve = null;
            for (Node hijo : n.hijos) {
                trouve = trouverNode(url, hijo);
            }
            if (trouve != null) {
                return trouve;
            } else {
                return null;
            }
        }       
    }
}
