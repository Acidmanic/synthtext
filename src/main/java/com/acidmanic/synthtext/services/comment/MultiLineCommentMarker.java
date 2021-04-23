/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.synthtext.services.comment;

import com.acidmanic.synthtext.models.TextMark;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class MultiLineCommentMarker implements CommentMarker {

    private final String startTag;
    private final String endTag;

    public MultiLineCommentMarker(String startTag, String endTag) {
        this.startTag = startTag;
        this.endTag = endTag;
    }

    @Override
    public List<TextMark> markTextTypes(String content) {
        List<TextMark> marks = new ArrayList<>();

        char[] chars = content.toCharArray();

        CommentStateMachine machine = new CommentStateMachine(startTag, endTag);

        for (char c : chars) {

            machine.pass(c);
        }
        machine.close();

        marks.addAll(machine.getTextMarks());

        return marks;
    }

}
