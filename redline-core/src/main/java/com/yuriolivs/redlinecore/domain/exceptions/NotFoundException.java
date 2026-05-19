package com.yuriolivs.redlinecore.domain.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String domain) {
        super(domain + " not found");
    }
}
