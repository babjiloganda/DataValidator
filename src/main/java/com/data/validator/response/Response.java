package com.data.validator.response;

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

        this.errors.add(new Error(field, message, eventId));

    }

    public boolean hasErrors() {

        return this.errors.size() > 0;
    }
}
