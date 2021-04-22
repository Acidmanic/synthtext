/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.synthtext.services;

import com.acidmanic.io.file.FileIOHelper;
import com.acidmanic.synthtext.models.CommentMarker;
import com.acidmanic.synthtext.models.CommentMarkers;
import com.acidmanic.synthtext.models.SynthTable;
import com.acidmanic.synthtext.services.comment.CommentExtractor;
import com.acidmanic.synthtext.services.comment.MultiLineCommentExtractor;
import com.acidmanic.synthtext.services.comment.SingleLineCommentExtractor;
import java.io.File;
import java.util.List;

/**
 *
 * @author diego
 */
public class SynthText {

    private final File inputFile;
    private final String inputContent;

    public SynthText(File inputFile) {
        this.inputFile = inputFile;

        this.inputContent = new FileIOHelper().tryReadAllText(inputFile);
    }

    public void generate() {

        SynthtextFile synthFile = new SynthtextFileFactory().make(inputFile);

        if (synthFile != null) {

            String allComments = extractAllComments(synthFile, this.inputContent);

            List<String> allSynthLines = extractSynthLines(allComments);

            SynthTable table = readSynthTable(allSynthLines);
            
            String outputContent = inputContent;
            
            for(String key:table.getReplaces().keySet()){
                
                String value = table.getReplaces().get(key);
                
                outputContent = outputContent.replace(key, value);
            }
            
            File outputFile = inputFile.getParentFile()
                    .toPath().resolve(table.getOutputFilename())
                    .toFile();
            
            try {
                new FileIOHelper().tryWriteAll(outputFile, outputContent);
            } catch (Exception e) {
            }
        }

    }

    private String extractAllComments(SynthtextFile synthFile, String inputContent) {

        StringBuilder sb = new StringBuilder();

        for (CommentMarker marker : synthFile.commentMarkers()) {

            CommentExtractor extractor = marker.isMultiLine()
                    ? new MultiLineCommentExtractor(marker.getStartTag(), marker.getEndTag())
                    : new SingleLineCommentExtractor(marker.getStartTag());

            List<String> comments = extractor.extractComments(inputContent);

            comments.forEach(c -> sb.append(c).append("\n"));
        }
        return sb.toString().trim();
    }

    private List<String> extractSynthLines(String allComments) {

        return new SingleLineCommentExtractor(CommentMarkers.SYNTHTEXT_LINE.getStartTag())
                .extractComments(allComments);
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
                    
                    if(segments.length>1){
                        
                        String key = segments[0];
                        
                        String value = lineCode.substring(key.length(),
                                lineCode.length()).trim();
                        
                        if(table.getReplaces().containsKey(key)){
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

}
