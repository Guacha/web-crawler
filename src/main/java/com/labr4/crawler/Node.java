/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labr4.crawler;

import java.net.URL;
import java.util.LinkedList;

/**
 *
 * @author Guacha
 */
public class Node {
    private URL url;
    LinkedList<Node> hijos;

    public Node(URL url) {
        this.url = url;
        hijos = new LinkedList<>();
    }

    public URL getUrl() {
        return url;
    }
    
    
    
}
