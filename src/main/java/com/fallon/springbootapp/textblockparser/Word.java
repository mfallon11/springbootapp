package com.fallon.springbootapp.textblockparser;

class Word implements Comparable<Word> {
    private String word;
    private int occurrences;

    public Word(String word) {
        occurrences = 1;
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public int getOccurrences() {
        return occurrences;
    }

    public void incrementOccurences() {
        occurrences++;
    }

    @Override
    public int compareTo(Word word) {
        return this.word.compareTo(word.getWord());
    }

}
