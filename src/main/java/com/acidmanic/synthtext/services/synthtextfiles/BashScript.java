/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.synthtext.services.synthtextfiles;

import com.acidmanic.synthtext.models.CommentTags;
import com.acidmanic.synthtext.models.builtin.CommentMarkers;
import com.acidmanic.synthtext.services.ListBuilder;
import com.acidmanic.synthtext.services.SynthtextFile;
import java.util.List;

/**
 *
 * @author diego
 */
public class BashScript implements SynthtextFile{

    @Override
    public String name() {
        return "Bash Script file format.";
    }

    @Override
    public String fileExtension() {
        return "sh";
    }

    @Override
    public List<CommentTags> commentMarkers() {
        return new ListBuilder<CommentTags>().add(CommentMarkers.HASHTAG).build();
    }
    
}
