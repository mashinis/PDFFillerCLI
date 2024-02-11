package ru.mashinis.model;

public class FieldValue {
    private int id;
    private String value;
    private int fieldId;
    private int userId;
    private int tripId;

    public FieldValue(String value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    @Override
    public String toString() {
        return "FieldValue{" +
                "id='" + id +
                "', value='" + value +
                "', fieldId='" + fieldId +
                "', userId='" + userId +
                "', tripId='" + tripId +
                "'}";
    }
}
