package com.batsworks.interfaces.utils;

import lombok.Getter;

@Getter
public enum RegexPattern {


    NOME("^\\d{1,50}"),
    EMAIL("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"),
    IDADE("^\\d{1,2}$"),
    SENHA("^\\d{1,8}$");

    RegexPattern(String code) {
        this.code = code;
    }

    private final String code;

}