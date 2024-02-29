package ru.mashinis.model;

public class FieldValue {
    private int id;
    private String content;
    private int userFormIdentifier;
    private String  createdAt;

    public FieldValue(String content, int userFormIdentifier) {
        this.content = content;
        this.userFormIdentifier = userFormIdentifier;
    }

    public FieldValue(int id, String content, int userFormIdentifier, String createdAt) {
        this.id = id;
        this.content = content;
        this.userFormIdentifier = userFormIdentifier;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserFormIdentifier() {
        return userFormIdentifier;
    }

    public void setUserFormIdentifier(int userFormIdentifier) {
        this.userFormIdentifier = userFormIdentifier;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
