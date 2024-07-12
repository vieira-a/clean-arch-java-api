package com.arch.presentation.responses;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class MessageResponse {
    public static void sendResponse(HttpExchange exchange, int statusCode, String responseMessage) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");

        String jsonResponse = "{\"statusCode\": " + statusCode + ", \"message\": \"" + responseMessage + "\"}";
        exchange.sendResponseHeaders(statusCode, jsonResponse.getBytes().length);
        try (OutputStream responseBody = exchange.getResponseBody()) {
            responseBody.write(jsonResponse.getBytes());
        }
    }
}
