package ru.mashinis.model;

public class Content {
    private int contentId;
    private int formId;
    private int userId;
    private String content;
    private String created_at;

    public Content(int formId, int userId, String content) {
        this.formId = formId;
        this.userId = userId;
        this.content = content;
    }

    public Content(int contentId, int formId, int userId, String content, String created_at) {
        this.contentId = contentId;
        this.formId = formId;
        this.userId = userId;
        this.content = content;
        this.created_at = created_at;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Content{" +
                "contentId=" + contentId +
                ", formId=" + formId +
                ", userId=" + userId +
                ", created_at='" + created_at + '\'' +
                '}';
    }
}
