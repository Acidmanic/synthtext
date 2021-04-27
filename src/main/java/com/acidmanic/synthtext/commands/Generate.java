/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.synthtext.commands;

import com.acidmanic.commandline.commands.FractalCommandBase;
import com.acidmanic.commandline.commands.Help;
import com.acidmanic.commandline.commands.TypeRegistery;
import com.acidmanic.synthtext.commands.arguments.ArgumentsContext;
import com.acidmanic.synthtext.commands.arguments.In;
import com.acidmanic.synthtext.commands.arguments.Profile;
import com.acidmanic.synthtext.services.SynthText;
import java.io.File;

/**
 *
 * @author diego
 */
public class Generate extends FractalCommandBase<ArgumentsContext> {

    @Override
    protected void addArgumentClasses(TypeRegistery registery) {
        registery.registerClass(Help.class);

        registery.registerClass(In.class);
        
        registery.registerClass(Profile.class);
    }

    @Override
    protected void execute(ArgumentsContext subCommandsExecutionContext) {

        if (subCommandsExecutionContext.isValid()) {
            
            File inputFile = subCommandsExecutionContext.getInputFile();
            
            SynthText synthText = new SynthText(inputFile,subCommandsExecutionContext.getProfile());
            
            synthText.generate();
        }
    }

    @Override
    protected ArgumentsContext createNewContext() {
        return new ArgumentsContext();
    }

    @Override
    protected String getUsageDescription() {
        return "This command will read the input file, "
                + "and generate a file based on synthtext tags found in "
                + "input file.";
    }

}
