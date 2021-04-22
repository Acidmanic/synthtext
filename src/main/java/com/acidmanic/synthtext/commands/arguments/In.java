/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.synthtext.commands.arguments;

import com.acidmanic.commandline.commands.CommandBase;
import java.io.File;

/**
 *
 * @author diego
 */
public class In extends CommandBase {

    @Override
    protected String getUsageDescription() {
        return "The Input file name";
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

                File inputFile = new File(args[0]);

                context.setInputFile(inputFile);

                return;

            } catch (Exception e) {
            }
        }
        error(In.class.getSimpleName() + " argument, should be followed by a file path.");

        context.invalid();
    }

    @Override
    public boolean hasArguments() {
        return true;
    }

}
