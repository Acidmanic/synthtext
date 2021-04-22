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
public class MultiLineCommentExtractor implements CommentExtractor {

    private final String startTag;
    private final String endTag;

    public MultiLineCommentExtractor(String startTag, String endTag) {
        this.startTag = startTag;
        this.endTag = endTag;
    }

    @Override
    public List<String> extractComments(String content) {

        List<String> comments = new ArrayList<>();

        int index = 0;

        while (index < content.length()) {

            index = content.indexOf(startTag);
            
            if(index==-1){
                break;
            }
            index += startTag.length();
            
            int endex = content.indexOf(endTag,index);
            
            if(endex==-1){
                endex = content.length();
            }
            String comment = content.substring(index,endex);
            
            comments.add(comment);
            
            endex += endTag.length();
            
            index = endex;
        }
        return comments;
    }

}
