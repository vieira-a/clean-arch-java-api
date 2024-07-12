package com.arch.presentation.router.user;

import com.arch.presentation.controller.user.CreateUserController;
import com.arch.presentation.module.user.UserModule;
import com.arch.utils.ParseJsonFields;
import com.arch.utils.ParseRequestBody;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class UserRouter {

    private final HttpServer server;
    private final UserModule userModule;

    public UserRouter(HttpServer server, UserModule userModule) {
        this.server = server;
        this.userModule = userModule;
    }

    public void configureRoutes() {
        CreateUserController createUserController = userModule.getCreateUserController();
        String createUserRoute = "/api/v1/users";
        server.createContext(createUserRoute, new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                try {
                    if ("POST".equals(exchange.getRequestMethod())) {
                        String requestBody = ParseRequestBody.parseBody(exchange);
                        System.out.println("Request Body: " + requestBody);

                        Map<String, String> fields = ParseJsonFields.parse(requestBody);
                        System.out.println("Parsed Fields: " + fields);

                        String firstName = fields.getOrDefault("firstname", "");
                        String lastName = fields.getOrDefault("lastname", "");
                        String email = fields.getOrDefault("email", "");

                        System.out.println("Extracted Fields: firstName=" + firstName + ", lastName=" + lastName + ", email=" + email);

                        createUserController.handle(firstName, lastName, email);

                        sendResponse(exchange, 201, "User created successfully");
                    } else {
                        sendResponse(exchange, 405, "Method Not Allowed");
                    }
                } catch (IllegalArgumentException e) {
                    sendErrorResponse(exchange, 400, e.getMessage());
                } catch (Exception e) {
                    sendErrorResponse(exchange, 500, "Internal Server Error");
                } finally {
                    exchange.close();
                }
            }
        });
    }


    private void sendResponse(HttpExchange exchange, int statusCode, String responseMessage) throws IOException {
        // Configurar o cabeçalho Content-Type
        exchange.getResponseHeaders().set("Content-Type", "application/json");

        // Montar o objeto de resposta em formato JSON
        String jsonResponse = "{\"statusCode\": " + statusCode + ", \"message\": \"" + responseMessage + "\"}";

        // Enviar a resposta com o status e corpo JSON
        exchange.sendResponseHeaders(statusCode, jsonResponse.getBytes().length);
        try (OutputStream responseBody = exchange.getResponseBody()) {
            responseBody.write(jsonResponse.getBytes());
        }
    }

    private void sendErrorResponse(HttpExchange exchange, int statusCode, String errorMessage) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        String jsonResponse = "{\"statusCode\": " + statusCode + ", \"message\": \"" + errorMessage + "\"}";
        exchange.sendResponseHeaders(statusCode, jsonResponse.getBytes().length);
        try (OutputStream responseBody = exchange.getResponseBody()) {
            responseBody.write(jsonResponse.getBytes());
        }
    }
}
