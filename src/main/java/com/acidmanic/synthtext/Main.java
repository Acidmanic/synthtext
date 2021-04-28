/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.synthtext;

import com.acidmanic.commandline.commands.Command;
import com.acidmanic.commandline.commands.CommandFactory;
import java.util.Map;

/**
 *
 * @author diego
 */
public class Main {

    public static void main(String[] args) {

        ApplicationRegistery registery = new ApplicationRegistery();

        CommandFactory factory = new CommandFactory(registery);

        Map<Command, String[]> commands = factory.make(args, true);

        commands.forEach((c, ar) -> c.execute(ar));

    }
}
