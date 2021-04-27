/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.synthtext.models.builtin;

import com.acidmanic.synthtext.models.CommentTags;

/**
 *
 * @author diego
 */
public final class CommentMarkers {

    private CommentMarkers() {
    }
    
    public static CommentTags SLASH_ASTRIX = new CommentTags("/*", "*/");
    public static CommentTags DOUBLE_SLASH = new CommentTags("//");
    public static CommentTags HASHTAG = new CommentTags("#");
    public static CommentTags REM = new CommentTags("REM");
    public static CommentTags SINGLE_QOUTE = new CommentTags("'");
    public static CommentTags SEMICOLUMN = new CommentTags(";");
    public static CommentTags TRIANGLES = new CommentTags("<!--", "-->");
    
    public static CommentTags SYNTHTEXT_LINE = new CommentTags("STX:");
    
}
