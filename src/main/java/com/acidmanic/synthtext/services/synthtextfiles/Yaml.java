/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.synthtext.services.synthtextfiles;

import com.acidmanic.synthtext.models.CommentMarker;
import com.acidmanic.synthtext.models.CommentMarkers;
import com.acidmanic.synthtext.services.ListBuilder;
import com.acidmanic.synthtext.services.SynthtextFile;
import java.util.List;

/**
 *
 * @author diego
 */
public class Yaml implements SynthtextFile{

    @Override
    public String name() {
        return "Yaml file format.";
    }

    @Override
    public String fileExtension() {
        return "yml";
    }

    @Override
    public List<CommentMarker> commentMarkers() {
        return new ListBuilder<CommentMarker>().add(CommentMarkers.HASHTAG).build();
    }
    
}
