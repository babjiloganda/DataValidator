package com.locala.data.validator.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
public class Response {
    private final List<Error> errors = Collections.synchronizedList(new ArrayList<>());

    public Collection<Error> getErrors() {

        return Collections.unmodifiableCollection(errors);

    }

    public void addError(String field, String message, String eventId) {

        this.errors.add(new Error(eventId, field, message));

    }

    public boolean hasErrors() {

        if (this.errors.size() > 0) {
            return true;
        }

        return false;
    }
}
