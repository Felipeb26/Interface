package com.batsworks.interfaces.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormatString {

    FormatString() {
        throw new IllegalStateException("Utility class");
    }

    public static String findEntityByClassName(Class<?> t) {
        var string = t.getName().replace(t.getPackageName(), "").toLowerCase();
        string = string.replace("model", "");
        string = string.replace("entity", "");
        return string.replaceAll("[^a-zA-Z0-9\\s]", "");
    }

    public static Map<String, String> formatStringToInsert(String t) {
        List<String> chaves = new ArrayList<>();
        List<String> valores = new ArrayList<>();

        t = t.substring(t.indexOf("(") + 1, t.length() - 1);
        String[] campos = t.split(",");
        for (var subCampo : campos) {
            var campo = subCampo.split("=");
            if (!campo[1].equalsIgnoreCase("null")) {
                chaves.add(campo[0]);
                valores.add(String.format("'%s'", campo[1]));
            }
        }

        Map<String, String> insert = new HashMap<>();
        insert.put("chave", String.join(",", chaves));
        insert.put("valor", String.join(",", valores));
        return insert;
    }

    public static String formatStringToUpdate(String t) {
        String updateString = "";

        t = t.substring(t.indexOf("(") + 1, t.length() - 1);
        String[] campos = t.split(",");
        for (var subCampo : campos) {
            var campo = subCampo.split("=");
            if (!campo[1].equalsIgnoreCase("null")) {
                updateString += campo[0].concat("=").concat(String.format("'%s'", campo[1]));
            }
        }
        return updateString;
    }

}
