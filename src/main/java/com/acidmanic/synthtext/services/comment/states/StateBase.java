/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.synthtext.services.comment.states;

import com.acidmanic.synthtext.services.statemachine.State;

/**
 *
 * @author diego
 */
public abstract class StateBase implements State {

    private final String name;

    public StateBase() {
        this.name = this.getClass().getName();
    }

    @Override
    public boolean equals(State state) {

        if (state instanceof StateBase) {
            return this.name.equals(((StateBase) state).name);
        }
        return false;
    }
}
