/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.synthtext.services.comment;

import com.acidmanic.synthtext.models.TextMark;
import java.util.List;

/**
 *
 * @author diego
 */
public interface CommentMarker {
    
    List<TextMark> markTextTypes(String content);
}
