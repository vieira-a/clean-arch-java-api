package com.arch.presentation.responses;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class ErrorResponse {
    public static void sendErrorResponse(HttpExchange exchange, int statusCode, String errorMessage) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        String jsonResponse = "{\"statusCode\": " + statusCode + ", \"message\": \"" + errorMessage + "\"}";
        exchange.sendResponseHeaders(statusCode, jsonResponse.getBytes().length);
        try (OutputStream responseBody = exchange.getResponseBody()) {
            responseBody.write(jsonResponse.getBytes());
        }
    }
}
