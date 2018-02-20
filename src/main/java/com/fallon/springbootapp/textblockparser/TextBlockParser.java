package com.fallon.springbootapp.textblockparser;

import java.util.StringTokenizer;
import java.util.TreeMap;

public class TextBlockParser {
    public Word[] parseText(String text) {
        StringTokenizer tokens = new StringTokenizer(text, " \t.\n");

        TreeMap<String, Word> words = new TreeMap();

        while(tokens.hasMoreTokens()) {
            String token = tokens.nextToken();
            if(words.containsKey(token)) {
                Word word = words.get(token);
                word.incrementOccurences();
            }
            else {
                words.put(token, new Word(token));
            }
        }

        return words.values().stream().toArray(Word[]::new);
    }
}
