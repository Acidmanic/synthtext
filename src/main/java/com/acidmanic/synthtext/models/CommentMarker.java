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
public class CommentMarker {

    private final String startTag;
    private final String endTag;
    private final boolean multiLine;

    public CommentMarker(String startTag, String endTag) {
        this.startTag = startTag;
        this.endTag = endTag;
        this.multiLine = true;
    }

    public CommentMarker(String startTag) {
        this.startTag = startTag;
        this.endTag = null;
        this.multiLine = false;
    }

    public String getStartTag() {
        return startTag;
    }

    public String getEndTag() {
        return endTag;
    }

    public boolean isMultiLine() {
        return multiLine;
    }

}
