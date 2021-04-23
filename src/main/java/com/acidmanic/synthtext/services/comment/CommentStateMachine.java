/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.synthtext.services.comment;

import com.acidmanic.synthtext.models.TextMark;
import com.acidmanic.synthtext.models.TextType;
import com.acidmanic.synthtext.services.comment.states.CommentText;
import com.acidmanic.synthtext.services.comment.states.EndTag;
import com.acidmanic.synthtext.services.comment.states.MainText;
import com.acidmanic.synthtext.services.comment.states.StartTag;
import com.acidmanic.synthtext.services.comment.states.StateBase;
import com.acidmanic.synthtext.services.statemachine.GenericMachine;
import com.acidmanic.synthtext.services.statemachine.Term;
import com.acidmanic.utilities.Final;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class CommentStateMachine extends GenericMachine<Character> {

    private static final StateBase MAIN_TEXT = new MainText();
    private static final StateBase START_TAG = new StartTag();
    private static final StateBase COMMENT_TEXT = new CommentText();
    private static final StateBase END_TAG = new EndTag();

    private List<TextMark> textMarks = new ArrayList<>();
    private Runnable finishPassing;

    public CommentStateMachine(String startTag, String stopTag) {
        super(MAIN_TEXT);

        Final<String> mainText = new Final<>("");
        Final<String> commentText = new Final<>("");

        Final<TagMatcher> matcher = new Final<>(null);
        Character startStart = startTag.charAt(0);
        Character stopStart = stopTag.charAt(0);
        Final<Integer> receivedLength = new Final<>(0);
        Final<Integer> lastMark = new Final<>(0);

        Runnable deliverMain = () -> {

            textMarks.add(new TextMark(lastMark.get(), receivedLength.get(), TextType.Main));

            lastMark.set(receivedLength.get());

            commentText.set(matcher.get().getReceivingTag());
        };

        Runnable deliverComment = () -> {

            commentText.set(matcher.get().getReceivingTag());

            textMarks.add(new TextMark(lastMark.get(), receivedLength.get(), TextType.Comment));

            lastMark.set(receivedLength.get());

            mainText.set("");
        };
        
        add(new Term<>(MAIN_TEXT, c -> {

            receivedLength.set(receivedLength.get() + 1);

            if (c == startStart) {

                matcher.set(new TagMatcher(startTag, "" + c));

                if (matcher.get().isMatched()) {

                    deliverMain.run();

                    return COMMENT_TEXT;
                }
                return START_TAG;
            }
            mainText.set(mainText.get() + c);

            return MAIN_TEXT;
        }));

        add(new Term<>(START_TAG, c -> {

            receivedLength.set(receivedLength.get() + 1);

            matcher.get().pass(c);

            if (matcher.get().isMatched()) {

                deliverMain.run();

                return COMMENT_TEXT;
            }
            if (matcher.get().isUnMatched()) {

                mainText.set(mainText.get() + matcher.get().getReceivingTag());

                return MAIN_TEXT;
            }
            return START_TAG;
        }));

        add(new Term<>(COMMENT_TEXT, c -> {

            receivedLength.set(receivedLength.get() + 1);

            if (c == stopStart) {

                matcher.set(new TagMatcher(stopTag, "" + c));

                if (matcher.get().isMatched()) {

                    deliverMain.run();

                    return MAIN_TEXT;
                }
                return END_TAG;
            }
            commentText.set(commentText.get() + c);

            return COMMENT_TEXT;
        }));

        add(new Term<>(END_TAG, c -> {

            receivedLength.set(receivedLength.get() + 1);

            matcher.get().pass(c);

            if (matcher.get().isMatched()) {

                deliverComment.run();

                return MAIN_TEXT;
            }
            if (matcher.get().isUnMatched()) {

                commentText.set(mainText.get() + matcher.get().getReceivingTag());

                return COMMENT_TEXT;
            }
            return END_TAG;
        }));
        
        
        this.finishPassing = () -> {
            if(getCurrentState().equals(MAIN_TEXT)){
                deliverMain.run();
            }
            if(getCurrentState().equals(COMMENT_TEXT)){
                deliverComment.run();
            }
            if(getCurrentState().equals(START_TAG)){
                mainText.set(mainText.get()+matcher.get().getReceivingTag());
                deliverMain.run();
            }
            if(getCurrentState().equals(END_TAG)){
                commentText.set(commentText.get()+matcher.get().getReceivingTag());
                deliverComment.run();
            }
        };
    }

    public void close(){
        this.finishPassing.run();
    }
    
    public List<TextMark> getTextMarks() {
        return textMarks;
    }

}
