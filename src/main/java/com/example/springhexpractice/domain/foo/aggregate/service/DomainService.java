package com.example.springhexpractice.domain.foo.aggregate.service;

import com.example.springhexpractice.config.exception.CheckErrorException;
import com.example.springhexpractice.config.exception.DataNotFoundException;

public interface DomainService<T> {
    void validation(T validator) throws CheckErrorException, DataNotFoundException;
}
