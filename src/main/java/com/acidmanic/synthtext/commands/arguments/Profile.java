/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.synthtext.commands.arguments;

import com.acidmanic.commandline.commands.CommandBase;
import com.acidmanic.synthtext.models.CommentProfile;
import com.acidmanic.synthtext.services.CommentProfileFactory;

/**
 *
 * @author diego
 */
public class Profile extends CommandBase {

    @Override
    protected String getUsageDescription() {
        return "Sets the comment profile by name ignoring file extention.";
    }

    @Override
    protected String getArgumentsDesciption() {
        return "";
    }

    @Override
    public void execute(String[] args) {

        ArgumentsContext context = getContext();

        if (args.length == 1) {

            try {

                String name = args[0];

                CommentProfile profile = new CommentProfileFactory().makeByName(name);

                context.setProfile(profile);

                return;

            } catch (Exception e) {
            }
        }
        error(Profile.class.getSimpleName() + " argument, should be followed"
                + " by a correct profile name. use command profiles to see a "
                + "list of available profiles.");

        context.invalid();
    }

    @Override
    public boolean hasArguments() {
        return true;
    }

}
