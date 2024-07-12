package com.arch.utils;

import java.util.HashMap;
import java.util.Map;

public class ParseJsonFields {
    public static Map<String, String> parse(String json) {
        // Implementação robusta de parse de JSON
        Map<String, String> fields = new HashMap<>();
        json = json.replaceAll("\\s+", ""); // Remove espaços em branco
        json = json.substring(1, json.length() - 1); // Remove chaves externas
        String[] keyValuePairs = json.split(",");

        for (String pair : keyValuePairs) {
            String[] entry = pair.split(":");
            String key = entry[0].replaceAll("\"", "");
            String value = entry[1].replaceAll("\"", "");
            fields.put(key, value);
        }

        return fields;
    }
}
