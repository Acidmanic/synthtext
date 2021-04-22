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
public final class CommentMarkers {

    private CommentMarkers() {
    }
    
    public static CommentMarker SLASH_ASTRIX = new CommentMarker("/*", "*/");
    public static CommentMarker DOUBLE_SLASH = new CommentMarker("//");
    public static CommentMarker HASHTAG = new CommentMarker("#");
    public static CommentMarker REM = new CommentMarker("REM");
    public static CommentMarker SINGLE_QOUTE = new CommentMarker("'");
    public static CommentMarker SEMICOLUMN = new CommentMarker(";");
    public static CommentMarker TRIANGLES = new CommentMarker("<!--", "-->");
    
    public static CommentMarker SYNTHTEXT_LINE = new CommentMarker("STX:");
    
}
