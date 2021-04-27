/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.synthtext.services.comment;

import com.acidmanic.synthtext.models.TextMark;
import com.acidmanic.synthtext.models.TextType;
import com.acidmanic.synthtext.services.ListBuilder;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author diego
 */
public class MultiLineCommentMarkerTest {

    public MultiLineCommentMarkerTest() {
    }

    @Test
    public void marker_givenMultiLineContent_shouldMarkCommentsCorrectly() {
        System.out.println("marker_givenMultiLineContent_shouldMarkCommentsCorrectly");
        String content
                = // 0 - 4
                "ABC\n"
                // 4 - 9
                + "/*DEF\n"
                // 9 - 13
                + "GHI\n"
                // 13 - 19
                + "JKL*/\n"
                // 19 - 23
                + "MNO";

        System.out.println("Content Length: " + content.length());

        MultiLineCommentMarker instance = new MultiLineCommentMarker("/*", "*/");
        List<TextMark> expResult = new ListBuilder<TextMark>()
                .add(new TextMark(0, 4, TextType.Main))
                .add(new TextMark(4, 19, TextType.Comment))
                .add(new TextMark(19, 23, TextType.Main))
                .build();
        List<TextMark> result = instance.markTextTypes(content);

        Assert.assertEquals(expResult.size(), result.size());

        assertEquals(expResult.get(0), result.get(0));
        assertEquals(expResult.get(1), result.get(1));
        assertEquals(expResult.get(2), result.get(2));

        System.out.println("-----------------------------------------");
        for (TextMark mark : result) {

            System.out.println(content.substring(mark.getStart(), mark.getEnd()));
            System.out.println("-----------------------------------------");
        }
    }
    
    @Test
    public void marker_givenSingleLineContent_shouldMarkCommentsCorrectly() {
        System.out.println("marker_givenSingleLineContent_shouldMarkCommentsCorrectly");
        // 0 3,3 10,10 13
        String content = "ABC/*DEF*/GHI";

        System.out.println("Content Length: " + content.length());

        MultiLineCommentMarker instance = new MultiLineCommentMarker("/*", "*/");
        List<TextMark> expResult = new ListBuilder<TextMark>()
                .add(new TextMark(0, 3, TextType.Main))
                .add(new TextMark(3, 10, TextType.Comment))
                .add(new TextMark(10, 13, TextType.Main))
                .build();
        List<TextMark> result = instance.markTextTypes(content);

        Assert.assertEquals(expResult.size(), result.size());

        assertEquals(expResult.get(0), result.get(0));
        assertEquals(expResult.get(1), result.get(1));
        assertEquals(expResult.get(2), result.get(2));

        System.out.println("-----------------------------------------");
        for (TextMark mark : result) {

            System.out.println(content.substring(mark.getStart(), mark.getEnd()));
            System.out.println("-----------------------------------------");
        }
    }

    private void assertEquals(TextMark expected, TextMark actual) {

        Assert.assertEquals(expected.getStart(), actual.getStart());

        Assert.assertEquals(expected.getEnd(), actual.getEnd());

        Assert.assertEquals(expected.getType(), actual.getType());

    }

}
