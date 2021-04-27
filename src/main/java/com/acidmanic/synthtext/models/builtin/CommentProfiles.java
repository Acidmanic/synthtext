/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.synthtext.models.builtin;

import com.acidmanic.synthtext.models.CommentProfile;
import com.acidmanic.synthtext.services.comment.BatchCommentMarker;
import com.acidmanic.synthtext.services.comment.MultiLineCommentMarker;
import com.acidmanic.synthtext.services.comment.SingleLineCommentMarker;

/**
 *
 * @author diego
 */
public class CommentProfiles {

    public static final CommentProfile C_SHARP = new CommentProfile(
            new BatchCommentMarker()
                    .add(new MultiLineCommentMarker("/*", "*/"))
                    .add(new SingleLineCommentMarker("//")),
            ".cs",
            "C#");
    
    public static final CommentProfile JAVA = new CommentProfile(
            new BatchCommentMarker()
                    .add(new MultiLineCommentMarker("/*", "*/"))
                    .add(new SingleLineCommentMarker("//")),
            ".java",
            "Java");
    
    public static final CommentProfile YAML = new CommentProfile(
            new SingleLineCommentMarker("#"),
            ".yml",
            "Yml");
    
    public static final CommentProfile BASH = new CommentProfile(
            new SingleLineCommentMarker("#"),
            ".sh",
            "Bash");
    
    public static final CommentProfile BAT = new CommentProfile(
            new SingleLineCommentMarker("REM"),
            ".bat",
            "Bash");
    
    public static final CommentProfile NULL = new CommentProfile(
            new BatchCommentMarker(),
            "/",
            "NULL");
    
    public static final CommentProfile XML = new CommentProfile(
            new MultiLineCommentMarker("<!--", "-->"),
            ".xml",
            "Xml");
    
    public static final CommentProfile HTML = new CommentProfile(
            new MultiLineCommentMarker("<!--", "-->"),
            ".html",
            "HTML");
    
    public static final CommentProfile HTM = new CommentProfile(
            new MultiLineCommentMarker("<!--", "-->"),
            ".htm",
            "HTM");
}
