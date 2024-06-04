package com.batsworks.interfaces.utils;

import lombok.extern.slf4j.Slf4j;

import static java.util.Objects.isNull;

@Slf4j
public class Wait {

    Wait() {
        throw new IllegalStateException("Utility class");
    }

    public static synchronized void till(Integer time) {
        try {
            time = isNull(time) ? 2500 : time;
            Thread.currentThread().wait(time);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
