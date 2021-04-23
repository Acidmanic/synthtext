/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.synthtext.services.statemachine;

import com.acidmanic.delegates.Function1;

/**
 *
 * @author diego
 * @param <T> Type of object in parsing sequence
 */
public class Term<T> {

    private State state;

    private Function1< State, T> on;
    private Runnable action = () -> {
    };

    public Term() {
    }

    public Term(State state, Function1< State, T> on) {
        this.state = state;
        this.on = on;
    }

    public Term(State state, Function1< State, T> on, Runnable action) {
        this.state = state;
        this.on = on;
        this.action = action;
    }

    public Term(State state, Function1<State, T> on, String description) {
        this.state = state;
        this.on = on;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Function1< State, T> getOn() {
        return on;
    }

    public void setOn(Function1< State, T> on) {
        this.on = on;
    }

    public Runnable getAction() {
        return action;
    }

    public void setAction(Runnable action) {
        this.action = action;
    }
}
