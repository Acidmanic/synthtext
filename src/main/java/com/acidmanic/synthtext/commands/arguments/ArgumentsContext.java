/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.synthtext.commands.arguments;

import com.acidmanic.commandline.commands.context.ExecutionContext;
import com.acidmanic.synthtext.models.CommentProfile;
import com.acidmanic.synthtext.models.builtin.CommentProfiles;
import java.io.File;

/**
 *
 * @author diego
 */
public class ArgumentsContext implements ExecutionContext {

    private File inputFile;
    private CommentProfile profile = CommentProfiles.NULL;

    private boolean valid;

    public ArgumentsContext() {
        this.valid = true;
    }

    public File getInputFile() {
        return inputFile;
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public void invalid() {
        this.valid = false;
    }

    public boolean isValid() {
        return valid;
    }

    public CommentProfile getProfile() {
        return profile;
    }

    public void setProfile(CommentProfile profile) {
        this.profile = profile;
    }

}
