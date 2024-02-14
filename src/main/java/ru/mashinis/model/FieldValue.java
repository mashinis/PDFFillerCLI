package ru.mashinis.model;

public class FieldValue {
    private int valueId;
    private String value;
    private int formInstanceId;
    private int fieldId;
    private String fieldName;
    private String fieldAlias;

    private int userId;
    private String formName;
    private String userName;


    public FieldValue(String value, int fieldId, int userId, int formId, int formInstanceId) {
        this.value = value;
        this.fieldId = fieldId;
        this.userId = userId;
        this.formId = formId;
        this.formInstanceId = formInstanceId;
    }

    public FieldValue() {
    }

    public FieldValue(int valueId, String value, int formInstanceId, String fieldName, String formName, String userName) {
        this.valueId = valueId;
        this.value = value;
        this.formInstanceId = formInstanceId;
        this.fieldName = fieldName;
        this.formName = formName;
        this.userName = userName;
    }

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
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

    private int formId;


    public int getValueId() {
        return valueId;
    }

    public void setValueId(int valueId) {
        this.valueId = valueId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getFormInstanceId() {
        return formInstanceId;
    }

    public void setFormInstanceId(int formInstanceId) {
        this.formInstanceId = formInstanceId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFieldAlias() {
        return fieldAlias;
    }

    public void setFieldAlias(String fieldAlias) {
        this.fieldAlias = fieldAlias;
    }

    @Override
    public String toString() {
        return "FieldValue{" +
                "valueId=" + valueId +
                ", value='" + value + '\'' +
                ", formInstanceId=" + formInstanceId +
                ", fieldName='" + fieldName + '\'' +
                ", formName='" + formName + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
