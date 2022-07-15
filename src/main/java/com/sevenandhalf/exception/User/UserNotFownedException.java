package com.sevenandhalf.exception.User;

import com.sevenandhalf.exception.NotFoundException;

public class UserNotFownedException extends NotFoundException {
    public UserNotFownedException(String message) {
        super(message);
    }
}

