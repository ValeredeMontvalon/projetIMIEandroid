package com.example.lorcdi02.applicationandroimie.Feeds;

/**
 * Created by lor.cdi02 on 24/08/2015.
 */
public class Note {
    String ecfName;
    int noteValue;
    String commentary;
    int idNote;

    public int getIdNote() {
        return idNote;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
    }

    public String getEcfName() {
        return ecfName;
    }

    public void setEcfName(String ecfName) {
        this.ecfName = ecfName;
    }

    public int getNoteValue() {
        return noteValue;
    }

    public void setNoteValue(int noteValue) {
        this.noteValue = noteValue;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }
}
