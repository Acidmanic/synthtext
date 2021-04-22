/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.synthtext.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author diego
 * @param <T> Type of items
 */
public class ListBuilder<T> {

    private final List<T> list;

    public ListBuilder() {
        this.list = new ArrayList<>();
    }

    public ListBuilder<T> add(T item) {

        this.list.add(item);

        return this;
    }

    public ListBuilder<T> addAll(Collection<? extends T> items) {

        this.list.addAll(items);

        return this;
    }

    public ListBuilder<T> clear() {

        this.list.clear();

        return this;
    }

    public ListBuilder<T> add(int index, T item) {

        this.list.add(index, item);

        return this;
    }

    public List<T> build() {
        ArrayList<T> result = new ArrayList<>();

        result.addAll(list);

        return result;
    }
}
