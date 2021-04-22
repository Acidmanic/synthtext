/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.synthtext.services;

import com.acidmanic.commandline.commands.TypeRegistery;
import com.acidmanic.synthtext.services.synthtextfiles.BashScript;
import com.acidmanic.synthtext.services.synthtextfiles.Java;
import com.acidmanic.synthtext.services.synthtextfiles.Yaml;
import java.io.File;
import java.util.List;

/**
 *
 * @author diego
 */
public class SynthtextFileFactory {

    private final TypeRegistery typeRegistery;

    public SynthtextFileFactory() {

        this.typeRegistery = new TypeRegistery();

        //REGISTER ANY NEW FILE HERE
        this.typeRegistery.registerClass(Yaml.class);
        this.typeRegistery.registerClass(Java.class);
        this.typeRegistery.registerClass(BashScript.class);
    }

    public SynthtextFile make(File file) {

        List<Class> classes = typeRegistery.getClasses(SynthtextFile.class);

        String filename = file.getName();

        for (Class t : classes) {

            try {

                SynthtextFile synthFile = (SynthtextFile) t.newInstance();

                if (filename.endsWith("." + synthFile.fileExtension().toLowerCase())) {
                    return synthFile;
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

}
