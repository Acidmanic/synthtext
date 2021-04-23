/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.synthtext.models;

/**
 *
 * @author diego
 */
public class TextMark {
    
    
    private int start;
    private int end;
    private TextType type;

    public TextMark() {
    }

    public TextMark(int start, int end, TextType type) {
        this.start = start;
        this.end = end;
        this.type = type;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public TextType getType() {
        return type;
    }

    public void setType(TextType type) {
        this.type = type;
    }
    
    
}
