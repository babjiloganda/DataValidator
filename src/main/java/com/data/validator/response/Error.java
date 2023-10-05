package com.data.validator.response;

public class Error {

    private String eventId;
    private final String field;
    private final String message;

    public Error(final String field, final String message, final String eventId) {
        this.field = field;
        this.message = message;
        this.eventId = eventId;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

    @Override public String toString() {
        return "Error{" +
                "eventId='" + eventId + '\'' +
                ", field='" + field + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(final String eventId) {
        this.eventId = eventId;
    }

}
