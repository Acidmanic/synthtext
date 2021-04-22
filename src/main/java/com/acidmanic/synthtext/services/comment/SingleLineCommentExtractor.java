/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.synthtext.services.comment;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class SingleLineCommentExtractor implements CommentExtractor {

    private final String startTag;

    public SingleLineCommentExtractor(String startTag) {
        this.startTag = startTag;
    }

    @Override
    public List<String> extractComments(String content) {
        String[] lines = content.split("\\n");

        List<String> comments = new ArrayList<>();

        for (String line : lines) {

            line = line.trim();

            if (line.startsWith(startTag)) {

                line = line.substring(startTag.length(), line.length());

                comments.add(line);
            }
        }
        return comments;
    }

}
