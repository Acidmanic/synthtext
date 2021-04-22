/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.synthtext.models;

import java.util.HashMap;

/**
 *
 * @author diego
 */
public class SynthTable {
    
    
    private HashMap<String,String> replaces = new HashMap<>();
    private String outputFilename;

    public HashMap<String, String> getReplaces() {
        return replaces;
    }

    public void setReplaces(HashMap<String, String> replaces) {
        this.replaces = replaces;
    }

    public String getOutputFilename() {
        return outputFilename;
    }

    public void setOutputFilename(String outputFilename) {
        this.outputFilename = outputFilename;
    }
    
    
    
}
