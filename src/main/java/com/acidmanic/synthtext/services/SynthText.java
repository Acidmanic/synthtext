/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.synthtext.services;

import com.acidmanic.io.file.FileIOHelper;
import com.acidmanic.synthtext.models.CommentProfile;
import com.acidmanic.synthtext.models.MarkedText;
import com.acidmanic.synthtext.models.SynthTable;
import com.acidmanic.synthtext.models.TextMark;
import com.acidmanic.synthtext.models.TextType;
import com.acidmanic.synthtext.models.builtin.CommentProfiles;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class SynthText {

    private final File inputFile;
    private final String inputContent;
    private CommentProfile commentProfile;

    public SynthText(File inputFile, CommentProfile commentProfile) {
        this.inputFile = inputFile;

        this.inputContent = new FileIOHelper().tryReadAllText(inputFile);

        this.commentProfile = commentProfile;
    }

    public void generate() {

        if (this.commentProfile == CommentProfiles.NULL) {

            this.commentProfile = new CommentProfileFactory()
                    .makeByExtention(this.inputFile.getName());
        }

        if (this.commentProfile == CommentProfiles.NULL) {

            //error("This source file is not known to synthtext.");
            return;
        }
        List<TextMark> marks = this.commentProfile.getCommentMarker()
                .markTextTypes(inputContent);

        List<MarkedText> texts = extractTexts(this.inputContent, marks);

        SynthTable table = readSynthTable(texts, this.commentProfile);

        texts.forEach(text -> {

            if (text.getType() == TextType.Main) {

                String reText = apply(text.getText(), table);

                text.setText(reText);
            }
        });
        StringBuilder outputContentBuilder = new StringBuilder();

        texts.forEach(text -> outputContentBuilder.append(text.getText()));

        File outputFile = inputFile.toPath().toAbsolutePath()
                .getParent().normalize()
                .resolve(table.getOutputFilename())
                .toFile();

        String outputContent = outputContentBuilder.toString();

        try {
            new FileIOHelper().tryWriteAll(outputFile, outputContent);
        } catch (Exception e) {
        }

    }

    private static final String LINEHEADER_REPLACE_PAIR = "rep";
    private static final String LINEHEADER_OUTPUT_FILE = "out";

    private SynthTable readSynthTable(List<String> allSynthLines) {

        SynthTable table = new SynthTable();

        for (String line : allSynthLines) {
            line = line.trim();

            if (line.length() > 4) {

                String lineHeader = line.substring(0, 3).toLowerCase();

                String lineCode = line.substring(4, line.length()).trim();

                if (LINEHEADER_REPLACE_PAIR.equals(lineHeader)) {

                    String[] segments = lineCode.split("\\s");

                    if (segments.length > 1) {

                        String key = segments[0];

                        String value = lineCode.substring(key.length(),
                                lineCode.length()).trim();

                        if (table.getReplaces().containsKey(key)) {
                            //TODO: warn
                            table.getReplaces().remove(key);
                        }
                        table.getReplaces().put(key, value);
                    }
                }
                if (LINEHEADER_OUTPUT_FILE.equals(lineHeader)) {

                    table.setOutputFilename(lineCode);
                }
            }

        }
        return table;
    }

    private List<MarkedText> extractTexts(String inputContent, List<TextMark> marks) {

        List<MarkedText> markedTexts = new ArrayList<>();

        marks.forEach(mark -> {

            String text = inputContent.substring(mark.getStart(), mark.getEnd());

            MarkedText markedText = new MarkedText(text, mark.getType());

            markedTexts.add(markedText);
        });
        return markedTexts;
    }

    private SynthTable readSynthTable(List<MarkedText> texts, CommentProfile profile) {

        StringBuilder sb = new StringBuilder();

        for (MarkedText text : texts) {

            if (text.getType() == TextType.Comment) {

                String uncommented = profile.getCommentMarker()
                        .uncomment(text.getText());

                sb.append(uncommented.trim()).append("\n");
            }
        }
        String[] commentLines = sb.toString().split("\n");

        List<String> synthLines = new ArrayList<>();

        for (String line : commentLines) {

            line = line.trim();

            if (line.startsWith("STX:")) {

                synthLines.add(line.substring(4,line.length()).trim());
            }
        }
        return readSynthTable(synthLines);
    }

    private String apply(String text, SynthTable table) {

        String outputContent = text;

        for (String key : table.getReplaces().keySet()) {

            String value = table.getReplaces().get(key);

            outputContent = outputContent.replace(key, value);
        }
        return outputContent;
    }

}
