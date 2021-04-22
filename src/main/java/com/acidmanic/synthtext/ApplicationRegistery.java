/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.synthtext;

import com.acidmanic.commandline.commands.Help;
import com.acidmanic.commandline.commands.TypeRegistery;
import com.acidmanic.synthtext.commands.Generate;

/**
 *
 * @author diego
 */
public class ApplicationRegistery extends TypeRegistery {

    public ApplicationRegistery() {

        this.registerClass(Help.class);
        this.registerClass(Generate.class);
    }

}
