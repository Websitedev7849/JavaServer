package com.HttpServer;


import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) {
        try {
//            create server instance
            HttpServer server = HttpServer.create(new InetSocketAddress("localhost",8080), 0);

//            set url endpoint (context)
            server.createContext("/").setHandler(new HttpHandler() {
                @Override
                public void handle(HttpExchange exchange) throws IOException {
                    String hello = "<h1> Hello Server in Java </h1>";

//                    print url
                    System.out.println(exchange.getRequestURI());

//                    set output to client
                    OutputStream os = exchange.getResponseBody();

//                    setting headers of response
                    Headers headers = exchange.getResponseHeaders();
                    headers.add("Content-Type", "text/html");

//                    set headers of response
                    exchange.sendResponseHeaders(200, hello.length());

//                    write to client
                    os.write(hello.getBytes());

//                    close request
                    os.close();
                }
            });

//            set url endpoint for /about
            server.createContext("/about").setHandler(new HttpHandler() {
                @Override
                public void handle(HttpExchange exchange) throws IOException {
                    String hello = "<h2> This is about page </h2>";

                    System.out.println(exchange.getRequestURI());

                    OutputStream os = exchange.getResponseBody();

                    Headers headers = exchange.getResponseHeaders();

                    headers.add("Content-Type", "text/html");

                    exchange.sendResponseHeaders(200, hello.length());

                    os.write(hello.getBytes());
                    os.close();
                }
            });

//            start server
            server.start();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
