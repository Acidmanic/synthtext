/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.synthtext.services;

import com.acidmanic.synthtext.models.CommentProfile;
import com.acidmanic.synthtext.models.builtin.CommentProfiles;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class CommentProfileFactory {

    private static List<CommentProfile> commentProfiles = new ArrayList<>();

    static {

        Class type = CommentProfiles.class;

        Field[] fileds = type.getFields();

        for (Field field : fileds) {

            try {
                CommentProfile profile = (CommentProfile) field.get(null);

                CommentProfileFactory.commentProfiles.add(profile);
            } catch (Exception e) {
            }
        }
    }

    public CommentProfile makeByExtention(String extention) {

        for (CommentProfile profile : commentProfiles) {

            if (profile.getFileExtension().equalsIgnoreCase(extention)) {
                
                return profile;
            }
        }
        return CommentProfiles.NULL;
    }
    
    public CommentProfile makeByName(String name) {

        for (CommentProfile profile : commentProfiles) {

            if (profile.getName().equalsIgnoreCase(name)) {
                
                return profile;
            }
        }
        return CommentProfiles.NULL;
    }
}
