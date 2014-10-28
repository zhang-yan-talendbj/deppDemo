package com.caribe.stone.anki;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thinkdeeply on 7/13/14.
 */
public class Deck {
    private Long deckId;
    private List<Note> words = new ArrayList<Note>();

    public Long getDeckId() {
        return deckId;
    }

    public void setDeckId(Long deckId) {
        this.deckId = deckId;
    }

    public List<Note> getWords() {
        return words;
    }

    public void setWords(List<Note> words) {
        this.words = words;
    }
}
