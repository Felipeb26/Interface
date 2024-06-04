package com.batsworks.interfaces.database;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Slf4j
public class SQLiteConnection {

    private static Connection connection;

    private SQLiteConnection() {
        throw new IllegalStateException("Utility class");
    }

    public static Connection connector() {
        try {
            Class.forName("org.sqlite.JDBC");
            if (connection == null || connection.isClosed() || !connection.isValid(2)) {
                connection = DriverManager.getConnection("jdbc:sqlite:Market.sqlite");
            }
            lateCompile();
            return connection;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    static Integer integer = 1;

    private static void lateCompile() {
        if (integer >= 3) return;
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.submit(() -> {
                log.info("%s Tentaiva%n".formatted(integer == 1 ? "Primeira" : "Segunda"));
                try {
                    integer++;
                    PreCompile.executeQuery();
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
                executor.shutdown();
            });
        };
        scheduler.schedule(task, 2, TimeUnit.SECONDS);
        scheduler.shutdown();
    }

}
