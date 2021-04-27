/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.synthtext.commands;

import com.acidmanic.commandline.commands.CommandBase;
import com.acidmanic.synthtext.services.CommentProfileFactory;

/**
 *
 * @author diego
 */
public class Profiles extends CommandBase {

    @Override
    protected String getUsageDescription() {
        return "Shows a list of available comment profiles for known source files.";
    }

    @Override
    protected String getArgumentsDesciption() {
        return "";
    }

    @Override
    public void execute(String[] args) {

        warning("----------------------------------");
        warning("All available profiles");
        warning("----------------------------------");
        new CommentProfileFactory().allProfiles()
                .forEach(p -> info("Profile name: " + p.getName()
                + ", associated file extention: " + p.getFileExtension()));
        warning("----------------------------------");
    }

    @Override
    public boolean hasArguments() {
        return false;
    }

}
