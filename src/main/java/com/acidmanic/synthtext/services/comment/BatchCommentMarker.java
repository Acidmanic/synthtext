/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.synthtext.services.comment;

import com.acidmanic.synthtext.models.TextMark;
import com.acidmanic.synthtext.models.TextType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class BatchCommentMarker implements CommentMarker {

    private final List<CommentMarker> commentMarkers;

    public BatchCommentMarker() {

        this.commentMarkers = new ArrayList<>();
    }

    public BatchCommentMarker(List<CommentMarker> commentMarkers) {

        this.commentMarkers = new ArrayList<>();

        this.commentMarkers.addAll(commentMarkers);
    }

    public BatchCommentMarker add(CommentMarker marker) {

        this.commentMarkers.add(marker);

        return this;
    }

    @Override
    public List<TextMark> markTextTypes(String content) {

        ArrayList<TextMark> markedTexts = new ArrayList<>();

        markedTexts.add(new TextMark(0, content.length(), TextType.Main));

        for (CommentMarker marker : this.commentMarkers) {

            markedTexts = markMainTextsOf(marker, markedTexts, content);
        }
        return markedTexts;
    }

    private ArrayList<TextMark> markMainTextsOf(CommentMarker marker, ArrayList<TextMark> marks, String content) {

        ArrayList<TextMark> newMarks = new ArrayList<>();

        for (TextMark mark : marks) {

            if (mark.getType() == TextType.Comment) {

                newMarks.add(mark);
            } else {

                String subText = content.substring(mark.getStart(), mark.getEnd());

                List<TextMark> subMarks = marker.markTextTypes(subText);

                int offset = mark.getStart();

                for (TextMark sub : subMarks) {

                    sub = new TextMark(offset + sub.getStart(),
                            offset + sub.getEnd(),
                            sub.getType());

                    newMarks.add(sub);
                }
            }
        }
        return newMarks;
    }

    @Override
    public String uncomment(String comment) {

        for (CommentMarker marker : this.commentMarkers) {
            try {
                String uncommented = marker.uncomment(comment);

                if (!comment.equals(uncommented)) {

                    return uncommented;
                }
            } catch (Exception e) {
            }
        }
        return comment;
    }

}
