/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.synthtext.services.statemachine;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 * @param <T> type of passing data
 */
public class GenericMachine<T> implements Machine<T> {

    private final List<Term<T>> terms = new ArrayList<>();
    private final List<State> history;

    private State currentState;

    public GenericMachine(State start) {
        this.currentState = start;
        this.addTerms(terms);
        this.history = new ArrayList<>();
        this.history.add(start);
    }

    @Override
    public synchronized void pass(T value) {
        for (Term<T> term : this.terms) {
            if (term.getState().equals(currentState)) {

                State nextState = term.getOn().perform(value);

                if (!nextState.equals(currentState)) {

                    term.getAction().run();

                    this.currentState = nextState;

                    this.history.add(nextState);

                    return;
                }
            }
        }
    }

    public void add(Term<T> term) {
        this.terms.add(term);
    }

    protected void addTerms(List<Term<T>> terms) {

    }

    public List<State> getHistory() {
        return history;
    }
    
    protected State getCurrentState(){
        return this.currentState;
    }

}
