/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.synthtext.services.statemachine;

/**
 *
 * @author diego
 */
public interface Machine<T> {

    void pass(T value);
}
