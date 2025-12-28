package model;

public class Note {
    private int id;
    private String content;

    public Note(int id, String content) {
        this.id = id;
        this.content = content;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
