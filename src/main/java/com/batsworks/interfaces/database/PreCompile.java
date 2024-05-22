package com.batsworks.interfaces.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class PreCompile {

    private static final Logger log = Logger.getLogger(PreCompile.class.getName());
    private static Connection connection = SQLiteConnection.connector();

    PreCompile() {
        throw new IllegalStateException("Utility class");
    }

    private static List<File> findScripts() {
        List<File> files = new ArrayList<>();


        File directory = new File("src/main/resources/db");
        if (directory.isDirectory()) {
            for (var file : Objects.requireNonNull(directory.listFiles())) {
                if (file.getName().startsWith("BW")) {
                    files.add(file);
                }
            }
        }
        return files;
    }

    private static String findQuerys() throws Exception {
        StringBuilder query = new StringBuilder();

        for (var it : findScripts().stream().sorted(Comparator.comparing(File::getName)).toList()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(it))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    query.append(line);
                }
            }
        }
        return query.toString();
    }

    public static void executeQuery() throws Exception {
        String[] querys = findQuerys().split(";");
        try {
            for (var query : querys) {
                log.info(query.concat("\n"));
                try (PreparedStatement pst = connection.prepareStatement(query)) {
                    pst.executeUpdate();
                } catch (Exception e) {
                    log.info(e.getMessage().concat("\n"));
                    continue;
                }
            }
        } catch (Exception e) {
            log.severe(e.getMessage());
        }
    }

}
