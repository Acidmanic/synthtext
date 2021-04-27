/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.synthtext.services;

import com.acidmanic.synthtext.models.CommentTags;
import java.util.List;

/**
 *
 * @author diego
 */
public interface SynthtextFile {
    
    
    String name();
    
    String fileExtension();
    
    List<CommentTags> commentMarkers();
    
    
}
