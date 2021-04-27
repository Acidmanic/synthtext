/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.synthtext.models;

import com.acidmanic.synthtext.services.comment.CommentMarker;

/**
 *
 * @author diego
 */
public class CommentProfile {
    
    private CommentMarker commentMarker;
    private String fileExtension;
    private String name;

    public CommentProfile(CommentMarker commentMarker, String fileExtension, String name) {
        this.commentMarker = commentMarker;
        this.fileExtension = fileExtension;
        this.name = name;
    }

    public CommentProfile() {
    }

    public CommentMarker getCommentMarker() {
        return commentMarker;
    }

    public void setCommentMarker(CommentMarker commentMarker) {
        this.commentMarker = commentMarker;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
