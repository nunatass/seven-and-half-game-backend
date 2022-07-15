package com.sevenandhalf.exception.wallet;

import com.sevenandhalf.exception.BadRequestException;

public class TransactionException extends BadRequestException {
    public TransactionException(String message) {
        super(message);
    }
}

