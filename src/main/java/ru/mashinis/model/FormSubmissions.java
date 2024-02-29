package ru.mashinis.model;

public class FormSubmissions {
    private int submissionId;
    private int userId;
    private int formId;
    private int userFormIdentifier;
    private String createdAt;

    public FormSubmissions(int userId, int formId) {
        this.userId = userId;
        this.formId = formId;
        this.userFormIdentifier = 1;
    }

    public FormSubmissions(int submissionId, int userId, int formId, int userFormIdentifier, String createdAt) {
        this.submissionId = submissionId;
        this.userId = userId;
        this.formId = formId;
        this.userFormIdentifier = userFormIdentifier;
        this.createdAt = createdAt;
    }

    public int getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(int submissionId) {
        this.submissionId = submissionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
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
