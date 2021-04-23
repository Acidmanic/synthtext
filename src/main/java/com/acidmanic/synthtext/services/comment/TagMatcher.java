/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.synthtext.services.comment;

/**
 *
 * @author diego
 */
public class TagMatcher {

    private final String tagToMatch;

    public TagMatcher(String tagToMatch) {
        this.tagToMatch = tagToMatch;
        this.receivingTag = "";
    }

    public TagMatcher(String tagToMatch, String receivingTag) {
        this.tagToMatch = tagToMatch;
        this.receivingTag = receivingTag;
        this.setMatchedState();
    }

    private String receivingTag;
    private boolean matched = false;
    private boolean unMatched = false;

    public void pass(char c) {
        this.receivingTag += c;

        setMatchedState();
    }

    public boolean isMatched() {
        return matched;
    }

    public boolean isUnMatched() {
        return unMatched;
    }

    public String getReceivingTag() {
        return receivingTag;
    }

    private void setMatchedState() {
        boolean startsWith = tagToMatch.startsWith(receivingTag);

        this.matched = startsWith
                && this.tagToMatch.length() == this.receivingTag.length();

        this.unMatched = !startsWith;
    }

}
